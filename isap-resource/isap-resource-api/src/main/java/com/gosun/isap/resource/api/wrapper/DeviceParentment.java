package com.gosun.isap.resource.api.wrapper;

import java.util.List;

import com.gosun.isap.dao.po.TDevice;

public class DeviceParentment {
	List<String> id;
	String parentment;
	String name;
	String deviceid;
	public List<String> getId() {
		return id;
	}
	public String getDeviceid() {
		return deviceid;
	}
	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid;
	}
	public void setId(List<String> id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getParentment() {
		return parentment;
	}
	public void setParentment(String parentment) {
		this.parentment = parentment;
	}

	
}
