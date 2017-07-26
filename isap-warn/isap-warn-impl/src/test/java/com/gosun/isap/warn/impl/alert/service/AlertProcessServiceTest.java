package com.gosun.isap.warn.impl.alert.service;

import com.gosun.isap.dao.mapper.alert.base.AlertBaseMapper;
import com.gosun.isap.dao.mapper.alert.base.AlertDetailBaseMapper;
import com.gosun.isap.dao.po.alert.Alert;
import com.gosun.isap.dao.po.alert.base.BaseAlert;
import com.gosun.isap.dao.po.alert.base.BaseAlertDetail;
import com.gosun.isap.warn.api.alert.model.enumeration.AlertStatus;
import com.gosun.isap.warn.api.alert.model.enumeration.AlertType;
import com.gosun.isap.warn.api.alert.model.enumeration.CallFailedReason;
import com.gosun.isap.warn.api.alert.service.AlertListService;
import com.gosun.isap.warn.api.alert.service.AlertProcessService;
import com.gosun.isap.warn.api.alert.service.AlertService;
import com.gosun.isap.warn.impl.alert.base.BaseUnitTest;
import com.gosun.isap.warn.impl.alert.base.ObjectService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * <p>创建时间：2017/5/9 8:49</p>
 *
 * @author 娄存银
 * @version 1.0
 */
public class AlertProcessServiceTest extends BaseUnitTest {
    private static final String STATUS = "status";

    @Autowired
    private AlertProcessService processingService;

    @Autowired
    private AlertBaseMapper alertBaseMapper;

    @Autowired
    private ObjectService objectService;

    @Autowired
    private AlertDetailBaseMapper detailBaseMapper;

    @Autowired
    private AlertService alertService;

    @Autowired
    private AlertListService listService;
    @Override
    public void autoWiredTest() {
        assertNotNull(processingService);
    }

    @Test
    public void falseAlertTest() {
        BaseAlert alert = getUnprocessedAlert();
        long userId = objectService.getUserId();

        falseAlert(alert, userId);
    }

    @Test
    public void noGuardsTest() {
        BaseAlert alert = getUnprocessedAlert();
        long userId = objectService.getUserId();

        confirm(alert, userId);
        callGuardFailed(alert, userId);
        noGuards(alert, userId);

        printAlertLog(alert);
    }

    @Test
    public void notArrive() {
        BaseAlert alert = getUnprocessedAlert();
        long userId = objectService.getUserId();
        long guardId = objectService.getGuardId();

        confirm(alert, userId);
        callGuardFailed(alert, userId);

        callGuardSuccess(alert, userId, guardId);
        guardNotArrive(alert, userId, guardId);

        printAlertLog(alert);
    }

    @Test
    public void noCallback() {
        BaseAlert alert = getUnprocessedAlert();
        long userId = objectService.getUserId();
        long guardId = objectService.getGuardId();

        confirm(alert, userId);
        callGuardFailed(alert, userId);

        callGuardSuccess(alert, userId, guardId);
        noCallback(alert);

        printAlertLog(alert);
    }

    @Test
    public void guardArriveAndQuestionSuspect() {
        BaseAlert alert = getUnprocessedAlert();
        long userId = objectService.getUserId();
        long guardId = objectService.getGuardId();

        confirm(alert, userId);
        callGuardFailed(alert, userId);
        callGuardSuccess(alert, userId, guardId);
        guardArriveAndQuestionSuspect(alert, userId, guardId);

        printAlertLog(alert);
    }

    @Test
    public void guardCallback() {
        BaseAlert alert = getUnprocessedAlert();
        long userId = objectService.getUserId();
        long guardId = objectService.getGuardId();

        confirm(alert, userId);
        callGuardFailed(alert, userId);
        callGuardSuccess(alert, userId, guardId);
        guardArrive(alert, userId, guardId);
        secondCall(alert, userId, guardId);

        printAlertLog(alert);
    }

    @Test
    public void noCallbackAgain() {
        BaseAlert alert = getUnprocessedAlert();
        long userId = objectService.getUserId();
        long guardId = objectService.getGuardId();

        confirm(alert, userId);
        callGuardFailed(alert, userId);
        callGuardSuccess(alert, userId, guardId);
        guardArrive(alert, userId, guardId);
        noCallbackAgain(alert);

        printAlertLog(alert);
    }

    @Test
    public void createTestData(){
        for (int i = 0; i < 5; i++) {
            falseAlertTest();
            noGuardsTest();
            notArrive();
            noCallback();
            guardArriveAndQuestionSuspect();
            guardCallback();
            guardCallback();
            guardCallback();
            guardCallback();
            noCallbackAgain();
            noCallbackAgain();
        }

    }

    @Test
    public void generateAnAlert(){
        getUnprocessedAlert();
    }

    @Test
    public void generateAWaitingCallbackAlert(){
        BaseAlert alert = getUnprocessedAlert();
        long userId = objectService.getUserId();
        long guardId = objectService.getGuardId();

        confirm(alert, userId);
        callGuardSuccess(alert, userId, guardId);
    }

    @Test
    public void generateAWaitingCallbackAgainAlert(){
        BaseAlert alert = getUnprocessedAlert();
        long userId = objectService.getUserId();
        long guardId = objectService.getGuardId();

        confirm(alert, userId);
        callGuardSuccess(alert, userId, guardId);
        guardArrive(alert, userId, guardId);
    }

    private BaseAlert getUnprocessedAlert() {
        List<Alert> list = listService.listUnprocessedAlerts(1,null);
        if(list != null && list.size() > 0){
            return list.get(0);
        }
        long id = alertService.createAlert(objectService.getDeviceId(),"移动侦测");
        return alertBaseMapper.selectByPrimaryKey(id);
    }

