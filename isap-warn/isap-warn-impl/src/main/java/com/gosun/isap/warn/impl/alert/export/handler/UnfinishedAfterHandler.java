package com.gosun.isap.warn.impl.alert.export.handler;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 * 未完成出警统计表导出成功后，用于合并第一列，第二列；同时在第三列统计数量。
 * <p>创建时间：2017-5-27 10:59</p>
 *
 * @author 娄存银
 * @version 1.0
 */
public class UnfinishedAfterHandler extends MergeCellHandler{
    private static final int SECOND_COLUMN = 1;

    @Override
    public void handler(Sheet sheet) {
        if(sheet == null){
            return;
        }
        final int startRow = 3;
        final int first = 0;
        mergeCells(sheet,startRow,first);
        mergeCells(sheet,startRow,SECOND_COLUMN);
    }

    @Override
    protected void doAfterMerge(int start, int end, int columnNumber, Sheet sheet) {
        // 如果是第二列，合并第三列，写入数量
        if(columnNumber == SECOND_COLUMN){
            // 第三列
            final int third = 2;
            CellRangeAddress address = new CellRangeAddress(start,end,third,third);
            sheet.addMergedRegion(address);
            Row row = sheet.getRow(start);
            setCount(row,third,end-start+1);
        }
    }

    private void setCount(Row row, int columnNumber, int value){
        if(row == null){
            return;
        }
        Cell cell = row.getCell(columnNumber);
        if(cell == null){
            cell = row.createCell(columnNumber);
        }
        cell.setCellValue(String.valueOf(value));
    }
}
