package com.gosun.isap.proxy.instance;

import com.alibaba.dubbo.container.Container;
import com.alibaba.dubbo.container.spring.SpringContainer;
import com.gosun.isap.proxy.api.instance.IProxyInstance;

/**
 * proxy实例模块
 * 
 * @author liuzk
 *
 */
public class ProxyInstanceModule implements Container {

	@Override
	public void start() {
		// 初始化proxy实例
		IProxyInstance instance = SpringContainer.getContext().getBean(IProxyInstance.class);
		instance.init();
	}

	@Override
	public void stop() {
		// 销毁proxy实例
		IProxyInstance instance = SpringContainer.getContext().getBean(IProxyInstance.class);
		instance.destroy();
	}
}
