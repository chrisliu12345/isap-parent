package com.gosun.isap.proxy.api.instance;

/**
 * proxy状态
 * 
 * @author liuzk
 *
 */
public enum ProxyStatus {
	/**
	 * 初始化
	 */
	INIT("init"),
	/**
	 * 运行
	 */
	RUNNING("running"),
	/**
	 * 退出
	 */
	EXIT("exit");

	private String status;

	ProxyStatus() {
		setStatus("init");
	}

	ProxyStatus(String status) {
		this.setStatus(status);
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
