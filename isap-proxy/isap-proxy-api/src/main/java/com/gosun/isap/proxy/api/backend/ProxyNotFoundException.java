package com.gosun.isap.proxy.api.backend;

/**
 * proxy实例不存在异常
 * 
 * @author liuzk
 *
 */
public class ProxyNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ProxyNotFoundException(String proxyId) {
		super("Proxy instance [" + proxyId + "] not found");
	}
}
