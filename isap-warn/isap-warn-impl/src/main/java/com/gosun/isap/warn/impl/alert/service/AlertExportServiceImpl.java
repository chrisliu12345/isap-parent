package com.gosun.isap.warn.impl.alert.service;

import com.gosun.isap.dao.mapper.alert.AlertExportMapper;
import com.gosun.isap.dao.mapper.alert.AlertMapper;
import com.gosun.isap.dao.mapper.alert.SqlLimit;
import com.gosun.isap.warn.api.alert.model.enumeration.AlertStatus;
import com.gosun.isap.warn.api.alert.service.AlertCommonService;
import com.gosun.isap.warn.api.alert.service.AlertExportService;
import com.gosun.isap.warn.api.alert.service.AlertServiceId;
import com.gosun.isap.warn.api.alert.service.AlertStatisticsService;
import com.gosun.isap.warn.api.alert.service.UserService;
import com.gosun.isap.warn.api.alert.util.ListUtils;
import com.gosun.isap.warn.impl.alert.util.StatisticsHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>创建时间：2017-5-25 14:16</p>
 *
 * @author 娄存银
 * @version 1.0
 */
@Service
public class AlertExportServiceImpl implements AlertExportService {
    @Autowired
    private AlertExportMapper mapper;
    @Autowired
    @Qualifier(AlertServiceId.USER_SERVICE)
    private UserService userService;

    @Autowired
    private AlertMapper alertMapper;

    @Autowired
    private AlertCommonService commonService;

    @Override
    public List<Map<String, Object>> alertLogBody(String departmentId, Long userId, Date start, Date end, SqlLimit limit) {
        if (userId == null) {
            return null;
        }
        if (departmentId == null) {
            return mapper.getAlertLogByUser(userId, start, end, limit);
        }
        List<String> departments = userService.getChildCommunity(userId, departmentId);
        return mapper.getAlertLogByDepartments(departments, start, end, limit);
    }

    @Override
    public int countAlertLog(String departmentId, Long userId, Date start, Date end) {
        if (userId == null) {
            return 0;
        }
        if (departmentId == null) {
            return mapper.countAlertLogByUser(userId, start, end);
        }
        List<String> departments = userService.getChildCommunity(userId, departmentId);
        return mapper.countAlertLogByDepartments(departments, start, end);
    }

    @Override
    public Map<String, Object> alertLogHeader(String departmentId, Long userId, Date start, Date end) {
        if (userId == null) {
            return null;
        }
        AlertStatisticsService service = StatisticsHelper.getStatisticsService(start, end, departmentId, userId);
        String userName = userService.getUserName(userId);
        int guardProcessed = service.guardProcessed();
        int callback = service.callback();
        int needGuard = service.needGuard();
        Map<String, Object> map = new HashMap<>();
        map.put(DATE, start);
        map.put(USER_NAME, userName);
        map.put(PROCESSED_COUNT, guardProcessed);
        map.put(CALLBACK_COUNT, callback);
        map.put(ALERTS_COUNT, needGuard);
        return map;
    }

    @Override
    public List<Map<String, Object>> alertDetailLogBody(String departmentId, Long userId, Date start, Date end, SqlLimit limit) {
        if (userId == null) {
            return null;
        }
        if (departmentId == null) {
            return mapper.getAlertDetailLogByUser(userId, start, end, limit);
        }
        List<String> departments = userService.getChildCommunity(userId, departmentId);
        return mapper.getAlertDetailLogByDepartments(departments, start, end, limit);
    }

    @Override
    public int countAlertDetailLog(String departmentId, Long userId, Date start, Date end) {
        if (userId == null) {
            return 0;
        }
        if (departmentId == null) {
            return mapper.countAlertDetailLogByUser(userId, start, end);
        }
        List<String> departments = userService.getChildCommunity(userId, departmentId);
        return mapper.countAlertDetailLogByDeps(departments, start, end);
    }

    @Override
    public Map<String, Object> alertDetailLogHeader(String departmentId, Long userId, Date start, Date end) {
        return null;
    }

    @Override
    public List<Map<String, Object>> getUnfinishedAlertsBody(String departmentId, Long userId, Date start, Date end, SqlLimit limit) {
        if (userId == null) {
            return null;
        }
        List<Map<String, Object>> list;
        if (departmentId == null) {
            list = mapper.getUnfinishedAlertsByUser(userId, start, end, limit);
        } else {
            List<String> departments = userService.getChildCommunity(userId, departmentId);
            list = mapper.getUnfinishedAlertsByDeps(departments, start, end, limit);
        }
        for (Map<String, Object> map : list) {
            String reason = (String) map.get(FAILED_REASON);
            Integer status = (Integer) map.get(STATUS);
            if (reason == null && status != null) {
                AlertStatus alertStatus = AlertStatus.get(status.byteValue());
                if (alertStatus != null) {
                    map.put(FAILED_REASON, alertStatus.getDescription());
                }
            }
            map.putIfAbsent(ARRIVED_TIME, NOT_ARRIVE);
        }

        return list;
    }

    @Override
    public int countUnfinishedAlerts(String departmentId, Long userId, Date start, Date end) {
        if (userId == null) {
            return 0;
        }
        if (departmentId == null) {
            return mapper.countUnfinishedAlertsByUser(userId, start, end);
        } else {
            List<String> departments = userService.getChildCommunity(userId, departmentId);
            return mapper.countUnfinishedAlertsByDeps(departments, start, end);
        }
    }

