package com.gosun.isap.proxy.api.utils;

/**
 * worker状态回调
 * 
 * @author liuzk
 *
 */
public interface IWorkerStateCallback {
	/**
	 * worker 注册成功回调
	 * 
	 * @param workerId
	 *            worker id
	 */
	void onWorkerReg(String workerId);

	/**
	 * worker 卸载成功回调
	 * 
	 * @param workerId
	 *            worker id
	 */
	void onWorkerUnreg(String workerId);
}
