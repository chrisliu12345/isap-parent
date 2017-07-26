package com.gosun.isap.warn.api.alert.service;

import com.gosun.isap.dao.po.alert.Alert;
import com.gosun.isap.dao.po.alert.base.BaseAlert;
import com.gosun.isap.warn.api.alert.model.ResponseData;

import java.util.Date;

/**
 * 警情处理服务，请求成功返回 null ，请求失败，返回带数据的 ResponseData<br>
 * <p>提供警情确认，警情处理相关服务。包括警情确认，调度保安，取消调度保安，联系不上保安，保安回电，下一条警情。</p>
 * <p>每个方法都会调用相应的 AlertLogService 记录相关日志信息。</p>
 * <p>创建时间：2017/4/17 15:43</p>
 *
 * @author 娄存银
 * @version 1.0
 */
public interface AlertProcessService {
    /**
     * 警情确认
     *
     * @param alertId   警情 id
     * @param start     开始处理时间
     * @param end       完成确认时间
     * @param alertType 警情类型
     * @param userId    用户 id
     * @return 请求结果
     */
    ResponseData confirm(long alertId, Date start, Date end, byte alertType, long userId);

    /**
     * 联系保安失败
     *
     * @param guardId    保安 id
     * @param failedType 失败类型
     * @param reason     失败原因
     * @param alertId    警情 id
     * @param userId     用户 id
     * @return 请求结果
     */
    ResponseData callGuardFailed(long guardId, byte failedType, String reason, long alertId, long userId);


    /**
     * 联系不到保安，警情处理失败
     *
     * @param alertId 警情 id
     * @param userId  用户 id
     * @return 请求结果
     */
    ResponseData callGuardsFailed(long alertId, long userId);

    /**
     * 调度保安
     *
     * @param guardId 保安 id
     * @param alertId 警情 id
     * @param userId  用户 id
     * @return 请求结果
     */
    ResponseData callGuardSuccess(long guardId, long alertId, long userId);

    /**
     * 取消调度保安
     *
     * @param logId   调度 log id
     * @param reason  原因
     * @param alertId 警情 id
     * @param userId  用户 id
     * @return 请求结果
     */
    ResponseData cancelCommand(long logId, String reason, long alertId, long userId);


    /**
     * 保安回电
     *
     * @param guardId         保安 id
     * @param content         回电内容
     * @param arrive          是否到达现场
     * @param questionSuspect 是否询问可疑人员
     * @param alertId         警情 id
     * @param userId          用户 id
     * @return 请求结果
     */
    ResponseData guardArrive(long guardId, String content, boolean arrive, boolean questionSuspect, long alertId, long userId);

    /**
     * 保安未回电
     *
     * @param alertId 警情 id
     * @return 请求结果
     */
    boolean noCallback(long alertId);

    /**
     * 保安未回电
     *
     * @param alert 警情信息
     * @return 请求结果
     */
    boolean noCallback(BaseAlert alert);

    /**
     * 保安没有二次回电
     *
     * @param alertId 警情 id
     * @return 请求结果
     */
    boolean noCallbackAgain(long alertId);

    /**
     * 保安没有二次回电
     *
     * @param alert 警情信息
     * @return 请求结果
     */
    boolean noCallbackAgain(BaseAlert alert);

    /**
     * 保安二次回电
     *
     * @param guardId         保安 id
     * @param content         回电内容
     * @param questionSuspect 是否询问可疑人员
     * @param alertId         警情 id
     * @param userId          用户 id
     * @return 请求结果
     */
    ResponseData guardCallbackAgain(long guardId, String content, boolean questionSuspect, long alertId, long userId);

    /**
     * 是否未在规定时间内处理
     *
     * @param alert 警情
     * @return 处理成功
     */
    boolean unProcessedInTime(Alert alert);

    /**
     * 下一条警情
     *
     * @param userId 用户 id
     * @return 请求结果
     */
    Alert next(long userId);
}
