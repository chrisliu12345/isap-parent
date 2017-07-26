package com.gosun.isap.dao.po;

import java.io.Serializable;

public class TAlertTimeLimit implements Serializable {
    private Long id;

    private String devId;

    private Integer arriveTime;

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

    public Integer getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(Integer arriveTime) {
        this.arriveTime = arriveTime;
    }
}