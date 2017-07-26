package com.gosun.isap.warn.impl.alert.export.setting;

/**
 * <p>创建时间：2017-5-23 11:05</p>
 *
 * @author 娄存银
 * @version 1.0
 */
public final class ExportStyle {
    private String dateFormat;

    private ExportStyle() {
    }

    private ExportStyle(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    /**
     * 获取一个 ExportStyle 实例
     *
     * @param dateFormatter 日期格式
     * @return exportStyle
     */
    public static ExportStyle getInstance(String dateFormatter) {
        if (dateFormatter == null || dateFormatter.isEmpty()) {
            return null;
        }
        return new ExportStyle(dateFormatter);
    }
}
