package com.gosun.isap.warn.api.report;

/**
 * 告警数据
 * 
 * @author lucf
 *
 */
public class AlarmData {

	/**
	 * 平台ID
	 */
	private Long platID;

	/**
	 * 告警设备ID(基础平台)
	 */
	private String deviceID;

	/**
	 * 告警类型
	 */
	private int alarmType;

	/**
	 * 告警标志
	 */
	private int alarmFlag;

	/**
	 * 告警数据
	 */
	private String data;

	public AlarmData() {
	};

	public AlarmData(Long platID, String deviceID, int alarmType, int alarmFlag, String data) {
		this.platID = platID;
		this.deviceID = deviceID;
		this.alarmType = alarmType;
		this.alarmFlag = alarmFlag;
		this.data = data;
	}

	public void setPlatID(Long platID) {
		this.platID = platID;
	}

	public Long getPlatID() {
		return this.platID;
	}

	public void setDeviceID(String deviceID) {
		this.deviceID = deviceID;
	}

	public String getDeviceID() {
		return this.deviceID;
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

	public void setAlarmData(String data) {
		this.data = data;
	}

	public String getAlarmData() {
		return this.data;
	}
}
