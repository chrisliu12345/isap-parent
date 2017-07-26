package com.gosun.isap.proxy.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.dubbo.container.spring.SpringContainer;
import com.gosun.isap.proxy.api.backend.NewProxyException;
import com.gosun.isap.proxy.api.instance.ProxyProperty;
import com.gosun.isap.proxy.api.instance.ProxyRunningInfo;
import com.gosun.isap.proxy.api.instance.ProxyStatus;
import com.gosun.isap.proxy.api.manager.AbstractProxyInstanceLoader;
import com.gosun.isap.proxy.api.manager.IProxyManager;
import com.gosun.isap.proxy.api.utils.IWorkerStateCallback;
import com.gosun.isap.proxy.api.utils.IWorker;
import com.gosun.isap.proxy.api.utils.IWorkerKeeper;

/**
 * proxy管理器实现
 * 
 * @author liuzk
 *
 */
public class ProxyManagerImpl implements IProxyManager {
	private static Logger logger = LoggerFactory.getLogger(ProxyManagerImpl.class);

	/**
	 * proxy启动超时时间
	 */
	private static final int PROXY_START_TIMEOUT = 15 * 1000;

	/**
	 * 运行状态检查器
	 */
	private IWorkerKeeper proxyKeeper;
	/**
	 * 属性持久化器
	 */
	private IPropertyPersistence persistence;
	/**
	 * 状态报告器
	 */
	private IWorker worker;
	/**
	 * proxy实例缓存
	 */
	private Map<String, AbstractProxyInstanceLoader> proxys = new HashMap<String, AbstractProxyInstanceLoader>();

	public ProxyManagerImpl(IWorkerKeeper keeper, IPropertyPersistence persistence, IWorker worker) {
		this.proxyKeeper = keeper;
		this.persistence = persistence;
		this.worker = worker;
	}

	/**
	 * 初始化
	 * 
	 * @throws Exception
	 *             checked异常
	 */
	public void init() {
		// 从远端加载已经在运行的proxy实例，保持远端和本地内存中的数据一致
		List<String> proxys = proxyKeeper.getAllWorkers();
		for (String proxyId : proxys) {
			Object o = proxyKeeper.getWorkerData(proxyId);
			if (o instanceof ProxyRunningInfo) {
				AbstractProxyInstanceLoader loader = SpringContainer.getContext()
						.getBean(AbstractProxyInstanceLoader.class);

				ProxyRunningInfo info = ((ProxyRunningInfo) o);
				// 从持久化中加载proxy实例的属性
				ProxyProperty property = persistence.load(info.getProxyId());
				loader.setProperty(property);
				loader.setRunningInfo(info);

				// 保存proxy实例
				cacheProxyInstance(loader);

				logger.info("Sync proxy from zookeeper, property " + property + ", runningInfo " + info);
			} else {
				logger.warn("Data type error " + o);
			}
		}

		// 设置proxy实例状态变更回调
		proxyKeeper.setEventCallback(new IWorkerStateCallback() {
			@Override
			public void onWorkerReg(String child) {
				/**
				 * proxy实例在启动成功时，会在zookeeper的相应目录下创建一个自己的节点，所以，
				 * 当监听目录下节点增加，表示有proxy实例启动成功了，需要唤醒等待线程
				 */
				Object o = proxyKeeper.getWorkerData(child);
				if (o instanceof ProxyRunningInfo) {
					ProxyRunningInfo info = (ProxyRunningInfo) o;
					if (null != info) {
						logger.info("Notify proxy startd, runingInfo " + info);
						notifyInstanceStarted(info.getProxyId(), info);
					}
				} else {
					logger.warn("Data type error " + o);
				}
			}

			@Override
			public void onWorkerUnreg(String child) {
				/**
				 * 当监听目下节点减少，说明有proxy实例挂掉了，需要重新拉起来。
				 * 有一种特殊情况：当proxy启动时，如果上一次与zookeeper的session还未结束，proxy自己会主动删除zookeeper节点上的数据，这样也会回调到这里来，
				 * 对于这种情况，是不需要重新唤醒的进程的，在这里需要排除这种情况
				 */
				AbstractProxyInstanceLoader loader = getCachedProxyInstance(child);
				if (null != loader && ProxyStatus.INIT != loader.getRunningInfo().getStatus()) {
					// 注意：这里必须重新用一个线程来启动，因为forkProxyInstance中需要等待通知消息，而当前线程是用来发送通知消息的，不能阻塞发送通知消息线程
					Thread t = new Thread(new Runnable() {
						@Override
						public void run() {
							try {
								logger.info("The proxy instance " + child + " has terminated unexcepted! recall it...");
								restartProxyInstance(persistence.load(child));
							} catch (NewProxyException e) {
								logger.error(e.getMessage());
							}
						}
					});
					t.start();
				}
			}
		});

		// 从持久化中读取所有的proxy实例属性，并启动实例
		List<ProxyProperty> props = persistence.loadAll();
		for (ProxyProperty p : props) {
			try {
				forkProxyInstance(p);
			} catch (NewProxyException e) {
				// 启动失败只记录错误日志，不做其他处理，不返回异常也
				logger.error(e.getMessage());
			}
		}

		// 注册到keeper上，名称现在写死为default
		worker.registerToKeeper("default", "online");
	}

