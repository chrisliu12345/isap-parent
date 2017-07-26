package com.gosun.isap.warn.api.alert.util;

import java.util.ArrayList;
import java.util.List;

/**
 * List 常用操作
 * 创建时间：2017/4/15 10:26
 *
 * @author 娄存银
 * @version 1.0
 */
public class ListUtils {

    /**
     * clear 一个 list
     *
     * @param list list
     * @param <T>  T
     * @return list
     */
    public static <T> List<T> clearList(List<T> list) {
        if (list == null) {
            list = new ArrayList<T>();
        } else {
            list.clear();
        }
        return list;
    }

    /**
     * list 为空会新建一个
     *
     * @param list list
     * @param <T>  T
     * @return 传入的 list
     */
    public static <T> List<T> initList(List<T> list) {
        if (list == null) {
            list = new ArrayList<T>();
        }
        return list;
    }

    /**
     * @param list list
     * @return 是否为空
     */
    public static boolean isEmpty(List list) {
        return list == null || list.size() == 0;
    }
}
