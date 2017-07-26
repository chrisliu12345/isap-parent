package com.gosun.isap.proxy.backend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.container.spring.SpringContainer;
import com.gosun.isap.proxy.api.backend.IProxyCache;
import com.gosun.isap.proxy.api.backend.NewProxyException;
import com.gosun.isap.proxy.api.backend.ProxyManagerStateException;
import com.gosun.isap.proxy.api.backend.ProxyNotFoundException;
import com.gosun.isap.proxy.api.backend.ProxyWrapper;
import com.gosun.isap.proxy.api.instance.ProxyProperty;
import com.gosun.isap.proxy.api.manager.IProxyManager;
import com.gosun.isap.proxy.api.utils.IWorkerKeeper;

/**
 * proxy cache实现
 * 
 * @author liuzk
 *
 */
public class ProxyCacheImpl implements IProxyCache {
	private static Logger logger = LoggerFactory.getLogger(ProxyCacheImpl.class);

	/**
	 * 注册中心地址
	 */
	private String registryAddress;
	/**
	 * 保存proxy
	 */
	private Map<String, ProxyWrapper> proxys = new HashMap<String, ProxyWrapper>();

	public ProxyCacheImpl(String registryAddress) {
		this.registryAddress = registryAddress;
	}

	@Override
	public ProxyWrapper newProxy(ProxyProperty property) throws NewProxyException, ProxyManagerStateException {
		if (StringUtils.isEmpty(property.getProxyId())) {
			throw new IllegalArgumentException("Invalid proxyId");
		}
		if (StringUtils.isEmpty(property.getAccessIpAddress()) || property.getAccessPort() <= 0) {
			throw new IllegalArgumentException("Invalid platform address or port");
		}
		if (StringUtils.isEmpty(property.getAccessUser()) || StringUtils.isEmpty(property.getAccessPassword())) {
			throw new IllegalArgumentException("Invalid user or password");
		}
		if (null == property.getPlatformType()) {
			throw new IllegalArgumentException("Invalid platform type");
		}

		logger.info("New proxy instance, property " + property);

		String proxyId = property.getProxyId();

		// 判断是否proxy实例是否已经存在，已经存在了，不需要再new
		ProxyWrapper cachedProxy = getCachedProxy(proxyId);
		if (null != cachedProxy) {
			logger.warn("Proxy instance has alreay exist, return the cached instance");
			return cachedProxy;
		}

		// 检测proxy manager是否已经启动
		IWorkerKeeper observer = SpringContainer.getContext().getBean(IWorkerKeeper.class);
		if (!observer.isWorkerExist(DEFAULT_PROXY_MANAGER_ID)) {
			throw new ProxyManagerStateException("Proxy manager offline");
		}

		IProxyManager manager = SpringContainer.getContext().getBean(IProxyManager.class);

		// 通知proxy管理器fork新的实例进程
		manager.forkProxyInstance(property);

		// 创建proxy包装器
		ProxyWrapper proxyWrapper = new ProxyWrapper();
		proxyWrapper.setProperty(property);
		proxyWrapper.setRegistryAddress(registryAddress);

		// 缓存proxy
		cacheProxy(proxyWrapper);

		logger.info("Succeed to new proxy instance, proxyId " + proxyId);

		return proxyWrapper;
	}

	@Override
	public void modifyProxy(ProxyProperty property) throws ProxyManagerStateException {
		if (StringUtils.isEmpty(property.getProxyId())) {
			throw new IllegalArgumentException("Invalid proxyId");
		}

		ProxyWrapper origProxy = getCachedProxy(property.getProxyId());
		if (null == origProxy) {
			throw new ProxyNotFoundException(property.getProxyId());
		}

		logger.info("Modify proxy, original property " + origProxy.getProperty() + ", new property " + property);

		// 校验修改参数，不允许修改平台类型
		if (origProxy.getProperty().getPlatformType() != property.getPlatformType()) {
			throw new IllegalArgumentException("You can not modify platform type");
		}

		// 检测proxy manager是否已经启动
		IWorkerKeeper observer = SpringContainer.getContext().getBean(IWorkerKeeper.class);
		if (!observer.isWorkerExist(DEFAULT_PROXY_MANAGER_ID)) {
			throw new ProxyManagerStateException("Proxy manager offline");
		}

		// 修改属性
		IProxyManager manager = SpringContainer.getContext().getBean(IProxyManager.class);
		manager.modifyProxyInstance(property);

		// 更新本地缓存的proxy属性
		origProxy.setProperty(property);
	}

