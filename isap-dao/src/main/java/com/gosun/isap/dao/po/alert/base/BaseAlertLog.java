package com.gosun.isap.dao.po.alert.base;

import java.util.Date;

public class BaseAlertLog {
    private Long id;

    private Long alertId;

    private Long userId;

    private Long referenceId;

    private Byte logType;

    private String content;

    private String reason;

    private Date addTime;

    public static final String ID = "id";

    public static final String ALERT_ID = "alert_id";

    public static final String USER_ID = "user_id";

    public static final String REFERENCE_ID = "reference_id";

    public static final String LOG_TYPE = "log_type";

    public static final String CONTENT = "content";

    public static final String REASON = "reason";

    public static final String ADD_TIME = "add_time";

    public static final String[] COLUMNS = {ID,ALERT_ID,USER_ID,REFERENCE_ID,LOG_TYPE,CONTENT,REASON,ADD_TIME};

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(Long referenceId) {
        this.referenceId = referenceId;
    }

    public Byte getLogType() {
        return logType;
    }

    public void setLogType(Byte logType) {
        this.logType = logType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
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
        sb.append(", userId=").append(userId);
        sb.append(", referenceId=").append(referenceId);
        sb.append(", logType=").append(logType);
        sb.append(", content=").append(content);
        sb.append(", reason=").append(reason);
        sb.append(", addTime=").append(addTime);
        sb.append(", ID=").append(ID);
        sb.append(", ALERT_ID=").append(ALERT_ID);
        sb.append(", USER_ID=").append(USER_ID);
        sb.append(", REFERENCE_ID=").append(REFERENCE_ID);
        sb.append(", LOG_TYPE=").append(LOG_TYPE);
        sb.append(", CONTENT=").append(CONTENT);
        sb.append(", REASON=").append(REASON);
        sb.append(", ADD_TIME=").append(ADD_TIME);
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
        BaseAlertLog other = (BaseAlertLog) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getAlertId() == null ? other.getAlertId() == null : this.getAlertId().equals(other.getAlertId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getReferenceId() == null ? other.getReferenceId() == null : this.getReferenceId().equals(other.getReferenceId()))
            && (this.getLogType() == null ? other.getLogType() == null : this.getLogType().equals(other.getLogType()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()))
            && (this.getReason() == null ? other.getReason() == null : this.getReason().equals(other.getReason()))
            && (this.getAddTime() == null ? other.getAddTime() == null : this.getAddTime().equals(other.getAddTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getAlertId() == null) ? 0 : getAlertId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getReferenceId() == null) ? 0 : getReferenceId().hashCode());
        result = prime * result + ((getLogType() == null) ? 0 : getLogType().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        result = prime * result + ((getReason() == null) ? 0 : getReason().hashCode());
        result = prime * result + ((getAddTime() == null) ? 0 : getAddTime().hashCode());
        return result;
    }
}