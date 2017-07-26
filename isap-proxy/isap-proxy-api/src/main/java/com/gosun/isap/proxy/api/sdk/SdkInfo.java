package com.gosun.isap.proxy.api.sdk;

import java.io.Serializable;

/**
 * sdk信息
 * 
 * @author liuzk
 *
 */
public class SdkInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 厂商
	 */
	public String vendor;
	/**
	 * 版本
	 */
	public String version;

	SdkInfo(String vendor, String version) {
		this.vendor = vendor;
		this.version = version;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
}
