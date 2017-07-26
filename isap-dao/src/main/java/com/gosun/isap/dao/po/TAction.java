package com.gosun.isap.dao.po;

import java.io.Serializable;

public class TAction implements Serializable {
    private Long id;

    private String name;

    private Byte paramNum;

    private Long linkageId;

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

    public Byte getParamNum() {
        return paramNum;
    }

    public void setParamNum(Byte paramNum) {
        this.paramNum = paramNum;
    }

    public Long getLinkageId() {
        return linkageId;
    }

    public void setLinkageId(Long linkageId) {
        this.linkageId = linkageId;
    }
}