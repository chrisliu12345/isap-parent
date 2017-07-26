package com.gosun.isap.warn.impl.alert.export.setting;

/**
 * <p>创建时间：2017-5-23 10:23</p>
 *
 * @author 娄存银
 * @version 1.0
 */
public abstract class ExportItem {
    private Integer row;
    private Integer column;

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Integer getColumn() {
        return column;
    }

    public void setColumn(Integer column) {
        this.column = column;
    }
}
