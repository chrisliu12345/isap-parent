package com.gosun.isap.dao.po;

import java.io.Serializable;

public class TAlertDetail implements Serializable {
    private Long id;

    private Long alertId;

    private Integer userResponseTime;

    private Integer userProcessTime;

    private Integer guardResponseTime;

    private Integer guardProcessTime;

    private Integer totalTime;

    private Integer falseAlerts;

    private Integer confirmAlerts;

    private Integer unprocessedAlerts;

    private Boolean isCallBack;

    private Boolean isSecondCallBack;

    private Boolean isArrived;

    private Boolean isQuestionSuspect;

    private Boolean isQuestionSuspectConfirmed;

    private Boolean isUserOverTime;

    private Boolean isGuardOverTime;

    private Byte failedReasonType;

    private String failedReason;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAlertId() {
        return alertId;
    }

    public void setAlertId(Long alertId) {
        this.alertId = alertId;
    }

    public Integer getUserResponseTime() {
        return userResponseTime;
    }

    public void setUserResponseTime(Integer userResponseTime) {
        this.userResponseTime = userResponseTime;
    }

    public Integer getUserProcessTime() {
        return userProcessTime;
    }

    public void setUserProcessTime(Integer userProcessTime) {
        this.userProcessTime = userProcessTime;
    }

    public Integer getGuardResponseTime() {
        return guardResponseTime;
    }

    public void setGuardResponseTime(Integer guardResponseTime) {
        this.guardResponseTime = guardResponseTime;
    }

    public Integer getGuardProcessTime() {
        return guardProcessTime;
    }

    public void setGuardProcessTime(Integer guardProcessTime) {
        this.guardProcessTime = guardProcessTime;
    }

    public Integer getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Integer totalTime) {
        this.totalTime = totalTime;
    }

    public Integer getFalseAlerts() {
        return falseAlerts;
    }

    public void setFalseAlerts(Integer falseAlerts) {
        this.falseAlerts = falseAlerts;
    }

    public Integer getConfirmAlerts() {
        return confirmAlerts;
    }

    public void setConfirmAlerts(Integer confirmAlerts) {
        this.confirmAlerts = confirmAlerts;
    }

    public Integer getUnprocessedAlerts() {
        return unprocessedAlerts;
    }

    public void setUnprocessedAlerts(Integer unprocessedAlerts) {
        this.unprocessedAlerts = unprocessedAlerts;
    }

    public Boolean getIsCallBack() {
        return isCallBack;
    }

    public void setIsCallBack(Boolean isCallBack) {
        this.isCallBack = isCallBack;
    }

    public Boolean getIsSecondCallBack() {
        return isSecondCallBack;
    }

    public void setIsSecondCallBack(Boolean isSecondCallBack) {
        this.isSecondCallBack = isSecondCallBack;
    }

    public Boolean getIsArrived() {
        return isArrived;
    }

    public void setIsArrived(Boolean isArrived) {
        this.isArrived = isArrived;
    }

    public Boolean getIsQuestionSuspect() {
        return isQuestionSuspect;
    }

    public void setIsQuestionSuspect(Boolean isQuestionSuspect) {
        this.isQuestionSuspect = isQuestionSuspect;
    }

    public Boolean getIsQuestionSuspectConfirmed() {
        return isQuestionSuspectConfirmed;
    }

    public void setIsQuestionSuspectConfirmed(Boolean isQuestionSuspectConfirmed) {
        this.isQuestionSuspectConfirmed = isQuestionSuspectConfirmed;
    }

    public Boolean getIsUserOverTime() {
        return isUserOverTime;
    }

    public void setIsUserOverTime(Boolean isUserOverTime) {
        this.isUserOverTime = isUserOverTime;
    }

    public Boolean getIsGuardOverTime() {
        return isGuardOverTime;
    }

    public void setIsGuardOverTime(Boolean isGuardOverTime) {
        this.isGuardOverTime = isGuardOverTime;
    }

    public Byte getFailedReasonType() {
        return failedReasonType;
    }

    public void setFailedReasonType(Byte failedReasonType) {
        this.failedReasonType = failedReasonType;
    }

    public String getFailedReason() {
        return failedReason;
    }

    public void setFailedReason(String failedReason) {
        this.failedReason = failedReason == null ? null : failedReason.trim();
    }
}