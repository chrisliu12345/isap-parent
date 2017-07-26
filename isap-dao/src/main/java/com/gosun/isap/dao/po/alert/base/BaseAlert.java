package com.gosun.isap.dao.po.alert.base;

import java.util.Date;

public class BaseAlert {
    private Long id;

    private String description;

    private Byte reason;

    private String devId;

    private String departmentId;

    private Byte status;

    private Boolean isOverTime;

    private Byte alertType;

    private Integer unprocessedAlerts;

    private Date addTime;

    private Date confirmStartTime;

    private Date confirmEndTime;

    private Integer confirmTime;

    private Integer confirmedAlerts;

    private Long userId;

    private String confirmDesc;

    private Date finishTime;

    public static final String ID = "id";

    public static final String DESCRIPTION = "description";

    public static final String REASON = "reason";

    public static final String DEV_ID = "dev_id";

    public static final String DEPARTMENT_ID = "department_id";

    public static final String STATUS = "status";

    public static final String IS_OVER_TIME = "is_over_time";

    public static final String ALERT_TYPE = "alert_type";

    public static final String UNPROCESSED_ALERTS = "unprocessed_alerts";

    public static final String ADD_TIME = "add_time";

    public static final String CONFIRM_START_TIME = "confirm_start_time";

    public static final String CONFIRM_END_TIME = "confirm_end_time";

    public static final String CONFIRM_TIME = "confirm_time";

    public static final String CONFIRMED_ALERTS = "confirmed_alerts";

    public static final String USER_ID = "user_id";

    public static final String CONFIRM_DESC = "confirm_desc";

    public static final String FINISH_TIME = "finish_time";

    public static final String[] COLUMNS = {ID,DESCRIPTION,REASON,DEV_ID,DEPARTMENT_ID,STATUS,IS_OVER_TIME,ALERT_TYPE,UNPROCESSED_ALERTS,ADD_TIME,CONFIRM_START_TIME,CONFIRM_END_TIME,CONFIRM_TIME,CONFIRMED_ALERTS,USER_ID,CONFIRM_DESC,FINISH_TIME};

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

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
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
        sb.append(", description=").append(description);
        sb.append(", reason=").append(reason);
        sb.append(", devId=").append(devId);
        sb.append(", departmentId=").append(departmentId);
        sb.append(", status=").append(status);
        sb.append(", isOverTime=").append(isOverTime);
        sb.append(", alertType=").append(alertType);
        sb.append(", unprocessedAlerts=").append(unprocessedAlerts);
        sb.append(", addTime=").append(addTime);
        sb.append(", confirmStartTime=").append(confirmStartTime);
        sb.append(", confirmEndTime=").append(confirmEndTime);
        sb.append(", confirmTime=").append(confirmTime);
        sb.append(", confirmedAlerts=").append(confirmedAlerts);
        sb.append(", userId=").append(userId);
        sb.append(", confirmDesc=").append(confirmDesc);
        sb.append(", finishTime=").append(finishTime);
        sb.append(", ID=").append(ID);
        sb.append(", DESCRIPTION=").append(DESCRIPTION);
        sb.append(", REASON=").append(REASON);
        sb.append(", DEV_ID=").append(DEV_ID);
        sb.append(", DEPARTMENT_ID=").append(DEPARTMENT_ID);
        sb.append(", STATUS=").append(STATUS);
        sb.append(", IS_OVER_TIME=").append(IS_OVER_TIME);
        sb.append(", ALERT_TYPE=").append(ALERT_TYPE);
        sb.append(", UNPROCESSED_ALERTS=").append(UNPROCESSED_ALERTS);
        sb.append(", ADD_TIME=").append(ADD_TIME);
        sb.append(", CONFIRM_START_TIME=").append(CONFIRM_START_TIME);
        sb.append(", CONFIRM_END_TIME=").append(CONFIRM_END_TIME);
        sb.append(", CONFIRM_TIME=").append(CONFIRM_TIME);
        sb.append(", CONFIRMED_ALERTS=").append(CONFIRMED_ALERTS);
        sb.append(", USER_ID=").append(USER_ID);
        sb.append(", CONFIRM_DESC=").append(CONFIRM_DESC);
        sb.append(", FINISH_TIME=").append(FINISH_TIME);
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
        BaseAlert other = (BaseAlert) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()))
            && (this.getReason() == null ? other.getReason() == null : this.getReason().equals(other.getReason()))
            && (this.getDevId() == null ? other.getDevId() == null : this.getDevId().equals(other.getDevId()))
            && (this.getDepartmentId() == null ? other.getDepartmentId() == null : this.getDepartmentId().equals(other.getDepartmentId()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getIsOverTime() == null ? other.getIsOverTime() == null : this.getIsOverTime().equals(other.getIsOverTime()))
            && (this.getAlertType() == null ? other.getAlertType() == null : this.getAlertType().equals(other.getAlertType()))
            && (this.getUnprocessedAlerts() == null ? other.getUnprocessedAlerts() == null : this.getUnprocessedAlerts().equals(other.getUnprocessedAlerts()))
            && (this.getAddTime() == null ? other.getAddTime() == null : this.getAddTime().equals(other.getAddTime()))
            && (this.getConfirmStartTime() == null ? other.getConfirmStartTime() == null : this.getConfirmStartTime().equals(other.getConfirmStartTime()))
            && (this.getConfirmEndTime() == null ? other.getConfirmEndTime() == null : this.getConfirmEndTime().equals(other.getConfirmEndTime()))
            && (this.getConfirmTime() == null ? other.getConfirmTime() == null : this.getConfirmTime().equals(other.getConfirmTime()))
            && (this.getConfirmedAlerts() == null ? other.getConfirmedAlerts() == null : this.getConfirmedAlerts().equals(other.getConfirmedAlerts()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getConfirmDesc() == null ? other.getConfirmDesc() == null : this.getConfirmDesc().equals(other.getConfirmDesc()))
            && (this.getFinishTime() == null ? other.getFinishTime() == null : this.getFinishTime().equals(other.getFinishTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        result = prime * result + ((getReason() == null) ? 0 : getReason().hashCode());
        result = prime * result + ((getDevId() == null) ? 0 : getDevId().hashCode());
        result = prime * result + ((getDepartmentId() == null) ? 0 : getDepartmentId().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getIsOverTime() == null) ? 0 : getIsOverTime().hashCode());
        result = prime * result + ((getAlertType() == null) ? 0 : getAlertType().hashCode());
        result = prime * result + ((getUnprocessedAlerts() == null) ? 0 : getUnprocessedAlerts().hashCode());
        result = prime * result + ((getAddTime() == null) ? 0 : getAddTime().hashCode());
        result = prime * result + ((getConfirmStartTime() == null) ? 0 : getConfirmStartTime().hashCode());
        result = prime * result + ((getConfirmEndTime() == null) ? 0 : getConfirmEndTime().hashCode());
        result = prime * result + ((getConfirmTime() == null) ? 0 : getConfirmTime().hashCode());
        result = prime * result + ((getConfirmedAlerts() == null) ? 0 : getConfirmedAlerts().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getConfirmDesc() == null) ? 0 : getConfirmDesc().hashCode());
        result = prime * result + ((getFinishTime() == null) ? 0 : getFinishTime().hashCode());
        return result;
    }
}