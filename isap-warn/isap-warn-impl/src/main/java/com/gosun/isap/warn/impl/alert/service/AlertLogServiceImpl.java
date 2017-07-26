package com.gosun.isap.warn.impl.alert.service;

import com.gosun.isap.dao.mapper.alert.AlertMapper;
import com.gosun.isap.dao.mapper.alert.base.AlertBaseMapper;
import com.gosun.isap.dao.mapper.alert.base.AlertLogBaseMapper;
import com.gosun.isap.dao.po.alert.base.BaseAlertLog;
import com.gosun.isap.warn.api.alert.AlertConst;
import com.gosun.isap.warn.api.alert.model.enumeration.AlertLogType;
import com.gosun.isap.warn.api.alert.model.enumeration.AlertType;
import com.gosun.isap.warn.api.alert.model.enumeration.CallFailedReason;
import com.gosun.isap.warn.api.alert.service.AlertLogService;
import com.gosun.isap.warn.api.alert.service.AlertServiceId;
import com.gosun.isap.warn.api.alert.service.GuardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>创建时间：2017/5/6 17:34</p>
 *
 * @author 娄存银
 * @version 1.0
 */
@Service
@Transactional
public class AlertLogServiceImpl implements AlertLogService{
    private static final String ARRIVED = "到达指定地点";
    private static final String ARRIVED_QUESTIONED_SUSPECT = ARRIVED + "，并询问可疑人员";
    private static final String  QUESTIONED_SUSPECT = "发现并询问可疑人员";
    private static final String  NOT_FIND_SUSPECT = "未找到可疑人员";
    private static final String NO_CALLBACK = String.format("保安 %d 分钟内未回电", AlertConst.PROCESSED_IN_TIME_MINUTE);

    @Autowired
    private AlertLogBaseMapper mapper;

    @Autowired
    private AlertMapper alertMapper;

    @Autowired
    @Qualifier(AlertServiceId.GUARD_SERVICE)
    private GuardService guardService;

    @Autowired
    private AlertBaseMapper alertBaseMapper;

    @Override
    public boolean needProcess(long userId, long alertId,byte alertType) {
        BaseAlertLog log = new BaseAlertLog();
        log.setLogType(AlertLogType.NEED_PROCESS.getType());

        AlertType type = AlertType.get(alertType);
        if(type != null) {
            log.setContent(type.getDescription());
        }
        log.setAlertId(alertId);
        log.setUserId(userId);
        int row = mapper.insertSelective(log);
        return row > 0;
    }

    @Override
    public boolean callGuardSuccess(long userId, long alertId, long guardId, String orderInfo) {
        BaseAlertLog log = new BaseAlertLog();
        log.setLogType(AlertLogType.CALL_GUARD_SUCCESS.getType());

        log.setAlertId(alertId);
        log.setUserId(userId);
        log.setReferenceId(guardId);
        log.setContent(guardService.getGuardInfo(guardId));
        log.setReason(orderInfo);
        int row = mapper.insertSelective(log);
        if(row < 1){
            return false;
        }

        // 删除失败记录
        Map<String,Object> map = new HashMap<>();
        map.put(BaseAlertLog.ALERT_ID,alertId);
        map.put(BaseAlertLog.REFERENCE_ID,guardId);
        map.put(BaseAlertLog.LOG_TYPE,AlertLogType.CALL_GUARD_FAILED.getType());
        BaseAlertLog failedLog = mapper.selectFirstByColumns(map);
        if (failedLog != null){
            mapper.deleteByColumn(BaseAlertLog.ID,failedLog.getId());
        }
        
        return row > 0;
    }

    @Override
    public boolean callGuardFailed(long userId, long alertId, long guardId,byte failedType, String reason) {
        BaseAlertLog log = new BaseAlertLog();
        log.setLogType(AlertLogType.CALL_GUARD_FAILED.getType());

        log.setAlertId(alertId);
        log.setUserId(userId);
        log.setReferenceId(guardId);
        log.setContent(guardService.getGuardInfo(guardId));
        if(reason == null || reason.isEmpty()){
            reason = CallFailedReason.getReason(failedType).getReason();
        }
        log.setReason(reason);

        int row = mapper.insertSelective(log);
        return row > 0;
    }

