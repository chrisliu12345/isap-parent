package com.gosun.isap.warn.api.alert.rest;

import com.gosun.isap.warn.api.alert.model.ResponseData;
import com.gosun.isap.dao.po.alert.base.BaseAlert;
import com.gosun.isap.warn.api.alert.to.AlertConfirm;
import com.gosun.isap.warn.api.alert.to.Callback;

/**
 * 警情处理服务<br>
 * <p>提供警情确认，警情处理相关服务。包括警情确认，调度保安，取消调度保安，联系不上保安，保安回电，下一条警情。</p>
 * <p>每个方法都会调用相应的 AlertLogService 记录相关日志信息。</p>
 * <p>创建时间：2017/4/17 15:43</p>
 *
 * @author 娄存银
 * @version 1.0
 */
public interface AlertProcessingRestService extends RestConst {
    /**
     * 警情确认
     *
     * @param confirm 警情确认信息
     * @param userId  用户 id
     * @return 请求结果
     */
    ResponseData confirm(AlertConfirm confirm, Long userId);

    /**
     * 保安回电
     *
     * @param callback 回电信息
     * @param userId   用户 id
     * @return 请求结果
     */
    ResponseData guardFirstCallback(Callback callback, Long userId);

    /**
     * 保安二次回电
     *
     * @param callback 回电信息
     * @param userId   用户 id
     * @return 请求结果
     */
    ResponseData guardSecondCallback(Callback callback, Long userId);

    /**
     * 下一条警情
     *
     * @param userId 用户 id
     * @return 请求结果
     */
    BaseAlert next(Long userId);
}
