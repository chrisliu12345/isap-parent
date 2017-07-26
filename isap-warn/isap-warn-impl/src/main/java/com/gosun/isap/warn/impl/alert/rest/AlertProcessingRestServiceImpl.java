package com.gosun.isap.warn.impl.alert.rest;

import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.gosun.isap.dao.po.alert.base.BaseAlert;
import com.gosun.isap.operlog.api.annotation.SysOperateLog;
import com.gosun.isap.warn.api.alert.model.ResponseData;
import com.gosun.isap.warn.api.alert.model.enumeration.AlertError;
import com.gosun.isap.warn.api.alert.model.enumeration.AlertType;
import com.gosun.isap.warn.api.alert.rest.AlertProcessingRestService;
import com.gosun.isap.warn.api.alert.service.AlertListService;
import com.gosun.isap.warn.api.alert.service.AlertProcessService;
import com.gosun.isap.warn.api.alert.to.AlertConfirm;
import com.gosun.isap.warn.api.alert.to.CallGuard;
import com.gosun.isap.warn.api.alert.to.Callback;
import com.gosun.isap.warn.impl.alert.aop.CheckEmptyParams;
import com.gosun.isap.warn.impl.alert.aop.CheckProcessMenuPermission;
import com.gosun.isap.warn.impl.alert.aop.OperationLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

import static com.gosun.isap.warn.api.alert.rest.RestConst.ROOT_PATH;
import static com.gosun.isap.warn.impl.alert.aop.OperationLog.*;

/**
 * <p>创建时间：2017/5/16 8:48</p>
 *
 * @author 娄存银
 * @version 1.0
 */
@Path(ROOT_PATH)
@Consumes({MediaType.APPLICATION_JSON})
@Produces({ContentType.APPLICATION_JSON_UTF_8})
@CheckProcessMenuPermission
@CheckEmptyParams
public class AlertProcessingRestServiceImpl implements AlertProcessingRestService {
    private static final int MAX_PROCESSING = 9;
    @Autowired
    private AlertProcessService processService;
    @Autowired
    private AlertListService listService;

    @Override
    @POST
    @Path(ALERTS_CONFIRM)
    @Transactional
    @SysOperateLog(serviceType = ALERT_PROCESS, operateType = ADD, description = "警情确认")
    public ResponseData confirm(AlertConfirm confirm, @HeaderParam(USER_ID) Long userId) {
        userId = init(userId);
        String invalidColumns = confirm.getInvalidColumns();
        if (invalidColumns != null) {
            return ResponseData.error(AlertError.INVALID_PARAMS, invalidColumns);
        }

        // 判断当前处于等待回电状态的警情条数
        int count = listService.countWaitingCallbackAlerts(userId);
        if(count > MAX_PROCESSING){
            return ResponseData.error(AlertError.OVER_MAX_PROGRESSING_COUNT);
        }

        // 不是误报，也不是处理失败，但是没有联系保安的信息
        List<CallGuard> guards = confirm.getGuards();
        if (!AlertType.isFalseAlert(confirm.getAlertType()) && !confirm.isProcessFailed()) {
            if (guards == null || guards.size() == 0) {
                return ResponseData.error(AlertError.NO_GUARDS_INFO);
            }
        }

        // callGuard 是否有效
        if (guards != null && guards.size() > 0) {
            for (CallGuard guard : guards) {
                Boolean success = guard.getCallSuccess();
                if (success != null && success) {
                    if (!guard.isValidSuccessData()) {
                        return ResponseData.error(AlertError.INVALID_GUARDS_CONTACT);
                    }
                } else {
                    if (!guard.isValidFailedData()) {
                        return ResponseData.error(AlertError.INVALID_GUARDS_CONTACT);
                    }
                }
            }
        }

        //不是误报，并且处理成功，必须有一个保安是联系成功的
        if (!AlertType.isFalseAlert(confirm.getAlertType()) && !confirm.isProcessFailed()) {
            boolean callSuccess = false;
            for (CallGuard guard : guards) {
                Boolean success = guard.getCallSuccess();
                if (success != null && success) {
                    callSuccess = true;
                    break;
                }
            }
            if (!callSuccess) {
                return ResponseData.error(AlertError.NO_GUARDS_CALL_SUCCESS);
            }
        }


        // 确认
        ResponseData responseData = processService.confirm(confirm.getAlertId(), confirm.getStartTime(), confirm.getEndTime(), confirm.getAlertType(), confirm.getUserId());
        if (AlertType.isFalseAlert(confirm.getAlertType())) {
            return ResponseData.ok();
        }

        // 记录联系保安
        if (guards != null) {
            for (CallGuard guard : guards) {
                Boolean success = guard.getCallSuccess();
                if (success != null && success) {
                    responseData = processService.callGuardSuccess(guard.getGuardId(), confirm.getAlertId(), confirm.getUserId());
                    confirm.setProcessFailed(false);
                } else {
                    responseData = processService.callGuardFailed(guard.getGuardId(), guard.getFailedType(), guard.getFailedReason(), confirm.getAlertId(), confirm.getUserId());
                }
                if (responseData != null) {
                    return responseData;
                }
            }
        }

        // 警情处理失败
        if (confirm.isProcessFailed()) {
            responseData = processService.callGuardsFailed(confirm.getAlertId(), confirm.getUserId());
        }

        if (responseData == null) {
            responseData = ResponseData.ok();
        }

        return responseData;
    }

    @Override
    @POST
    @Path(ALERTS_CALLBACK)
    @Transactional
    @SysOperateLog(serviceType = ALERT_PROCESS, operateType = ADD, description = "保安回电处理")
    public ResponseData guardFirstCallback(Callback callback, @HeaderParam(USER_ID) Long userId) {
        // 首次回电必须包含是否到达的信息
        String columns = callback.getInvalidColumns();
        if (columns != null || callback.getArrived() == null) {
            if (columns == null) {
                columns = "arrived";
            }
            return ResponseData.error(AlertError.INVALID_PARAMS, columns);
        }

        // 保安未到达必须有原因
        if (!callback.getArrived()) {
            if (callback.getContent() == null || callback.getContent().isEmpty()) {
                return ResponseData.error(AlertError.INVALID_PARAMS, "content");
            }
        }

        // 插入数据
        ResponseData responseData = processService.guardArrive(callback.getGuardId(), callback.getContent(), callback.getArrived(), callback.getQuestionSuspect(), callback.getAlertId(), callback.getUserId());
        if (responseData == null) {
            responseData = ResponseData.ok();
        }

        return responseData;
    }

    @Override
    @POST
    @Transactional
    @Path(ALERTS_SECOND_CALLBACK)
    @SysOperateLog(serviceType = ALERT_PROCESS, operateType = ADD, description = "保安二次回电处理")
    public ResponseData guardSecondCallback(Callback callback, @HeaderParam(USER_ID) Long userId) {
        String columns = callback.getInvalidColumns();
        if (columns != null) {
            return ResponseData.error(AlertError.INVALID_PARAMS, columns);
        }

        // 插入数据
        ResponseData responseData = processService.guardCallbackAgain(callback.getGuardId(), callback.getContent(), callback.getQuestionSuspect(), callback.getAlertId(), callback.getUserId());
        if (responseData == null) {
            responseData = ResponseData.ok();
        }
        return responseData;
    }

    @Override
    public BaseAlert next(@HeaderParam(USER_ID) Long userId) {
        return null;
    }
}
