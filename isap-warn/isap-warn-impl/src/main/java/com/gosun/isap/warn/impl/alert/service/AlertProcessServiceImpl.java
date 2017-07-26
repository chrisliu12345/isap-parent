package com.gosun.isap.warn.impl.alert.service;

import com.gosun.isap.dao.mapper.alert.AlertMapper;
import com.gosun.isap.dao.mapper.alert.AlertsCountMapper;
import com.gosun.isap.dao.mapper.alert.base.AlertBaseMapper;
import com.gosun.isap.dao.mapper.alert.base.GuardAlertBaseMapper;
import com.gosun.isap.dao.po.alert.Alert;
import com.gosun.isap.dao.po.alert.base.BaseAlert;
import com.gosun.isap.dao.po.alert.base.BaseGuardAlert;
import com.gosun.isap.warn.api.alert.AlertConst;
import com.gosun.isap.warn.api.alert.model.ResponseData;
import com.gosun.isap.warn.api.alert.model.enumeration.AlertError;
import com.gosun.isap.warn.api.alert.model.enumeration.AlertResourceType;
import com.gosun.isap.warn.api.alert.model.enumeration.AlertStatus;
import com.gosun.isap.warn.api.alert.model.enumeration.AlertType;
import com.gosun.isap.warn.api.alert.model.enumeration.CallFailedReason;
import com.gosun.isap.warn.api.alert.service.AlertLogService;
import com.gosun.isap.warn.api.alert.service.AlertProcessService;
import com.gosun.isap.warn.api.alert.service.AlertService;
import com.gosun.isap.warn.impl.alert.util.PictureCaptureUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 警情处理服务
 * <p>创建时间：2017/5/6 15:12</p>
 *
 * @author 娄存银
 * @version 1.0
 */
