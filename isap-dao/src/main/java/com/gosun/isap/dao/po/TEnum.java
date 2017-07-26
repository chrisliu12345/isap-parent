package com.gosun.isap.dao.po;

import java.io.Serializable;

public class TEnum implements Serializable {
    private String enumKey;

    private Integer enumValue;

    private String label;

    private String description;

    private static final long serialVersionUID = 1L;

    public String getEnumKey() {
        return enumKey;
    }

    public void setEnumKey(String enumKey) {
        this.enumKey = enumKey == null ? null : enumKey.trim();
    }

    public Integer getEnumValue() {
        return enumValue;
    }

    public void setEnumValue(Integer enumValue) {
        this.enumValue = enumValue;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label == null ? null : label.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}