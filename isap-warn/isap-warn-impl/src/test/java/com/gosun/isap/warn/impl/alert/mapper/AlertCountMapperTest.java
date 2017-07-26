package com.gosun.isap.warn.impl.alert.mapper;

import com.gosun.isap.dao.mapper.alert.AlertsCountMapper;
import com.gosun.isap.warn.api.alert.model.enumeration.AlertStatus;
import com.gosun.isap.warn.impl.alert.base.BaseUnitTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * <p>创建时间：2017/5/8 10:09</p>
 *
 * @author 娄存银
 * @version 1.0
 */
public class AlertCountMapperTest extends BaseUnitTest {

    @Autowired
    private AlertsCountMapper mapper;

    @Override
    public void autoWiredTest() {
        assertNotNull(mapper);
    }

    @Test
    public void countByStatus() {
        String departmentId = "fccd3ca0-31fd-11e7-be49-4ccc6aa6e080";
        List<String> ids = new ArrayList<>();
        ids.add(departmentId);

        AlertStatus[] statuses = AlertStatus.values();
        int countOfAllStatus = 0;
        for (AlertStatus status : statuses) {
            int countOfStatus = mapper.countByDepartmentsAndStatus(ids, status.getStatus());
            countOfAllStatus += countOfStatus;
            System.out.printf("%s : %d %n",status.getDescription(),countOfStatus);
        }

        int totalCount = mapper.totalCountByDepartments(ids,null,null);
        assertEquals(countOfAllStatus,totalCount);
    }
}
