package com.gosun.isap.dao.po.customer;

import java.util.List;

import com.gosun.isap.dao.po.TRole;
import com.gosun.isap.dao.po.TUser;

public class TUserCustomer extends TUser {
	private List<TRole> roleList;

	public List<TRole> getRoleList() {
		return roleList;
	}

	/*
	 * public void setRoleList(List<Integer> ids) { TRole role = new TRole();
	 * for (Integer integer : ids) { role.setId((long) integer);
	 * roleList.add(role); } }
	 */

	public void setRoleList(List<TRole> roleList) {
		this.roleList = roleList;
	}

}
