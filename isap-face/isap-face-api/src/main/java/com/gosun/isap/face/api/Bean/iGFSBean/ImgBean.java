package com.gosun.isap.face.api.Bean.iGFSBean;

import java.io.Serializable;

import javax.ws.rs.FormParam;

public class ImgBean implements Serializable{
	@FormParam("imgdata")
	private byte[] imgdata;
	@FormParam("width")
	private String width;
	@FormParam("height")
	private String height;
	
	public byte[] getImgdata() {
		return imgdata;
	}
	public void setImgdata(byte[] imgdata) {
		this.imgdata = imgdata;
	}
	public String getWidth() {
		return width;
	}
	public void setWidth(String width) {
		this.width = width;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	
}
