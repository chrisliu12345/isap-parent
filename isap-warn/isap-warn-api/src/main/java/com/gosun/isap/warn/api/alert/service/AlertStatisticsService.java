package com.gosun.isap.warn.api.alert.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 警情信息统计<br>
 * <p>用于查找警情统计警情数据</p>
 * 创建时间：2017/4/14 15:22
 *
 * @author 娄存银
 * @version 1.0
 */
public interface AlertStatisticsService {
    /**
     * 警情总数
     *
     * @return totalCount
     */
    int totalCount();

    /**
     * 处理完成的警情数
     *
     * @return finished
     */
    int finished();

    /**
     * 处理中的警情数
     *
     * @return processing
     */
    int processing();

    /**
     * 等待处理的警情数
     *
     * @return waitingProcess
     */
    int waitingProcess();

    /**
     * 未处理的警情数
     *
     * @return unprocessed
     */
    int unprocessed();

    /**
     * 误报的警情数
     *
     * @return falseAlerts
     */
    int falseAlerts();

    /**
     * 异常出警数
     *
     * @return abnormal
     */
    int abnormal();

    /**
     * 可疑出警数
     *
     * @return suspect
     */
    int suspect();

    /**
     * 处理成功的警情数
     *
     * @return successful
     */
    int successful();

    /**
     * 处理失败的警情数
     *
     * @retur failedn
     */
    int failed();

    /**
     * 询问可疑人员的次数
     *
     * @return hasQuestionSuspect
     */
    int hasQuestionSuspect();

    /**
     * 等待回电的警情数
     *
     * @return waitingCallback
     */
    int waitingCallback();

    /**
     * 等待二次回电的警情数
     *
     * @return waitingCallbackAgain
     */
    int waitingCallbackAgain();

    /**
     * 找不到保安出警的警情数
     *
     * @return noGuards
     */
    int noGuards();

    /**
     * 保安超时的警情数
     *
     * @return guardOverTime
     */
    int guardOverTime();

    /**
     * 保安未到达的警情数
     *
     * @return notArrive
     */
    int notArrive();

    /**
     * 保安未回电的警情数
     *
     * @return noCallback
     */
    int noCallback();

    /**
     * 初始化方法
     *
     * @param start    开始时间
     * @param end      截止时间
     * @param parentId 父部门 id
     * @param userId   用户 id
     */
    void init(Date start, Date end, String parentId, Long userId);

    /**
     * 初始化方法
     *
     * @param start        开始时间
     * @param end          截止时间
     * @param departmentIds 部门 id
     */
    void init(Date start, Date end, List<String> departmentIds);

    /**
     * 设置要统计的部门 id
     *
     * @param departmentId 部门 id
     * @param userId       用户 id
     * @param child        是否为子部门
     */
    void setDepartmentId(String departmentId, Long userId, boolean child);

    /**
     * 需要出警的警情数量
     *
     * @return needGuard
     */
    int needGuard();

    /**
     * 保安处理过的警情数量
     *
     * @return guardProcessed
     */
    int guardProcessed();

    /**
     * 警情平均确认时间
     *
     * @return getAverageConfirmTime
     */
    Double getAverageConfirmTime();

    /**
     * 用户从警情发生到警情确认平均用时
     *
     * @return getAverageUserResponseTime
     */
    Double getAverageUserResponseTime();

    /**
     * 保安平均出警时间
     *
     * @return getAverageGuardResponseTime
     */
    Double getAverageGuardResponseTime();

    /**
     * 警情发生时，平均未处理警情数
     *
     * @return getAverageUnprocessedAlerts
     */
    Double getAverageUnprocessedAlerts();

    /**
     * 警情发生到警情确认期间平均确认的警情数
     *
     * @return getAverageConfirmedAlerts
     */
    Double getAverageConfirmedAlerts();

    /**
     * 回电次数
     *
     * @return callback
     */
    int callback();

    /**
     * 二次回电次数
     *
     * @return callbackAgain
     */
    int callbackAgain();
}
