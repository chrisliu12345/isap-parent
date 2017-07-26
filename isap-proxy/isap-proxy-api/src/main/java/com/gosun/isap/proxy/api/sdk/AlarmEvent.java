package com.gosun.isap.proxy.api.sdk;

import java.io.Serializable;

/**
 * 告警事件
 * 
 * @author liuzk
 *
 */
public class AlarmEvent implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 基础平台设备编码
	 */
	public String deviceCode;
	/**
	 * 告警类型
	 */
	public int alarmType;
	/**
	 * 告警标志
	 */
	public int alarmFlag;
	/**
	 * 告警数据
	 */
	public String alarmData;
	// 平台ID
	private Long platId;

	public AlarmEvent() {
	}

	public AlarmEvent(int alarmType, String deviceCode, int alarmFlag, String alarmData) {
		this.alarmType = alarmType;
		this.deviceCode = deviceCode;
		this.alarmFlag = alarmFlag;
		this.alarmData = alarmData;
	}

	public String getDeviceCode() {
		return deviceCode;
	}

	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}

	public int getAlarmType() {
		return alarmType;
	}

	public void setAlarmType(int alarmType) {
		this.alarmType = alarmType;
	}

	public int getAlarmFlag() {
		return alarmFlag;
	}

	public void setAlarmFlag(int alarmFlag) {
		this.alarmFlag = alarmFlag;
	}

	public String getAlarmData() {
		return alarmData;
	}

	public void setAlarmData(String alarmData) {
		this.alarmData = alarmData;
	}

	public Long getPlatId() {
		return platId;
	}

	public void setPlatId(Long platId) {
		this.platId = platId;
	}
}
