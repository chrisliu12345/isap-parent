package com.gosun.isap.face.api.Bean.iGFSBean;

public class BlacklistGroupBean {
	

	private String ID;										//名单组ID
	private String Name;									//名单组名字
	private String Remark;								//备注
	private int State;										//状态
	private int ContactID;								//用户
	
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getRemark() {
		return Remark;
	}
	public void setRemark(String remark) {
		Remark = remark;
	}
	public int getState() {
		return State;
	}
	public void setState(int state) {
		State = state;
	}
	public int getContactID() {
		return ContactID;
	}
	public void setContactID(int contactID) {
		ContactID = contactID;
	}
	
}
