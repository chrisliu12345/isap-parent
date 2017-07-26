package com.gosun.isap.warn.impl.alert.export.base;

import java.util.Map;

/**
 * 数据列 handler 。
 * 用于处理 map 中不存在的 key ,或者将相关 key 对应的值包装后返回。
 * <p>创建时间：2017-5-23 9:22</p>
 *
 * @author 娄存银
 * @version 1.0
 */
public interface ColumnHandler {
    /**
     * 根据 key 获取处理后的值
     *
     * @param key    列名
     * @param object 数据
     * @return 处理后的值
     */
    Object handle(String key, Map<String, Object> object);

    /**
     * 判断是否为图片
     *
     * @param key 列名
     * @return 是否是图片
     */
    default boolean isPicture(String key) {
        return false;
    }

    /**
     * 获取图片的类型
     *
     * @param key    列名
     * @param object 数据
     * @return 图片类型
     */
    default int getPictureType(String key, Map<String, Object> object) {
        return 0;
    }
}
