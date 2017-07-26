package com.gosun.isap.warn.api.alert.rest;

import com.gosun.isap.warn.api.alert.model.statistics.AverageItem;
import com.gosun.isap.warn.api.alert.model.ResponseData;
import com.gosun.isap.warn.api.alert.model.statistics.StatisticsDiagramData;
import com.gosun.isap.warn.api.alert.model.statistics.StatisticsNode;

import java.util.Date;
import java.util.List;

/**
 * 警情统计服务。
 * <p>创建时间：2017-5-22 15:27</p>
 *
 * @author 娄存银
 * @version 1.0
 */
public interface AlertStatisticsRestService extends RestConst {
    /**
     * @param date         日期
     * @param start        开始时间
     * @param end          截止时间
     * @param departmentId 部门 id
     * @param userId       用户 id
     * @return 数量统计
     */
    ResponseData<List<StatisticsNode>> getStatisticsItem(String date, String start, String end, String departmentId, Long userId);

    /**
     * @param date         日期
     * @param start        开始时间
     * @param end          截止时间
     * @param departmentId 部门 id
     * @param userId       用户 id
     * @return 平均数据统计
     */
    ResponseData<List<AverageItem>> getAverageItem(String date, String start, String end, String departmentId, Long userId);

    /**
     * @param date         日期
     * @param start        开始时间
     * @param end          截止时间
     * @param departmentId 部门 id
     * @param userId       用户 id
     * @return 表格数据
     */
    ResponseData<List<StatisticsDiagramData>> getDiagramData(String date, String start, String end, String departmentId, Long userId);
}
