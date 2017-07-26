package com.gosun.isap.face.api.Bean;

import java.io.Serializable;

public class ListFilterInfoBean implements Serializable{
	private String departmentID;								//部门ID
	private Integer ID;													//误报ID
	private Integer listType;											//名单类型
	private String listFeature;										//名单特征值
	private String listFaceURL;										//名单人脸照片路径
	private String faceURL;											//人脸照片路径
	private String backGroundURL;							//全景照片
	private Double similarity;										//相识度
	private String capFacesTime;								//拍摄时间
	private String cameraAddress;								//相机地址
	private String editTime;											//误报确认时间
	private String editor;												//误报确认者
	private Integer capfaceID;										//抓拍人脸ID
	private Integer blacklistID;									//名单ID
	
	public String getDepartmentID() {
		return departmentID;
	}
	public void setDepartmentID(String departmentID) {
		this.departmentID = departmentID;
	}
	public Integer getID() {
		return ID;
	}
	public void setID(Integer iD) {
		ID = iD;
	}
	public Integer getListType() {
		return listType;
	}
	public void setListType(Integer listType) {
		this.listType = listType;
	}
	public String getListFeature() {
		return listFeature;
	}
	public void setListFeature(String listFeature) {
		this.listFeature = listFeature;
	}
	public String getListFaceURL() {
		return listFaceURL;
	}
	public void setListFaceURL(String listFaceURL) {
		this.listFaceURL = listFaceURL;
	}
	public String getFaceURL() {
		return faceURL;
	}
	public void setFaceURL(String faceURL) {
		this.faceURL = faceURL;
	}
	public String getBackGroundURL() {
		return backGroundURL;
	}
	public void setBackGroundURL(String backGroundURL) {
		this.backGroundURL = backGroundURL;
	}
	public Double getSimilarity() {
		return similarity;
	}
	public void setSimilarity(Double similarity) {
		this.similarity = similarity;
	}
	public String getCapFacesTime() {
		return capFacesTime;
	}
	public void setCapFacesTime(String capFacesTime) {
		this.capFacesTime = capFacesTime;
	}
	public String getCameraAddress() {
		return cameraAddress;
	}
	public void setCameraAddress(String cameraAddress) {
		this.cameraAddress = cameraAddress;
	}
	public String getEditTime() {
		return editTime;
	}
	public void setEditTime(String editTime) {
		this.editTime = editTime;
	}
	public String getEditor() {
		return editor;
	}
	public void setEditor(String editor) {
		this.editor = editor;
	}
	public Integer getCapfaceID() {
		return capfaceID;
	}
	public void setCapfaceID(Integer capfaceID) {
		this.capfaceID = capfaceID;
	}
	public Integer getBlacklistID() {
		return blacklistID;
	}
	public void setBlacklistID(Integer blacklistID) {
		this.blacklistID = blacklistID;
	}
	
	
}
