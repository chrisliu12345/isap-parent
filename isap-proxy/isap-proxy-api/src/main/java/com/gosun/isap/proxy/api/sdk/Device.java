package com.gosun.isap.proxy.api.sdk;

import java.io.Serializable;

/**
 * 设备
 * 
 * @author liuzk
 *
 */
public class Device implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 基础平台设备编码
	 */
	public String deviceCode;
	/**
	 * 基础平台父设备编码
	 */
	public String parentDeviceCode;
	/**
	 * 基础平台组织编码
	 */
	public String orgCode;
	/**
	 * 名称
	 */
	public String alias;
	/**
	 * 厂商
	 */
	public String manufactory;
	/**
	 * 经度
	 */
	public double lonitude;
	/**
	 * 纬度
	 */
	public double latitude;
	/**
	 * 设备类型
	 */
	public int deviceType;
	/**
	 * 通道类型
	 */
	public int channelType;
	/**
	 * 通道子类型
	 */
	public int chnanelSubType;
	/**
	 * 在线状态
	 */
	public int online;
	/**
	 * 用户名
	 */
	public String user;
	/**
	 * 密码
	 */
	public String password;
	/**
	 * ip地址
	 */
	public String ipaddr;
	/**
	 * 端口
	 */
	public int port;

	Device(String deviceCode, String parentDeviceCode, String orgCode, String alias, String manufactory,
			double lonitude, double latitude, int deviceType, int channelType, int chnanelSubType, int online,
			String user, String password, String ipaddr, int port) {
		this.deviceCode = deviceCode;
		this.parentDeviceCode = parentDeviceCode;
		this.orgCode = orgCode;
		this.alias = alias;
		this.manufactory = manufactory;
		this.lonitude = lonitude;
		this.latitude = latitude;
		this.deviceType = deviceType;
		this.channelType = channelType;
		this.chnanelSubType = chnanelSubType;
		this.online = online;
		this.user = user;
		this.password = password;
		this.ipaddr = ipaddr;
		this.port = port;
	}

	public String getDeviceCode() {
		return deviceCode;
	}

	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}

	public String getParentDeviceCode() {
		return parentDeviceCode;
	}

	public void setParentDeviceCode(String parentDeviceCode) {
		this.parentDeviceCode = parentDeviceCode;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getManufactory() {
		return manufactory;
	}

	public void setManufactory(String manufactory) {
		this.manufactory = manufactory;
	}

	public double getLonitude() {
		return lonitude;
	}

	public void setLonitude(double lonitude) {
		this.lonitude = lonitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public int getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(int deviceType) {
		this.deviceType = deviceType;
	}

	public int getChannelType() {
		return channelType;
	}

	public void setChannelType(int channelType) {
		this.channelType = channelType;
	}

	public int getChnanelSubType() {
		return chnanelSubType;
	}

	public void setChnanelSubType(int chnanelSubType) {
		this.chnanelSubType = chnanelSubType;
	}

	public int getOnline() {
		return online;
	}

	public void setOnline(int online) {
		this.online = online;
	}

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

	public String getIpaddr() {
		return ipaddr;
	}

	public void setIpaddr(String ipaddr) {
		this.ipaddr = ipaddr;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
}
