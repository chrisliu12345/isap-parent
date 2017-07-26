package com.gosun.isap.proxy.api.manager;

import java.util.List;

import com.gosun.isap.proxy.api.backend.NewProxyException;
import com.gosun.isap.proxy.api.instance.ProxyProperty;

/**
 * proxy实例管理器
 * 
 * @author Administrator
 *
 */
public interface IProxyManager {

	/**
	 * 初始化
	 * 
	 * @throws Exception
	 *             exception
	 */
	void init();

	/**
	 * 获取所有的property
	 * 
	 * @return
	 */
	List<ProxyProperty> getAllProperties();

	/**
	 * fork一个proxy实例
	 * 
	 * @param property
	 *            proxy属性
	 * @throws NewProxyException
	 *             新建proxy异常
	 */
	void forkProxyInstance(ProxyProperty property) throws NewProxyException;

	/**
	 * 修改proxy实例属性
	 * 
	 * @param property
	 *            proxy属性
	 */
	void modifyProxyInstance(ProxyProperty property);

	/**
	 * 销毁一个proxy实例
	 * 
	 * @param proxyId
	 *            proxy实例id
	 */
	void destroyProxyInstance(String proxyId);

	/**
	 * 重启proxy实例
	 * 
	 * @param newProperty
	 *            新的属性
	 * @throws NewProxyException
	 *             新建proxy异常
	 */
	void restartProxyInstance(ProxyProperty newProperty) throws NewProxyException;
}
