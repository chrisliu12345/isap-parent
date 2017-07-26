package com.gosun.isap.proxy.api.backend;

/**
 * 创建proxy实例异常
 * 
 * @author liuzk
 *
 */
public class NewProxyException extends Exception {

	private static final long serialVersionUID = 1L;

	public NewProxyException(String proxyId, String msg) {
		super("New proxy exception, proxyId " + proxyId + ", message " + msg);
	}
}
