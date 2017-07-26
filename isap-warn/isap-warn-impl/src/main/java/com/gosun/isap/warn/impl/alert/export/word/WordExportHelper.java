package com.gosun.isap.warn.impl.alert.export.word;

import com.gosun.isap.warn.api.alert.AlertConst;
import com.gosun.isap.warn.impl.alert.export.base.AfterWriteDataHandler;
import com.gosun.isap.warn.impl.alert.export.base.ColumnHandler;
import com.gosun.isap.warn.impl.alert.export.setting.ExportBody;
import com.gosun.isap.warn.impl.alert.export.setting.ExportCell;
import com.gosun.isap.warn.impl.alert.export.setting.ExportColumn;
import com.gosun.isap.warn.impl.alert.export.base.ExportHelper;
import com.gosun.isap.warn.impl.alert.export.setting.ExportStyle;
import com.gosun.isap.warn.impl.alert.export.setting.ExportTemplate;
import com.gosun.isap.warn.impl.alert.export.setting.TableSetting;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xwpf.usermodel.BreakType;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 只支持 docx 导出，使用 poi 实现的文档导出功能
 * <p>创建时间：2017-5-23 20:10</p>
 *
 * @author 娄存银
 * @version 1.0
 */
public class WordExportHelper extends ExportHelper {
    private XWPFTable table;
    private XWPFDocument document;

    public WordExportHelper(TableSetting setting, Map<String, Object> header) {
        super(setting, header);
    }

    public WordExportHelper(TableSetting setting, List<Map<String, Object>> body) {
        super(setting, body);
    }

    public WordExportHelper(TableSetting setting, Map<String, Object> header, List<Map<String, Object>> body) {
        super(setting, header, body);
    }

    @Override
    public void export(OutputStream outputStream) throws IOException {
        if (!isValid() || outputStream == null) {
            return;
        }

        init();

        if (document != null) {
            document.write(outputStream);
        }
    }

