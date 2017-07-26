package com.gosun.isap.proxy.api.manager;

import com.gosun.isap.proxy.api.instance.ProxyProperty;
import com.gosun.isap.proxy.api.instance.ProxyRunningInfo;

/**
 * proxy实例加载器
 * 
 * @author liuzk
 *
 */
public abstract class AbstractProxyInstanceLoader {
	private ProxyProperty property;
	private ProxyRunningInfo runningInfo;

	public ProxyProperty getProperty() {
		return property;
	}

	public void setProperty(ProxyProperty property) {
		this.property = property;
	}

	public ProxyRunningInfo getRunningInfo() {
		return runningInfo;
	}

	public void setRunningInfo(ProxyRunningInfo runningInfo) {
		this.runningInfo = runningInfo;
	}

	/**
	 * 启动
	 * 
	 * @throws Exception
	 *             异常
	 */
	public abstract void start() throws Exception;

	/**
	 * 停止
	 */
	public abstract void stop();
}
