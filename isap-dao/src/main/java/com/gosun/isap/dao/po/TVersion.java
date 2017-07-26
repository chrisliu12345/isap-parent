package com.gosun.isap.dao.po;

import java.io.Serializable;

public class TVersion implements Serializable {
    private Long id;

    private String versionType;

    private String innerVersion;

    private String outerVersion;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVersionType() {
        return versionType;
    }

    public void setVersionType(String versionType) {
        this.versionType = versionType == null ? null : versionType.trim();
    }

    public String getInnerVersion() {
        return innerVersion;
    }

    public void setInnerVersion(String innerVersion) {
        this.innerVersion = innerVersion == null ? null : innerVersion.trim();
    }

    public String getOuterVersion() {
        return outerVersion;
    }

    public void setOuterVersion(String outerVersion) {
        this.outerVersion = outerVersion == null ? null : outerVersion.trim();
    }
}