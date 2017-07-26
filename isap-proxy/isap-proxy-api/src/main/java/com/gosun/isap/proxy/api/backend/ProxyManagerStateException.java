package com.gosun.isap.proxy.api.backend;

/**
 * proxy manager状态异常
 * 
 * @author liuzk
 *
 */
public class ProxyManagerStateException extends Exception {
	private static final long serialVersionUID = 1L;

	public ProxyManagerStateException(String msg) {
		super("Proxy manager status exception, detail " + msg);
	}
}
