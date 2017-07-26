package com.gosun.isap.dao.po;

import java.io.Serializable;

public class TGuardPlanRes implements Serializable {
    private Long id;

    private Long planId;

    private Byte guardStatus;

    private String devId;

    private Long alarmType;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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