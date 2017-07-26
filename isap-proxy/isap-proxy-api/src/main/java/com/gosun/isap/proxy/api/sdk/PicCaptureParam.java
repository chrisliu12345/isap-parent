package com.gosun.isap.proxy.api.sdk;

import java.io.Serializable;

/**
 * 抓图参数
 * 
 * @author liuzk
 *
 */
public class PicCaptureParam implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 基础平台设备编码
	 */
	public String deviceCode;

	/**
	 * 图片格式类型，取值PicFormatType
	 */
	public int picFormatType;

	/**
	 * 保存路径(如果设置为空，则由系统自动生成)
	 */
	public String filePath;

	public String getDeviceCode() {
		return deviceCode;
	}

	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}

	public int getPicFormatType() {
		return picFormatType;
	}

	public void setPicFormatType(int picFormatType) {
		this.picFormatType = picFormatType;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
}