    private void falseAlert(BaseAlert alert, long userId) {

        // 确认
        processingService.confirm(alert.getId(), new Date(new Date().getTime() - 10000), new Date(), AlertType.FALSE_ALERT.getType(), userId);

        BaseAlert afterProcess = alertBaseMapper.selectByPrimaryKey(alert.getId());

        // 确认为误报后，警情处理结束
        assert (afterProcess.getStatus() == AlertStatus.PROCESSED_SUCCESS.getStatus());
        assert (afterProcess.getAlertType() == AlertType.FALSE_ALERT.getType());
        assert (afterProcess.getUserId() == userId);
    }

    private void confirm(BaseAlert alert, long userId) {

        // 确认
        processingService.confirm(alert.getId(), new Date(new Date().getTime() - 10000), new Date(), AlertType.NORMAL_ALERT.getType(), userId);

        BaseAlert afterProcess = alertBaseMapper.selectByPrimaryKey(alert.getId());

        // 确认为出警后，状态为等待处理
        assert (afterProcess.getStatus() == AlertStatus.WAITING_PROCESS.getStatus());
        assert (afterProcess.getAlertType() == AlertType.NORMAL_ALERT.getType());
        assert (afterProcess.getUserId() == userId);
    }

    private void callGuardFailed(BaseAlert alert, long userId) {
        long guardId = objectService.getGuardId();
        processingService.callGuardFailed(guardId, CallFailedReason.NO_ANSWER.getType(), null, alert.getId(), userId);

        // 呼叫保安失败，只插入日志
        BaseAlert afterProcess = alertBaseMapper.selectByPrimaryKey(alert.getId());
        assert (afterProcess.getStatus() == AlertStatus.WAITING_PROCESS.getStatus());
    }

    private void noGuards(BaseAlert alert, long userId) {
        processingService.callGuardsFailed(alert.getId(), userId);

        //插入日志，更新状态
        BaseAlert afterProcess = alertBaseMapper.selectByPrimaryKey(alert.getId());
        assert (afterProcess.getStatus() == AlertStatus.FAILED_NO_GUARD.getStatus());
    }

    private void callGuardSuccess(BaseAlert alert, long userId, long guardId) {
        processingService.callGuardSuccess(guardId, alert.getId(), userId);

        // 呼叫保安成功，插入日志，更新状态
        BaseAlert afterProcess = alertBaseMapper.selectByPrimaryKey(alert.getId());
        assert (afterProcess.getStatus() == AlertStatus.WAITING_CALLBACK.getStatus());
    }

    private void guardArrive(BaseAlert alert, long userId, long guardId) {
        processingService.guardArrive(guardId, null, true, false, alert.getId(), userId);

        // 保安到达，插入日志，更新状态
        BaseAlert afterProcess = alertBaseMapper.selectByPrimaryKey(alert.getId());
        assert (afterProcess.getStatus() == AlertStatus.WAITING_CALLBACK_AGAIN.getStatus());
    }

    private void guardArriveAndQuestionSuspect(BaseAlert alert, long userId, long guardId) {
        processingService.guardArrive(guardId, null, true, true, alert.getId(), userId);

        // 询问可疑人员，更新状态，插入统计信息
        BaseAlert afterProcess = alertBaseMapper.selectByPrimaryKey(alert.getId());
        assertEquals((long) afterProcess.getStatus(), AlertStatus.PROCESSED_SUCCESS.getStatus());

        BaseAlertDetail detail = detailBaseMapper.selectFirstByColumn(BaseAlertDetail.ALERT_ID, alert.getId());
        assertNotNull(detail);
    }

    private void guardNotArrive(BaseAlert alert, long userId, long guardId) {
        processingService.guardArrive(guardId, "道路积水无法通过", false, false, alert.getId(), userId);

        // 未到达，插入日志，更新状态
        BaseAlert afterProcess = alertBaseMapper.selectByPrimaryKey(alert.getId());
        assertEquals((long) afterProcess.getStatus(), AlertStatus.FAILED_GUARD_NOT_ARRIVED.getStatus());
    }

    private void secondCall(BaseAlert alert, long userId, long guardId) {
        processingService.guardCallbackAgain(guardId, null, false, alert.getId(), userId);

        // 插入日志，更新状态
        BaseAlert afterProcess = alertBaseMapper.selectByPrimaryKey(alert.getId());
        assertEquals((long) afterProcess.getStatus(), AlertStatus.PROCESSED_SUCCESS.getStatus());
    }

    private void noCallback(BaseAlert alert) {
        processingService.noCallback(alert.getId());

        // 插入日志，更新状态
        BaseAlert afterProcess = alertBaseMapper.selectByPrimaryKey(alert.getId());
        assertEquals((long) afterProcess.getStatus(), AlertStatus.FAILED_NO_CALLBACK.getStatus());
    }

    private void noCallbackAgain(BaseAlert alert) {
        processingService.noCallbackAgain(alert.getId());

        // 插入日志，更新状态，插入统计信息
        BaseAlert afterProcess = alertBaseMapper.selectByPrimaryKey(alert.getId());
        assertEquals((long) afterProcess.getStatus(), AlertStatus.PROCESSED_SUCCESS.getStatus());

        BaseAlertDetail detail = detailBaseMapper.selectFirstByColumn(BaseAlertDetail.ALERT_ID, alert.getId());
        assertNotNull(detail);
    }

    private void printAlertLog(BaseAlert alert) {
/*        List<String> logs = alertService.getAlertLog(alert.getId());
        for (String log : logs) {
            System.out.println(log);
        }*/
    }

}
