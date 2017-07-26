package com.gosun.isap.dao.po;

import java.io.Serializable;

public class TActionParam implements Serializable {
    private Long id;

    private String name;

    private String paramValue;

    private Byte paramSequence;

    private Long actionId;

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

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue == null ? null : paramValue.trim();
    }

    public Byte getParamSequence() {
        return paramSequence;
    }

    public void setParamSequence(Byte paramSequence) {
        this.paramSequence = paramSequence;
    }

    public Long getActionId() {
        return actionId;
    }

    public void setActionId(Long actionId) {
        this.actionId = actionId;
    }
}