    @Override
    protected void afterWriteData() {
        AfterWriteDataHandler handler = getSetting().getHandler();
        if (handler != null) {
            handler.handler(document);
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
                XWPFTableCell cell = getCell(getTable(), exportCell.getRow(), exportCell.getColumn());
                Object object = getHeader().get(exportCell.getKey());
                writeDateToCell(cell, object, null);
            }
        }
    }

    private XWPFTableCell getCell(XWPFTable table, Integer row, Integer column) {
        if (table == null) {
            return null;
        }
        return getCell(getRow(table, row), column);
    }

    @Override
    protected void writeBody() {
        List<Map<String, Object>> body = getBody();
        if (isValidBody()) {

            ExportBody bodySetting = getBodySetting();
            if (bodySetting == null) {
                return;
            }

            XWPFTable table;
            int rowNumber = bodySetting.getRow();
            Integer maxRow = bodySetting.getMaxRow();
            int size = body.size();
            int count = 1;
            if (maxRow != null && maxRow != 0) {
                count = size / maxRow;
                if (size % maxRow != 0) {
                    count++;
                }
            }
            if (maxRow == null || maxRow <= 0 || count == 1) {
                table = getTable();
                for (Map<String, Object> map : body) {
                    XWPFTableRow row = getRow(table, rowNumber);
                    writeBodyToRow(row, map);
                    rowNumber++;
                }
            } else {
                XWPFDocument document = getDocument();
                int index = getTemplateSetting().getIndex();
                // 复制
                XWPFDocument temp = new XWPFDocument();
                for (int i = 0; i < count; i++) {
                    // 插入页
                    WordOperationHelper.copyDocument(document, temp);
                    // 插入分页
                    if (i != count - 1) {
                        XWPFParagraph paragraph = temp.createParagraph();
                        paragraph.createRun().addBreak(BreakType.PAGE);
                    }
                }

                try {
                    File file = new File(UUID.randomUUID().toString());
                    temp.write(new FileOutputStream(file));
                    this.document = document = new XWPFDocument(new FileInputStream(file));
                    file.delete();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                table = getTableFromDocument(document, index);
                // 填充
                for (int i = 0, k = 1; i < body.size(); k++) {
                    // 初始化第一行
                    rowNumber = bodySetting.getRow();
                    for (int j = 0; j < maxRow && i < body.size(); j++, i++) {
                        Map<String, Object> map = body.get(i);
                        XWPFTableRow row = getRow(table, rowNumber);
                        writeBodyToRow(row, map);
                        rowNumber++;
                    }
                    if (i < body.size() - 1) {
                        table = getTableFromDocument(document, index + k);
                    }
                }
            }
        }
    }

    private void writeBodyToRow(XWPFTableRow row, Map<String, Object> map) {
        List<ExportColumn> columns = getColumns();
        if (columns == null || row == null || map == null) {
            return;
        }
        ColumnHandler handler = getColumnHandler();
        for (ExportColumn column : columns) {
            Integer index = column.getColumn();
            if (index != null) {
                XWPFTableCell cell = getCell(row, index);
                Object object = map.get(column.getKey());
                if (handler != null) {
                    object = handler.handle(column.getKey(), map);
                    if (handler.isPicture(column.getKey())) {
                        writePictureToCell(cell, (byte[]) object, handler.getPictureType(column.getKey(), map), column);
                    } else {
                        writeDateToCell(cell, object, column.getDateFormat());
                    }
                } else {
                    writeDateToCell(cell, object, column.getDateFormat());
                }
            }
        }
    }

    private void writePictureToCell(XWPFTableCell cell, byte[] bytes, int type, ExportColumn column) {
        if (cell == null || bytes == null || column == null) {
            return;
        }

        Integer width = column.getWidth();
        if (width == null) {
            width = DEFAULT_WIDTH;
        }
        Integer height = column.getHeight();
        if (height == null) {
            height = DEFAULT_HEIGHT;
        }

        XWPFRun run = cell.addParagraph().createRun();
        try {
            run.addPicture(new ByteArrayInputStream(bytes), type, "", width, height);
        } catch (InvalidFormatException | IOException e) {
            e.printStackTrace();
        }
    }

    private void writeDateToCell(XWPFTableCell cell, Object object, String dateFormat) {
        if (cell == null || object == null) {
            return;
        }
        ExportStyle style = getStyleSetting();
        if (object instanceof Date) {
            SimpleDateFormat format;
            if (dateFormat != null) {
                format = new SimpleDateFormat(dateFormat);
            } else if (style != null && style.getDateFormat() != null) {
                format = new SimpleDateFormat(style.getDateFormat());
            } else {
                format = new SimpleDateFormat(AlertConst.DATE_TIME_PATTERN);
            }
            cell.setText(format.format(object));
        } else {
            cell.setText(object.toString());
        }
    }

    private XWPFTable getTable() {
        if (table != null) {
            return table;
        }
        ExportTemplate template = getTemplateSetting();
        int index = 0;
        if (template != null) {
            index = template.getIndex();
        }
        table = getTableFromDocument(getDocument(), index);
        return table;
    }

    private XWPFDocument getDocument() {
        if (document == null) {
            document = initDocumentFromSetting(getTemplateSetting());
        }
        return document;
    }

    private XWPFDocument initDocumentFromSetting(ExportTemplate template) {
        XWPFDocument document = null;
        if (template != null && template.hasTemplate()) {
            try {
                document = new XWPFDocument(template.getTemplate());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (document == null) {
            document = new XWPFDocument();
        }
        return document;
    }

    private XWPFTable getTableFromDocument(XWPFDocument document, int index) {
        List<XWPFTable> tables = document.getTables();
        XWPFTable table = null;
        if (index < tables.size()) {
            table = tables.get(index);
        }

        if (table == null) {
            int currentIndex = 0;
            if (tables.size() > 0) {
                currentIndex = tables.size();
            }
            for (int i = currentIndex; i < index; i++) {
                document.createTable();
            }
            table = document.createTable();
        }
        return table;
    }

    private XWPFTableRow getRow(XWPFTable table, int rowNumber) {
        XWPFTableRow row = null;
        List<XWPFTableRow> rows = table.getRows();
        if (rowNumber < rows.size()) {
            row = rows.get(rowNumber);
        }

        if (row == null) {
            int currentIndex = 0;
            if (rows.size() > 0) {
                currentIndex = rows.size();
            }
            for (int i = currentIndex; i < rowNumber; i++) {
                table.createRow();
            }
            row = table.createRow();
        }
        return row;
    }

    private XWPFTableCell getCell(XWPFTableRow row, int columnNumber) {
        XWPFTableCell cell = row.getCell(columnNumber);
        if (cell == null) {
            int currentIndex = 0;
            List<XWPFTableCell> cells = row.getTableCells();
            if (cells != null && cells.size() > 0) {
                currentIndex = cells.size();
            }
            for (int i = currentIndex; i < columnNumber; i++) {
                row.createCell();
            }
            cell = row.createCell();
        }
        return cell;
    }

}
