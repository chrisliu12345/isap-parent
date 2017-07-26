package com.gosun.isap.proxy.instance;

/**
 * 属性监听器
 * 
 * @author liuzk
 *
 */
public interface IPropertyObserver {
	/**
	 * 把某个proxy实例加入监听，当property数据变更时，接收到通知
	 * 
	 * @param proxyId
	 *            proxy id
	 * @param callback
	 *            property数据变化回调
	 */
	void watch(String proxyId, IPropertyChangeCallback callback);
}
