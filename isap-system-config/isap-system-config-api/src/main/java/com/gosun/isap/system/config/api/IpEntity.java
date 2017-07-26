package com.gosun.isap.system.config.api;


import org.hibernate.validator.constraints.NotEmpty;

public class IpEntity {
    private String ip;
    private String mask;
    private String gateway;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotEmpty(message = "IP地址不能为空")
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @NotEmpty(message = "IP地址不能为空")
    public String getMask() {
        return mask;
    }

    public void setMask(String mask) {
        this.mask = mask;
    }

    @NotEmpty(message = "网关不能为空+")
    public String getGateway() {
        return gateway;
    }

    public void setGateway(String gateway) {
        this.gateway = gateway;
    }
}
