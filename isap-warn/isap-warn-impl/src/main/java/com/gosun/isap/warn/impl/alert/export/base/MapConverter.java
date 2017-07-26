package com.gosun.isap.warn.impl.alert.export.base;

import java.util.List;
import java.util.Map;

/**
 * 用于转换给客户端的数据
 * <p>创建时间：2017-5-31 8:58</p>
 *
 * @author 娄存银
 * @version 1.0
 */
public interface MapConverter {
    /**
     * 转换 map 中的键值对。
     *
     * @param map 源数据
     */
    void convert(Map<String, Object> map);

    /**
     * 转换列表数据
     *
     * @param list 源数据列表
     */
    default void convert(List<Map<String, Object>> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        for (Map<String, Object> map : list) {
            convert(map);
        }
    }
}
