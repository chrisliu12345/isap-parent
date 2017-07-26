package com.gosun.isap.dao.po.face;

import java.io.Serializable;

public class GroupblacklistsKey implements Serializable {
    private Integer blacklistgroupid;

    private Integer blacklistid;

    private static final long serialVersionUID = 1L;

    public Integer getBlacklistgroupid() {
        return blacklistgroupid;
    }

    public void setBlacklistgroupid(Integer blacklistgroupid) {
        this.blacklistgroupid = blacklistgroupid;
    }

    public Integer getBlacklistid() {
        return blacklistid;
    }

    public void setBlacklistid(Integer blacklistid) {
        this.blacklistid = blacklistid;
    }
}