    @Override
    public Map<String, Object> getUnfinishedAlertsHeader(String departmentId, Long userId, Date start, Date end) {
        if (userId == null) {
            return null;
        }
        AlertStatisticsService service = StatisticsHelper.getStatisticsService(start, end, departmentId, userId);
        Map<String, Object> map = new HashMap<>();
        map.put(TOTAL, service.failed());
        return map;
    }

    @Override
    public List<Map<String, Object>> getAlertsSummary(String departmentId, Long userId, Date start, Date end, SqlLimit limit) {
        if (userId == null) {
            return null;
        }
        List<String> childDepartments = userService.getChildCommunity(userId, departmentId, limit);
        if (ListUtils.isEmpty(childDepartments)) {
            return null;
        }
        AlertStatisticsService service = StatisticsHelper.getStatisticsService(start, end, departmentId, userId);
        String parentName = commonService.getDepartmentName(departmentId);
        List<Map<String, Object>> list = new ArrayList<>();
        for (String childId : childDepartments) {
            service.setDepartmentId(childId, userId, true);
            String name = commonService.getDepartmentName(childId);
            Map<String, Object> map = new HashMap<>();
            if (parentName == null) {
                map.put(AREA_NAME, commonService.getParentDepartmentName(childId));
            } else {
                map.put(AREA_NAME, parentName);
            }
            map.put(COMMUNITY_NAME, name);
            map.put(TOTAL, service.totalCount());
            map.put(GUARD_PROCESSED, service.needGuard());
            map.put(ABNORMAL_ALERTS, service.abnormal());
            map.put(SUSPECT_ALERTS, service.suspect());
            map.put(GUARD_OVER_TIME, service.guardOverTime());
            map.put(QUESTION_SUSPECT, service.hasQuestionSuspect());
            map.put(CALLBACK_AGAIN, service.callbackAgain());
            map.put(FAILED_ALERTS, service.failed());
            map.put(NO_GUARDS, service.noGuards());
            Integer count = commonService.getRelationDepartmentCount(childId);
            if (count != null && count == 1) {
                map.put(GUARD_TYPE, GUARD_SINGLE);
            } else {
                map.put(GUARD_TYPE, GUARD_AREA);
            }
            count = commonService.countDepartmentGuards(childId);
            map.put(GUARD_COUNT, count);
            list.add(map);
        }
        return list;
    }

    @Override
    public int countSummary(String departmentId, Long userId, Date start, Date end) {
        return userService.countChildCommunity(userId, departmentId);
    }

    @Override
    public List<Map<String, Object>> confirmQuestionSuspect(String departmentId, Long userId, Date start, Date end, SqlLimit limit) {
        if (userId == null) {
            return null;
        }
        List<Map<String, Object>> list;
        if (departmentId == null) {
            list = mapper.getQuestionSuspectByUser(userId, start, end, limit);
        } else {
            List<String> departments = userService.getChildCommunity(userId, departmentId);
            list = mapper.getQuestionSuspectByDeps(departments, start, end, limit);
        }
        return list;
    }

    @Override
    public int countConfirmQuestionSuspect(String departmentId, Long userId, Date start, Date end) {
        if (userId == null) {
            return 0;
        }
        if (departmentId == null) {
            return mapper.countQuestionSuspectByUser(userId, start, end);
        } else {
            List<String> departments = userService.getChildCommunity(userId, departmentId);
            return mapper.countQuestionSuspectByDeps(departments, start, end);
        }
    }

    @Override
    public List<Map<String, Object>> guardProcessedAlerts(String departmentId, Long userId, Date start, Date end, SqlLimit limit) {
        if (userId == null) {
            return null;
        }
        List<Map<String, Object>> list;
        if (departmentId == null) {
            list = mapper.getGuardProcessedAlertsByUser(userId, start, end, limit);
        } else {
            List<String> departments = userService.getChildCommunity(userId, departmentId);
            list = mapper.getGuardProcessedByDepartment(departments, start, end, limit);
        }
        return list;
    }

    @Override
    public Map<String, Object> guardProcessedAlertsHeader(String departmentId, Long userId, Date startTime, Date endTime) {
        AlertStatisticsService service = StatisticsHelper.getStatisticsService(startTime, endTime, departmentId, userId);
        int total = service.totalCount();
        int suspect = service.needGuard();
        int guardProcessed = service.guardProcessed();
        String fromMonth = getMonth(startTime);
        String fromDay = getDay(startTime);
        String toMonth = getMonth(endTime);
        String toDay = getDay(endTime);

        Map<String, Object> map = new HashMap<>();
        map.put(TOTAL, total);
        map.put(SUSPECT_ALERTS, suspect);
        map.put(GUARD_PROCESSED, guardProcessed);
        map.put(FROM_MONTH, fromMonth);
        map.put(FROM_DAY, fromDay);
        map.put(TO_MONTH, toMonth);
        map.put(TO_DAY, toDay);
        return map;
    }

    private String getMonth(Date date) {
        if (date == null) {
            return null;
        }
        DateFormat format = new SimpleDateFormat(MONTH_FORMAT);
        return format.format(date);
    }

    private String getDay(Date date) {
        if (date == null) {
            return null;
        }
        DateFormat format = new SimpleDateFormat(DAY_FORMAT);
        return format.format(date);
    }

    @Override
    public int countProcessedAlerts(String departmentId, Long userId, Date start, Date end) {
        if (userId == null) {
            return 0;
        }
        if (departmentId == null) {
            return mapper.countGuardProcessedByUser(userId, start, end);
        } else {
            List<String> departments = userService.getChildCommunity(userId, departmentId);
            return mapper.countGuardProcessedByDep(departments, start, end);
        }
    }
}
