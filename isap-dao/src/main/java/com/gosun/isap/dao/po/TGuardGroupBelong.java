package com.gosun.isap.dao.po;

import java.io.Serializable;

public class TGuardGroupBelong implements Serializable {
    private Long id;

    private Long groupId;

    private String departmentId;

    private String belongDesc;

    private Long userId;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId == null ? null : departmentId.trim();
    }

    public String getBelongDesc() {
        return belongDesc;
    }

    public void setBelongDesc(String belongDesc) {
        this.belongDesc = belongDesc == null ? null : belongDesc.trim();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}