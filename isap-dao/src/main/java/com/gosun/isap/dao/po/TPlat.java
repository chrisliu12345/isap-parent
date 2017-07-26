package com.gosun.isap.dao.po;

import java.io.Serializable;

public class TPlat implements Serializable {
    private Long id;

    private String name;

    private Byte vendorType;

    private Byte accessType;

    private String accessIpAddress;

    private Integer accessPort;

    private String loginUser;

    private String loginPasswd;

    private Byte status;

    private String description;

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

    public Byte getVendorType() {
        return vendorType;
    }

    public void setVendorType(Byte vendorType) {
        this.vendorType = vendorType;
    }

    public Byte getAccessType() {
        return accessType;
    }

    public void setAccessType(Byte accessType) {
        this.accessType = accessType;
    }

    public String getAccessIpAddress() {
        return accessIpAddress;
    }

    public void setAccessIpAddress(String accessIpAddress) {
        this.accessIpAddress = accessIpAddress == null ? null : accessIpAddress.trim();
    }

    public Integer getAccessPort() {
        return accessPort;
    }

    public void setAccessPort(Integer accessPort) {
        this.accessPort = accessPort;
    }

    public String getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(String loginUser) {
        this.loginUser = loginUser == null ? null : loginUser.trim();
    }

    public String getLoginPasswd() {
        return loginPasswd;
    }

    public void setLoginPasswd(String loginPasswd) {
        this.loginPasswd = loginPasswd == null ? null : loginPasswd.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}