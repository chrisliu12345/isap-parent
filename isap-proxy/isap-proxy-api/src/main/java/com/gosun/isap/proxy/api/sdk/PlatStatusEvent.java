package com.gosun.isap.proxy.api.sdk;

import java.io.Serializable;

/**
 * 平台状态事件
 * 
 * @author liuzk
 *
 */
public class PlatStatusEvent implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long platId;
	private boolean online;

	public Long getPlatId() {
		return platId;
	}

	public void setPlatId(Long platId) {
		this.platId = platId;
	}

	public boolean isOnline() {
		return online;
	}

	public void setOnline(boolean online) {
		this.online = online;
	}
}
