package com.gosun.isap.warn.api.alert.service;

import com.gosun.isap.dao.mapper.alert.SqlLimit;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 表格导出数据 Service 。
 * <p>创建时间：2017-5-25 14:14</p>
 *
 * @author 娄存银
 * @version 1.0
 */
public interface AlertExportService {
    /**
     * date
     */
    String DATE = "date";
    /**
     * userName
     */
    String USER_NAME = "userName";
    /**
     * processedCount
     */
    String PROCESSED_COUNT = "processedCount";
    /**
     * alertsCount
     */
    String ALERTS_COUNT = "alertsCount";
    /**
     * callbackCount
     */
    String CALLBACK_COUNT = "callbackCount";
    /**
     * failedReason
     */
    String FAILED_REASON = "failedReason";
    /**
     * status
     */
    String STATUS = "status";
    /**
     * arrivedTime
     */
    String ARRIVED_TIME = "arrivedTime";
    /**
     * total
     */
    String TOTAL = "total";
    /**
     * 未到达
     */
    String NOT_ARRIVE = "未到达";

    /**
     * areaName
     */
    String AREA_NAME = "areaName";
    /**
     * communityName
     */
    String COMMUNITY_NAME = "communityName";
    /**
     * guardProcessed
     */
    String GUARD_PROCESSED = "guardProcessed";
    /**
     * abnormalAlerts
     */
    String ABNORMAL_ALERTS = "abnormalAlerts";
    /**
     * suspectAlerts
     */
    String SUSPECT_ALERTS = "suspectAlerts";
    /**
     * guardOverTime
     */
    String GUARD_OVER_TIME = "guardOverTime";
    /**
     * questionSuspect
     */
    String QUESTION_SUSPECT = "questionSuspect";
    /**
     * callbackAgain
     */
    String CALLBACK_AGAIN = "callbackAgain";
    /**
     * failedAlerts
     */
    String FAILED_ALERTS = "failedAlerts";
    /**
     * halfDeviceOffline
     */
    String HALF_DEVICE_OFFLINE = "halfDeviceOffline";
    /**
     * noGuards
     */
    String NO_GUARDS = "noGuards";
    /**
     * otherFailedReason
     */
    String OTHER_FAILED_REASON = "otherFailedReason";
    /**
     * guardType
     */
    String GUARD_TYPE = "guardType";
    /**
     * guardCount
     */
    String GUARD_COUNT = "guardCount";
    /**
     * 连片
     */
    String GUARD_AREA = "连片";
    /**
     * 单独
     */
    String GUARD_SINGLE = "单独";

    /**
     * fromMonth
     */
    String FROM_MONTH = "fromMonth";
    /**
     * fromDay
     */
    String FROM_DAY = "fromDay";
    /**
     * toMonth
     */
    String TO_MONTH = "toMonth";
    /**
     * toDay
     */
    String TO_DAY = "toDay";
    /**
     * MM
     */
    String MONTH_FORMAT = "MM";
    /**
     * dd
     */
    String DAY_FORMAT = "dd";

    /**
     * @param departmentId 部门 id
     * @param userId       用户 id
     * @param start        开始时间
     * @param end          截止时间
     * @param limit        分页
     * @return 出警记录数据
     */
    List<Map<String, Object>> alertLogBody(String departmentId, Long userId, Date start, Date end, SqlLimit limit);

    /**
     * @param departmentId 部门 id
     * @param userId       用户 id
     * @param start        开始时间
     * @param end          截止时间
     * @return 出警记录条数
     */
    int countAlertLog(String departmentId, Long userId, Date start, Date end);

    /**
     * @param departmentId 部门 id
     * @param userId       用户 id
     * @param start        开始时间
     * @param end          截止时间
     * @return 出警记录表头
     */
    Map<String, Object> alertLogHeader(String departmentId, Long userId, Date start, Date end);

    /**
     * @param departmentId 部门 id
     * @param userId       用户 id
     * @param start        开始时间
     * @param end          截止时间
     * @param limit        分页
     * @return 警情详情数据
     */
    List<Map<String, Object>> alertDetailLogBody(String departmentId, Long userId, Date start, Date end, SqlLimit limit);

    /**
     * @param departmentId 部门 id
     * @param userId       用户 id
     * @param start        开始时间
     * @param end          截止时间
     * @return 警情详情条数
     */
    int countAlertDetailLog(String departmentId, Long userId, Date start, Date end);

    /**
     * @param departmentId 部门 id
     * @param userId       用户 id
     * @param start        开始时间
     * @param end          截止时间
     * @return 警情详情表头
     */
    Map<String, Object> alertDetailLogHeader(String departmentId, Long userId, Date start, Date end);

    /**
     * @param departmentId 部门 id
     * @param userId       用户 id
     * @param start        开始时间
     * @param end          截止时间
     * @param limit        分页
     * @return 未完成出警数据
     */
    List<Map<String, Object>> getUnfinishedAlertsBody(String departmentId, Long userId, Date start, Date end, SqlLimit limit);

    /**
     * @param departmentId 部门 id
     * @param userId       用户 id
     * @param start        开始时间
     * @param end          截止时间
     * @return 未完成出警条数
     */
    int countUnfinishedAlerts(String departmentId, Long userId, Date start, Date end);

    /**
     * @param departmentId 部门 id
     * @param userId       用户 id
     * @param start        开始时间
     * @param end          截止时间
     * @return 未完成出警表头
     */
    Map<String, Object> getUnfinishedAlertsHeader(String departmentId, Long userId, Date start, Date end);

    /**
     * @param departmentId 部门 id
     * @param userId       用户 id
     * @param start        开始时间
     * @param end          截止时间
     * @param limit        分页
     * @return 警情汇总数据
     */
    List<Map<String, Object>> getAlertsSummary(String departmentId, Long userId, Date start, Date end, SqlLimit limit);

    /**
     * @param departmentId 部门 id
     * @param userId       用户 id
     * @param start        开始时间
     * @param end          截止时间
     * @return 警情汇总条数
     */
    int countSummary(String departmentId, Long userId, Date start, Date end);

    /**
     * @param departmentId 部门 id
     * @param userId       用户 id
     * @param start        开始时间
     * @param end          截止时间
     * @param limit        分页
     * @return 可疑人员询问确认表数据
     */
    List<Map<String, Object>> confirmQuestionSuspect(String departmentId, Long userId, Date start, Date end, SqlLimit limit);

    /**
     * @param departmentId 部门 id
     * @param userId       用户 id
     * @param start        开始时间
     * @param end          截止时间
     * @return 可疑人员询问确认表条数
     */
    int countConfirmQuestionSuspect(String departmentId, Long userId, Date start, Date end);

    /**
     * @param departmentId 部门 id
     * @param userId       用户 id
     * @param start        开始时间
     * @param end          截止时间
     * @param limit        分页
     * @return 保安出警记录数据
     */
    List<Map<String, Object>> guardProcessedAlerts(String departmentId, Long userId, Date start, Date end, SqlLimit limit);

    /**
     * @param departmentId 部门 id
     * @param userId       用户 id
     * @param start        开始时间
     * @param end          截止时间
     * @return 保安出警记录表头
     */
    Map<String, Object> guardProcessedAlertsHeader(String departmentId, Long userId, Date start, Date end);

    /**
     * @param departmentId 部门 id
     * @param userId       用户 id
     * @param start        开始时间
     * @param end          截止时间
     * @return 保安出警记录条数
     */
    int countProcessedAlerts(String departmentId, Long userId, Date start, Date end);
}
