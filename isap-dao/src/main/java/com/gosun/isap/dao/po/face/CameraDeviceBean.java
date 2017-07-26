package com.gosun.isap.dao.po.face;

public class CameraDeviceBean {
    private String id;
    private String name;
    private String faceCode;
    private String Description;
    private Integer faceFalg;
    private String locationX;
    private String locationY;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFaceCode() {
		return faceCode;
	}
	public void setFaceCode(String faceCode) {
		this.faceCode = faceCode;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public Integer getFaceFalg() {
		return faceFalg;
	}
	public void setFaceFalg(Integer faceFalg) {
		this.faceFalg = faceFalg;
	}
	public String getLocationX() {
		return locationX;
	}
	public void setLocationX(String locationX) {
		this.locationX = locationX;
	}
	public String getLocationY() {
		return locationY;
	}
	public void setLocationY(String locationY) {
		this.locationY = locationY;
	}
}
