package com.gosun.isap.warn.impl.alert.export.handler;

import org.apache.poi.ss.usermodel.Sheet;

/**
 *
 * 盘问可疑人员确认表导出成功后，用于合并第一列、第二列单元格
 * <p>创建时间：2017-5-27 16:04</p>
 *
 * @author 娄存银
 * @version 1.0
 */
public class QuestionSuspectAfterHandler extends MergeCellHandler{
    @Override
    public void handler(Sheet sheet) {
        final int startRow = 2;
        final int firstColumn = 0;
        final int secondColumn = 1;
        mergeCells(sheet,startRow,firstColumn);
        mergeCells(sheet,startRow,secondColumn);
    }
}
