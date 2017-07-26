package com.gosun.isap.system.config.api;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import javax.swing.*;


public class ResponseData {
    private String ip;
    private String mask;
    private String gateway;


    @JsonSerialize(include =
    JsonSerialize.Inclusion.NON_NULL)
    public String getIp(){
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
    @JsonSerialize(include =
    JsonSerialize.Inclusion.NON_NULL)
    public String getGateway() {
        return gateway;
    }

    public void setGateway(String gateway) {
        this.gateway = gateway;
    }

    @JsonSerialize(include =
    JsonSerialize.Inclusion.NON_NULL)
    public String getMask() {
        return mask;
    }

    public void setMask(String mask) {
        this.mask = mask;
    }

}
