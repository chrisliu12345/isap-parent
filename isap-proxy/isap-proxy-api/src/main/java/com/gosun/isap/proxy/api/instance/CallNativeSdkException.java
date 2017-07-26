package com.gosun.isap.proxy.api.instance;

/**
 * sdk调用异常 checked异常
 * 
 * @author liuzk
 *
 */
public class CallNativeSdkException extends Exception {

	private static final long serialVersionUID = 1L;

	public CallNativeSdkException(int handle, String sdkInterface, int retCode, String message) {
		super("Failed to call native sdk method [" + sdkInterface + "], handle=" + handle + ", ret=" + retCode
				+ ", message = " + message);
	}

	public CallNativeSdkException(int handle, String sdkInterface, int retCode) {
		super("Failed to call native sdk method [" + sdkInterface + "], handle=" + handle + ", ret=" + retCode);
	}
}
