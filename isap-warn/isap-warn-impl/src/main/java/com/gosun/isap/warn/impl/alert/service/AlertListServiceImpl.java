package com.gosun.isap.warn.impl.alert.service;

import com.gosun.isap.dao.mapper.alert.AlertMapper;
import com.gosun.isap.dao.mapper.alert.Interval;
import com.gosun.isap.dao.mapper.alert.SqlLimit;
import com.gosun.isap.dao.po.alert.Alert;
import com.gosun.isap.warn.api.alert.AlertConst;
import com.gosun.isap.warn.api.alert.model.enumeration.AlertStatus;
import com.gosun.isap.warn.api.alert.service.AlertCommonService;
import com.gosun.isap.warn.api.alert.service.AlertListService;
import com.gosun.isap.warn.api.alert.service.AlertProcessService;
import com.gosun.isap.warn.api.alert.service.AlertServiceId;
import com.gosun.isap.warn.api.alert.service.UserService;
import com.gosun.isap.warn.api.alert.util.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 获取警情列表的服务
 * <p>创建时间：2017/5/11 9:01</p>
 *
 * @author 娄存银
 * @version 1.0
 */
@Service
public class AlertListServiceImpl implements AlertListService {

    @Autowired
    private AlertMapper alertMapper;

    @Autowired
    private AlertCommonService commonService;

    @Autowired
    @Qualifier(AlertServiceId.USER_SERVICE)
    private UserService userService;

    @Autowired
    private AlertProcessService processService;


    @Override
    public List<Alert> listAlerts(Long userId, String departmentId, Byte status, Date startTime, Date endTime, List<String> orderBy, SqlLimit limit) {
        List<String> departments = null;
        // 按 department 筛选
        if (departmentId != null) {
            departments = userService.getChildCommunity(userId, departmentId);
        }
        if (!userService.isSupperAdmin(userId) && departmentId == null) {
            departments = userService.getCommunities(userId);
        }
        return alertMapper.listAlertsByStatusAndDeps(status, departments, startTime, endTime, orderBy, limit);
    }

    @Override
    public int countAlerts(Long userId, String departmentId, Byte status, Date start, Date end) {
        List<String> departments = null;
        // 按 department 筛选
        if (departmentId != null) {
            departments = userService.getChildCommunity(userId, departmentId);
        }
        if (!userService.isSupperAdmin(userId) && departmentId == null) {
            departments = userService.getCommunities(userId);
        }
        return alertMapper.countAlertsByStatusAndDeps(status, departments, start, end);
    }

    @Override
    public List<Alert> listAlerts(Long userId, String departmentId, Interval<Byte> statusInterval, Date startTime, Date endTime, List<String> orderBy, SqlLimit limit) {
        List<String> departments = null;
        // 按 department 筛选
        if (departmentId != null) {
            departments = userService.getChildCommunity(userId, departmentId);
        }
        if (!userService.isSupperAdmin(userId) && departmentId == null) {
            departments = userService.getCommunities(userId);
        }
        return alertMapper.listByStatusIntervalAndDeps(statusInterval, departments, startTime, endTime, orderBy, limit);
    }

    @Override
    public int countAlerts(Long userId, String departmentId, Interval<Byte> statusInterval, Date start, Date end) {
        List<String> departments = null;
        // 按 department 筛选
        if (departmentId != null) {
            departments = userService.getChildCommunity(userId, departmentId);
        }
        if (!userService.isSupperAdmin(userId) && departmentId == null) {
            departments = userService.getCommunities(userId);
        }
        return alertMapper.countByStatusIntervalAndDeps(statusInterval, departments, start,end);
    }

    @Override
    public List<Alert> listAlerts(List<String> departments, Byte status, Date startTime, Date endTime, List<String> orderBy, SqlLimit limit) {
        if (departments == null) {
            return null;
        }
        if (status == null) {
            return alertMapper.listAlertsByDepartments(departments, startTime, endTime, orderBy, limit);
        }
        return alertMapper.listAlertsByStatusAndDeps(status, departments, startTime, endTime, orderBy, limit);
    }

    @Override
    public int countAlerts(List<String> departmentIds, Byte status, Date startTime, Date endTime) {
        if (departmentIds == null) {
            return 0;
        }
        return alertMapper.countAlertsByStatusAndDeps(status, departmentIds, startTime, endTime);
    }

