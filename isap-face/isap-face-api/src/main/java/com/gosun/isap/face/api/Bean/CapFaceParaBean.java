package com.gosun.isap.face.api.Bean;

import java.io.Serializable;

/**
 * 抓拍人脸的检索条件<br>
 * <p>创建时间：2017/05/02</p>
 *
 * @author 王栋梁
 * @version 1.0
 */
public class CapFaceParaBean implements Serializable{
	private String feature;										//特征值
	private String faceURL;										//对比照片URL
	private String departmentID;							//部门ID
	private String cameraID;									//相机ID
	private String startTime;									//开始时间
	private String endTime;										//结束时间
	private String minSimilarity;								//最小相似度
	private int maxReturn;										//最大返回值
	private Integer start;											//页索引
	private Integer limit;											//一页总数
	
	public String getFeature() {
		return feature;
	}
	public void setFeature(String feature) {
		this.feature = feature;
	}
	public String getFaceURL() {
		return faceURL;
	}
	public void setFaceURL(String faceURL) {
		this.faceURL = faceURL;
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
	public String getMinSimilarity() {
		return minSimilarity;
	}
	public void setMinSimilarity(String minSimilarity) {
		this.minSimilarity = minSimilarity;
	}
	public int getMaxReturn() {
		return maxReturn;
	}
	public void setMaxReturn(int maxReturn) {
		this.maxReturn = maxReturn;
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
