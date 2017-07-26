package com.gosun.isap.warn.api.alert.model.statistics;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

/**
 * 统计信息树父节点
 * <p>创建时间：2017/4/18 14:12</p>
 *
 * @author 娄存银
 * @version 1.0
 */
public interface StatisticsParentNode extends StatisticsNode {
    /**
     * @return 子节点
     */
    List<StatisticsNode> getChildren();

    /**
     * 添加子节点
     *
     * @param node 子节点
     */
    void addChild(StatisticsNode node);

    /**
     * 添加子节点
     *
     * @param children 子节点列表
     */
    void setChildren(List<StatisticsNode> children);

    /**
     * 是否包含节点
     * @param node 节点
     * @return 当前节点是否包含
     */
    boolean contain(StatisticsNode node);

    /**
     * 移除子节点
     * @param node 子节点
     */
    void remove(StatisticsNode node);

    /**
     * @return 是否使用子节点代替当前节点进行统计
     */
    @JsonIgnore
    boolean isReplaceWithChildren();

    /**
     * 设置使用子节点代替当前节点
     * @param replaceWithChildren 使用子节点
     */
    void setReplaceWithChildren(boolean replaceWithChildren);

    /**
     * @return 标题
     */
    String getTitle();

    /**
     * 设置标题
     * @param title 标题
     */
    void setTitle(String title);

    /**
     * @return 统计数据
     */
    @JsonIgnore
    List<StatisticsItem> getStatisticsData();

    /**
     * 通过基数获取统计数据
     *
     * @param baseNumber 统计数据的基数（用于计算比例）
     * @return 统计数据
     */
    List<StatisticsItem> getStatisticsData(int baseNumber);

    /**
     * @return 获取一个具有相同属性值的叶子节点
     */
    StatisticsNode asLeafNode();
}
