package com.gosun.isap.dao.po.alarm;

import java.util.Date;

public class AlarmRecordExampleExtend {

	private Integer limit;
	
	private Integer offset;
	
	private Integer alarmType;
	
	private Integer alarmLevel;
	
	private Integer alarmConfirm;
	
	private Date alarmStartTime;
	
	private Date alarmEndTime;
	
    private String fuzzyField;
	
	private String fuzzyValue;
	
	private String orderByClause;

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	public Integer getAlarmType() {
		return alarmType;
	}

	public void setAlarmType(Integer alarmType) {
		this.alarmType = alarmType;
	}

	public Integer getAlarmLevel() {
		return alarmLevel;
	}

	public void setAlarmLevel(Integer alarmLevel) {
		this.alarmLevel = alarmLevel;
	}

	public Integer getAlarmConfirm() {
		return alarmConfirm;
	}

	public void setAlarmConfirm(Integer alarmConfirm) {
		this.alarmConfirm = alarmConfirm;
	}

	public Date getAlarmStartTime() {
		return alarmStartTime;
	}

	public void setAlarmStartTime(Date alarmStartTime) {
		this.alarmStartTime = alarmStartTime;
	}

	public Date getAlarmEndTime() {
		return alarmEndTime;
	}

	public void setAlarmEndTime(Date alarmEndTime) {
		this.alarmEndTime = alarmEndTime;
	}

	public String getFuzzyField() {
		return fuzzyField;
	}

	public void setFuzzyField(String fuzzyField) {
		this.fuzzyField = fuzzyField;
	}

	public String getFuzzyValue() {
		return fuzzyValue;
	}

	public void setFuzzyValue(String fuzzyValue) {
		this.fuzzyValue = fuzzyValue;
	}

	public String getOrderByClause() {
		return orderByClause;
	}

	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}
}
