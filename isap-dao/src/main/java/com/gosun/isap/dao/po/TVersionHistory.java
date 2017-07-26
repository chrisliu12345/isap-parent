package com.gosun.isap.dao.po;

import java.io.Serializable;
import java.util.Date;

public class TVersionHistory implements Serializable {
    private Long id;

    private Byte installFlag;

    private String fromInnerVersion;

    private String toInnerVersion;

    private Date upateTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Byte getInstallFlag() {
        return installFlag;
    }

    public void setInstallFlag(Byte installFlag) {
        this.installFlag = installFlag;
    }

    public String getFromInnerVersion() {
        return fromInnerVersion;
    }

    public void setFromInnerVersion(String fromInnerVersion) {
        this.fromInnerVersion = fromInnerVersion == null ? null : fromInnerVersion.trim();
    }

    public String getToInnerVersion() {
        return toInnerVersion;
    }

    public void setToInnerVersion(String toInnerVersion) {
        this.toInnerVersion = toInnerVersion == null ? null : toInnerVersion.trim();
    }

    public Date getUpateTime() {
        return upateTime;
    }

    public void setUpateTime(Date upateTime) {
        this.upateTime = upateTime;
    }
}