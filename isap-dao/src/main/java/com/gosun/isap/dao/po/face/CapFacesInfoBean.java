package com.gosun.isap.dao.po.face;

import com.google.gson.annotations.SerializedName;

/**
 * 抓拍人脸的消息<br>
 * <p>创建时间：2017/05/02</p>
 *
 * @author 王栋梁
 * @version 1.0
 */
public class CapFacesInfoBean {

	private Integer ID;	
	@SerializedName("FaceURL")
	private String faceURL;											//人脸照片路径
	@SerializedName("BackgroundURL")
	private String backGroundURL;							//全景照片
/*	@SerializedName("Feature")
	private String feature;											//特征值
*/	@SerializedName("CreateTime")
	private String capFacesTime;								//拍摄时间
	@SerializedName("Address")
	private String cameraAddress;								//相机地址
	
	public Integer getID() {
		return ID;
	}
	public void setID(Integer iD) {
		ID = iD;
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
/*	public String getFeature() {
		return feature;
	}
	public void setFeature(String feature) {
		this.feature = feature;
	}*/
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
}
