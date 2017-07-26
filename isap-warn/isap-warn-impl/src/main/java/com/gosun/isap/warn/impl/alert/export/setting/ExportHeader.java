package com.gosun.isap.warn.impl.alert.export.setting;

import java.util.ArrayList;
import java.util.List;

/**
 * 导出设置中的 header 设置
 * <p>创建时间：2017-5-23 10:24</p>
 *
 * @author 娄存银
 * @version 1.0
 */
public final class ExportHeader extends ExportItem {
    private List<ExportCell> cells;

    private ExportHeader(Integer row, Integer column, List<ExportCell> cells) {
        setColumn(column);
        setRow(row);
        this.cells = cells;
    }

    /**
     * 初始化 cell 的 row column;
     * 如果 cell 未设置 row column , 默认所有 cell 在同一行，column 依次递增。
     */
    public void init() {
        if (cells != null) {
            Integer column = getColumn();
            Integer row = getRow();
            if (row == null) {
                row = 0;
            }
            if (column == null) {
                column = 0;
            }
            for (ExportCell cell : cells) {
                if (cell != null) {
                    if (cell.getRow() == null) {
                        cell.setRow(row);
                    } else {
                        row = cell.getRow();
                    }

                    if (cell.getColumn() == null) {
                        cell.setColumn(column);
                    } else {
                        column = cell.getColumn();
                    }
                }
                column++;
            }
        }
    }

    /**
     * 为 header 添加一个 cell
     *
     * @param cell cell
     */
    public void add(ExportCell cell) {
        if (cell == null) {
            return;
        }
        if (cells == null) {
            cells = new ArrayList<>();
        }
        cells.add(cell);
    }

    public List<ExportCell> getCells() {
        return cells;
    }

    public void setCells(List<ExportCell> cells) {
        this.cells = cells;
    }

    /**
     * 示例化一个 header 对象
     *
     * @param row    第几行
     * @param column 第几列
     * @param cells  cells
     * @return header
     */
    public static ExportHeader getInstance(Integer row, Integer column, List<ExportCell> cells) {
        if (cells == null || cells.size() == 0) {
            return null;
        }
        if (row == null) {
            row = 0;
        }
        if (column == null) {
            column = 0;
        }
        ExportHeader header = new ExportHeader(row, column, cells);
        header.init();
        return header;
    }
}
