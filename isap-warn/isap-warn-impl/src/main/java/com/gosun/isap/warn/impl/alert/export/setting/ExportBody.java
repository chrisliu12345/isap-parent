package com.gosun.isap.warn.impl.alert.export.setting;

import com.gosun.isap.warn.impl.alert.export.base.ColumnHandler;

import java.util.List;

/**
 * 导出设置中的 body 设置
 * <p>创建时间：2017-5-23 10:24</p>
 *
 * @author 娄存银
 * @version 1.0
 */
public final class ExportBody extends ExportItem {
    private List<ExportColumn> columns;
    private ColumnHandler handler;
    private Integer maxRow;

    private ExportBody(Integer row, Integer column, Integer maxRow, List<ExportColumn> columns) {
        setRow(row);
        setColumn(column);
        this.maxRow = maxRow;
        this.columns = columns;
    }

    /**
     * 初始化
     */
    public void init() {
        if (columns != null) {
            Integer column = getColumn();
            Integer row = getRow();
            if (row == null) {
                row = 0;
            }
            if (column == null) {
                column = 0;
            }
            for (ExportColumn exportColumn : columns) {
                if (exportColumn != null) {
                    if (exportColumn.getColumn() == null) {
                        exportColumn.setColumn(column);
                    } else {
                        column = exportColumn.getColumn();
                    }
                }
                column++;
            }
        }
    }

    public List<ExportColumn> getColumns() {
        return columns;
    }

    public void setColumns(List<ExportColumn> columns) {
        this.columns = columns;
    }

    public ColumnHandler getHandler() {
        return handler;
    }

    public void setHandler(ColumnHandler handler) {
        this.handler = handler;
    }


    public Integer getMaxRow() {
        return maxRow;
    }

    public void setMaxRow(Integer maxRow) {
        this.maxRow = maxRow;
    }

    /**
     * 获取 ExportBody 实例
     *
     * @param row     起始行
     * @param column  起始列
     * @param maxRow  一页中最多的行数
     * @param columns 列设置
     * @return ExportBody 实例
     */
    public static ExportBody getInstance(Integer row, Integer column, Integer maxRow, List<ExportColumn> columns) {
        if (columns == null || columns.size() == 0) {
            return null;
        }
        if (row == null) {
            row = 0;
        }
        if (column == null) {
            column = 0;
        }
        ExportBody body = new ExportBody(row, column, maxRow, columns);
        body.init();
        return body;
    }

    /**
     * 通过 handler 的类名，加载一个 handler 对象
     *
     * @param handler 类名
     */
    public void initHandler(String handler) {
        if (handler == null || handler.isEmpty()) {
            return;
        }
        try {
            Class handlerClass = Class.forName(handler);
            this.handler = (ColumnHandler) handlerClass.newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }
}
