package com.gosun.isap.resource.api.wrapper;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class DepartmentWrapper {
	private String departId;
	private String departName;
	private String parent;
	private int communityFlag;
	private String parentIds;

	public String getDepartId() {
		return departId;
	}

	public void setDepartId(String departId) {
		this.departId = departId;
	}

	@NotNull(message = "组织名不能为空")
	@Length(min = 1, max = 50)
	public String getDepartName() {
		return departName;
	}

	public void setDepartName(String departName) {
		this.departName = departName;
	}

	//@NotNull(message = "父类组织不能为空")
	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public int getCommunityFlag() {
		return communityFlag;
	}

	public void setCommunityFlag(int communityFlag) {
		this.communityFlag = communityFlag;
	}

	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}
	

}
