package com.gosun.isap.resource.api.wrapper;

import java.util.List;

public class UserFindRoleWrapper {

	private int roleId;
	private String roleName;
	private List<DeviceAndAuth> devAuthId;
	private List<Integer> menuAuthId;
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public List<DeviceAndAuth> getDevAuthId() {
		return devAuthId;
	}
	public void setDevAuthId(List<DeviceAndAuth> devAuthId) {
		this.devAuthId = devAuthId;
	}
	public List<Integer> getMenuAuthId() {
		return menuAuthId;
	}
	public void setMenuAuthId(List<Integer> menuAuthId) {
		this.menuAuthId = menuAuthId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	
	
	
}
