package com.gosun.isap.proxy.api.utils;

/**
 * 服务状态报告器
 * 
 * @author liuzk
 *
 */
public interface IWorker {

	/**
	 * 注册到keeper
	 *
	 * @param workerId
	 *            worker id
	 * @param data
	 *            注册时携带的数据
	 */
	void registerToKeeper(String workerId, Object data);

	/**
	 * 从keeper上卸载
	 * 
	 * @param workerId
	 *            worker id
	 */
	void unregisterFromKeeper(String workerId);
}
