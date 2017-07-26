package com.gosun.isap.ptz.impl;

public class PtzPresetQureyReq {
	private String deviceid;//设备ID
	private String platformid;//平台ID
	public String getPlatformid() {
		return platformid;
	}
	public void setPlatformid(String platformid) {
		this.platformid = platformid;
	}
	public String getDeviceid() {
		return deviceid;
	}
	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid;
	}

}
