package com.gosun.isap.warn.impl.alert.export.base;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

/**
 * 数据写入文档后，需要附加的操作。
 * <p>创建时间：2017-5-27 9:31</p>
 *
 * @author 娄存银
 * @version 1.0
 */
public interface AfterWriteDataHandler {
    /**
     * 数据写入 sheet 后需要做的处理
     *
     * @param sheet 表格对象
     */
    default void handler(Sheet sheet) {

    }

    /**
     * 数据写入 document 后需要做的处理
     *
     * @param document 文档对象
     */
    default void handler(XWPFDocument document) {

    }
}
