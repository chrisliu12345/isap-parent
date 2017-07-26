package com.gosun.isap.warn.impl.alert.export.setting;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * 导出模板，可以通过 path resource 设置导出模板文件，同时设置时，优先使用 resource
 * <p>创建时间：2017-5-23 11:05</p>
 *
 * @author 娄存银
 * @version 1.0
 */
public final class ExportTemplate {
    // 模板的文件路径
    private String path;
    // 模板的资源路径
    private String resource;
    // sheet 索引（Excel 有效）
    private int index;

    private InputStream inputStream;

    private ExportTemplate(String path, String resource, int index) {
        this.path = path;
        this.index = index;
        this.resource = resource;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * @return 模板的 inputStream
     * @throws FileNotFoundException FileNotFoundException
     */
    public InputStream getTemplate() throws FileNotFoundException {
        if (inputStream == null) {
            if (resource != null) {
                inputStream = this.getClass().getClassLoader().getResourceAsStream(resource);
            } else if (path != null) {
                inputStream = new FileInputStream(path);
            }
        }
        return inputStream;
    }

    /**
     * @return 模板是否存在
     */
    public boolean hasTemplate() {
        try {
            InputStream inputStream = getTemplate();
            if (inputStream != null) {
                return true;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 实例化一个 ExportTemplate 对象
     *
     * @param path     模板文件路径
     * @param resource 模板文件资源路径
     * @param index    模板 sheet index
     * @return ExportTemplate
     */
    public static ExportTemplate getInstance(String path, String resource, Integer index) {
        if (path == null && resource == null) {
            return null;
        }
        if (index == null) {
            index = 0;
        }
        return new ExportTemplate(path, resource, index);
    }
}
