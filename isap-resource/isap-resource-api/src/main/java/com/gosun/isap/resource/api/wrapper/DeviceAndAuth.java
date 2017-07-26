package com.gosun.isap.resource.api.wrapper;

import java.util.List;

import javax.validation.constraints.NotNull;

public class DeviceAndAuth {
	//String deptId;
	String devOrDepId;
	List<Integer> deviceAuthIds;
	
	/*@NotNull(message = "部门ID不能为空")
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}*/
	 
	@NotNull(message = "设备权限不能为空")
	public List<Integer> getDeviceAuthIds() {
		return deviceAuthIds;
	}
	@NotNull(message = "设备或部门ID不能为空")
	public String getDevOrDepId() {
		return devOrDepId;
	}
	
	public void setDevOrDepId(String devOrDepId) {
		this.devOrDepId = devOrDepId;
	}
	public void setDeviceAuthIds(List<Integer> deviceAuthIds) {
		this.deviceAuthIds = deviceAuthIds;
	}
	
}
