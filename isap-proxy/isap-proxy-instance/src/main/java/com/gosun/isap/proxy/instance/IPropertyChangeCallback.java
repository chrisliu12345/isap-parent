package com.gosun.isap.proxy.instance;

import com.gosun.isap.proxy.api.instance.ProxyProperty;

/**
 * property数据监听器
 * 
 * @author liuzk
 *
 */
public interface IPropertyChangeCallback {
	/**
	 * property数据变更
	 * 
	 * @param property
	 *            新的property
	 */
	void onChanged(ProxyProperty property);

	/**
	 * property数据被删除
	 * 
	 * @param proxyId
	 *            proxy id
	 */
	void onDeleted(String proxyId);
}
