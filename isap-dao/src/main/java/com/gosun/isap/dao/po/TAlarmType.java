package com.gosun.isap.dao.po;

import java.io.Serializable;

public class TAlarmType implements Serializable {
    private Long alarmType;

    private String alarmName;

    private Byte alarmLevel;

    private static final long serialVersionUID = 1L;

    public Long getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(Long alarmType) {
        this.alarmType = alarmType;
    }

    public String getAlarmName() {
        return alarmName;
    }

    public void setAlarmName(String alarmName) {
        this.alarmName = alarmName == null ? null : alarmName.trim();
    }

    public Byte getAlarmLevel() {
        return alarmLevel;
    }

    public void setAlarmLevel(Byte alarmLevel) {
        this.alarmLevel = alarmLevel;
    }
}