	@Override
	public void destroyProxy(String proxyId) throws ProxyManagerStateException {
		ProxyWrapper wrapper = getCachedProxy(proxyId);
		if (null == wrapper) {
			return;
		}

		logger.info("Destroy proxy instance, proxyId " + proxyId);

		// 检测proxy manager是否已经启动
		IWorkerKeeper observer = SpringContainer.getContext().getBean(IWorkerKeeper.class);
		if (!observer.isWorkerExist(DEFAULT_PROXY_MANAGER_ID)) {
			throw new ProxyManagerStateException("Proxy manager offline");
		}

		IProxyManager manager = SpringContainer.getContext().getBean(IProxyManager.class);

		// 销毁引用
		wrapper.destroy();

		// 销毁proxy实例
		manager.destroyProxyInstance(proxyId);

		deleteCachedProxy(proxyId);
	}

	@Override
	public ProxyWrapper getProxy(String proxyId) throws ProxyNotFoundException {
		ProxyWrapper proxy = getCachedProxy(proxyId);
		if (null == proxy) {
			throw new ProxyNotFoundException(proxyId);
		}
		return proxy;
	}

	@Override
	public void syncRemote(List<ProxyProperty> properties) throws ProxyManagerStateException {
		logger.info("Sync properties with proxy manager");

		// 检测proxy manager是否已经启动
		IWorkerKeeper observer = SpringContainer.getContext().getBean(IWorkerKeeper.class);
		if (!observer.isWorkerExist(DEFAULT_PROXY_MANAGER_ID)) {
			throw new ProxyManagerStateException("Proxy manager offline");
		}

		// 重置内部缓存
		reset();

		IProxyManager manager = SpringContainer.getContext().getBean(IProxyManager.class);

		// 从proxy manager上获取最新的所有属性
		List<ProxyProperty> remoteProperties = manager.getAllProperties();

		// 同步到本地缓存
		for (ProxyProperty p : remoteProperties) {
			try {
				newProxy(p);
			} catch (NewProxyException e) {
				logger.error(e.getMessage());
			}
		}

		// 获取两边的差异(ProxyProperty重载了equals和hashCode接口，可以直接比较)
		List<ProxyProperty> increaseProperties = new ArrayList<ProxyProperty>(properties);
		List<ProxyProperty> decreaseProperties = new ArrayList<ProxyProperty>(remoteProperties);
		increaseProperties.removeAll(remoteProperties);
		decreaseProperties.removeAll(properties);

		logger.info("DecreaseProperties " + decreaseProperties);
		logger.info("IncreaseProperties " + increaseProperties);

		// 无效的property，需要销毁对应的proxy实例
		for (ProxyProperty p : decreaseProperties) {
			destroyProxy(p.getProxyId());
		}

		// 新增的property，需要创建对应的proxy实例
		for (ProxyProperty p : increaseProperties) {
			try {
				newProxy(p);
			} catch (NewProxyException e) {
				logger.error(e.getMessage());
			}
		}
	}

	/**
	 * 重置缓存
	 */
	private void reset() {
		synchronized (proxys) {
			for (String id : proxys.keySet()) {
				proxys.get(id).destroy();
			}
		}

		proxys.clear();
	}

	/**
	 * 从缓存中获取一个proxy实例
	 * 
	 * @param proxyId
	 *            proxy id
	 * @return 获取到的proxy实例
	 */
	private ProxyWrapper getCachedProxy(String proxyId) {
		synchronized (proxys) {
			for (String id : proxys.keySet()) {
				if (id.equals(proxyId)) {
					return proxys.get(id);
				}
			}
			return null;
		}
	}

	/**
	 * 把proxy实例缓存到本地
	 * 
	 * @param proxy
	 *            proxy wrapper
	 */
	private void cacheProxy(ProxyWrapper proxy) {
		synchronized (proxys) {
			proxys.put(proxy.getProperty().getProxyId(), proxy);
		}
	}

	/**
	 * 删除缓存的proxy实例
	 * 
	 * @param proxyId
	 *            proxy实例id
	 */
	private void deleteCachedProxy(String proxyId) {
		synchronized (proxys) {
			proxys.remove(proxyId);
		}
	}
}
