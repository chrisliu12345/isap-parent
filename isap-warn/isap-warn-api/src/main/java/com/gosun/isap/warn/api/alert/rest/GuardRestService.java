package com.gosun.isap.warn.api.alert.rest;

import com.gosun.isap.dao.po.alert.Alert;
import com.gosun.isap.warn.api.alert.model.ResponseData;
import com.gosun.isap.warn.api.alert.model.statistics.StatisticsNode;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import java.util.List;
import java.util.Map;

/**
 * 保安的操作
 * <p>创建时间：2017-6-8 16:16</p>
 *
 * @author 娄存银
 * @version 1.0
 */
public interface GuardRestService extends RestConst{
    /**
     * @param guardId   保安 id
     * @param strDate   时间
     * @param startTime 开始时间
     * @param endTime   截止时间
     * @param start     分页
     * @param limit     分页
     * @param userId    用户 id
     * @return Alerts
     */
    ResponseData<List<Alert>> alerts(Long guardId, String strDate, String startTime, String endTime, Integer start, Integer limit, Long userId);

    /**
     * @param guardId   保安 id
     * @param strDate   时间
     * @param startTime 开始时间
     * @param endTime   截止时间
     * @param start     分页
     * @param limit     分页
     * @param userId    用户 id
     * @return Alerts
     */
    ResponseData<List<Map<String,Object>>> unfinished(Long guardId, String strDate, String startTime, String endTime, Integer start, Integer limit, Long userId);

    /**
     * @param guardId   保安 id
     * @param strDate   时间
     * @param startTime 开始时间
     * @param endTime   截止时间
     * @param start     分页
     * @param limit     分页
     * @param userId    用户 id
     * @return Alerts
     */
    ResponseData<List<Map<String,Object>>> questionedSuspect(Long guardId, String strDate, String startTime, String endTime, Integer start, Integer limit, Long userId);

    /**
     * @param guardId   保安 id
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @param userId    用户 id
     * @return 统计信息
     */
    ResponseData<List<StatisticsNode>> statistic(Long guardId, String startDate, String endDate, Long userId);

    /**
     * @param guardId 保安 id
     * @param input   表单数据
     * @param userId  用户 id
     * @return responseData
     */
    ResponseData confirmQuestionSuspect(Long guardId, MultipartFormDataInput input, Long userId);
}
