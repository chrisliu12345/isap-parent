package com.gosun.isap.resource.api.wrapper;



import java.util.List;

import javax.validation.constraints.NotNull;


import org.hibernate.validator.constraints.Length;





public class AddRoleReceivePara {

	private Long roleId;
	
	private String roleName;
	
	private List<Integer> devAuthId;
	
	//private List<DeviceAndAuth> devAuthList;
	
	private List<String> devId;
	
	private List<Integer> menuAuthId;

	
	 
	//@NotNull(message = "角色设备权限不能为空")
	public List<Integer> getDevAuthId() {
		return devAuthId;
	}

	public void setDevAuthId(List<Integer> devAuthId) {
		this.devAuthId = devAuthId;
	}

	//@NotNull(message = "设备Id不能为空")
	public List<String> getDevId() {
		return devId;
	}

	public void setDevId(List<String> devId) {
		this.devId = devId;
	}

	/*public List<DeviceAndAuth> getDevAuthList() {
		return devAuthList;
	}

	public void setDevAuthList(List<DeviceAndAuth> devAuthList) {
		this.devAuthList = devAuthList;
	}*/

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	//@NotNull(message = "角色名称不能为空")
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	//@NotNull(message = "菜单权限不能为空")
	public List<Integer> getMenuAuthId() {
		return menuAuthId;
	}
	
	
	public void setMenuAuthId(List<Integer> menuAuthId) {
		this.menuAuthId = menuAuthId;
	}
	

	
	
}
