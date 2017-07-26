package com.gosun.isap.warn.api.alert.rest;


import com.gosun.isap.dao.po.alert.Alert;
import com.gosun.isap.warn.api.alert.model.ResponseData;

import java.util.List;

/**
 * 警情列表服务<br>
 * <p>主要用于警情列表的查询，</p>
 * <p>通用参数：</p>
 * <p>userId 、token 为必须参数，用于用户鉴权。</p>
 * <p>page、pageSize 可以为空，对于处理中、以及未处理的警情，不进行分页，默认显示全部列表；对于处理过的，以及全部状态的
 * 列表，默认分页显示第一页。</p>
 *
 * @author 娄存银
 * @version 1.0
 *          <p>
 *          创建时间： 2017/4/14 14:54
 */
public interface AlertListRestService extends RestConst {

    /**
     * 获取未处理的警情列表
     *
     * @param start  分页选项
     * @param limit  分页选项
     * @param userId 用户 id
     * @return 警情列表
     */

    ResponseData<List<Alert>> getUnprocessedAlerts(Integer start, Integer limit, Long userId);

    /**
     * 处于等待状态的警情列表(等待保安回电与等待保安二次回电)
     *
     * @param start  分页选项
     * @param limit  分页选项
     * @param userId 用户 id
     * @return 警情列表
     */
    ResponseData<List<Alert>> getWaitingAlerts(Integer start, Integer limit, Long userId);

    /**
     * 处于等待保安回电状态的警情列表
     *
     * @param start  分页选项
     * @param limit  分页选项
     * @param userId 用户 id
     * @return 警情列表
     */

    ResponseData<List<Alert>> getWaitingCallAlerts(Integer start, Integer limit, Long userId);

    /**
     * 处于等待保安二次回电状态的警情列表
     *
     * @param start  分页选项
     * @param limit  分页选项
     * @param userId 用户 id
     * @return 警情列表
     */

    ResponseData<List<Alert>> getWaitingSecondCallAlerts(Integer start, Integer limit, Long userId);


    /**
     * 获取处理过的警情列表，包括处理完成的和处理失败的
     *
     * @param userId 用户 id
     * @param offset 偏移量
     * @param size   条数
     * @return 警情列表
     */

    ResponseData<List<Alert>> getProcessedAlerts(Integer offset, Integer size, Long userId);

    /**
     * 获取全部警情列表，支持按指定列，指定次序排序。
     *
     * @param userId       用户 id
     * @param departmentId 区域 id , 需要验证用户是否有对相关区域的操作权限，为空就获取所有相关区域
     * @param dateStr      日期
     * @param startTime    起始日期，可以为空
     * @param endTime      截止日期，默认当前时间
     * @param status       处理状态
     * @param sort         排序依据，默认按警情发生时间，倒序排序
     * @param start        偏移量
     * @param limit        数量
     * @return 警情列表
     */
    ResponseData<List<Alert>> getAllAlerts(String departmentId, String dateStr, String startTime, String endTime,
                                           Integer status, Integer startStatus, Integer endStatus,
                                           String sort, Integer start, Integer limit, Long userId);

}
