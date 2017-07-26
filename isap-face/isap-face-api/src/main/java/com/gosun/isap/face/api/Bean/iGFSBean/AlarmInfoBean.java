package com.gosun.isap.face.api.Bean.iGFSBean;

/**
 * 告警信息<br>
 *     <p>对特定人员进行查询，对不是此人员的记录去除</p>
 * <p>创建时间：2017/05/02</p>
 *
 * @author 王栋梁
 * @version 1.0
 */
public class AlarmInfoBean {
	
    public Integer ID;
    public Integer ContactID;
    public String Name;
    public String IDCard;
    public int CapFaceID;
    public int BlacklistID;
    public String FaceImg;
    public String BlacklistImg;
    public String BackgroundImg;
    public float Simi ;
    public String Reason;
    public String CameraName;
    public String CapAddr;
    public String HostName;
    
	public Integer getID() {
		return ID;
	}
	public void setID(Integer iD) {
		ID = iD;
	}
	public Integer getContactID() {
		return ContactID;
	}
	public void setContactID(Integer contactID) {
		ContactID = contactID;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getIDCard() {
		return IDCard;
	}
	public void setIDCard(String iDCard) {
		IDCard = iDCard;
	}
	public int getCapFaceID() {
		return CapFaceID;
	}
	public void setCapFaceID(int capFaceID) {
		CapFaceID = capFaceID;
	}
	public int getBlacklistID() {
		return BlacklistID;
	}
	public void setBlacklistID(int blacklistID) {
		BlacklistID = blacklistID;
	}
	public String getFaceImg() {
		return FaceImg;
	}
	public void setFaceImg(String faceImg) {
		FaceImg = faceImg;
	}
	public String getBlacklistImg() {
		return BlacklistImg;
	}
	public void setBlacklistImg(String blacklistImg) {
		BlacklistImg = blacklistImg;
	}
	public String getBackgroundImg() {
		return BackgroundImg;
	}
	public void setBackgroundImg(String backgroundImg) {
		BackgroundImg = backgroundImg;
	}
	public float getSimi() {
		return Simi;
	}
	public void setSimi(float simi) {
		Simi = simi;
	}
	public String getReason() {
		return Reason;
	}
	public void setReason(String reason) {
		Reason = reason;
	}
	public String getCameraName() {
		return CameraName;
	}
	public void setCameraName(String cameraName) {
		CameraName = cameraName;
	}
	public String getCapAddr() {
		return CapAddr;
	}
	public void setCapAddr(String capAddr) {
		CapAddr = capAddr;
	}
	public String getHostName() {
		return HostName;
	}
	public void setHostName(String hostName) {
		HostName = hostName;
	}
	
    
}
