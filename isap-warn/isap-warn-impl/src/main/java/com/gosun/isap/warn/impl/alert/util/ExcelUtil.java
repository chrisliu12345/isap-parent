package com.gosun.isap.warn.impl.alert.util;

import com.gosun.isap.warn.api.alert.AlertConst;
import org.apache.poi.ss.usermodel.*;

import java.util.Date;

/**
 * Excel 的基本操作封装
 * <p>创建时间：2017-6-15 17:16</p>
 *
 * @author 娄存银
 * @version 1.0
 */
public class ExcelUtil {
    /**
     * 从 workbook 根据 index 初始化 sheet ,有则获取，没有就新建
     *
     * @param workbook workbook
     * @param index    sheet 所在的位置
     * @return sheet
     */
    public static Sheet getSheet(Workbook workbook, int index) {
        if (workbook == null) {
            return null;
        }
        Sheet sheet = null;
        if (workbook.getNumberOfSheets() > index) {
            sheet = workbook.getSheetAt(index);
        }
        if (sheet == null) {
            sheet = workbook.createSheet();
        }
        return sheet;
    }

    /**
     * 从 sheet 根据 row 所在位置 初始化 row ,有则获取，没有就新建
     *
     * @param sheet     sheet
     * @param rowNumber 行号
     * @return row
     */
    public static Row getRow(Sheet sheet, int rowNumber) {
        if (sheet == null) {
            return null;
        }
        Row row = sheet.getRow(rowNumber);
        if (row == null) {
            row = sheet.createRow(rowNumber);
        }
        return row;
    }

    /**
     * 从 row 根据 column 初始化 cell ,有则获取，没有就新建
     *
     * @param row          row
     * @param columnNumber 第几列
     * @return cell
     */
    public static Cell getCell(Row row, int columnNumber) {
        if (row == null) {
            return null;
        }
        Cell cell = row.getCell(columnNumber);
        if (cell == null) {
            cell = row.createCell(columnNumber);
        }
        return cell;
    }

    /**
     * @param sheet        sheet
     * @param rowNumber    行
     * @param columnNumber 列
     * @return cell
     */
    public static Cell getCell(Sheet sheet, int rowNumber, int columnNumber) {
        if (sheet == null) {
            return null;
        }
        Row row = getRow(sheet, rowNumber);
        return getCell(row, columnNumber);
    }

    /**
     * 向单元格写入数据
     *
     * @param cell   cell
     * @param object object
     */
    public static void writeDataToCell(Cell cell, Object object) {
        writeDataToCell(cell, object, null);
    }

    /**
     * 向单元格写入数据
     *
     * @param cell   cell
     * @param object object
     * @param style  cellStyle
     */
    public static void writeDataToCell(Cell cell, Object object, CellStyle style) {
        if (cell == null || object == null) {
            return;
        }
        if (style != null) {
            cell.setCellStyle(style);
        }
        if (object instanceof Date) {
            cell.setCellValue((Date) object);
            if (style == null) {
                initDateStyle(cell, AlertConst.DATE_TIME_PATTERN);
            }
        } else {
            cell.setCellValue(object.toString());
        }
    }

    /**
     * 初始化 cell 的日期格式
     *
     * @param cell    cell
     * @param pattern 日期格式
     */
    public static void initDateStyle(Cell cell, String pattern) {
        if (cell == null) {
            return;
        }
        Row row = cell.getRow();
        Sheet sheet = row.getSheet();
        Workbook workbook = sheet.getWorkbook();
        CellStyle style = workbook.createCellStyle();
        short dateFormat = workbook.createDataFormat().getFormat(pattern);
        style.setDataFormat(dateFormat);
        cell.setCellStyle(style);
    }
}
