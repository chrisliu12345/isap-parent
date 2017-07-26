package com.gosun.isap.warn.impl.alert.base;

import com.google.gson.Gson;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * <p>创建时间：2017-6-10 17:33</p>
 *
 * @author 娄存银
 * @version 1.0
 */
public class ExcelReportHelper implements ReportHelper {
    private static final int METHOD_INDEX = 0;
    private static final int RESULT_INDEX = 1;
    private static final int SIZE_INDEX = 2;
    private static final int PARAM_INDEX = 3;

    private int row = 1;
    private HSSFWorkbook workbook;
    private Sheet sheet;

    @Override
    public void export(String path) throws IOException {
        if (workbook != null) {
            File file = new File(path);
            workbook.write(file);
            workbook.close();
        }
    }

    @Override
    public void saveResult(String methodName, Object object, Object... params) {
        if (sheet == null) {
            clearReport();
        }
        Gson gson = new Gson();
        String result = gson.toJson(object);
        Row row = getRow(sheet, this.row);
        Cell methodCell = getCell(row, METHOD_INDEX);
        Cell resultCell = getCell(row, RESULT_INDEX);
        methodCell.setCellValue(methodName);
        resultCell.setCellValue(result);

        if (object != null && object instanceof List) {
            Cell sizeCell = getCell(row, SIZE_INDEX);
            sizeCell.setCellValue(((List) object).size());
        }

        if (params != null) {
            int column = PARAM_INDEX;
            for (Object param : params) {
                Cell paramCell = getCell(row, column);
                if (param != null) {
                    paramCell.setCellValue(param.toString());
                }
                column += 1;
            }
        }
        this.row += 1;
    }

    @Override
    public void saveResult(boolean useLastResult, String methodName, Object object, Object... params) {

    }


    @Override
    public void clearReport() {
        workbook = new HSSFWorkbook();
        sheet = getSheet(workbook);
        Row row = getRow(sheet, 0);
        Cell method = getCell(row, METHOD_INDEX);
        method.setCellValue("方法");
        Cell result = getCell(row, RESULT_INDEX);
        result.setCellValue("结果");
        Cell size = getCell(row, SIZE_INDEX);
        size.setCellValue("结果长度");
        Cell params = getCell(row, PARAM_INDEX);
        params.setCellValue("参数");
        this.row = 1;
    }

    private Sheet getSheet(HSSFWorkbook workbook) {
        Sheet sheet = null;
        try {
            sheet = workbook.getSheetAt(0);
        } catch (Exception ignored) {
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
    private Row getRow(Sheet sheet, int rowNumber) {
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
    private Cell getCell(Row row, int columnNumber) {
        if (row == null) {
            return null;
        }
        Cell cell = row.getCell(columnNumber);
        if (cell == null) {
            cell = row.createCell(columnNumber);
        }
        return cell;
    }
}
