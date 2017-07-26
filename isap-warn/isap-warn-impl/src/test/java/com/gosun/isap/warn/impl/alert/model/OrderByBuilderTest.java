package com.gosun.isap.warn.impl.alert.model;

import com.gosun.isap.dao.mapper.alert.OrderByBuilder;
import com.gosun.isap.dao.po.alert.base.BaseAlert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.junit.Assert.*;
/**
 * <p>创建时间：2017/5/12 16:08</p>
 *
 * @author 娄存银
 * @version 1.0
 */
public class OrderByBuilderTest {
    private static final Logger logger = LoggerFactory.getLogger(OrderByBuilderTest.class);
    @Test
    public void builderTest(){
        OrderByBuilder builder = new OrderByBuilder();
        builder.setContains(BaseAlert::contains);
        builder.add(BaseAlert.ADD_TIME);
        builder.add("test");
        builder.add(BaseAlert.ID);
        builder.add(BaseAlert.CONFIRM_DESC);
        builder.add(BaseAlert.ALERT_TYPE);

        List<String> list = builder.build();
        assertEquals(4L,list.size());
        assertEquals(list.get(0),BaseAlert.ADD_TIME);
        System.out.println(list);

        String orderBy = OrderByBuilder.parseSortStringToOrderBy("-description,id,-time,description");
        String result = "description,id,time desc";
        assertEquals(result,orderBy);
        System.out.println(orderBy);
    }
}
