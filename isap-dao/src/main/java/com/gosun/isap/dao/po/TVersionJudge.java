package com.gosun.isap.dao.po;

import java.io.Serializable;

public class TVersionJudge implements Serializable {
    private Long id;

    private String srcVersion;

    private String currVersion;

    private String targetVersion;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSrcVersion() {
        return srcVersion;
    }

    public void setSrcVersion(String srcVersion) {
        this.srcVersion = srcVersion == null ? null : srcVersion.trim();
    }

    public String getCurrVersion() {
        return currVersion;
    }

    public void setCurrVersion(String currVersion) {
        this.currVersion = currVersion == null ? null : currVersion.trim();
    }

    public String getTargetVersion() {
        return targetVersion;
    }

    public void setTargetVersion(String targetVersion) {
        this.targetVersion = targetVersion == null ? null : targetVersion.trim();
    }
}