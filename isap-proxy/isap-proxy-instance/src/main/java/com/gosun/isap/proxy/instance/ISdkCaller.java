package com.gosun.isap.proxy.instance;

import com.gosun.isap.proxy.api.instance.CallNativeSdkException;
import com.gosun.isap.proxy.api.instance.ISdkAdapter;
import com.gosun.isap.proxy.api.sdk.EventCallback;

/**
 * sdk调用器
 * 
 * @author liuzk
 *
 */
public interface ISdkCaller extends ISdkAdapter {
	/**
	 * 初始化
	 * 
	 * @param platformType
	 *            平台类型
	 * @throws CallNativeSdkException
	 *             调用本地sdk接口失败
	 */
	void init(String platformType) throws CallNativeSdkException;

	/**
	 * 销毁sdk
	 * 
	 * @throws CallNativeSdkException
	 *             调用本地sdk接口失败
	 */
	void destroy() throws CallNativeSdkException;

	/**
	 * 是否已经登录
	 * 
	 * @return true已经登录，false没有登录
	 */
	boolean isLogined();

	/**
	 * 设置登录状态
	 * 
	 * @param hasLogin
	 *            是否登录
	 */
	void setLogined(boolean hasLogin);

	/**
	 * 设置事件回调
	 * 
	 * @param callback
	 *            回调接口
	 * @throws CallNativeSdkException
	 *             调用本地sdk接口失败
	 */
	void setEventCallback(EventCallback callback) throws CallNativeSdkException;
}
