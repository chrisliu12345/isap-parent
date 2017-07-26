package com.gosun.isap.face.api.Bean.iGFSBean;

public class BlacklistBean {
	
    public int ID;
    public int ContactID;
    public String Name;
    public int Sex ; 							//1男  ，  2女
    public String IDCard ;
    public String Reason ;
    public String ImgURL ;
    public String Feature ;
    
    
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public int getContactID() {
		return ContactID;
	}
	public void setContactID(int contactID) {
		ContactID = contactID;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public int getSex() {
		return Sex;
	}
	public void setSex(int sex) {
		Sex = sex;
	}
	public String getIDCard() {
		return IDCard;
	}
	public void setIDCard(String iDCard) {
		IDCard = iDCard;
	}
	public String getReason() {
		return Reason;
	}
	public void setReason(String reason) {
		Reason = reason;
	}
	public String getImgURL() {
		return ImgURL;
	}
	public void setImgURL(String imgURL) {
		ImgURL = imgURL;
	}
	public String getFeature() {
		return Feature;
	}
	public void setFeature(String feature) {
		Feature = feature;
	}
    
}
