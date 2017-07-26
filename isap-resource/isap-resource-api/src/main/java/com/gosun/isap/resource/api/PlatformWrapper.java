package com.gosun.isap.resource.api;

import java.io.Serializable;

import aj.org.objectweb.asm.Type;

//import javax.validation.constraints.Pattern;

public class PlatformWrapper implements Serializable {

	long ID;
	String platName;
	Byte vendorType;
	Byte accessType;
	String ipAddreaccess;
	String loginUser;
	String loginPasswd;
	Byte platStatus;
	String port;

	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	public String getPlatName() {
		return platName;
	}

	public void setPlatName(String platName) {
		this.platName = platName;
	}

	public Byte getVendorType() {
		return vendorType;
	}

	public void setVendorType(Byte vendorType) {
		this.vendorType = vendorType;
	}

	public Byte getAccessType() {
		return accessType;
	}

	public void setAccessType(Byte accessType) {
		this.accessType = accessType;
	}

	public String getIpAddreaccess() {
		return ipAddreaccess;
	}

	public void setIpAddreaccess(String ipAddreaccess) {
		this.ipAddreaccess = ipAddreaccess;
	}

	public String getLoginUser() {
		return loginUser;
	}

	public void setLoginUser(String loginUser) {
		this.loginUser = loginUser;
	}

	public String getLoginPasswd() {
		return loginPasswd;
	}

	public void setLoginPasswd(String loginPasswd) {
		this.loginPasswd = loginPasswd;
	}

	public Byte getPlatStatus() {
		return platStatus;
	}

	public void setPlatStatus(Byte platStatus) {
		this.platStatus = platStatus;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

}
