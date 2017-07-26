package com.gosun.isap.warn.api.report;

import java.io.Serializable;
import java.util.List;

/**
 * 告警确认信息
 * 
 * @author lucf
 *
 */
public class AlarmAckInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<RecordID> recordObj;

	/**
	 * 告警确认用户
	 */
	private String checkUser;

	/**
	 * 告警确认时间
	 */
	private String checkTime;

	/**
	 * 确认建议
	 */
	private String suggestion;

	public void setRecordObj(List<RecordID> recordObj) {
		this.recordObj = recordObj;
	}

	public List<RecordID> getRecordObj() {
		return this.recordObj;
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

	/**
	 * 告警记录ID
	 * 
	 * @author lucf
	 *
	 */
	public static class RecordID implements Serializable {

		private static final long serialVersionUID = 1L;

		private Long id;

		public void setRecordID(Long id) {
			this.id = id;
		}

		public Long getRecordID() {
			return this.id;
		}
	}
}
