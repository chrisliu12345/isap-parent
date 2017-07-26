package com.gosun.isap.dao.po;

import java.io.Serializable;
import java.util.Date;

public class TAlarmRecord implements Serializable {
    private Long id;

    private String devId;

    private Long alarmType;

    private Byte flag;

    private Byte alarmLevel;

    private Date alarmTime;

    private String alarmData;

    private Byte confirm;

    private String checkUser;

    private Date checkTime;

    private String suggestion;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Byte getFlag() {
        return flag;
    }

    public void setFlag(Byte flag) {
        this.flag = flag;
    }

    public Byte getAlarmLevel() {
        return alarmLevel;
    }

    public void setAlarmLevel(Byte alarmLevel) {
        this.alarmLevel = alarmLevel;
    }

    public Date getAlarmTime() {
        return alarmTime;
    }

    public void setAlarmTime(Date alarmTime) {
        this.alarmTime = alarmTime;
    }

    public String getAlarmData() {
        return alarmData;
    }

    public void setAlarmData(String alarmData) {
        this.alarmData = alarmData == null ? null : alarmData.trim();
    }

    public Byte getConfirm() {
        return confirm;
    }

    public void setConfirm(Byte confirm) {
        this.confirm = confirm;
    }

    public String getCheckUser() {
        return checkUser;
    }

    public void setCheckUser(String checkUser) {
        this.checkUser = checkUser == null ? null : checkUser.trim();
    }

    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion == null ? null : suggestion.trim();
    }
}