package com.gosun.isap.ptz.api;
/**
 * 预置位 添加 删除请求 
 * @author 1
 *
 */
public class PtzPresetReq {
	private String deviceId;//设备ID
	private String name;//预置位名称
	private int presetId;//预置位编号
	

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getPresetId() {
		return presetId;
	}
	public void setPresetId(int presetId) {
		this.presetId = presetId;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}


}