    @Override
    public List<Alert> listAlerts(List<String> departmentIds, Interval<Byte> statusInterval, Date startTime, Date endTime, List<String> orderBy, SqlLimit limit) {
        if (departmentIds == null) {
            return null;
        }
        return alertMapper.listByStatusIntervalAndDeps(statusInterval, departmentIds, startTime, endTime, orderBy, limit);
    }

    @Override
    public int countAlerts(List<String> departmentIds, Interval<Byte> statusInterval, Date startTime, Date endTime) {
        if (departmentIds == null) {
            return 0;
        }
        return alertMapper.countByStatusIntervalAndDeps(statusInterval, departmentIds, startTime, endTime);
    }


    @Override
    public List<Alert> listUnprocessedAlerts(long userId, SqlLimit limit) {
        // 处理超过 30 分钟未处理的警情
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, -AlertConst.PROCESSED_IN_TIME_MINUTE);
        alertMapper.updateAlertsAsOverTime(calendar.getTime());

        // 查询未处理的警情
        List<String> departmentIds = null;

        if (userService.isSupperAdmin(userId)) {
            departmentIds = userService.getCommunities(userId);
            if (ListUtils.isEmpty(departmentIds)) {
                return null;
            }
        }

        List<Alert> alerts = alertMapper.listUnprocessedAlerts(departmentIds, limit);
        if (ListUtils.isEmpty(alerts)) {
            return null;
        }
        return alerts;
    }

    @Override
    public int countUnprocessedAlerts(long userId) {
        List<String> departmentIds = userService.getCommunities(userId);
        if (ListUtils.isEmpty(departmentIds)) {
            return 0;
        }
        return alertMapper.countUnprocessedAlerts(departmentIds);
    }

    @Override
    public List<Alert> listWaitingCallbackAlerts(long userId, SqlLimit limit) {
        return getWaitingAlerts(userId, AlertStatus.WAITING_CALLBACK, limit);
    }

    @Override
    public int countWaitingCallbackAlerts(long userId) {
        return countWaitingAlerts(userId, AlertStatus.WAITING_CALLBACK);
    }

    private int countWaitingAlerts(long userId, AlertStatus waitingCallback) {
        return alertMapper.countAlertsByStatusAndUserId(AlertStatus.WAITING_CALLBACK.getStatus(), userId, null, null);
    }

    @Override
    public List<Alert> listWaitingCallbackAgainAlerts(long userId, SqlLimit limit) {
        return getWaitingAlerts(userId, AlertStatus.WAITING_CALLBACK_AGAIN, limit);
    }

    @Override
    public int countWaitingCallbackAgainAlerts(Long userId) {
        return alertMapper.countAlertsByStatusAndUserId(AlertStatus.WAITING_CALLBACK_AGAIN.getStatus(), userId, null, null);
    }

    @Override
    public List<Alert> listFinishedAlerts(long userId, Date start, Date end, List<String> orderBy, SqlLimit limit) {
        return listAlerts(userId,null,AlertStatus.getFinishedInterval(),start,end,orderBy,limit);
    }

    @Override
    public int countFinishedAlerts(long userId, Date start, Date end) {
        return countAlerts(userId,null,AlertStatus.getFinishedInterval(),start,end);
    }

    private boolean isInTime(Date date) {
        Date now = new Date();
        return (date.getTime() > now.getTime() - AlertConst.PROCESSED_IN_TIME_MILLISECOND);
    }

    private List<Alert> getWaitingAlerts(long userId, AlertStatus status, SqlLimit limit) {
        List<Alert> alerts = null;
        switch (status) {
            case WAITING_CALLBACK:
            case WAITING_CALLBACK_AGAIN:
                alerts = alertMapper.listAlertsByStatusAndUserId(status.getStatus(), userId, null, null, Alert.CONFIRM_ORDER_BY, limit);
                break;
            default:
                return null;
        }

        // 处理超过 30 分钟未处理完毕的警情
        for (int i = alerts.size() - 1; i >= 0; i--) {
            Alert alert = alerts.get(i);
            if (!isInTime(alert.getConfirmEndTime())) {
                if (status == AlertStatus.WAITING_CALLBACK) {
                    processService.noCallback(alert);
                } else {
                    processService.noCallbackAgain(alert);
                }
                alerts.remove(i);
            }
        }

        // 是否有数据
        if (alerts.size() == 0) {
            return null;
        }
        return alerts;
    }
}
