package com.gosun.isap.warn.impl.alert.model.statistics;

import com.gosun.isap.warn.api.alert.model.statistics.StatisticsNode;
import com.gosun.isap.warn.api.alert.model.statistics.StatisticsParentNode;

/**
 * 用于统计数据的数据叶子节点<br>
 * <p>number 数据，description 名称，parent 父节点。每个节点只能有一个父节点。</p>
 * <p>
 * <p>创建时间：2017/4/18 13:53</p>
 *
 * @author 娄存银
 * @version 1.0
 */
public class StatisticsLeafNode implements StatisticsNode {

    private int number;

    private String description;

    private String name;

    private StatisticsParentNode parent;

    public StatisticsLeafNode() {
    }

    public StatisticsLeafNode(int number, String description, String name) {
        this.number = number;
        this.description = description;
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getNumber() {
        return number;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public boolean isLeafNode() {
        return true;
    }

    @Override
    public StatisticsParentNode getParent() {
        return parent;
    }

    @Override
    public void setParent(StatisticsParentNode parent) {
        if (this.parent != null && this.parent != parent) {
            this.parent.remove(this);
        }
        if (parent == null || this.parent == parent) {
            return;
        }

        this.parent = parent;
        if (!parent.contain(this)) {
            parent.addChild(this);
        }
    }

    @Override
    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }
}
