package com.gosun.isap.dao.po.alert.base;

import java.util.Date;

public class BaseAlertDetail {
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

    private Date addTime;

    private Date updateTime;

    public static final String ID = "id";

    public static final String ALERT_ID = "alert_id";

    public static final String USER_RESPONSE_TIME = "user_response_time";

    public static final String USER_PROCESS_TIME = "user_process_time";

    public static final String GUARD_RESPONSE_TIME = "guard_response_time";

    public static final String GUARD_PROCESS_TIME = "guard_process_time";

    public static final String TOTAL_TIME = "total_time";

    public static final String FALSE_ALERTS = "false_alerts";

    public static final String CONFIRM_ALERTS = "confirm_alerts";

    public static final String UNPROCESSED_ALERTS = "unprocessed_alerts";

    public static final String IS_CALL_BACK = "is_call_back";

    public static final String IS_SECOND_CALL_BACK = "is_second_call_back";

    public static final String IS_ARRIVED = "is_arrived";

    public static final String IS_QUESTION_SUSPECT = "is_question_suspect";

    public static final String IS_QUESTION_SUSPECT_CONFIRMED = "is_question_suspect_confirmed";

    public static final String IS_USER_OVER_TIME = "is_user_over_time";

    public static final String IS_GUARD_OVER_TIME = "is_guard_over_time";

    public static final String FAILED_REASON_TYPE = "failed_reason_type";

    public static final String FAILED_REASON = "failed_reason";

    public static final String ADD_TIME = "add_time";

    public static final String UPDATE_TIME = "update_time";

