package com.gosun.isap.dao.po.customer;

import java.io.Serializable;
import java.util.Date;

import com.gosun.isap.dao.po.TDevice;
import com.gosun.isap.dao.po.TRoleDepExample;

public class DeviceDevAuthInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	private TDevice device;
	private String devId;
	private int authId;
	private String name;
	private String permission;
	private int devType;
	private Date endDate;

	public String getDevId() {
		return devId;
	}

	public void setDevId(String devId) {
		this.devId = devId;
	}

	public TDevice getDevice() {
		return device;
	}

	public void setDevice(TDevice device) {
		this.device = device;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public int getAuthId() {
		return authId;
	}

	public void setAuthId(int authId) {
		this.authId = authId;
	}

	public int getDevType() {
		return devType;
	}

	public void setDevType(int devType) {
		this.devType = devType;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

}
