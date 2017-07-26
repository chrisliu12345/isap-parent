package com.gosun.isap.dao.po.customer;

import java.util.List;

public class DeviceAndAuth {
	int deptId;
	int deviceId;
	List<Integer> deviceAuthIds;
	public int getDeptId() {
		return deptId;
	}
	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}
	public int getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(int deviceId) {
		this.deviceId = deviceId;
	}
	public List<Integer> getDeviceAuthIds() {
		return deviceAuthIds;
	}
	public void setDeviceAuthIds(List<Integer> deviceAuthIds) {
		this.deviceAuthIds = deviceAuthIds;
	}
	
}
