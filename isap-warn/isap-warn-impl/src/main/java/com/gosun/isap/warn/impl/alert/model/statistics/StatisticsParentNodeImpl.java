package com.gosun.isap.warn.impl.alert.model.statistics;

import com.gosun.isap.warn.api.alert.model.statistics.StatisticsItem;
import com.gosun.isap.warn.api.alert.model.statistics.StatisticsNode;
import com.gosun.isap.warn.api.alert.model.statistics.StatisticsParentNode;
import com.gosun.isap.warn.api.alert.util.ListUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 用于统计数据的数据父节点
 * <p>父节点可以有一系列的子节点。父节点的 number 是子节点的 number 之和。
 * </p>
 * <p>创建时间：2017/4/18 14:18</p>
 *
 * @author 娄存银
 * @version 1.0
 */
public class StatisticsParentNodeImpl extends StatisticsLeafNode implements StatisticsParentNode {
    private List<StatisticsNode> children;
    private String title;
    private boolean replaceWithChildren;

    @Override
    public List<StatisticsNode> getChildren() {
        return children;
    }

    @Override
    public void addChild(StatisticsNode child) {
        if (child.getParent() != this) {
            child.setParent(this);
        }
        children = ListUtils.initList(children);
        if (!children.contains(child)) {
            children.add(child);
        }
    }

    @Override
    public boolean isLeafNode() {
        return children == null || children.size() == 0;
    }

    @Override
    public int getNumber() {
        if (children == null) {
            return 0;
        }

        int number = 0;
        for (StatisticsNode node :
                children) {
            number += node.getNumber();
        }
        return number;
    }

    @Override
    public void setChildren(List<StatisticsNode> children) {
        this.children = children;
    }

    @Override
    public boolean contain(StatisticsNode node) {
        return !isLeafNode() && children.contains(node);
    }

    @Override
    public void remove(StatisticsNode node) {
        if (contain(node)) {
            children.remove(node);
            node.setParent(null);
        }
    }

    @Override
    public boolean isReplaceWithChildren() {
        return replaceWithChildren;
    }

    @Override
    public void setReplaceWithChildren(boolean replaceWithChildren) {
        this.replaceWithChildren = replaceWithChildren;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public List<StatisticsItem> getStatisticsData() {
        return getStatisticsData(getNumber());
    }

    @Override
    public List<StatisticsItem> getStatisticsData(int baseNumber) {
        if (isLeafNode()) {
            return null;
        }

        int total = getNumber();
        total = total < baseNumber ? baseNumber : total;

        if (total == 0) {
            return null;
        }

        List<StatisticsItem> list = new ArrayList<>();

        for (StatisticsNode node : children) {
            if (node instanceof StatisticsParentNode && ((StatisticsParentNode) node).isReplaceWithChildren()) {
                List<StatisticsItem> childrenList = ((StatisticsParentNode) node).getStatisticsData();
                for (StatisticsItem item : childrenList) {
                    item.setRatio((float) item.getNumber() / total);
                }
                list.addAll(childrenList);
            } else {
                list.add(new StatisticsItem(node.getName(),node.getDescription(), node.getNumber(), (float) node.getNumber() / total));
            }
        }
        return list;
    }

    @Override
    public StatisticsNode asLeafNode() {
        StatisticsNode node = new StatisticsLeafNode();
        node.setName(getName());
        node.setDescription(getDescription());
        node.setParent(getParent());
        node.setNumber(getNumber());
        return node;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

}
