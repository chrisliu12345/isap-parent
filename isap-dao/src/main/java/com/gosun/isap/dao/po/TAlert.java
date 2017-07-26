package com.gosun.isap.dao.po;

import java.io.Serializable;
import java.util.Date;

public class TAlert implements Serializable {
    private Long id;

    private String description;

    private Byte reason;

    private String devId;

    private String departmentId;

    private Byte status;

    private Boolean isOverTime;

    private Byte alertType;

    private Integer unprocessedAlerts;

    private Date confirmStartTime;

    private Date confirmEndTime;

    private Integer confirmTime;

    private Integer confirmedAlerts;

    private Long userId;

    private String confirmDesc;

    private Date finishTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Byte getReason() {
        return reason;
    }

    public void setReason(Byte reason) {
        this.reason = reason;
    }

    public String getDevId() {
        return devId;
    }

    public void setDevId(String devId) {
        this.devId = devId == null ? null : devId.trim();
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId == null ? null : departmentId.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Boolean getIsOverTime() {
        return isOverTime;
    }

    public void setIsOverTime(Boolean isOverTime) {
        this.isOverTime = isOverTime;
    }

    public Byte getAlertType() {
        return alertType;
    }

    public void setAlertType(Byte alertType) {
        this.alertType = alertType;
    }

    public Integer getUnprocessedAlerts() {
        return unprocessedAlerts;
    }

    public void setUnprocessedAlerts(Integer unprocessedAlerts) {
        this.unprocessedAlerts = unprocessedAlerts;
    }

    public Date getConfirmStartTime() {
        return confirmStartTime;
    }

    public void setConfirmStartTime(Date confirmStartTime) {
        this.confirmStartTime = confirmStartTime;
    }

    public Date getConfirmEndTime() {
        return confirmEndTime;
    }

    public void setConfirmEndTime(Date confirmEndTime) {
        this.confirmEndTime = confirmEndTime;
    }

    public Integer getConfirmTime() {
        return confirmTime;
    }

    public void setConfirmTime(Integer confirmTime) {
        this.confirmTime = confirmTime;
    }

    public Integer getConfirmedAlerts() {
        return confirmedAlerts;
    }

    public void setConfirmedAlerts(Integer confirmedAlerts) {
        this.confirmedAlerts = confirmedAlerts;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getConfirmDesc() {
        return confirmDesc;
    }

    public void setConfirmDesc(String confirmDesc) {
        this.confirmDesc = confirmDesc == null ? null : confirmDesc.trim();
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }
}