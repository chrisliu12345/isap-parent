package com.gosun.isap.dao.po.customer;

import com.gosun.isap.dao.po.TRole;

public class TRoleDepDevCustomer extends TRole{

	

	private String depId;
	private String depName;
	private String depParent;
	private String depCommunityFlag;
	
	public String getDepId() {
		return depId;
	}
	public void setDepId(String depId) {
		this.depId = depId;
	}
	public String getDepName() {
		return depName;
	}
	public void setDepName(String depName) {
		this.depName = depName;
	}
	public String getDepParent() {
		return depParent;
	}
	public void setDepParent(String depParent) {
		this.depParent = depParent;
	}
	public String getDepCommunityFlag() {
		return depCommunityFlag;
	}
	public void setDepCommunityFlag(String depCommunityFlag) {
		this.depCommunityFlag = depCommunityFlag;
	}
	
	
}
