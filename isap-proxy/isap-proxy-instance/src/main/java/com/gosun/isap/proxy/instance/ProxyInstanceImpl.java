package com.gosun.isap.proxy.instance;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gosun.isap.proxy.api.instance.CallNativeSdkException;
import com.gosun.isap.proxy.api.instance.IProxyInstance;
import com.gosun.isap.proxy.api.instance.ProxyProperty;
import com.gosun.isap.proxy.api.instance.ProxyRunningInfo;
import com.gosun.isap.proxy.api.instance.ProxyStatus;
import com.gosun.isap.proxy.api.sdk.constants.PlatformType;
import com.gosun.isap.proxy.api.utils.IWorker;

/**
 * proxy实例实现
 * 
 * @author liuzk
 *
 */
public class ProxyInstanceImpl implements IProxyInstance {
	private static Logger logger = LoggerFactory.getLogger(ProxyInstanceImpl.class);

	/**
	 * proxyid 配置
	 */
	private static final String PROXYID_CONFIG = "dubbo.application.name";
	/**
	 * 平台类型配置
	 */
	private static final String PLATFORM_TYPE_CONFIG = "isap.proxy.platform_type";
	/**
	 * 平台IP地址配置
	 */
	private static final String PLATFORM_IPADDRESS_CONFIG = "isap.proxy.platform_ipaddress";
	/**
	 * 平台端口配置
	 */
	private static final String PLATFORM_PORT_CONFIG = "isap.proxy.platform_port";
	/**
	 * 平台用户配置
	 */
	private static final String PLATFORM_USER = "isap.proxy.user";
	/**
	 * 平台密码配置
	 */
	private static final String PLATFORM_PASSWORD = "isap.proxy.password";

	/**
	 * 事件线程
	 */
	private EventThread eventThread;
	/**
	 * 运行报告器
	 */
	private IWorker worker;
	/**
	 * sdk调用器
	 */
	private ISdkCaller sdkCaller;
	/**
	 * 属性读取器
	 */
	private IPropertyObserver propertyObserver;

	// proxy信息
	private String proxyId;
	private String platIpaddress;
	private Integer platPort;
	private String platUser;
	private String platPassword;
	private String platformType;

	public ProxyInstanceImpl(IWorker worker, EventThread eventThread, ISdkCaller sdkCaller,
			IPropertyObserver propertyObserver) {
		this.worker = worker;
		this.eventThread = eventThread;
		this.sdkCaller = sdkCaller;
		this.propertyObserver = propertyObserver;

		// 获取proxyid
		proxyId = System.getProperty(PROXYID_CONFIG);
		if (null == proxyId || 0 == proxyId.length()) {
			logger.error("Illegal argument " + PROXYID_CONFIG);
			throw new IllegalArgumentException(PROXYID_CONFIG);
		}

		// 平台类型
		platformType = System.getProperty(PLATFORM_TYPE_CONFIG);
		if (null == platformType || 0 == platformType.length()) {
			logger.error("Illegal argument " + PLATFORM_TYPE_CONFIG);
			throw new IllegalArgumentException(PLATFORM_TYPE_CONFIG);
		}

		platIpaddress = System.getProperty(PLATFORM_IPADDRESS_CONFIG);
		if (null == platIpaddress || 0 == platIpaddress.length()) {
			logger.error("Illegal argument " + PLATFORM_IPADDRESS_CONFIG);
			throw new IllegalArgumentException(PLATFORM_IPADDRESS_CONFIG);
		}

		String port = System.getProperty(PLATFORM_PORT_CONFIG);
		if (null == port || 0 == port.length()) {
			logger.error("Illegal argument " + PLATFORM_PORT_CONFIG);
			throw new IllegalArgumentException(PLATFORM_PORT_CONFIG);
		}
		platPort = Integer.parseInt(port);

		platUser = System.getProperty(PLATFORM_USER);
		if (null == platUser || 0 == platUser.length()) {
			logger.error("Illegal argument " + PLATFORM_USER);
			throw new IllegalArgumentException(PLATFORM_USER);
		}

		platPassword = System.getProperty(PLATFORM_PASSWORD);
		if (null == platPassword || 0 == platPassword.length()) {
			logger.error("Illegal argument " + PLATFORM_PASSWORD);
			throw new IllegalArgumentException(PLATFORM_PASSWORD);
		}

		// 监听proxy属性变化
		propertyObserver.watch(proxyId, new IPropertyChangeCallback() {
			@Override
			public void onChanged(ProxyProperty property) {
				/**
				 * property属性变更，需要通知事件线程进行处理
				 */
				logger.info("Property changed, property " + property);
				eventThread.onPropertyChanged(property.getAccessIpAddress(), property.getAccessPort(),
						property.getAccessUser(), property.getAccessPassword());
			}

			@Override
			public void onDeleted(String proxyId) {
				// 不处理
			}
		});
	}

	@Override
	public void init() {
		try {
			// 初始化sdk
			PlatformType type = PlatformType.valueOf(Integer.parseInt(platformType));
			sdkCaller.init(type.toString());
		} catch (CallNativeSdkException e) {
			logger.error(e.getMessage());
			throw new RuntimeException(e);
		}

		// 启动事件线程
		eventThread.init(proxyId, platIpaddress, platPort, platUser, platPassword);
		eventThread.start();

		// 由于proxy进程中现在只有一个模块，所以，这里简单处理一下，这个模块启动成功，就认为进程启动成功了。
		ProxyRunningInfo info = new ProxyRunningInfo(proxyId);
		info.setPid(Long.parseLong(sdkCaller.getPid()));
		info.setStatus(ProxyStatus.RUNNING);
		worker.registerToKeeper(proxyId, info);
	}

	@Override
	public void destroy() {
		worker.unregisterFromKeeper(proxyId);
		// eventThread.join();
	}
}
