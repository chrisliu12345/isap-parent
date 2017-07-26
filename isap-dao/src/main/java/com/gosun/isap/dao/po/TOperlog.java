package com.gosun.isap.dao.po;

import java.io.Serializable;
import java.util.Date;

public class TOperlog implements Serializable {
    private Long id;

    private String operName;

    private Date operTime;

    private String ipAddress;

    private Byte serviceType;

    private Byte operType;

    private Boolean operResult;

    private String description;

    private String failureCause;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOperName() {
        return operName;
    }

    public void setOperName(String operName) {
        this.operName = operName == null ? null : operName.trim();
    }

    public Date getOperTime() {
        return operTime;
    }

    public void setOperTime(Date operTime) {
        this.operTime = operTime;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress == null ? null : ipAddress.trim();
    }

    public Byte getServiceType() {
        return serviceType;
    }

    public void setServiceType(Byte serviceType) {
        this.serviceType = serviceType;
    }

    public Byte getOperType() {
        return operType;
    }

    public void setOperType(Byte operType) {
        this.operType = operType;
    }

    public Boolean getOperResult() {
        return operResult;
    }

    public void setOperResult(Boolean operResult) {
        this.operResult = operResult;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getFailureCause() {
        return failureCause;
    }

    public void setFailureCause(String failureCause) {
        this.failureCause = failureCause == null ? null : failureCause.trim();
    }
}