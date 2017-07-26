package com.gosun.isap.common;

/**
 * ISAP基础异常类
 * 
 * @author liuzk
 *
 */
public class IsapBaseException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private int errorCode = 0;

	public IsapBaseException(int errorCode, String msg) {
		super(msg);
		this.setErrorCode(errorCode);
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

}
