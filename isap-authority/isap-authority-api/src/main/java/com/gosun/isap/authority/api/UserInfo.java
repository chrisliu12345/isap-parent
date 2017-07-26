package com.gosun.isap.authority.api;

import com.gosun.isap.dao.po.TUser;

public class UserInfo {
	private TUser user;
	private String ip;

	public UserInfo(TUser user) {
		this.user = user;
	}

	public UserInfo(TUser user, String ip) {
		this.user = user;
		this.ip = ip;
	}

	public TUser getUser() {
		return user;
	}

	public void setUser(TUser user) {
		this.user = user;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

}
