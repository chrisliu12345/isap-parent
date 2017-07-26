package com.gosun.isap.dao.po.alert.base;

import java.util.Date;

public class BaseAlertTimeLimit {
    private Long id;

    private String devId;

    private Integer arriveTime;

    private Date addTime;

    private Date updateTime;

    public static final String ID = "id";

    public static final String DEV_ID = "dev_id";

    public static final String ARRIVE_TIME = "arrive_time";

    public static final String ADD_TIME = "add_time";

    public static final String UPDATE_TIME = "update_time";

    public static final String[] COLUMNS = {ID,DEV_ID,ARRIVE_TIME,ADD_TIME,UPDATE_TIME};

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
        sb.append(", devId=").append(devId);
        sb.append(", arriveTime=").append(arriveTime);
        sb.append(", addTime=").append(addTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", ID=").append(ID);
        sb.append(", DEV_ID=").append(DEV_ID);
        sb.append(", ARRIVE_TIME=").append(ARRIVE_TIME);
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
        BaseAlertTimeLimit other = (BaseAlertTimeLimit) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getDevId() == null ? other.getDevId() == null : this.getDevId().equals(other.getDevId()))
            && (this.getArriveTime() == null ? other.getArriveTime() == null : this.getArriveTime().equals(other.getArriveTime()))
            && (this.getAddTime() == null ? other.getAddTime() == null : this.getAddTime().equals(other.getAddTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getDevId() == null) ? 0 : getDevId().hashCode());
        result = prime * result + ((getArriveTime() == null) ? 0 : getArriveTime().hashCode());
        result = prime * result + ((getAddTime() == null) ? 0 : getAddTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }
}