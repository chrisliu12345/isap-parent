package com.gosun.isap.warn.impl.alert.service;

import com.gosun.isap.dao.po.alert.base.BaseAlertLogTemplate;
import com.gosun.isap.warn.api.alert.model.enumeration.AlertLogType;
import com.gosun.isap.warn.api.alert.util.helper.AlertLogHelper;
import com.gosun.isap.warn.impl.alert.base.BaseUnitTest;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * <p>创建时间：2017/5/6 16:54</p>
 *
 * @author 娄存银
 * @version 1.0
 */
public class AlertLogHelperTest extends BaseUnitTest {

    @Override
    public void autoWiredTest() {

    }

    @Test
    public void getType() {
        BaseAlertLogTemplate t0 = AlertLogHelper.getTemplate((byte) 0);

        BaseAlertLogTemplate t1 = AlertLogType.NEED_PROCESS.getTemplate();

        System.out.println(t0);
        System.out.println(t1);
        assertEquals(t0, t1);
    }
}
