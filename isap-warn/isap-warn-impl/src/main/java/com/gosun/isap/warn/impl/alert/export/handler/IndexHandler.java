package com.gosun.isap.warn.impl.alert.export.handler;

import com.gosun.isap.warn.impl.alert.export.base.ColumnHandler;

import java.util.Map;

/**
 * 用于生成序号的 handler
 * <p>创建时间：2017-5-31 11:48</p>
 *
 * @author 娄存银
 * @version 1.0
 */
public class IndexHandler implements ColumnHandler {
    private static final String INDEX = "index";

    private int index;

    @Override
    public Object handle(String key, Map<String, Object> object) {
        switch (key) {
            case INDEX:
                return ++index;
            default:
                return object.get(key);
        }
    }
}
