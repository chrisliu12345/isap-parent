package com.gosun.isap.warn.impl.alert.export.base;

import com.gosun.isap.warn.api.alert.util.ConfigUtil;
import com.gosun.isap.warn.impl.alert.export.setting.TableSetting;
import org.dom4j.DocumentException;

import javax.ws.rs.core.StreamingOutput;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 用于实现文件导出。
 * 在导出过程中，负责初始化 TableSetting ，提供 content-disposition，子类需要重写 write 方法完成导出功能。
 * <p>创建时间：2017-5-18 19:34</p>
 *
 * @author 娄存银
 * @version 1.0
 */
public abstract class ExportStreamingOutput implements StreamingOutput {
    /**
     * content-disposition
     */
    public static final String CONTENT_DISPOSITION = "content-disposition";
    private static final String DISPOSITION_TEMPLATE = "attachment; filename=\"%s.%s\"";


    /**
     * 表格数据
     */
    private List<Map<String, Object>> body;
    /**
     * 表格导出设置
     */
    private TableSetting setting;
    /**
     * 导出的文档名
     */
    private String name;
    /**
     * 表格头
     */
    private Map<String, Object> header;

    public ExportStreamingOutput() {

    }

    public ExportStreamingOutput(String settingResource, Map<String, Object> header, List<Map<String, Object>> body, String name) {
        this.body = body;
        this.name = name;
        this.header = header;
        setSettingResource(settingResource);
    }

    public List<Map<String, Object>> getBody() {
        return body;
    }

    public void setBody(List<Map<String, Object>> body) {
        this.body = body;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    /**
     * @return ContentDisposition
     */
    public String getContentDisposition() {
        String name = this.name + "-" + new Date().getTime();
        try {
            name = URLEncoder.encode(name, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return String.format(DISPOSITION_TEMPLATE, name, getSuffixName());
    }

    /**
     * 通过资源初始化 setting
     *
     * @param resource 资源路径
     */
    public void setSettingResource(String resource) {
        try {
            InputStream inputStream = ConfigUtil.getInputStream(resource);
            setting = TableSetting.getSetting(inputStream);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过文件初始化 setting
     *
     * @param path 文件路径
     */
    public void setSettingFile(String path) {
        try {
            setting = TableSetting.getSettingFromFile(path);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取导出的文档类型
     *
     * @return mediaType
     */
    public abstract String getMediaType();

    /**
     * 获取文件后缀名
     *
     * @return 拼接文件后缀名
     */
    protected abstract String getSuffixName();
}
