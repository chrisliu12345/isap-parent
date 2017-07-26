package com.gosun.isap.warn.api.alert.rest;

import com.gosun.isap.dao.po.alert.Alert;
import com.gosun.isap.warn.api.alert.model.ResponseData;
import com.gosun.isap.warn.api.alert.model.statistics.AverageItem;
import com.gosun.isap.warn.api.alert.model.statistics.StatisticsDiagramData;
import com.gosun.isap.warn.api.alert.model.statistics.StatisticsNode;

import java.util.List;
import java.util.Map;

/**
 * <p>创建时间：2017-6-8 16:47</p>
 *
 * @author 娄存银
 * @version 1.0
 */
public interface GuardGroupRestService extends RestConst{
    /**
     * @param groupId   保安 id
     * @param strDate   时间
     * @param startTime 开始时间
     * @param endTime   截止时间
     * @param start     分页
     * @param limit     分页
     * @param userId    用户 id
     * @return Alerts
     */
    ResponseData<List<Alert>> alerts(Long groupId, String strDate, String startTime, String endTime, Integer start, Integer limit, Long userId);

    /**
     * @param groupId   保安 id
     * @param strDate   时间
     * @param startTime 开始时间
     * @param endTime   截止时间
     * @param start     分页
     * @param limit     分页
     * @param userId    用户 id
     * @return Alerts
     */
    ResponseData<List<Map<String,Object>>> unfinished(Long groupId, String strDate, String startTime, String endTime, Integer start, Integer limit, Long userId);

    /**
     * @param groupId   保安 id
     * @param strDate   时间
     * @param startTime 开始时间
     * @param endTime   截止时间
     * @param start     分页
     * @param limit     分页
     * @param userId    用户 id
     * @return Alerts
     */
    ResponseData<List<Map<String,Object>>> questionedSuspect(Long groupId, String strDate, String startTime, String endTime, Integer start, Integer limit, Long userId);

    /**
     * @param date    日期
     * @param start   开始时间
     * @param end     截止时间
     * @param groupId 保安组 id
     * @param userId  用户 id
     * @return 数量统计
     */
    ResponseData<List<StatisticsNode>> getStatisticsItem(String date, String start, String end, Long groupId, Long userId);

    /**
     * @param date    日期
     * @param start   开始时间
     * @param end     截止时间
     * @param groupId 保安组 id
     * @param userId  用户 id
     * @return 平均数据统计
     */
    ResponseData<List<AverageItem>> getAverageItem(String date, String start, String end, Long groupId, Long userId);

    /**
     * @param date    日期
     * @param start   开始时间
     * @param end     截止时间
     * @param groupId 保安组 id
     * @param userId  用户 id
     * @return 表格数据
     */
    ResponseData<List<StatisticsDiagramData>> getDiagramData(String date, String start, String end, Long groupId, Long userId);
}
