package com.gosun.isap.dao.po.face;
/**
 * 告警名单信息<br>
 *     <p>对特定人员进行查询，对不是此人员的记录去除</p>
 * <p>创建时间：2017/05/02</p>
 *
 * @author 王栋梁
 * @version 1.0
 */
public class AlarmManInfoBean {
	
	private Integer listID;											//名单ID
	private String	name;											//人员姓名
	private Integer sex;											//性别(1.男2.女)
	private String IDCard;										//身份证号码
	private String reason;										//进入名单理由
	private String faceURL;										//黑名单照片路径
	private Integer number;									//出现次数
	
	public Integer getListID() {
		return listID;
	}
	public void setListID(Integer listID) {
		this.listID = listID;
	}
	public String getFaceURL() {
		return faceURL;
	}
	public void setFaceURL(String faceURL) {
		this.faceURL = faceURL;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getIDCard() {
		return IDCard;
	}
	public void setIDCard(String iDCard) {
		IDCard = iDCard;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
}
