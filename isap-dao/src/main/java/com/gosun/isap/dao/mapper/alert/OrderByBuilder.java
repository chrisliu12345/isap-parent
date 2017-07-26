package com.gosun.isap.dao.mapper.alert;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * 构建 order by list ,自动解析 -column 这种形式的字符串
 * add 方法，后面添加的会覆盖前面添加的规则；
 * addDefault 方法，如果 column 不存在，添加的规则会生效，如果存在，添加的规则不生效
 * <p>
 * <p>
 * <p>创建时间：2017/5/12 15:47</p>
 *
 * @author 娄存银
 * @version 1.0
 */
public class OrderByBuilder {
    private static final String DESC = " desc";
    /**
     * 用于存储 order by 数据
     */
    private Map<String, Boolean> columns = new LinkedHashMap<>();

    /**
     * 用于过滤不符合条件的列名
     */
    private Function<String, Boolean> contains = null;

    public OrderByBuilder() {
    }

    public OrderByBuilder(Function<String, Boolean> contains) {
        this.contains = contains;
    }

    /**
     * 添加一个排序条件
     *
     * @param column 列名
     * @param desc   是否倒序
     * @return 当前对象
     */
    public OrderByBuilder add(String column, boolean desc) {
        if (column == null || column.isEmpty() || !contains(column)) {
            return this;
        }
        columns.put(column, desc);
        return this;
    }

    /**
     * 添加一条 orderBy 解析第一个字符，如果第一个字符为 ‘-’认定为倒序排序，如：
     * -name 会被解析为 add("name",true),name 会被解析为 add(name,false)
     *
     * @param column 排序列，
     * @return 当前对象
     */
    public OrderByBuilder add(String column) {
        if (column == null || column.isEmpty()) {
            return this;
        }
        boolean desc = isDesc(column);
        column = getColumn(column);

        return add(column, desc);
    }

    /**
     * 添加排序列列表
     *
     * @param columns 列名列表
     * @return 当前对象
     */
    public OrderByBuilder add(List<String> columns) {
        if (columns == null || columns.size() == 0) {
            return this;
        }
        for (String column : columns) {
            add(column);
        }
        return this;
    }

    /**
     * 解析并添加排序列
     *
     * @param string 排序字符串
     * @return 当前对象
     */
    public OrderByBuilder parse(String string) {
        if (string == null || string.isEmpty()) {
            return this;
        }
        String[] strings = string.split(",");
        add(Arrays.asList(strings));
        return this;
    }

    /**
     * 添加默认排序规则，当规则存在时，默认排序规则不生效
     *
     * @param column 默认排序规则列
     * @return 当前对象
     */
    public OrderByBuilder addDefault(String column) {
        if (column == null || column.isEmpty()) {
            return this;
        }

        boolean desc = isDesc(column);
        column = getColumn(column);

        if (columns.containsKey(column)) {
            return this;
        }

        return add(column, desc);
    }

    /**
     * 添加默认排序规则，当规则存在时，默认排序规则不生效
     *
     * @param columns 默认排序
     * @return 当前对象
     */
    public OrderByBuilder addDefault(List<String> columns) {
        if (columns == null || columns.size() == 0) {
            return this;
        }
        for (String column : columns) {
            addDefault(column);
        }
        return this;
    }

    /**
     * @return 用于拼接 order by 的字符串列表
     */
    public List<String> build() {
        if (columns == null || columns.size() == 0) {
            return null;
        }

        List<String> list = new ArrayList<>();
        for (Map.Entry<String, Boolean> entry : columns.entrySet()) {
            String column = entry.getKey();
            Boolean desc = entry.getValue();

            if (desc == null || !desc) {
                list.add(column.trim());
            } else {
                list.add(column + DESC);
            }
        }
        return list;
    }

    public void setContains(Function<String, Boolean> contains) {
        this.contains = contains;
    }

    private boolean contains(String column) {
        if (contains == null) {
            return true;
        } else {
            Boolean result = contains.apply(column);
            if (result != null) {
                return result;
            } else {
                return true;
            }
        }
    }

    private boolean isDesc(String column) {
        char firstChar = column.charAt(0);
        return firstChar == '-';
    }

    private String getColumn(String column) {
        if (isDesc(column)) {
            return column.substring(1);
        }
        return column;
    }

    /**
     * 将排序字符串解析为 order by 列表
     * @param sort 排序字符串
     * @return 列表
     */
    public static List<String> parseSortString(String sort) {
        return parseSortString(sort, null);
    }

    /**
     * 将排序字符串解析为 order by 列表
     *
     * @param sort 排序字符串
     * @param contains 过滤 function
     * @return 列表
     */
    public static List<String> parseSortString(String sort, Function<String, Boolean> contains) {
        if (sort == null || sort.isEmpty()) {
            return null;
        }
        OrderByBuilder builder = new OrderByBuilder();
        builder.setContains(contains);
        builder.parse(sort);
        return builder.build();
    }

    /**
     * 将排序字符串拼接成 order by 需要的字符串
     *
     * @param sort 排序字符串
     * @return order by 需要的字符串
     */
    public static String parseSortStringToOrderBy(String sort) {
        return parseSortStringToOrderBy(sort, null);
    }

    /**
     * 将排序字符串拼接成 order by 需要的字符串
     *
     * @param sort 排序字符串
     * @param contains 过滤 function
     * @return order by 需要的字符串
     */
    public static String parseSortStringToOrderBy(String sort, Function<String, Boolean> contains) {
        List<String> list = parseSortString(sort, contains);
        if (list == null || list.size() == 0) {
            return null;
        }
        StringBuilder builder = new StringBuilder();
        for (String column : list) {
            builder.append(column)
                    .append(',');
        }
        String orderBy = builder.toString();
        return orderBy.substring(0, orderBy.length() - 1);
    }
}
