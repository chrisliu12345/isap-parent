package com.gosun.isap.warn.api.guard;

import java.io.Serializable;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;

import org.hibernate.validator.constraints.NotEmpty;

public class GuardPlanRes implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long planId;

	private Byte guardStatus;

	@NotEmpty(message = "设备id不能为空")
	private String devId;

	@DecimalMin(value = "0")
	@DecimalMax(value = "5")
	private Long alarmType;

	public Long getPlanId() {
		return planId;
	}

	public void setPlanId(Long planId) {
		this.planId = planId;
	}

	public Byte getGuardStatus() {
		return guardStatus;
	}

	public void setGuardStatus(Byte guardStatus) {
		this.guardStatus = guardStatus;
	}

	public String getDevId() {
		return devId;
	}

	public void setDevId(String devId) {
		this.devId = devId == null ? null : devId.trim();
	}

	public Long getAlarmType() {
		return alarmType;
	}

	public void setAlarmType(Long alarmType) {
		this.alarmType = alarmType;
	}
}
