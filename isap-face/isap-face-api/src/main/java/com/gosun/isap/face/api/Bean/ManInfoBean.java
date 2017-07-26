package com.gosun.isap.face.api.Bean;

import java.io.Serializable;

import javax.ws.rs.FormParam;

import org.jboss.resteasy.annotations.providers.multipart.PartType;

import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.google.gson.annotations.SerializedName;

/**
 * 名单人员信息<br>
 * <p>创建时间：2017/05/02</p>
 *
 * @author 王栋梁
 * @version 1.0
 */
public class ManInfoBean implements Serializable{
	

	@SerializedName("ID")
	private Integer listID;											//名单ID
	@FormParam("name")
	@SerializedName("Name")
	@PartType(ContentType.TEXT_PLAIN_UTF_8)
	private String	name;											//人员姓名
	@FormParam("IDCard")
	private String IDCard;										//身份证号码
	@FormParam("sex")
	@SerializedName("Sex")
	private Integer sex;											//性别(1.男2.女)
	@FormParam("reason")
	@SerializedName("Reason")
	private String reason;										//进入名单理由
	@SerializedName("ImgURL")
	private String faceURL;										//人脸照片路径
	private String backGroundURL;						//全景照片路径
	private String tagging;										//标注
	@FormParam("listType")
	private Integer listType;										//名单类型
	@FormParam("feature")
	@SerializedName("Feature")
	private String feature;										//特征值
	@FormParam("imgdata")
	private byte[] imgdata;										//照片信息
	private Integer start;
	private Integer limit;
	
	public Integer getListID() {
		return listID;
	}
	public void setListID(Integer listID) {
		this.listID = listID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIDCard() {
		return IDCard;
	}
	public void setIDCard(String iDCard) {
		IDCard = iDCard;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getFaceURL() {
		return faceURL;
	}
	public void setFaceURL(String faceURL) {
		this.faceURL = faceURL;
	}
	public String getBackgroundURL() {
		return backGroundURL;
	}
	public void setBackgroundURL(String backGroundURL) {
		this.backGroundURL = backGroundURL;
	}
	public String getTagging() {
		return tagging;
	}
	public void setTagging(String tagging) {
		this.tagging = tagging;
	}
	public Integer getListType() {
		return listType;
	}
	public void setListType(Integer listType) {
		this.listType = listType;
	}
	public String getFeature() {
		return feature;
	}
	public void setFeature(String feature) {
		this.feature = feature;
	}
	public String getBackGroundURL() {
		return backGroundURL;
	}
	public void setBackGroundURL(String backGroundURL) {
		this.backGroundURL = backGroundURL;
	}
	public byte[] getImgdata() {
		return imgdata;
	}
	public void setImgdata(byte[] imgdata) {
		this.imgdata = imgdata;
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
