package com.gosun.isap.operlog.impl;

import com.alibaba.dubbo.container.Container;
import com.alibaba.dubbo.container.spring.SpringContainer;
import com.gosun.isap.operlog.api.IOperateLogService;

/**
 * Hello world!
 *
 */
public class OperLogModule implements Container {

	@Override
	public void start() {
		// TODO Auto-generated method stub
		// 初始化日志单例对象
		SpringContainer.getContext().getBean(IOperateLogService.class);
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub

	}

}
