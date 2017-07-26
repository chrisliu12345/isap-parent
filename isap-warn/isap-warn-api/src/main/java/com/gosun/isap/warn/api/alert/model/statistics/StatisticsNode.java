package com.gosun.isap.warn.api.alert.model.statistics;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 统计信息树节点
 * <p>创建时间：2017/4/18 14:08</p>
 *
 * @author 娄存银
 * @version 1.0
 */
public interface StatisticsNode {
    /**
     * @return 节点数值
     */
    int getNumber();

    /**
     * 设置节点数值
     *
     * @param number 数值
     */
    void setNumber(int number);

    /**
     * @return 节点描述
     */
    String getDescription();

    /**
     * 设置节点描述
     *
     * @param description 节点描述
     */
    void setDescription(String description);

    /**
     * @return 节点名
     */
    String getName();

    /**
     * 设置节点名
     *
     * @param name 节点名
     */
    void setName(String name);

    /**
     * @return 是否为叶子节点
     */
    @JsonIgnore
    boolean isLeafNode();

    /**
     * @return 获取父节点
     */
    @JsonIgnore
    StatisticsParentNode getParent();

    /**
     * @param parent 设置父节点
     */
    void setParent(StatisticsParentNode parent);
}
