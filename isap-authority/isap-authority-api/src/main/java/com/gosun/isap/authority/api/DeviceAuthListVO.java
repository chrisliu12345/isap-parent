package com.gosun.isap.authority.api;

import java.io.Serializable;
import java.util.List;

import com.gosun.isap.dao.po.TDevAuthdef;

/**
 * 用户工具类用来返回用户拥有的设备及对应得权限
 * 
 * @author lyf
 *
 */
public class DeviceAuthListVO implements Serializable {
	private String deviceId;
	private List<String> authList;
	private static final long serialVersionUID = 1L;

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public List<String> getAuthList() {
		return authList;
	}

	public void setAuthList(List<String> authList) {
		this.authList = authList;
	}

}