    @Override
    public boolean callGuardsFailed(long userId, long alertId) {
        BaseAlertLog log = new BaseAlertLog();
        log.setLogType(AlertLogType.CALL_GUARDS_FAILED.getType());

        log.setAlertId(alertId);
        log.setUserId(userId);

        int row = mapper.insertSelective(log);
        return row > 0;
    }

    @Override
    public boolean guardArrive(long userId, long alertId, long guardId,boolean questionSuspect) {
        BaseAlertLog log = new BaseAlertLog();
        log.setLogType(AlertLogType.GUARDS_ARRIVED.getType());

        log.setAlertId(alertId);
        log.setUserId(userId);
        log.setReferenceId(guardId);
        log.setContent(guardService.getGuardInfo(guardId));
        if(questionSuspect){
            log.setReason(ARRIVED_QUESTIONED_SUSPECT);
        }else {
            log.setReason(ARRIVED);
        }

        int row = mapper.insertSelective(log);
        return row > 0;
    }

    @Override
    public boolean guardNotArrive(long userId, long alertId, long guardId, String reason) {
        BaseAlertLog log = new BaseAlertLog();
        log.setLogType(AlertLogType.GUARDS_NOT_ARRIVED.getType());

        log.setAlertId(alertId);
        log.setUserId(userId);
        log.setReferenceId(guardId);
        log.setContent(guardService.getGuardInfo(guardId));
        log.setReason(reason);

        int row = mapper.insertSelective(log);
        return row > 0;
    }


    @Override
    public boolean guardCallBackAgain(long userId, long alertId, long guardId, String callInfo,boolean questionSuspect) {
        BaseAlertLog log = new BaseAlertLog();
        log.setLogType(AlertLogType.GUARDS_CALLBACK_AGAIN.getType());

        log.setAlertId(alertId);
        log.setUserId(userId);
        log.setReferenceId(guardId);
        log.setContent(guardService.getGuardInfo(guardId));
        if(callInfo == null ){
            if(questionSuspect){
                log.setReason(QUESTIONED_SUSPECT);
            }else {
                log.setReason(NOT_FIND_SUSPECT);
            }
        }else {
            log.setReason(callInfo);
        }

        int row = mapper.insertSelective(log);
        return row > 0;
    }

    @Override
    public boolean guardQuestionSuspect(long userId, long alertId, long guardId) {
        BaseAlertLog log = new BaseAlertLog();
        log.setLogType(AlertLogType.GUARDS_QUESTIONED_SUSPECT.getType());

        log.setAlertId(alertId);
        log.setUserId(userId);
        log.setReferenceId(guardId);
        log.setContent(guardService.getGuardInfo(guardId));

        int row = mapper.insertSelective(log);
        return row > 0;
    }

    @Override
    public boolean noCallback(long alertId) {
        BaseAlertLog log = new BaseAlertLog();
        log.setLogType(AlertLogType.NOT_CALLBACK.getType());
        log.setAlertId(alertId);

        BaseAlertLog alertLog = mapper.selectFirstByColumn(BaseAlertLog.ALERT_ID,alertId);
        if(alertLog != null){
            log.setUserId(alertLog.getUserId());
        }else {
            log.setUserId(0L);
        }

        Long guardId = alertMapper.getAlertLogReferenceId(alertId,AlertLogType.CALL_GUARD_SUCCESS.getType());
        if(guardId == null){
            guardId = 0L;
        }
        log.setReferenceId(guardId);
        log.setContent(guardService.getGuardInfo(guardId));
        log.setReason(NO_CALLBACK);

        int row = mapper.insertSelective(log);
        return row > 0;
    }

    @Override
    public boolean noCallbackAgain(long alertId) {
        BaseAlertLog log = new BaseAlertLog();
        log.setLogType(AlertLogType.NOT_CALLBACK_AGAIN.getType());

        log.setAlertId(alertId);
        log.setUserId(0L);
        Long guardId = alertMapper.getAlertLogReferenceId(alertId,AlertLogType.CALL_GUARD_SUCCESS.getType());
        if(guardId == null){
            guardId = 0L;
        }
        log.setReferenceId(guardId);
        log.setContent(guardService.getGuardInfo(guardId));

        int row = mapper.insertSelective(log);
        return row > 0;
    }

    @Override
    public List<BaseAlertLog> getLogs(long alertId) {
        return mapper.selectByColumn(BaseAlertLog.ALERT_ID,alertId,null) ;
    }

}
