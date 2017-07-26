package com.gosun.isap.face.api.Bean.iGFSBean;

public class CapFaceCompareSearchParaBean {
    private int ContactID;
    private int OriganizeID ;
    private int HostID ;
    private int CameraID ;
    private String  Feature ;
    private float MinSimi ;
    private int MaxReturn ;
    private String StartTime ;
    private String EndTime ;
    
	public int getContactID() {
		return ContactID;
	}
	public void setContactID(int contactID) {
		ContactID = contactID;
	}
	public int getOriganizeID() {
		return OriganizeID;
	}
	public void setOriganizeID(int origanizeID) {
		OriganizeID = origanizeID;
	}
	public int getHostID() {
		return HostID;
	}
	public void setHostID(int hostID) {
		HostID = hostID;
	}
	public int getCameraID() {
		return CameraID;
	}
	public void setCameraID(int cameraID) {
		CameraID = cameraID;
	}
	public String getFeature() {
		return Feature;
	}
	public void setFeature(String feature) {
		Feature = feature;
	}
	public float getMinSimi() {
		return MinSimi;
	}
	public void setMinSimi(float minSimi) {
		MinSimi = minSimi;
	}
	public int getMaxReturn() {
		return MaxReturn;
	}
	public void setMaxReturn(int maxReturn) {
		MaxReturn = maxReturn;
	}
	public String getStartTime() {
		return StartTime;
	}
	public void setStartTime(String startTime) {
		StartTime = startTime;
	}
	public String getEndTime() {
		return EndTime;
	}
	public void setEndTime(String  endTime) {
		EndTime = endTime;
	}
}
