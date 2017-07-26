package com.gosun.isap.proxy.api.backend;

import com.gosun.isap.proxy.api.instance.ISdkAdapter;
import com.gosun.isap.proxy.api.instance.ProxyProperty;

/**
 * proxy包装器
 * 
 * @author liuzk
 *
 */
public class ProxyWrapper {
	private String registryAddress;
	private ProxyProperty property;

	/**
	 * 获取sdk适配器接口
	 * 
	 * @return sdk适配器
	 * @throws ProxyNotFoundException
	 *             proxy实例不存在异常
	 */
	public ISdkAdapter getSdkAdapter() throws ProxyNotFoundException {
		String group = property.getProxyId();
		ISdkAdapter sdk = ReferenceUtils.getReferenceConfig(buildBusinessName(), getRegistryAddress(), group).get();
		if (null == sdk) {
			throw new ProxyNotFoundException(property.getProxyId());
		}
		return sdk;
	}

	public ProxyProperty getProperty() {
		return property;
	}

	public void setProperty(ProxyProperty property) {
		this.property = property;
	}

	/**
	 * 销毁
	 */
	public void destroy() {
		ReferenceUtils.destroyReferenceConfig(buildBusinessName());
	}

	private String buildBusinessName() {
		String business = ISdkAdapter.class.getName() + "@" + property.getProxyId();
		return business;
	}

	public String getRegistryAddress() {
		return registryAddress;
	}

	public void setRegistryAddress(String registryAddress) {
		this.registryAddress = registryAddress;
	}
}
