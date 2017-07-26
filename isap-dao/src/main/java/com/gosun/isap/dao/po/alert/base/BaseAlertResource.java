package com.gosun.isap.dao.po.alert.base;

import java.util.Date;

public class BaseAlertResource {
    private Long id;

    private Long alertId;

    private Byte resourceType;

    private Byte resourceIndex;

    private String path;

    private String url;

    private String contentType;

    private Date addTime;

    public static final String ID = "id";

    public static final String ALERT_ID = "alert_id";

    public static final String RESOURCE_TYPE = "resource_type";

    public static final String RESOURCE_INDEX = "resource_index";

    public static final String PATH = "path";

    public static final String URL = "url";

    public static final String CONTENT_TYPE = "content_type";

    public static final String ADD_TIME = "add_time";

    public static final String[] COLUMNS = {ID,ALERT_ID,RESOURCE_TYPE,RESOURCE_INDEX,PATH,URL,CONTENT_TYPE,ADD_TIME};

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

    public Byte getResourceType() {
        return resourceType;
    }

    public void setResourceType(Byte resourceType) {
        this.resourceType = resourceType;
    }

    public Byte getResourceIndex() {
        return resourceIndex;
    }

    public void setResourceIndex(Byte resourceIndex) {
        this.resourceIndex = resourceIndex;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType == null ? null : contentType.trim();
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
        sb.append(", resourceType=").append(resourceType);
        sb.append(", resourceIndex=").append(resourceIndex);
        sb.append(", path=").append(path);
        sb.append(", url=").append(url);
        sb.append(", contentType=").append(contentType);
        sb.append(", addTime=").append(addTime);
        sb.append(", ID=").append(ID);
        sb.append(", ALERT_ID=").append(ALERT_ID);
        sb.append(", RESOURCE_TYPE=").append(RESOURCE_TYPE);
        sb.append(", RESOURCE_INDEX=").append(RESOURCE_INDEX);
        sb.append(", PATH=").append(PATH);
        sb.append(", URL=").append(URL);
        sb.append(", CONTENT_TYPE=").append(CONTENT_TYPE);
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
        BaseAlertResource other = (BaseAlertResource) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getAlertId() == null ? other.getAlertId() == null : this.getAlertId().equals(other.getAlertId()))
            && (this.getResourceType() == null ? other.getResourceType() == null : this.getResourceType().equals(other.getResourceType()))
            && (this.getResourceIndex() == null ? other.getResourceIndex() == null : this.getResourceIndex().equals(other.getResourceIndex()))
            && (this.getPath() == null ? other.getPath() == null : this.getPath().equals(other.getPath()))
            && (this.getUrl() == null ? other.getUrl() == null : this.getUrl().equals(other.getUrl()))
            && (this.getContentType() == null ? other.getContentType() == null : this.getContentType().equals(other.getContentType()))
            && (this.getAddTime() == null ? other.getAddTime() == null : this.getAddTime().equals(other.getAddTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getAlertId() == null) ? 0 : getAlertId().hashCode());
        result = prime * result + ((getResourceType() == null) ? 0 : getResourceType().hashCode());
        result = prime * result + ((getResourceIndex() == null) ? 0 : getResourceIndex().hashCode());
        result = prime * result + ((getPath() == null) ? 0 : getPath().hashCode());
        result = prime * result + ((getUrl() == null) ? 0 : getUrl().hashCode());
        result = prime * result + ((getContentType() == null) ? 0 : getContentType().hashCode());
        result = prime * result + ((getAddTime() == null) ? 0 : getAddTime().hashCode());
        return result;
    }
}