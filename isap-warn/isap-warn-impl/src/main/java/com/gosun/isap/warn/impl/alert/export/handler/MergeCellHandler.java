package com.gosun.isap.warn.impl.alert.export.handler;

import com.gosun.isap.warn.impl.alert.export.base.AfterWriteDataHandler;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 * 合并一列中具有相同值的单元格
 * <p>创建时间：2017-5-27 15:53</p>
 *
 * @author 娄存银
 * @version 1.0
 */
public abstract class MergeCellHandler implements AfterWriteDataHandler {
    /**
     * 合并一列中具有相同值的单元格
     *
     * @param sheet        表格对象
     * @param rowNumber    起始行
     * @param columnNumber 所在列
     */
    protected void mergeCells(Sheet sheet, int rowNumber, int columnNumber) {
        Row row = sheet.getRow(rowNumber);
        int start = rowNumber;
        int end = rowNumber;
        String previous = null;
        while (row != null) {
            Cell cell = row.getCell(columnNumber);
            String string = cell.getStringCellValue();
            if (previous != null && previous.equals(string)) {
                end = rowNumber;
            } else {
                if (start != end) {
                    CellRangeAddress address = new CellRangeAddress(start, end, columnNumber, columnNumber);
                    sheet.addMergedRegion(address);
                    doAfterMerge(start, end, columnNumber, sheet);
                }
                start = rowNumber;
                end = rowNumber;
                previous = string;
            }
            rowNumber += 1;
            row = sheet.getRow(rowNumber);
        }
        if (start != end) {
            CellRangeAddress address = new CellRangeAddress(start, end, columnNumber, columnNumber);
            sheet.addMergedRegion(address);
            doAfterMerge(start, end, columnNumber, sheet);
        }
    }

    /**
     * 表格合并后的操作
     *
     * @param start        起始行
     * @param end          结束行
     * @param columnNumber 所在列
     * @param sheet        表格对象
     */
    protected void doAfterMerge(int start, int end, int columnNumber, Sheet sheet) {

    }
}
