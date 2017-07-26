package com.gosun.isap.proxy.api.sdk;

import java.io.Serializable;

/**
 * 告警配置参数
 * 
 * @author liuzk
 *
 */
public class AlarmConfigParam implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 告警基础平台设备编码
	 */
	public String alarmDeviceId;
	/**
	 * 适配通道
	 */
	public int videoNo;
	/**
	 * 告警输入通道
	 */
	public int alarmInputNo;
	/**
	 * 告警类型，取值AlarmType
	 */
	public int alarmType;

	public String getAlarmDeviceId() {
		return alarmDeviceId;
	}

	public void setAlarmDeviceId(String alarmDeviceId) {
		this.alarmDeviceId = alarmDeviceId;
	}

	public int getVideoNo() {
		return videoNo;
	}

	public void setVideoNo(int videoNo) {
		this.videoNo = videoNo;
	}

	public int getAlarmInputNo() {
		return alarmInputNo;
	}

	public void setAlarmInputNo(int alarmInputNo) {
		this.alarmInputNo = alarmInputNo;
	}

	public int getAlarmType() {
		return alarmType;
	}

	public void setAlarmType(int alarmType) {
		this.alarmType = alarmType;
	}
}
