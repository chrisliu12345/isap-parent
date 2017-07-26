package com.gosun.isap.dao.po;

import java.io.Serializable;
import java.util.Date;

public class TUser implements Serializable {
    private Long id;

    private String name;

    private String loginName;

    private String passwd;

    private String mobile;

    private String email;

    private Byte priority;

    private Byte status;

    private Date lockStartTime;

    private Date lockEndTime;

    private static final long serialVersionUID = 1L;

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

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd == null ? null : passwd.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Byte getPriority() {
        return priority;
    }

    public void setPriority(Byte priority) {
        this.priority = priority;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getLockStartTime() {
        return lockStartTime;
    }

    public void setLockStartTime(Date lockStartTime) {
        this.lockStartTime = lockStartTime;
    }

    public Date getLockEndTime() {
        return lockEndTime;
    }

    public void setLockEndTime(Date lockEndTime) {
        this.lockEndTime = lockEndTime;
    }
}