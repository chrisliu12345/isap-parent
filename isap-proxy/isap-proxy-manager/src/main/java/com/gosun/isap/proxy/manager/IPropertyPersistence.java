package com.gosun.isap.proxy.manager;

import java.util.List;

import com.gosun.isap.proxy.api.instance.ProxyProperty;

/**
 * 属性持久化器
 * 
 * @author liuzk
 *
 */
public interface IPropertyPersistence {

	/**
	 * 加载一个proxy实例的属性
	 * 
	 * @param proxyId
	 *            proxy id
	 * @return proxy属性
	 */
	ProxyProperty load(String proxyId);

	/**
	 * 加载所有proxy实例的属性
	 * 
	 * @return proxy属性列表
	 */
	List<ProxyProperty> loadAll();

	/**
	 * 保存proxy实例属性
	 * 
	 * @param property
	 *            proxy属性
	 */
	void save(ProxyProperty property);

	/**
	 * 删除proxy实例属性
	 * 
	 * @param proxyId
	 *            proxy id
	 */
	void delete(String proxyId);

}
