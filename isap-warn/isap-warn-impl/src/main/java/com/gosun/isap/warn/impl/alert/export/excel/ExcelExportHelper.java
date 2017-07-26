package com.gosun.isap.warn.impl.alert.export.excel;

import com.gosun.isap.warn.impl.alert.export.base.AfterWriteDataHandler;
import com.gosun.isap.warn.impl.alert.export.base.ColumnHandler;
import com.gosun.isap.warn.impl.alert.export.setting.ExportBody;
import com.gosun.isap.warn.impl.alert.export.setting.ExportCell;
import com.gosun.isap.warn.impl.alert.export.setting.ExportColumn;
import com.gosun.isap.warn.impl.alert.export.base.ExportHelper;
import com.gosun.isap.warn.impl.alert.export.setting.ExportStyle;
import com.gosun.isap.warn.impl.alert.export.setting.ExportTemplate;
import com.gosun.isap.warn.impl.alert.export.setting.TableSetting;
import com.gosun.isap.warn.impl.alert.util.ExcelUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 只支持 xls 导出
 * <p>创建时间：2017-5-23 13:47</p>
 *
 * @author 娄存银
 * @version 1.0
 */
public class ExcelExportHelper extends ExportHelper {
    private Workbook workbook;
    private Sheet sheet;

    // 记录第一行的行高
    private Short rowHeight;

    public ExcelExportHelper(TableSetting setting, Map<String, Object> header) {
        super(setting, header);
    }

    public ExcelExportHelper(TableSetting setting, List<Map<String, Object>> body) {
        super(setting, body);
    }

    public ExcelExportHelper(TableSetting setting, Map<String, Object> header, List<Map<String, Object>> body) {
        super(setting, header, body);
    }

    @Override
    public void export(OutputStream outputStream) throws IOException {
        if (!isValid() || outputStream == null) {
            return;
        }

        init();

        if (workbook != null) {
            workbook.write(outputStream);
        }
    }

    private Workbook getWorkbook() {
        if (workbook == null) {
            workbook = initWorkbookFromSetting(getTemplateSetting());
        }
        return workbook;
    }

    private Sheet getSheet() {
        if (sheet != null) {
            return sheet;
        }

        ExportTemplate template = getTemplateSetting();
        int index = 0;
        if (template != null) {
            index = template.getIndex();
        }

        sheet = getSheet(getWorkbook(), index);
        return sheet;
    }

    @Override
    protected void afterWriteData() {
        AfterWriteDataHandler handler = getSetting().getHandler();
        if (handler != null) {
            handler.handler(sheet);
        }
    }

    @Override
    protected void writeHeader() {
        if (getHeader() != null) {
            List<ExportCell> cells = getHeaderCells();
            if (cells == null || cells.size() == 0) {
                return;
            }

            for (ExportCell exportCell : cells) {
                Cell cell = getCell(getSheet(), exportCell.getRow(), exportCell.getColumn());
                Object object = getHeader().get(exportCell.getKey());
                writeDateToCell(cell, object, null);
            }
        }
    }

    @Override
    protected void writeBody() {
        if (isValidBody()) {
            ExportBody bodySetting = getBodySetting();
            if (bodySetting == null) {
                return;
            }

            Sheet sheet = getSheet();
            int rowNumber = bodySetting.getRow();

            for (Map<String, Object> map : getBody()) {
                Row row = getRow(sheet, rowNumber);
                writeBodyToRow(row, map);
                rowNumber++;
            }
        }
    }

    private void writeBodyToRow(Row row, Map<String, Object> map) {
        List<ExportColumn> columns = getColumns();
        if (columns == null || row == null || map == null) {
            return;
        }

        if (rowHeight == null) {
            rowHeight = row.getHeight();
        } else {
            row.setHeight(rowHeight);
        }

        // 创建日期格式
        Workbook workbook = row.getSheet().getWorkbook();
        ExportStyle exportStyle = getStyleSetting();
        CellStyle sheetStyle = workbook.createCellStyle();
        initCellStyle(sheetStyle);
        if (exportStyle != null && exportStyle.getDateFormat() != null) {
            short dateFormat = workbook.createDataFormat().getFormat(exportStyle.getDateFormat());
            sheetStyle.setDataFormat(dateFormat);
        }
        ColumnHandler handler = getColumnHandler();
        Map<String, CellStyle> styleMap = new HashMap<>();
        for (ExportColumn column : columns) {
            String format = column.getDateFormat();
            if (format != null) {
                CellStyle style = workbook.createCellStyle();
                initCellStyle(style);
                short dateFormat = workbook.createDataFormat().getFormat(format);
                style.setDataFormat(dateFormat);
                styleMap.put(column.getKey(), style);
            }
        }

        for (ExportColumn column : columns) {
            Integer index = column.getColumn();
            if (index != null) {
                Cell cell = row.getCell(column.getColumn());
                if (cell == null) {
                    cell = row.createCell(column.getColumn());
                }
                Object object = map.get(column.getKey());
                if (handler != null) {
                    object = handler.handle(column.getKey(), map);
                }
                CellStyle style = styleMap.get(column.getKey());
                if (style == null) {
                    style = sheetStyle;
                }
                writeDateToCell(cell, object, style);
            }
        }
    }

    private void writeDateToCell(Cell cell, Object object, CellStyle style) {
        if (cell != null) {
            if (style != null) {
                cell.setCellStyle(style);
            }
            if (object == null) {
                return;
            }
            if (object instanceof Date) {
                cell.setCellValue((Date) object);
            } else if (object instanceof Calendar) {
                cell.setCellValue((Calendar) object);
            } else if (object instanceof RichTextString) {
                cell.setCellValue((RichTextString) object);
            } else {
                cell.setCellValue(object.toString());
            }
        }
    }

    /**
     * 从 excel 初始化一个 workBook，读取 excel 中的模板信息，如果存在模板，打开模板，不存在则新建一个
     *
     * @param template 模板信息
     * @return workbook
     */
    private Workbook initWorkbookFromSetting(ExportTemplate template) {
        Workbook workbook = null;
        if (template != null && template.hasTemplate()) {
            try {
                workbook = WorkbookFactory.create(template.getTemplate());
            } catch (Exception ignored) {
            }
        }
        if (workbook == null) {
            workbook = new HSSFWorkbook();
        }
        return workbook;
    }

    private Sheet getSheet(Workbook workbook, int index) {
        return ExcelUtil.getSheet(workbook,index);
    }

    private Row getRow(Sheet sheet, int rowNumber) {
        return ExcelUtil.getRow(sheet,rowNumber);
    }

    private Cell getCell(Row row, int columnNumber) {
        return ExcelUtil.getCell(row,columnNumber);
    }

    private Cell getCell(Sheet sheet, int rowNumber, int columnNumber) {
        return ExcelUtil.getCell(sheet,rowNumber,columnNumber);
    }

    private void initCellStyle(CellStyle style) {
        if (style == null) {
            return;
        }
        // 设置边框
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);

        // 设置居中
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setAlignment(HorizontalAlignment.CENTER);
    }
}
