package com.gosun.isap.dao.po.customer;

import java.util.List;

public class RoleMenuAuthCustomer {

	
    private int roleId;
	private String roleName;
	private List<Integer> menuAuthId;
	private String menuPermission;
	
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public List<Integer> getMenuAuthId() {
		return menuAuthId;
	}
	public void setMenuAuthId(List<Integer> menuAuthId) {
		this.menuAuthId = menuAuthId;
	}
	public String getMenuPermission() {
		return menuPermission;
	}
	public void setMenuPermission(String menuPermission) {
		this.menuPermission = menuPermission;
	}
	
	
}
