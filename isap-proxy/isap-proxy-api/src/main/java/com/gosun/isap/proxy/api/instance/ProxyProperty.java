package com.gosun.isap.proxy.api.instance;

import java.io.Serializable;

import org.apache.commons.lang.builder.HashCodeBuilder;

import com.gosun.isap.proxy.api.sdk.constants.PlatformType;

/**
 * proxy属性
 * 
 * @author liuzk
 *
 */
public class ProxyProperty implements Serializable {

	private static final long serialVersionUID = 1L;
	private String proxyId;
	private String description;
	private Short protocolPort = 0; // 默认为0，自动分配端口
	private PlatformType platformType;
	private String accessIpAddress;
	private Integer accessPort;
	private String accessUser;
	private String accessPassword;
	private boolean autoLogin = true; // 是否自动登录，如果是，则会自动登录

	public Short getProtocolPort() {
		return protocolPort;
	}

	public void setProtocolPort(Short protocolPort) {
		this.protocolPort = protocolPort;
	}

	public String getProxyId() {
		return proxyId;
	}

	public void setProxyId(String proxyId) {
		this.proxyId = proxyId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public PlatformType getPlatformType() {
		return platformType;
	}

	public void setPlatformType(PlatformType platformType) {
		this.platformType = platformType;
	}

	public String getAccessIpAddress() {
		return accessIpAddress;
	}

	public void setAccessIpAddress(String accessIpAddress) {
		this.accessIpAddress = accessIpAddress;
	}

	public Integer getAccessPort() {
		return accessPort;
	}

	public void setAccessPort(Integer accessPort) {
		this.accessPort = accessPort;
	}

	public String getAccessUser() {
		return accessUser;
	}

	public void setAccessUser(String accessUser) {
		this.accessUser = accessUser;
	}

	public String getAccessPassword() {
		return accessPassword;
	}

	public void setAccessPassword(String accessPassword) {
		this.accessPassword = accessPassword;
	}

	public boolean isAutoLogin() {
		return autoLogin;
	}

	public void setAutoLogin(boolean autoLogin) {
		this.autoLogin = autoLogin;
	}

	/**
	 * tostring
	 * 
	 * @return string
	 */
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("{");
		builder.append("proxyId:" + proxyId).append(", ");
		builder.append("protocolPort:" + protocolPort).append(", ");
		builder.append("description:" + description).append(", ");
		builder.append("platformType:" + platformType).append(", ");
		builder.append("accessIpAddress:" + accessIpAddress).append(", ");
		builder.append("accessPort:" + accessPort).append(", ");
		builder.append("accessUser:" + accessUser).append(", ");
		builder.append("accessPassword:" + "******"); // 密码不显示
		builder.append("}");
		return builder.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (null == obj) {
			return false;
		}

		if (obj.getClass() != getClass()) {
			return false;
		}

		ProxyProperty p = (ProxyProperty) obj;
		if (proxyId.equals(p.getProxyId()) && platformType.equals(p.getPlatformType())
				&& accessIpAddress.equals(p.getAccessIpAddress()) && accessPort.equals(p.getAccessPort())
				&& accessUser.equals(p.getAccessUser()) && accessPassword.equals(p.getAccessPassword())) {
			return true;

		}
		
		return false;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 27).append(proxyId).append(platformType).append(accessIpAddress)
				.append(accessPort).append(accessUser).append(accessPassword).toHashCode();
	}
}
