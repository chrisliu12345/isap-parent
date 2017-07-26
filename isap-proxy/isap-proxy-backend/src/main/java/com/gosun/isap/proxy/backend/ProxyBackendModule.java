package com.gosun.isap.proxy.backend;

import com.alibaba.dubbo.container.Container;
import com.alibaba.dubbo.container.spring.SpringContainer;

/**
 * proxy outter模块
 * 
 * @author liuzk
 *
 */
public class ProxyBackendModule implements Container {

	@Override
	public void start() {
		// 初始化proxy cache初始化器
		ProxyCacheInitiator initiator = SpringContainer.getContext().getBean(ProxyCacheInitiator.class);
		initiator.init();
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub

	}

}
