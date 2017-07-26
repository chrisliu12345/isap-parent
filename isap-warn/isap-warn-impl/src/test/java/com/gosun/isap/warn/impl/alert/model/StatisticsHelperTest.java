package com.gosun.isap.warn.impl.alert.model;

import com.gosun.isap.warn.api.alert.service.AlertStatisticsService;
import com.gosun.isap.warn.impl.alert.base.BaseUnitTest;
import com.gosun.isap.warn.impl.alert.util.StatisticsHelper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>创建时间：2017-5-22 12:00</p>
 *
 * @author 娄存银
 * @version 1.0
 */
public class StatisticsHelperTest extends BaseUnitTest{

    StatisticsHelper helper = new StatisticsHelper();
    @Autowired
    AlertStatisticsService service;

    @Test
    public void test(){
        service.init(null,null,"eb6d4cd7-3d21-11e7-b6d1-4ccc6aa6e080",null);
        helper.setService(service);
        System.out.println(helper.totalStatistics().getStatisticsData());

    }

    @Override
    public void autoWiredTest() {

    }
}
