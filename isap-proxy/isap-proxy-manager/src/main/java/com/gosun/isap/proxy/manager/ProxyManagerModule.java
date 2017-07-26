package com.gosun.isap.proxy.manager;

import com.alibaba.dubbo.container.Container;
import com.alibaba.dubbo.container.spring.SpringContainer;
import com.gosun.isap.proxy.api.manager.IProxyManager;

/**
 * proxy管理器模块
 * 
 * @author liuzk
 *
 */
public class ProxyManagerModule implements Container {

	@Override
	public void start() {
		IProxyManager manager = SpringContainer.getContext().getBean(IProxyManager.class);
		try {
			// 初始化管理器
			manager.init();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void stop() {
		// IProxyManager manager =
		// SpringContainer.getContext().getBean(IProxyManager.class);
	}

}
