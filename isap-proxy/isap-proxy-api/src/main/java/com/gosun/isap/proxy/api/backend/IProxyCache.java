package com.gosun.isap.proxy.api.backend;

import java.util.List;

import com.gosun.isap.proxy.api.instance.ProxyProperty;

/**
 * proxy缓存
 * 
 * @author liuzk
 *
 */
public interface IProxyCache {
	/**
	 * 默认proxy manager id
	 */
	String DEFAULT_PROXY_MANAGER_ID = "default";

	/**
	 * 创建proxy实例
	 * 
	 * @param property
	 *            proxy属性
	 * @return proxy包装器
	 * @throws NewProxyException
	 *             新建proxy实例异常
	 * @throws ProxyManagerStateException
	 *             proxy manager状态异常
	 */
	ProxyWrapper newProxy(ProxyProperty property) throws NewProxyException, ProxyManagerStateException;

	/**
	 * 修改proxy属性
	 * 
	 * @param property
	 *            proxy新的property
	 * @throws ProxyManagerStateException
	 *             proxy manager状态异常
	 */
	void modifyProxy(ProxyProperty property) throws ProxyManagerStateException;

	/**
	 * 销毁一个proxy实例
	 * 
	 * @param proxyId
	 *            proxy实例ID
	 * @throws ProxyManagerStateException
	 *             manager状态异常
	 */
	void destroyProxy(String proxyId) throws ProxyManagerStateException;

	/**
	 * 根据proxyId获取proxy实例
	 * 
	 * @param proxyId
	 *            proxy实例ID
	 * @return 获取到的proxy实例
	 * @throws ProxyNotFoundException
	 *             proxy实例未找到异常
	 */
	ProxyWrapper getProxy(String proxyId) throws ProxyNotFoundException;

	/**
	 * 同步远端
	 * 
	 * @param properties
	 *            属性列表
	 * @throws ProxyManagerStateException
	 *             proxy manager状态异常
	 */
	void syncRemote(List<ProxyProperty> properties) throws ProxyManagerStateException;
}