	@Override
	public List<ProxyProperty> getAllProperties() {
		List<ProxyProperty> properties = new ArrayList<ProxyProperty>();
		synchronized (proxys) {
			for (Map.Entry<String, AbstractProxyInstanceLoader> entry : proxys.entrySet()) {
				properties.add(entry.getValue().getProperty());
			}
		}
		return properties;
	}

	@Override
	public void forkProxyInstance(ProxyProperty property) throws NewProxyException {
		logger.info("Begin to fork proxy instance, property " + property);

		// 检查proxy实例是否已经启动，避免出现重复启动
		if (null != getCachedProxyInstance(property.getProxyId())) {
			logger.warn("The proxy instance has already started or in starting, proxyid " + property.getProxyId());
			return;
		}

		// 如果没有指定端口，则由manager自动分配一个端口
		if (0 == property.getProtocolPort()) {
			Short port = AvailablePortHelper.getAvailablePort();
			property.setProtocolPort(port);
			logger.info("Get available port " + port);
		}

		// 获取一个proxy实例加载器
		AbstractProxyInstanceLoader loader = SpringContainer.getContext().getBean(AbstractProxyInstanceLoader.class);

		loader.setProperty(property);
		loader.setRunningInfo(new ProxyRunningInfo(property.getProxyId()));

		// 使用加载器启动proxy实例
		try {
			loader.start();
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new NewProxyException(property.getProxyId(), e.getMessage());
		}

		// proxy实例加载成功后，缓存加载器
		cacheProxyInstance(loader);

		// 等待实例启动ok
		waitInstanceStartOk(loader, PROXY_START_TIMEOUT);

		// 此处有可能是超时返回，也有可能是notify唤醒， 所以，需要检测proxy是否启动成功
		if (ProxyStatus.RUNNING != loader.getRunningInfo().getStatus()) {
			stopProxyInstance(loader);
			String msg = "Wait proxy instance start timeout, proxyid " + property.getProxyId();
			logger.error(msg);
			throw new NewProxyException(property.getProxyId(), msg);
		}

		// 对property进行持久化
		persistence.save(property);

		logger.info("Succeed to start proxy, proxyid " + property.getProxyId());
	}

	@Override
	public void modifyProxyInstance(ProxyProperty property) {
		AbstractProxyInstanceLoader loader = getCachedProxyInstance(property.getProxyId());
		if (null != loader) {
			logger.info("Modify proxy instance, old property " + loader.getProperty() + ", new property " + property);
			// 更新本地数据
			loader.setProperty(property);
			// 更新持久化数据
			persistence.save(property);
		}
	}

	@Override
	public void destroyProxyInstance(String proxyId) {
		AbstractProxyInstanceLoader loader = getCachedProxyInstance(proxyId);
		if (null != loader) {
			logger.info("Destroy proxy instance, property " + loader.getProperty() + ", runningInfo "
					+ loader.getRunningInfo());
			stopProxyInstance(loader);
			persistence.delete(proxyId);
		}
	}

	@Override
	public void restartProxyInstance(ProxyProperty newProperty) throws NewProxyException {
		AbstractProxyInstanceLoader loader = getCachedProxyInstance(newProperty.getProxyId());
		if (null == loader) {
			logger.warn("Proxy " + newProperty.getProxyId() + " not found, ignore restart");
			return;
		}
		if (ProxyStatus.INIT != loader.getRunningInfo().getStatus()) {
			logger.info("Restart proxy instance, property " + newProperty);
			stopProxyInstance(loader);
			forkProxyInstance(newProperty);
		}
	}

	private void stopProxyInstance(AbstractProxyInstanceLoader loader) {
		deleteCachedProxyInstance(loader.getProperty().getProxyId());
		loader.stop();
		loader = null;
	}

	/**
	 * 等待proxy实例启动ok
	 * 
	 * @param ins
	 *            proxy实例加载器
	 * @param timeout
	 *            超时时间
	 */
	private void waitInstanceStartOk(AbstractProxyInstanceLoader ins, int timeout) {
		// 等待proxy进程启动完成
		synchronized (ins) {
			try {
				ins.wait(timeout);
			} catch (InterruptedException e) {
			}
		}
	}

	/**
	 * 通知proxy实例已经启动
	 * 
	 * @param proxyId
	 *            实例id
	 * @param info
	 *            运行信息
	 */
	private void notifyInstanceStarted(String proxyId, ProxyRunningInfo info) {
		AbstractProxyInstanceLoader ins = getCachedProxyInstance(proxyId);
		if (null != ins) {
			// 更新本地缓存的proxy的运行信息
			ins.setRunningInfo(info);

			// 唤醒等待线程
			synchronized (ins) {
				ins.notifyAll();
			}
		}
	}

	private void cacheProxyInstance(AbstractProxyInstanceLoader loader) {
		synchronized (proxys) {
			proxys.put(loader.getProperty().getProxyId(), loader);
		}
	}

	private void deleteCachedProxyInstance(String proxyId) {
		synchronized (proxys) {
			proxys.remove(proxyId);
		}
	}

	private AbstractProxyInstanceLoader getCachedProxyInstance(String proxyId) {
		synchronized (proxys) {
			return proxys.get(proxyId);
		}
	}
}
