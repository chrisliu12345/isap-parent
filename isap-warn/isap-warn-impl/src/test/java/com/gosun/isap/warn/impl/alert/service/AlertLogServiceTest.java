package com.gosun.isap.warn.impl.alert.service;

import com.gosun.isap.warn.api.alert.service.AlertLogService;
import com.gosun.isap.warn.impl.alert.base.BaseUnitTest;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertNotNull;

/**
 * <p>创建时间：2017/5/8 9:09</p>
 *
 * @author 娄存银
 * @version 1.0
 */

public class AlertLogServiceTest extends BaseUnitTest {
    @Autowired
    AlertLogService logService;

    @Override
    public void autoWiredTest(){
        assertNotNull(logService);
    }
}