@Service
public class AlertProcessServiceImpl implements AlertProcessService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AlertProcessServiceImpl.class);
    private static final String LOG_ERROR = "写入警情日志失败";

    @Autowired
    private AlertBaseMapper alertBaseMapper;

    @Autowired
    private AlertMapper alertMapper;

    @Autowired
    private AlertLogService alertLogService;

    @Autowired
    private AlertService alertService;

    @Autowired
    private AlertsCountMapper countMapper;

    @Autowired
    private GuardAlertBaseMapper guardAlertBaseMapper;

    @Autowired
    private PictureCaptureUtil captureUtil;

    @Override
    public ResponseData confirm(long alertId, Date start, Date end, byte alertType, long userId) {
        // 获取警情信息
        BaseAlert alert = alertBaseMapper.selectByPrimaryKey(alertId);

        // 验证警情信息
        ResponseData data = checkStatus(alert, AlertStatus.UNPROCESSED);
        if (data != null) {
            return data;
        }

        // 更新警情信息
        alert.setAlertType(alertType);
        alert.setUserId(userId);
        alert.setUnprocessedAlerts(alertService.getCountOfUnprocessedAlerts(userId,alert));

        // 警情确认时间
        if(start != null && end != null) {
            int confirmTime = (int) ((end.getTime() - start.getTime()) / AlertConst.SECOND_TO_MILLISECOND);
            Date serverEnd = new Date();
            Date serverStart = new Date(serverEnd.getTime() - confirmTime * AlertConst.SECOND_TO_MILLISECOND);
            alert.setConfirmStartTime(serverStart);
            alert.setConfirmEndTime(serverEnd);
            alert.setConfirmTime(confirmTime);
        }

        alert.setConfirmedAlerts(countMapper.countConfirmedInResponseTime(alert.getAddTime(),null,userId));

        // 更新警情状态
        AlertType type = AlertType.get(alertType);
        if (type == null){
            return ResponseData.error(AlertError.ALERT_TYPE_NOT_EXIST);
        }
        switch (type) {
            case FALSE_ALERT:
            case FALSE_ALERT_MANUAL:
                alert.setStatus(AlertStatus.PROCESSED_SUCCESS.getStatus());
                alert.setFinishTime(new Date());
                break;
            case NORMAL_ALERT:
            case ABNORMAL_ALERT:
                alert.setStatus(AlertStatus.WAITING_PROCESS.getStatus());
                break;
            default:
                break;
        }

        int row = alertBaseMapper.updateByPrimaryKeySelective(alert);
        if (row < 1) {
            return ResponseData.error(AlertError.UPDATE_ALERT_FAILED);
        }

        // 记录警情日志
        if(!alertLogService.needProcess(userId, alertId,alertType)){
            return ResponseData.error(AlertError.INSERT_LOG_FAILED);
        }

        return null;
    }

    @Override
    public ResponseData callGuardFailed(long guardId, byte failedType, String reason, long alertId, long userId) {
        // 不值班不记录
        CallFailedReason failedReason = CallFailedReason.getReason(failedType);
        if(failedReason == CallFailedReason.OFF_WORK){
            return null;
        }
        
        // 验证警情信息
        ResponseData data = checkStatus(alertId, AlertStatus.WAITING_PROCESS);
        if (data != null) {
            return data;
        }

        if (reason == null || reason.isEmpty()) {
            reason = CallFailedReason.getReason(failedType).getReason();
        }

        if (alertLogService.callGuardFailed(userId, alertId, guardId, failedType, reason)) {
            return null;
        }

        return ResponseData.error(AlertError.INSERT_LOG_FAILED);
    }

    @Override
    public ResponseData callGuardsFailed(long alertId, long userId) {
        // 验证警情信息
        ResponseData data = checkStatus(alertId, AlertStatus.WAITING_PROCESS);
        if (data != null) {
            return data;
        }

        // 插入日志
        if (!alertLogService.callGuardsFailed(userId, alertId)) {
            return ResponseData.error(AlertError.INSERT_LOG_FAILED);
        }

        // 更新警情处理状态
        int row = alertMapper.updateStatusAndFinishTime(alertId, AlertStatus.FAILED_NO_GUARD.getStatus(),new Date());
        if (row < 1) {
            return ResponseData.error(AlertError.UPDATE_ALERT_FAILED);
        }

        return null;
    }

    @Override
    public ResponseData callGuardSuccess(long guardId, long alertId, long userId) {
        // 验证警情信息
        ResponseData data = checkStatus(alertId, AlertStatus.WAITING_PROCESS);
        if (data != null) {
            return data;
        }

        // 插入日志
        if (!alertLogService.callGuardSuccess(userId, alertId, guardId, null)) {
            return ResponseData.error(AlertError.INSERT_LOG_FAILED);
        }

        // 更新状态
        int row = alertMapper.updateStatus(alertId, AlertStatus.WAITING_CALLBACK.getStatus());
        if (row < 1) {
            return ResponseData.error(AlertError.UPDATE_ALERT_FAILED);
        }

        // 绑定保安与警情
        BaseGuardAlert guardAlert = new BaseGuardAlert();
        guardAlert.setGuardId(guardId);
        guardAlert.setAlertId(alertId);
        row = guardAlertBaseMapper.insertSelective(guardAlert);
        if(row < 1){
            return ResponseData.error(AlertError.INSERT_GUARD_ALERT_FAILED);
        }

        return null;
    }

    @Override
    public ResponseData cancelCommand(long logId, String reason, long alertId, long userId) {
        return null;
    }

    @Override
    public ResponseData guardArrive(long guardId, String content, boolean arrive, boolean questionSuspect, long alertId, long userId) {
        //验证警情信息
        ResponseData data = checkStatus(alertId, AlertStatus.WAITING_CALLBACK);
        if (data != null) {
            return data;
        }

        // 未到达现场
        if (!arrive) {
            // 插入日志
            if (!alertLogService.guardNotArrive(userId, alertId, guardId, content)) {
                LOGGER.error("guardArrive : {}",LOG_ERROR);
            }

            // 更新警情状态
            int row = alertMapper.updateStatusAndFinishTime(alertId, AlertStatus.FAILED_GUARD_NOT_ARRIVED.getStatus(),new Date());
            if (row < 1) {
                LOGGER.error("guardArrive : {}",LOG_ERROR);
            }

            return null;
        }

        // 截图保存
        captureUtil.capturePicture(alertId, AlertResourceType.GUARD_ARRIVE_PICTURE.getType());

        // 插入日志
        if (!alertLogService.guardArrive(userId, alertId, guardId,questionSuspect)) {
            LOGGER.error("guardArrive : {}",LOG_ERROR);
        }

        // 已盘查可疑人员
        int row ;
        if (questionSuspect) {
            if (!alertLogService.guardQuestionSuspect(userId, alertId, guardId)) {
                LOGGER.error("guardArrive : {}",LOG_ERROR);
            }
            // 统计警情信息
            if (!alertService.generateAlertDetail(alertId)) {
                return ResponseData.error(AlertError.GENERATE_ALERT_DETAIL_FAILED);
            }
            // 更新状态
            row = alertMapper.updateStatusAndFinishTime(alertId, AlertStatus.PROCESSED_SUCCESS.getStatus(),new Date());
        } else {
            // 更新状态
            row = alertMapper.updateStatus(alertId, AlertStatus.WAITING_CALLBACK_AGAIN.getStatus());
        }

        return row<1?ResponseData.error(AlertError.UPDATE_ALERT_FAILED):null;
    }

    @Override
    public boolean noCallback(long alertId) {
       BaseAlert alert = alertBaseMapper.selectByPrimaryKey(alertId);
       return noCallback(alert);
    }

    @Override
    public boolean noCallback(BaseAlert alert) {
        ResponseData data = checkStatus(alert,AlertStatus.WAITING_CALLBACK);
        if (data != null) {
            return false;
        }

        // 记录日志
        if (!alertLogService.noCallback(alert.getId())) {
            return false;
        }

        // 更新警情状态
        int row = alertMapper.updateStatusAndFinishTime(alert.getId(), AlertStatus.FAILED_NO_CALLBACK.getStatus(),new Date());
        return row > 0;
    }

    @Override
    public boolean noCallbackAgain( long alertId) {
        BaseAlert alert = alertBaseMapper.selectByPrimaryKey(alertId);
        return noCallbackAgain(alert);
    }

    @Override
    public boolean noCallbackAgain(BaseAlert alert) {
        //验证警情信息
        ResponseData data = checkStatus(alert, AlertStatus.WAITING_CALLBACK_AGAIN);
        if (data != null) {
            return false;
        }
        // 记录日志
        if (!alertLogService.noCallbackAgain(alert.getId())) {
            return false;
        }

        // 更新警情状态
        int row = alertMapper.updateStatusAndFinishTime(alert.getId(), AlertStatus.PROCESSED_SUCCESS.getStatus(),new Date());
        if(row < 1){
            return false;
        }

        // 统计警情信息
        return alertService.generateAlertDetail(alert.getId());
    }

    @Override
    public ResponseData guardCallbackAgain(long guardId, String content,  boolean questionSuspect, long alertId, long userId) {
        //验证警情信息
        ResponseData data = checkStatus(alertId, AlertStatus.WAITING_CALLBACK_AGAIN);
        if (data != null) {
            return data;
        }

        // 插入日志
        if (!alertLogService.guardCallBackAgain(userId, alertId, guardId, content,questionSuspect)) {
            LOGGER.error("guardCallbackAgain : {}",LOG_ERROR);
        }

        // 已盘查可疑人员
        if (questionSuspect) {
            if (!alertLogService.guardQuestionSuspect(userId, alertId, guardId)) {
                LOGGER.error("guardCallbackAgain : {}",LOG_ERROR);
            }
        }

        // 更新警情状态
        int row = alertMapper.updateStatusAndFinishTime(alertId, AlertStatus.PROCESSED_SUCCESS.getStatus(),new Date());
        if (row < 1) {
            return ResponseData.error(AlertError.UPDATE_ALERT_FAILED);
        }

        // 统计警情信息
        if (!alertService.generateAlertDetail(alertId)) {
            return ResponseData.error(AlertError.GENERATE_ALERT_DETAIL_FAILED);
        }

        return null;
    }

    @Override
    public boolean unProcessedInTime(Alert alert) {

        // 修改警情状态
        alert.setIsOverTime(true);
        alert.setStatus(AlertStatus.FAILED_UNPROCESSED.getStatus());
        alertBaseMapper.updateByPrimaryKeySelective(alert);

        return false;
    }


    @Override
    public Alert next(long userId) {
        return null;
    }

    private ResponseData checkStatus(long alertId, AlertStatus alertStatus) {
        System.out.printf("check status alertId : %d,alert status %d %n",alertId,alertStatus.getStatus());
        // 验证警情信息
        BaseAlert alert = alertBaseMapper.selectByPrimaryKey(alertId);
        return checkStatus(alert, alertStatus);
    }

    private ResponseData checkStatus(BaseAlert alert, AlertStatus alertStatus) {
        if(alert == null){
            return ResponseData.error(AlertError.ALERT_NOT_EXIST);
        }

        // 验证警情信息
        Byte status = alert.getStatus();
        if (status == null) {
            return ResponseData.error(AlertError.UNKNOWN_STATUS);
        } else if (status != alertStatus.getStatus()) {
            return ResponseData.error(AlertError.ERROR_STATUS);
        }
        return null;
    }
}
