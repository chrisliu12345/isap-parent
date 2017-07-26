package com.gosun.isap.dao.po.alert.base;

import java.util.Date;

public class BaseAlertLogTemplate {
    private Integer id;

    private Byte logType;

    private String content;

    private Byte paramsCount;

    private Date updateTime;

    public static final String ID = "id";

    public static final String LOG_TYPE = "log_type";

    public static final String CONTENT = "content";

    public static final String PARAMS_COUNT = "params_count";

    public static final String UPDATE_TIME = "update_time";

    public static final String[] COLUMNS = {ID,LOG_TYPE,CONTENT,PARAMS_COUNT,UPDATE_TIME};

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Byte getParamsCount() {
        return paramsCount;
    }

    public void setParamsCount(Byte paramsCount) {
        this.paramsCount = paramsCount;
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
        sb.append(", logType=").append(logType);
        sb.append(", content=").append(content);
        sb.append(", paramsCount=").append(paramsCount);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", ID=").append(ID);
        sb.append(", LOG_TYPE=").append(LOG_TYPE);
        sb.append(", CONTENT=").append(CONTENT);
        sb.append(", PARAMS_COUNT=").append(PARAMS_COUNT);
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
        BaseAlertLogTemplate other = (BaseAlertLogTemplate) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getLogType() == null ? other.getLogType() == null : this.getLogType().equals(other.getLogType()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()))
            && (this.getParamsCount() == null ? other.getParamsCount() == null : this.getParamsCount().equals(other.getParamsCount()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getLogType() == null) ? 0 : getLogType().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        result = prime * result + ((getParamsCount() == null) ? 0 : getParamsCount().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }
}