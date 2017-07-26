package com.gosun.isap.warn.impl.alert.mapper;

import com.gosun.isap.dao.mapper.alert.AlertMapper;
import com.gosun.isap.dao.mapper.alert.SqlLimit;
import com.gosun.isap.dao.po.alert.Alert;
import com.gosun.isap.warn.api.alert.model.enumeration.AlertStatus;
import com.gosun.isap.warn.impl.alert.base.BaseUnitTest;
import com.gosun.isap.warn.impl.alert.base.ObjectService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * <p>创建时间：2017/5/11 11:56</p>
 *
 * @author 娄存银
 * @version 1.0
 */
public class AlertMapperTest extends BaseUnitTest{
    @Autowired
    AlertMapper alertMapper;

    @Autowired
    ObjectService objectService;

    @Override
    public void autoWiredTest() {
    }

    @Test
    public void getAlertTest(){
        Alert alert = alertMapper.selectAlertById(26L);
        System.out.println(alert);
        if(alert != null){
            assertNotNull(alert.getDepartmentName());
            assertNotNull(alert.getDeviceName());
        }
    }

    @Test
    public void limitTest(){
        List<Alert> list = alertMapper.listByStatusIntervalAndUserId(null,1L,null,null,Alert.DEFAULT_ORDER_BY,new SqlLimit(1,2));

        System.out.println(list);
        if(list != null) {
            assert (list.size() <= 2);
        }
        String[] departmentIds = {objectService.getDepartmentId()};
        list = alertMapper.listByStatusIntervalAndDeps(null, Arrays.asList(departmentIds),null,null,Alert.DEFAULT_ORDER_BY,new SqlLimit(2));
        System.out.println(list);
    }

    @Test
    public void finishedAlerts(){
        List<Alert> alerts = alertMapper.listByStatusIntervalAndUserId(AlertStatus.getFinishedInterval(),1L,null,null,Alert.DEFAULT_ORDER_BY,new SqlLimit(2));
        System.out.println(alerts);
    }

    @Test
    public void listAllTest(){
        String[] departmentIds = {objectService.getDepartmentId()};
        List<Alert> alerts = alertMapper.listAlertsByDepartments(Arrays.asList(departmentIds),null,null,null,null);
        System.out.println(alerts);
    }
}