    public static final String[] COLUMNS = {ID,ALERT_ID,USER_RESPONSE_TIME,USER_PROCESS_TIME,GUARD_RESPONSE_TIME,GUARD_PROCESS_TIME,TOTAL_TIME,FALSE_ALERTS,CONFIRM_ALERTS,UNPROCESSED_ALERTS,IS_CALL_BACK,IS_SECOND_CALL_BACK,IS_ARRIVED,IS_QUESTION_SUSPECT,IS_QUESTION_SUSPECT_CONFIRMED,IS_USER_OVER_TIME,IS_GUARD_OVER_TIME,FAILED_REASON_TYPE,FAILED_REASON,ADD_TIME,UPDATE_TIME};

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

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public static boolean contains(String value) {
        if(value == null){
            return false;
        }
        for (String column:COLUMNS) {
            if(column.equals(value)){
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", alertId=").append(alertId);
        sb.append(", userResponseTime=").append(userResponseTime);
        sb.append(", userProcessTime=").append(userProcessTime);
        sb.append(", guardResponseTime=").append(guardResponseTime);
        sb.append(", guardProcessTime=").append(guardProcessTime);
        sb.append(", totalTime=").append(totalTime);
        sb.append(", falseAlerts=").append(falseAlerts);
        sb.append(", confirmAlerts=").append(confirmAlerts);
        sb.append(", unprocessedAlerts=").append(unprocessedAlerts);
        sb.append(", isCallBack=").append(isCallBack);
        sb.append(", isSecondCallBack=").append(isSecondCallBack);
        sb.append(", isArrived=").append(isArrived);
        sb.append(", isQuestionSuspect=").append(isQuestionSuspect);
        sb.append(", isQuestionSuspectConfirmed=").append(isQuestionSuspectConfirmed);
        sb.append(", isUserOverTime=").append(isUserOverTime);
        sb.append(", isGuardOverTime=").append(isGuardOverTime);
        sb.append(", failedReasonType=").append(failedReasonType);
        sb.append(", failedReason=").append(failedReason);
        sb.append(", addTime=").append(addTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", ID=").append(ID);
        sb.append(", ALERT_ID=").append(ALERT_ID);
        sb.append(", USER_RESPONSE_TIME=").append(USER_RESPONSE_TIME);
        sb.append(", USER_PROCESS_TIME=").append(USER_PROCESS_TIME);
        sb.append(", GUARD_RESPONSE_TIME=").append(GUARD_RESPONSE_TIME);
        sb.append(", GUARD_PROCESS_TIME=").append(GUARD_PROCESS_TIME);
        sb.append(", TOTAL_TIME=").append(TOTAL_TIME);
        sb.append(", FALSE_ALERTS=").append(FALSE_ALERTS);
        sb.append(", CONFIRM_ALERTS=").append(CONFIRM_ALERTS);
        sb.append(", UNPROCESSED_ALERTS=").append(UNPROCESSED_ALERTS);
        sb.append(", IS_CALL_BACK=").append(IS_CALL_BACK);
        sb.append(", IS_SECOND_CALL_BACK=").append(IS_SECOND_CALL_BACK);
        sb.append(", IS_ARRIVED=").append(IS_ARRIVED);
        sb.append(", IS_QUESTION_SUSPECT=").append(IS_QUESTION_SUSPECT);
        sb.append(", IS_QUESTION_SUSPECT_CONFIRMED=").append(IS_QUESTION_SUSPECT_CONFIRMED);
        sb.append(", IS_USER_OVER_TIME=").append(IS_USER_OVER_TIME);
        sb.append(", IS_GUARD_OVER_TIME=").append(IS_GUARD_OVER_TIME);
        sb.append(", FAILED_REASON_TYPE=").append(FAILED_REASON_TYPE);
        sb.append(", FAILED_REASON=").append(FAILED_REASON);
        sb.append(", ADD_TIME=").append(ADD_TIME);
        sb.append(", UPDATE_TIME=").append(UPDATE_TIME);
        sb.append(", COLUMNS=").append(COLUMNS);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        BaseAlertDetail other = (BaseAlertDetail) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getAlertId() == null ? other.getAlertId() == null : this.getAlertId().equals(other.getAlertId()))
            && (this.getUserResponseTime() == null ? other.getUserResponseTime() == null : this.getUserResponseTime().equals(other.getUserResponseTime()))
            && (this.getUserProcessTime() == null ? other.getUserProcessTime() == null : this.getUserProcessTime().equals(other.getUserProcessTime()))
            && (this.getGuardResponseTime() == null ? other.getGuardResponseTime() == null : this.getGuardResponseTime().equals(other.getGuardResponseTime()))
            && (this.getGuardProcessTime() == null ? other.getGuardProcessTime() == null : this.getGuardProcessTime().equals(other.getGuardProcessTime()))
            && (this.getTotalTime() == null ? other.getTotalTime() == null : this.getTotalTime().equals(other.getTotalTime()))
            && (this.getFalseAlerts() == null ? other.getFalseAlerts() == null : this.getFalseAlerts().equals(other.getFalseAlerts()))
            && (this.getConfirmAlerts() == null ? other.getConfirmAlerts() == null : this.getConfirmAlerts().equals(other.getConfirmAlerts()))
            && (this.getUnprocessedAlerts() == null ? other.getUnprocessedAlerts() == null : this.getUnprocessedAlerts().equals(other.getUnprocessedAlerts()))
            && (this.getIsCallBack() == null ? other.getIsCallBack() == null : this.getIsCallBack().equals(other.getIsCallBack()))
            && (this.getIsSecondCallBack() == null ? other.getIsSecondCallBack() == null : this.getIsSecondCallBack().equals(other.getIsSecondCallBack()))
            && (this.getIsArrived() == null ? other.getIsArrived() == null : this.getIsArrived().equals(other.getIsArrived()))
            && (this.getIsQuestionSuspect() == null ? other.getIsQuestionSuspect() == null : this.getIsQuestionSuspect().equals(other.getIsQuestionSuspect()))
            && (this.getIsQuestionSuspectConfirmed() == null ? other.getIsQuestionSuspectConfirmed() == null : this.getIsQuestionSuspectConfirmed().equals(other.getIsQuestionSuspectConfirmed()))
            && (this.getIsUserOverTime() == null ? other.getIsUserOverTime() == null : this.getIsUserOverTime().equals(other.getIsUserOverTime()))
            && (this.getIsGuardOverTime() == null ? other.getIsGuardOverTime() == null : this.getIsGuardOverTime().equals(other.getIsGuardOverTime()))
            && (this.getFailedReasonType() == null ? other.getFailedReasonType() == null : this.getFailedReasonType().equals(other.getFailedReasonType()))
            && (this.getFailedReason() == null ? other.getFailedReason() == null : this.getFailedReason().equals(other.getFailedReason()))
            && (this.getAddTime() == null ? other.getAddTime() == null : this.getAddTime().equals(other.getAddTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getAlertId() == null) ? 0 : getAlertId().hashCode());
        result = prime * result + ((getUserResponseTime() == null) ? 0 : getUserResponseTime().hashCode());
        result = prime * result + ((getUserProcessTime() == null) ? 0 : getUserProcessTime().hashCode());
        result = prime * result + ((getGuardResponseTime() == null) ? 0 : getGuardResponseTime().hashCode());
        result = prime * result + ((getGuardProcessTime() == null) ? 0 : getGuardProcessTime().hashCode());
        result = prime * result + ((getTotalTime() == null) ? 0 : getTotalTime().hashCode());
        result = prime * result + ((getFalseAlerts() == null) ? 0 : getFalseAlerts().hashCode());
        result = prime * result + ((getConfirmAlerts() == null) ? 0 : getConfirmAlerts().hashCode());
        result = prime * result + ((getUnprocessedAlerts() == null) ? 0 : getUnprocessedAlerts().hashCode());
        result = prime * result + ((getIsCallBack() == null) ? 0 : getIsCallBack().hashCode());
        result = prime * result + ((getIsSecondCallBack() == null) ? 0 : getIsSecondCallBack().hashCode());
        result = prime * result + ((getIsArrived() == null) ? 0 : getIsArrived().hashCode());
        result = prime * result + ((getIsQuestionSuspect() == null) ? 0 : getIsQuestionSuspect().hashCode());
        result = prime * result + ((getIsQuestionSuspectConfirmed() == null) ? 0 : getIsQuestionSuspectConfirmed().hashCode());
        result = prime * result + ((getIsUserOverTime() == null) ? 0 : getIsUserOverTime().hashCode());
        result = prime * result + ((getIsGuardOverTime() == null) ? 0 : getIsGuardOverTime().hashCode());
        result = prime * result + ((getFailedReasonType() == null) ? 0 : getFailedReasonType().hashCode());
        result = prime * result + ((getFailedReason() == null) ? 0 : getFailedReason().hashCode());
        result = prime * result + ((getAddTime() == null) ? 0 : getAddTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }
}