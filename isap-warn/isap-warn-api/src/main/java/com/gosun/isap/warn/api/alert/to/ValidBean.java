package com.gosun.isap.warn.api.alert.to;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

/**
 * 实现类必须为标准的 java bean ,所有字段都必须有 get 方法
 * <p>创建时间：2017/5/16 8:57</p>
 *
 * @author 娄存银
 * @version 1.0
 */
public interface ValidBean {

    /**
     * 校验不能为空的参数是否都非空
     *
     * @return 参数值是否有效
     */
    default boolean isNotValid() {
        Field[] fields = this.getClass().getDeclaredFields();

        if (fields == null || fields.length == 0) {
            return false;
        }

        String[] nullableFields = getNullableFields();
        List<String> nullableList = null;
        if (nullableFields != null) {
            nullableList = Arrays.asList(nullableFields);
        }

        for (Field field : fields) {
            String name = field.getName();
            if (nullableList != null && nullableList.size() > 0 && nullableList.contains(name)) {
                continue;
            }

            try {
                PropertyDescriptor descriptor = new PropertyDescriptor(name, this.getClass());
                Method getMethod = descriptor.getReadMethod();
                if (getMethod != null) {
                    Object value = getMethod.invoke(this);
                    if (value == null) {
                        return true;
                    }
                }
            } catch (IntrospectionException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    /**
     * @return 获取无效的参数信息
     */
    default String getInvalidColumns() {
        Field[] fields = this.getClass().getDeclaredFields();

        if (fields == null || fields.length == 0) {
            return null;
        }

        String[] nullableFields = getNullableFields();
        List<String> nullableList = null;
        if (nullableFields != null) {
            nullableList = Arrays.asList(nullableFields);
        }
        StringBuilder columns = new StringBuilder();
        for (Field field : fields) {
            String name = field.getName();
            if (nullableList != null && nullableList.size() > 0 && nullableList.contains(name)) {
                continue;
            }

            try {
                PropertyDescriptor descriptor = new PropertyDescriptor(name, this.getClass());
                Method getMethod = descriptor.getReadMethod();
                if (getMethod != null) {
                    Object value = getMethod.invoke(this);
                    if (value == null) {
                        columns.append(name)
                                .append(",");
                    }
                }
            } catch (IntrospectionException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        String string = columns.toString();
        if (string.isEmpty()) {
            return null;
        }
        return string.substring(0, string.length() - 1);
    }

    /**
     * 获取可以为空的参数名数组
     *
     * @return 可以为空的参数名数组
     */
    default String[] getNullableFields() {
        return null;
    }
}
