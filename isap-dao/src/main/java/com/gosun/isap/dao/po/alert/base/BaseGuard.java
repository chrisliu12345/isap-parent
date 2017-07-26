package com.gosun.isap.dao.po.alert.base;

import java.util.Date;

public class BaseGuard {
    private Long id;

    private String name;

    private String phone;

    private String guardDesc;

    private Long groupId;

    private String cid;

    private String homeAddress;

    private Date birthday;

    private Long userId;

    private Date addTime;

    private Date updateTime;

    public static final String ID = "id";

    public static final String NAME = "name";

    public static final String PHONE = "phone";

    public static final String GUARD_DESC = "guard_desc";

    public static final String GROUP_ID = "group_id";

    public static final String CID = "cid";

    public static final String HOME_ADDRESS = "home_address";

    public static final String BIRTHDAY = "birthday";

    public static final String USER_ID = "user_id";

    public static final String ADD_TIME = "add_time";

    public static final String UPDATE_TIME = "update_time";

    public static final String[] COLUMNS = {ID,NAME,PHONE,GUARD_DESC,GROUP_ID,CID,HOME_ADDRESS,BIRTHDAY,USER_ID,ADD_TIME,UPDATE_TIME};

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getGuardDesc() {
        return guardDesc;
    }

    public void setGuardDesc(String guardDesc) {
        this.guardDesc = guardDesc == null ? null : guardDesc.trim();
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid == null ? null : cid.trim();
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress == null ? null : homeAddress.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
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
        sb.append(", name=").append(name);
        sb.append(", phone=").append(phone);
        sb.append(", guardDesc=").append(guardDesc);
        sb.append(", groupId=").append(groupId);
        sb.append(", cid=").append(cid);
        sb.append(", homeAddress=").append(homeAddress);
        sb.append(", birthday=").append(birthday);
        sb.append(", userId=").append(userId);
        sb.append(", addTime=").append(addTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", ID=").append(ID);
        sb.append(", NAME=").append(NAME);
        sb.append(", PHONE=").append(PHONE);
        sb.append(", GUARD_DESC=").append(GUARD_DESC);
        sb.append(", GROUP_ID=").append(GROUP_ID);
        sb.append(", CID=").append(CID);
        sb.append(", HOME_ADDRESS=").append(HOME_ADDRESS);
        sb.append(", BIRTHDAY=").append(BIRTHDAY);
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
        BaseGuard other = (BaseGuard) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getPhone() == null ? other.getPhone() == null : this.getPhone().equals(other.getPhone()))
            && (this.getGuardDesc() == null ? other.getGuardDesc() == null : this.getGuardDesc().equals(other.getGuardDesc()))
            && (this.getGroupId() == null ? other.getGroupId() == null : this.getGroupId().equals(other.getGroupId()))
            && (this.getCid() == null ? other.getCid() == null : this.getCid().equals(other.getCid()))
            && (this.getHomeAddress() == null ? other.getHomeAddress() == null : this.getHomeAddress().equals(other.getHomeAddress()))
            && (this.getBirthday() == null ? other.getBirthday() == null : this.getBirthday().equals(other.getBirthday()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getAddTime() == null ? other.getAddTime() == null : this.getAddTime().equals(other.getAddTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getPhone() == null) ? 0 : getPhone().hashCode());
        result = prime * result + ((getGuardDesc() == null) ? 0 : getGuardDesc().hashCode());
        result = prime * result + ((getGroupId() == null) ? 0 : getGroupId().hashCode());
        result = prime * result + ((getCid() == null) ? 0 : getCid().hashCode());
        result = prime * result + ((getHomeAddress() == null) ? 0 : getHomeAddress().hashCode());
        result = prime * result + ((getBirthday() == null) ? 0 : getBirthday().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getAddTime() == null) ? 0 : getAddTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }
}