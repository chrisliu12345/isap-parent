package com.gosun.isap.dao.po.face;

import java.io.Serializable;

public class ListFilterParaBean implements Serializable{
	private Integer listType;										//名单类型(1.黑名单 2.白名单)
	private String departmentID;							//部门ID
	private String cameraID;									//相机ID
	private String startTime;									//开始时间
	private String endTime;										//结束时间
	private Integer start;											//页索引
	private Integer limit;											//一页总数
	
	public Integer getListType() {
		return listType;
	}
	public void setListType(Integer listType) {
		this.listType = listType;
	}
	public String getDepartmentID() {
		return departmentID;
	}
	public void setDepartmentID(String departmentID) {
		this.departmentID = departmentID;
	}
	public String getCameraID() {
		return cameraID;
	}
	public void setCameraID(String cameraID) {
		this.cameraID = cameraID;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
}
