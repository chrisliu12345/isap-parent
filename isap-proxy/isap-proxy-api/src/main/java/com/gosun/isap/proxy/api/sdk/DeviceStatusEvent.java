package com.gosun.isap.proxy.api.sdk;

import java.io.Serializable;

/**
 * 设备状态事件
 * 
 * @author liuzk
 *
 */
public class DeviceStatusEvent implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 基础平台设备编码
	 */
	public String deviceCode;
	/**
	 * 是否在线
	 */
	public boolean online;

	private Long platId;
	
	public DeviceStatusEvent(){
		
	}

	public DeviceStatusEvent(String deviceCode, boolean online) {
		this.deviceCode = deviceCode;
		this.online = online;
	}

	public Long getPlatId() {
		return platId;
	}

	public void setPlatId(Long platId) {
		this.platId = platId;
	}

	public String getDeviceCode() {
		return deviceCode;
	}

	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}

	public boolean isOnline() {
		return online;
	}

	public void setOnline(boolean online) {
		this.online = online;
	}

}
