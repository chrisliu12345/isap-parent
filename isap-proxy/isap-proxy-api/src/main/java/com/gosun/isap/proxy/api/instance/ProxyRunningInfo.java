package com.gosun.isap.proxy.api.instance;

import java.io.Serializable;

/**
 * proxy运行信息
 * 
 * @author liuzk
 *
 */
public class ProxyRunningInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String proxyId;

	// proxy进程状态，初始化爲init
	private ProxyStatus status = ProxyStatus.INIT;
	// 进程pid，初始化爲0
	private Long pid = (long) 0;

	public ProxyRunningInfo(String proxyId) {
		this.proxyId = proxyId;
	}

	public ProxyStatus getStatus() {
		return status;
	}

	public void setStatus(ProxyStatus status) {
		this.status = status;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	/**
	 * tostring
	 * 
	 * @return string
	 */
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("{");
		builder.append("proxyId:" + proxyId).append(", ");
		builder.append("status:" + status.getStatus()).append(", ");
		builder.append("pid:" + pid);
		builder.append("}");
		return builder.toString();
	}

	public String getProxyId() {
		return proxyId;
	}

	public void setProxyId(String proxyId) {
		this.proxyId = proxyId;
	}
}
