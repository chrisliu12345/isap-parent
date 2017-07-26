package com.gosun.isap.warn.impl.alert.export.base;

import com.gosun.isap.warn.impl.alert.export.setting.*;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * <p>创建时间：2017-5-23 13:47</p>
 *
 * @author 娄存银
 * @version 1.0
 */
public abstract class ExportHelper {
    /**
     * 文档中图片的默认尺寸
     */
    protected static final int DEFAULT_WIDTH = 300;
    /**
     * 文档中图片的默认尺寸
     */
    protected static final int DEFAULT_HEIGHT = 200;

    /**
     * setting
     */
    private TableSetting setting;
    /**
     * header 数据
     */
    private Map<String, Object> header;
    /**
     * body 数据
     */
    private List<Map<String, Object>> body;
    /**
     * 已经初始化
     */
    private boolean init;

    public ExportHelper(TableSetting setting, Map<String, Object> header) {
        this.setting = setting;
        this.header = header;
    }

    public ExportHelper(TableSetting setting, List<Map<String, Object>> body) {
        this.setting = setting;
        this.body = body;
    }

    public ExportHelper(TableSetting setting, Map<String, Object> header, List<Map<String, Object>> body) {
        this.setting = setting;
        this.header = header;
        this.body = body;
    }

    protected ExportHeader getHeaderSetting() {
        return setting == null ? null : setting.getHeader();
    }

    protected ExportTemplate getTemplateSetting() {
        return setting == null ? null : setting.getTemplate();
    }

    protected ExportStyle getStyleSetting() {
        return setting == null ? null : setting.getStyle();
    }

    protected ExportBody getBodySetting() {
        return setting == null ? null : setting.getBody();
    }

    public TableSetting getSetting() {
        return setting;
    }

    public void setSetting(TableSetting setting) {
        this.setting = setting;
    }

    public Map<String, Object> getHeader() {
        return header;
    }

    public void setHeader(Map<String, Object> header) {
        this.header = header;
    }

    public List<Map<String, Object>> getBody() {
        return body;
    }

    public void setBody(List<Map<String, Object>> body) {
        this.body = body;
    }

    public boolean isInit() {
        return init;
    }

    public void setInit(boolean init) {
        this.init = init;
    }

    /**
     * 导出到 outputStream
     *
     * @param outputStream outputStream
     * @throws IOException IOException
     */
    public abstract void export(OutputStream outputStream) throws IOException;

    /**
     * @return ExportColumn list
     */
    protected List<ExportColumn> getColumns() {
        if (setting != null && setting.getBody() != null) {
            return setting.getBody().getColumns();
        }
        return null;
    }

    /**
     * @return ExportCell list
     */
    protected List<ExportCell> getHeaderCells() {
        if (setting != null && setting.getHeader() != null) {
            return setting.getHeader().getCells();
        }
        return null;
    }

    /**
     * @return ColumnHandler
     */
    protected ColumnHandler getColumnHandler() {
        ExportBody bodySetting = getBodySetting();
        return bodySetting == null ? null : bodySetting.getHandler();
    }


    /**
     * 用于初始化
     */
    public void init() {
        if (!isValid() || init) {
            return;
        }

        writeHeader();
        writeBody();
        afterWriteData();

        init = true;
    }

    /**
     * 初始化完成后执行的操作
     */
    protected void afterWriteData() {
    }

    /**
     * 写入header
     */
    protected abstract void writeHeader();

    /**
     * 写入 body
     */
    protected abstract void writeBody();

    /**
     * @return 是否有效
     */
    protected boolean isValid() {
        if (setting == null) {
            return false;
        }

        if (!isValidBody() && header == null) {
            return false;
        }

        return true;
    }

    /**
     * @return body 是否有效
     */
    protected boolean isValidBody() {
        return !(body == null || body.size() == 0);
    }
}
