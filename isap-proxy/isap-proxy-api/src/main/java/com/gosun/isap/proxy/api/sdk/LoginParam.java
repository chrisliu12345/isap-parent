package com.gosun.isap.proxy.api.sdk;

import java.io.Serializable;

/**
 * 登录参数
 * 
 * @author liuzk
 *
 */
public class LoginParam implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 用户
	 */
	public String user;
	/**
	 * 密码
	 */
	public String password;
	/**
	 * ip
	 */
	public String ipAddress;
	/**
	 * 端口
	 */
	public short port;
	/**
	 * 登录类型
	 */
	public int loginType;
	/**
	 * 配置文件路径
	 */
	public String configPath;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public short getPort() {
		return port;
	}

	public void setPort(short port) {
		this.port = port;
	}

	public int getLoginType() {
		return loginType;
	}

	public void setLoginType(int loginType) {
		this.loginType = loginType;
	}

	public String getConfigPath() {
		return configPath;
	}

	public void setConfigPath(String configPath) {
		this.configPath = configPath;
	}
}
