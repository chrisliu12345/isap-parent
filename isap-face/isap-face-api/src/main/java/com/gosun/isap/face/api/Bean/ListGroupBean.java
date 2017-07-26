package com.gosun.isap.face.api.Bean;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

/**
 * 名单组信息<br>
 * <p>创建时间：2017/05/03</p>
 *
 * @author 王栋梁
 * @version 1.0
 */
public class ListGroupBean implements Serializable{
	@SerializedName("ID")
	private Integer listGroupID;								//名单组ID
	@SerializedName("Name")
	private String listGroupName;							//名单组名字
	@SerializedName("Remark")
	private String remark;										//备注
	private Integer state;											//状态
	

	public Integer getListGroupID() {
		return listGroupID;
	}
	public void setListGroupID(Integer listGroupID) {
		this.listGroupID = listGroupID;
	}
	public String getListGroupName() {
		return listGroupName;
	}
	public void setListGroupName(String listGroupName) {
		this.listGroupName = listGroupName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
}
