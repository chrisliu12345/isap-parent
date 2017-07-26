package com.gosun.isap.warn.impl.alert.model;

import com.gosun.isap.warn.api.alert.model.statistics.*;
import com.gosun.isap.warn.impl.alert.model.statistics.StatisticsLeafNode;
import com.gosun.isap.warn.impl.alert.model.statistics.StatisticsParentNodeImpl;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
/**
 * 统计数据用的树形结构测试
 * 创建时间：2017/4/15 10:35
 *
 * @author 娄存银
 * @version 1.0
 */
public class StaticsNodeImplTest {
    @Test
    public void addChildren(){
        StatisticsNode node1 = new StatisticsLeafNode();
        node1.setDescription("child1");
        node1.setNumber(10);

        StatisticsNode node2 = new StatisticsLeafNode();
        node2.setNumber(5);
        node2.setDescription("child2");

        StatisticsParentNode parent = new StatisticsParentNodeImpl();
        parent.setDescription("parent");

        node1.setParent(parent);
        node2.setParent(parent);

        assertEquals(parent.getNumber(),node1.getNumber()+node2.getNumber());

        StatisticsParentNode parent2 = new StatisticsParentNodeImpl();
        parent2.setDescription("parent2");

        parent2.addChild(node1);
        parent2.addChild(node2);

        System.out.println(parent2.getStatisticsData());

        assertEquals(parent2,node1.getParent());
        assertEquals(parent2.getNumber(),node1.getNumber()+node2.getNumber());
        assertEquals(parent.getNumber(),0);

    }

    @Test
    public void addSame(){
        StatisticsNode node1 = new StatisticsLeafNode();
        node1.setDescription("child1");
        node1.setNumber(10);

        StatisticsParentNode parent = new StatisticsParentNodeImpl();
        parent.setDescription("parent");

        node1.setParent(parent);
        parent.addChild(node1);

        assertEquals(parent.getNumber(),node1.getNumber());
    }

    @Test
    public void replaceWithChildren(){
        StatisticsNode[] nodes = new StatisticsNode[7];
        for (int i = 0; i < 7; i++) {
            StatisticsNode node = new StatisticsLeafNode();
            node.setDescription("node"+i);
            node.setNumber(i+3);
            nodes[i] = node;
        }

        StatisticsParentNode[] parents = new StatisticsParentNode[5];
        for (int i = 0; i < 5; i++) {
            StatisticsParentNode parentNode = new StatisticsParentNodeImpl();
            parentNode.setDescription("parent"+i);
            parents[i] = parentNode;
        }

        nodes[0].setParent(parents[0]);
        nodes[1].setParent(parents[1]);
        nodes[2].setParent(parents[2]);
        nodes[3].setParent(parents[3]);
        nodes[4].setParent(parents[3]);
        nodes[5].setParent(parents[4]);
        nodes[6].setParent(parents[4]);

        parents[1].setParent(parents[0]);
        parents[2].setParent(parents[0]);
        parents[3].setParent(parents[1]);
        parents[4].setParent(parents[2]);

        parents[1].setReplaceWithChildren(true);
        parents[3].setReplaceWithChildren(true);


        List<StatisticsItem> list = parents[0].getStatisticsData();
        System.out.println(list);
        assertEquals(5,list.size());

        parents[4].setParent(parents[1]);
        list = parents[0].getStatisticsData();
        System.out.println(list);
        assertEquals(6,list.size());
    }

    @Test
    public void equalTest(){
        StatisticsNode node1 = new StatisticsLeafNode();
        StatisticsNode node2 = new StatisticsLeafNode();
        node1.setNumber(1);
        node2.setNumber(1);

        node1.setDescription("hh");
        node2.setDescription("hh");

        StatisticsParentNode parentNode = new StatisticsParentNodeImpl();

        node1.setParent(parentNode);
        node2.setParent(parentNode);

        assertEquals(node1,node2);
    }

}
