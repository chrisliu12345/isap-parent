package com.gosun.isap.warn.impl.alert.export.setting;

/**
 * 导出设置中的 列
 * <p>创建时间：2017-5-23 10:24</p>
 *
 * @author 娄存银
 * @version 1.0
 */
public class ExportColumn {
    private String key;
    private String dateFormat;
    private Integer column;
    private boolean picture;
    private Integer width;
    private Integer height;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getColumn() {
        return column;
    }

    public void setColumn(Integer column) {
        this.column = column;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public boolean isPicture() {
        return picture;
    }

    public void setPicture(boolean picture) {
        this.picture = picture;
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }
}
