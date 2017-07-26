package com.gosun.isap.dao.po.alert.base;

import java.util.Date;

public class BaseGuardGroupBelong {
    private Long id;

    private Long groupId;

    private String departmentId;

    private String belongDesc;

    private Long userId;

    private Date addTime;

    private Date updateTime;

    public static final String ID = "id";

    public static final String GROUP_ID = "group_id";

    public static final String DEPARTMENT_ID = "department_id";

    public static final String BELONG_DESC = "belong_desc";

    public static final String USER_ID = "user_id";

    public static final String ADD_TIME = "add_time";

    public static final String UPDATE_TIME = "update_time";

    public static final String[] COLUMNS = {ID,GROUP_ID,DEPARTMENT_ID,BELONG_DESC,USER_ID,ADD_TIME,UPDATE_TIME};

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

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
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
        sb.append(", groupId=").append(groupId);
        sb.append(", departmentId=").append(departmentId);
        sb.append(", belongDesc=").append(belongDesc);
        sb.append(", userId=").append(userId);
        sb.append(", addTime=").append(addTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", ID=").append(ID);
        sb.append(", GROUP_ID=").append(GROUP_ID);
        sb.append(", DEPARTMENT_ID=").append(DEPARTMENT_ID);
        sb.append(", BELONG_DESC=").append(BELONG_DESC);
        sb.append(", USER_ID=").append(USER_ID);
        sb.append(", ADD_TIME=").append(ADD_TIME);
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
        BaseGuardGroupBelong other = (BaseGuardGroupBelong) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getGroupId() == null ? other.getGroupId() == null : this.getGroupId().equals(other.getGroupId()))
            && (this.getDepartmentId() == null ? other.getDepartmentId() == null : this.getDepartmentId().equals(other.getDepartmentId()))
            && (this.getBelongDesc() == null ? other.getBelongDesc() == null : this.getBelongDesc().equals(other.getBelongDesc()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getAddTime() == null ? other.getAddTime() == null : this.getAddTime().equals(other.getAddTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getGroupId() == null) ? 0 : getGroupId().hashCode());
        result = prime * result + ((getDepartmentId() == null) ? 0 : getDepartmentId().hashCode());
        result = prime * result + ((getBelongDesc() == null) ? 0 : getBelongDesc().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getAddTime() == null) ? 0 : getAddTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }
}