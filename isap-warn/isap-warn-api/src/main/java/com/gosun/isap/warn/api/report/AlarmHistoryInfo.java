package com.gosun.isap.warn.api.report;

import java.io.Serializable;

/**
 * 历史告警信息类
 * 
 * @author lucf
 *
 */
public class AlarmHistoryInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 告警记录ID
	 */
	private long recordID;

	/**
	 * 告警设备ID
	 */
	private String deviceID;

	/**
	 * 设备名称
	 */
	private String deviceName;

	/**
	 * 告警类型
	 */
	private int alarmType;

	/**
	 * 告警标志（0:告警产生,1:告警恢复）
	 */
	private int alarmFlag;

	/**
	 * 告警级别
	 */
	private int alarmLevel;

	/**
	 * 告警时间
	 */
	private String alarmTime;

	/**
	 * 告警确认
	 */
	private int alarmConfirm;

	/**
	 * 告警确认用户
	 */
	private String checkUser;

	/**
	 * 告警确认时间
	 */
	private String checkTime;

	/**
	 * 告警确认建议
	 */
	private String suggestion;

	public AlarmHistoryInfo(long recordID, String deviceID, String deviceName, int alarmType, int alarmFlag,
			int alarmLevel, String alarmTime, int alarmConfirm, String checkUser, String checkTime, String suggestion) {
		this.recordID = recordID;
		this.deviceID = deviceID;
		this.deviceName = deviceName;
		this.alarmType = alarmType;
		this.alarmFlag = alarmFlag;
		this.alarmLevel = alarmLevel;
		this.alarmTime = alarmTime;
		this.alarmConfirm = alarmConfirm;
		this.checkUser = checkUser;
		this.checkTime = checkTime;
		this.suggestion = suggestion;
	}

	public void setRecordID(long recordID) {
		this.recordID = recordID;
	}

	public long getRecordID() {
		return this.recordID;
	}

	public void setDeviceID(String deviceID) {
		this.deviceID = deviceID;
	}

	public String getDeviceID() {
		return this.deviceID;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getDeviceName() {
		return this.deviceName;
	}

	public void setAlarmType(int alarmType) {
		this.alarmType = alarmType;
	}

	public int getAlarmType() {
		return this.alarmType;
	}

	public void setAlarmFlag(int alarmFlag) {
		this.alarmFlag = alarmFlag;
	}

	public int getAlarmFlag() {
		return this.alarmFlag;
	}

	public void setAlarmLevel(int alarmLevel) {
		this.alarmLevel = alarmLevel;
	}

	public int getAlarmLevel() {
		return this.alarmLevel;
	}

	public void setAlarmTime(String alarmTime) {
		this.alarmTime = alarmTime;
	}

	public String getAlarmTime() {
		return this.alarmTime;
	}

	public void setAlarmConfirm(int alarmConfirm) {
		this.alarmConfirm = alarmConfirm;
	}

	public int getAlarmConfirm() {
		return this.alarmConfirm;
	}

	public void setCheckUser(String checkUser) {
		this.checkUser = checkUser;
	}

	public String getCheckUser() {
		return this.checkUser;
	}

	public void setCheckTime(String checkTime) {
		this.checkTime = checkTime;
	}

	public String getCheckTime() {
		return this.checkTime;
	}

	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}

	public String getSuggestion() {
		return this.suggestion;
	}
}
