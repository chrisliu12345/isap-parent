package com.gosun.isap.warn.api.alert.service;

import com.gosun.isap.dao.po.alert.base.BaseAlertLog;

import java.util.List;

/**
 * 警情日志服务<br>
 * <p>
 * <p>警情日志服务，不对外提供 RESTful 接口，只在内部调用，鉴权是在操作之前完成的。</p>
 * <p>记录到警情处理结束日志时，自动插入或更新警情统计信息</p>
 * <p>
 * <p>创建时间：2017/4/17 15:34</p>
 *
 * @author 娄存银
 * @version 1.0
 */
public interface AlertLogService {
    /**
     * 警情确认成功后，非误报警情调用，插入等待处理日志
     *
     * @param userId  用户 id
     * @param alertId 警情 id
     * @param alertType 警情类型
     * @return 请求结果
     */
    boolean needProcess(long userId, long alertId, byte alertType);

    /**
     * 调度保安
     *
     * @param userId    用户 id
     * @param alertId   警情 id
     * @param guardId   保安 id
     * @param orderInfo 命令信息 到 XXX 做 XXX
     * @return 请求结果
     */
    boolean callGuardSuccess(long userId, long alertId, long guardId, String orderInfo);


    /**
     * 联系保安失败
     *
     * @param userId     用户 id
     * @param alertId    警情 id
     * @param guardId    保安id
     * @param failedType 失败类型
     * @param reason     原因
     * @return 请求结果
     */
    boolean callGuardFailed(long userId, long alertId, long guardId, byte failedType, String reason);

    /**
     * 联系不到保安
     *
     * @param userId  用户 id
     * @param alertId 警情 id
     * @return 请求结果
     */
    boolean callGuardsFailed(long userId, long alertId);

    /**
     * 保安到达现场
     *
     * @param userId   用户 id
     * @param alertId  警情 id
     * @param guardId  保安 id
     * @param question 是否询问可疑人员
     * @return 请求结果
     */
    boolean guardArrive(long userId, long alertId, long guardId, boolean question);

    /**
     * 保安未到达
     *
     * @param userId  用户 id
     * @param alertId 警情 id
     * @param guardId 保安 id
     * @param reason  未到达原因
     * @return 请求结果
     */
    boolean guardNotArrive(long userId, long alertId, long guardId, String reason);

    /**
     * 保安回电
     *
     * @param userId          用户 id
     * @param alertId         警情 id
     * @param guardId         保安 id
     * @param callInfo        回电内容
     * @param questionSuspect 是否询问可疑人员
     * @return 请求结果
     */
    boolean guardCallBackAgain(long userId, long alertId, long guardId, String callInfo, boolean questionSuspect);


    /**
     * 保安盘问可疑人员
     *
     * @param userId  用户 id
     * @param alertId 警情 id
     * @param guardId 保安 id
     * @return 请求结果
     */
    boolean guardQuestionSuspect(long userId, long alertId, long guardId);

    /**
     * 保安未回电
     *
     * @param alertId 警情 id
     * @return 请求结果
     */
    boolean noCallback(long alertId);

    /**
     * 保安没有二次回电
     *
     * @param alertId 警情 id
     * @return 请求结果
     */
    boolean noCallbackAgain(long alertId);

    /**
     * 获取警情的处理日志列表
     *
     * @param alertId 警情 id
     * @return 日志列表
     */
    List<BaseAlertLog> getLogs(long alertId);
}
