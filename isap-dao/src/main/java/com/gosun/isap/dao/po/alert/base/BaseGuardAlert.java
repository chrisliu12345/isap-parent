package com.gosun.isap.dao.po.alert.base;

import java.util.Date;

public class BaseGuardAlert {
    private Long id;

    private Long guardId;

    private Long alertId;

    private Date addTime;

    public static final String ID = "id";

    public static final String GUARD_ID = "guard_id";

    public static final String ALERT_ID = "alert_id";

    public static final String ADD_TIME = "add_time";

    public static final String[] COLUMNS = {ID,GUARD_ID,ALERT_ID,ADD_TIME};

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGuardId() {
        return guardId;
    }

    public void setGuardId(Long guardId) {
        this.guardId = guardId;
    }

    public Long getAlertId() {
        return alertId;
    }

    public void setAlertId(Long alertId) {
        this.alertId = alertId;
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
        sb.append(", guardId=").append(guardId);
        sb.append(", alertId=").append(alertId);
        sb.append(", addTime=").append(addTime);
        sb.append(", ID=").append(ID);
        sb.append(", GUARD_ID=").append(GUARD_ID);
        sb.append(", ALERT_ID=").append(ALERT_ID);
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
        BaseGuardAlert other = (BaseGuardAlert) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getGuardId() == null ? other.getGuardId() == null : this.getGuardId().equals(other.getGuardId()))
            && (this.getAlertId() == null ? other.getAlertId() == null : this.getAlertId().equals(other.getAlertId()))
            && (this.getAddTime() == null ? other.getAddTime() == null : this.getAddTime().equals(other.getAddTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getGuardId() == null) ? 0 : getGuardId().hashCode());
        result = prime * result + ((getAlertId() == null) ? 0 : getAlertId().hashCode());
        result = prime * result + ((getAddTime() == null) ? 0 : getAddTime().hashCode());
        return result;
    }
}