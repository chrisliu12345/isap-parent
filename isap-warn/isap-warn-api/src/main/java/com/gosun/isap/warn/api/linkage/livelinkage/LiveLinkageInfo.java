package com.gosun.isap.warn.api.linkage.livelinkage;

import java.io.Serializable;

import java.util.List;

/**
 * 告警联动实况配置信息
 * 
 * @author lucf
 *
 */
public class LiveLinkageInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 告警设备ID
	 */
	private String alarmDeviceID;

	/**
	 * 告警类型
	 */
	private int alarmType;

	/**
	 * 联动对象集合
	 */
	private List<ActionObj> linkageObj;

	public void setAlarmDeviceID(String alarmDeviceID) {
		this.alarmDeviceID = alarmDeviceID;
	}

	public String getAlarmDeviceID() {
		return this.alarmDeviceID;
	}

	public void setAlarmType(int alarmType) {
		this.alarmType = alarmType;
	}

	public int getAlarmType() {
		return this.alarmType;
	}

	public void setLinkageObj(List<ActionObj> linkageObj) {
		this.linkageObj = linkageObj;
	}

	public List<ActionObj> getLinkageObj() {
		return this.linkageObj;
	}

	/**
	 * 动作对象
	 */
	public static class ActionObj implements Serializable {

		private static final long serialVersionUID = 1L;

		/**
		 * 相机设备编码
		 */
		private String cameraCode;

		/**
		 * 用户编码
		 */
		private String userCode;

		/**
		 * 窗格编号
		 */
		private int paneIndex;

		public void setCameraCode(String cameraCode) {
			this.cameraCode = cameraCode;
		}

		public String getCameraCode() {
			return this.cameraCode;
		}

		public void setUserCode(String userCode) {
			this.userCode = userCode;
		}

		public String getUserCode() {
			return this.userCode;
		}

		public void setPaneIndex(int paneIndex) {
			this.paneIndex = paneIndex;
		}

		public int getPaneIndex() {
			return this.paneIndex;
		}
	}
}
