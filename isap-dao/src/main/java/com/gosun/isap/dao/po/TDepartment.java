package com.gosun.isap.dao.po;

import java.io.Serializable;

public class TDepartment implements Serializable {
    private String id;

    private String name;

    private String parent;

    private Byte communityFlag;

    private String parentIds;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent == null ? null : parent.trim();
    }

    public Byte getCommunityFlag() {
        return communityFlag;
    }

    public void setCommunityFlag(Byte communityFlag) {
        this.communityFlag = communityFlag;
    }

    public String getParentIds() {
        return parentIds;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds == null ? null : parentIds.trim();
    }
}