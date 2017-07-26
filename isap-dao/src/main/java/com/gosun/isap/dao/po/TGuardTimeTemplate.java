package com.gosun.isap.dao.po;

import java.io.Serializable;

public class TGuardTimeTemplate implements Serializable {
    private Long id;

    private String name;

    private Byte templateType;

    private String description;

    private Integer refCount;

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

    public Byte getTemplateType() {
        return templateType;
    }

    public void setTemplateType(Byte templateType) {
        this.templateType = templateType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getRefCount() {
        return refCount;
    }

    public void setRefCount(Integer refCount) {
        this.refCount = refCount;
    }
}