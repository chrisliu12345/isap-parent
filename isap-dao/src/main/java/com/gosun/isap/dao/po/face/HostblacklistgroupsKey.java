package com.gosun.isap.dao.po.face;

import java.io.Serializable;

public class HostblacklistgroupsKey implements Serializable {
    private Integer hostid;

    private Integer blacklistgroupid;

    private static final long serialVersionUID = 1L;

    public Integer getHostid() {
        return hostid;
    }

    public void setHostid(Integer hostid) {
        this.hostid = hostid;
    }

    public Integer getBlacklistgroupid() {
        return blacklistgroupid;
    }

    public void setBlacklistgroupid(Integer blacklistgroupid) {
        this.blacklistgroupid = blacklistgroupid;
    }
}