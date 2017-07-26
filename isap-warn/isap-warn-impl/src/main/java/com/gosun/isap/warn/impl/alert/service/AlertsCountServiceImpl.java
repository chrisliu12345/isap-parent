package com.gosun.isap.warn.impl.alert.service;

import com.gosun.isap.dao.mapper.alert.AlertMapper;
import com.gosun.isap.dao.mapper.alert.AlertsCountMapper;
import com.gosun.isap.warn.api.alert.model.enumeration.AlertStatus;
import com.gosun.isap.warn.api.alert.model.enumeration.AlertType;
import com.gosun.isap.warn.api.alert.service.AlertsCountService;
import com.gosun.isap.warn.api.alert.util.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 警情数量统计服务
 * <p>创建时间：2017/5/6 10:11</p>
 *
 * @author 娄存银
 * @version 1.0
 */
@Service
public class AlertsCountServiceImpl implements AlertsCountService {
    private static final int THIRTY = 30;
    private static final int EIGHT = 8;
    private static final int TEN_PM = 22;
    @Autowired
    private AlertsCountMapper countMapper;

    @Autowired
    private AlertMapper alertMapper;

    @Override
    public int unprocessed(List<String> departmentIds) {
        if (ListUtils.isEmpty(departmentIds)) {
            return 0;
        }
        return countMapper.countByDepartmentsAndStatus(departmentIds, AlertStatus.UNPROCESSED.getStatus());
    }

    @Override
    public int confirmedInResponseTime(Date start, long userId) {
        return countMapper.countConfirmedInResponseTime(start, null, userId);
    }

    @Override
    public int falseAlertsInResponseTime(Date start, Date end, long userId) {
        return countMapper.countFalseInResponseTime(start, end, userId);
    }

    @Override
    public int currentCountOfProcessed(String departmentId) {
        if (departmentId == null) {
            return 0;
        }
        Date end = new Date();
        Date start = getStatisticsStartTime();
        Integer count = countMapper.countByTypeStatusSectionAndDepartment(AlertType.getNeedProcessInterval(), AlertStatus.getSuccessInterval(), start, end, departmentId);
        return count == null ? 0 : count;
    }

    private Date getStatisticsStartTime() {
        Calendar calendar = Calendar.getInstance();
        // 毫秒置 0
        calendar.set(Calendar.MILLISECOND, 0);

        // 0:30
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, THIRTY);
        Date zeroThirty = calendar.getTime();

        // 8:00
        calendar.set(Calendar.HOUR_OF_DAY, EIGHT);
        calendar.set(Calendar.MINUTE, 0);
        Date eightClock = calendar.getTime();

        // 22:00
        calendar.set(Calendar.HOUR_OF_DAY, TEN_PM);
        calendar.set(Calendar.MINUTE, 0);
        Date tenClockPM = calendar.getTime();

        Date date = new Date();

        // 0:30 之前，从前一天 22:00 统计
        if (date.getTime() < zeroThirty.getTime()) {
            calendar.setTime(tenClockPM);
            calendar.add(Calendar.DAY_OF_YEAR, -1);
            return calendar.getTime();
        } else if (date.getTime() >= zeroThirty.getTime() && date.getTime() < eightClock.getTime()) {
            // 0:30 到 8:00 ，从 0:30 统计
            return zeroThirty;
        } else if (date.getTime() > tenClockPM.getTime()) {
            // 22:00 到 24:00，从 22:00 统计
            return tenClockPM;
        } else {
            // 8:00 到 22:00，从 8:00 统计
            return eightClock;
        }
    }

}
