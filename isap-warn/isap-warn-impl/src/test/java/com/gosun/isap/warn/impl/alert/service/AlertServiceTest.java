package com.gosun.isap.warn.impl.alert.service;

import com.gosun.isap.dao.mapper.alert.AlertMapper;
import com.gosun.isap.dao.mapper.alert.base.AlertBaseMapper;
import com.gosun.isap.dao.po.alert.base.BaseAlert;
import com.gosun.isap.warn.api.alert.AlertConst;
import com.gosun.isap.warn.api.alert.model.enumeration.AlertStatus;
import com.gosun.isap.warn.api.alert.service.AlertService;
import com.gosun.isap.warn.impl.alert.base.BaseUnitTest;
import com.gosun.isap.warn.impl.alert.base.ObjectService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;
/**
 * <p>创建时间：2017/5/6 9:18</p>
 *
 * @author 娄存银
 * @version 1.0
 */
public class AlertServiceTest extends BaseUnitTest {

    @Autowired
    private AlertService alertService;

    @Autowired
    private AlertBaseMapper alertBaseMapper;

    @Autowired
    private ObjectService objectService;

    @Autowired
    private AlertMapper alertMapper;

    @Test
    @Override
    public void autoWiredTest() {
        assertNotNull(alertService);
    }

    @Test
    public void createAlert(){
        String deviceId = objectService.getDeviceId();
        String description = "移动侦测";

        // 获取未处理的警情数
        int countBeforeInsert = alertMapper.countAlertsByStatusAndDeps(AlertStatus.UNPROCESSED.getStatus(),null,null,null);

        // 插入数据库
        long alertId = alertService.createAlert(deviceId,description);
        assertNotEquals(0L,alertId);
        int countAfterInsert = alertMapper.countAlertsByStatusAndDeps(AlertStatus.UNPROCESSED.getStatus(),null,null,null);
        assertEquals(countBeforeInsert+1,countAfterInsert);

        // 从数据库取出数据
        BaseAlert alert = alertBaseMapper.selectByPrimaryKey(alertId);
        assertNotNull(alert);

        // 对比存入的数据
        assertEquals(deviceId,alert.getDevId());
    }

    @Test
    public void createAlerts(){
        for (int i = 0; i < 40; i++) {
            String deviceId = objectService.getDeviceId();
            String description = "移动侦测";
            alertService.createAlert(deviceId,description);
/*            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
        }
    }

    @Test
    public void latestProcess(){
        String departmentId = objectService.getDepartmentId();
        System.out.println(departmentId);
        Date date = alertService.latestProcessedTime(departmentId);
        DateFormat format = new SimpleDateFormat(AlertConst.DATE_TIME_PATTERN);
        System.out.println(format.format(date));
    }


}
