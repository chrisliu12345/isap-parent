package com.gosun.isap.warn.impl.alert.service;

import com.gosun.isap.dao.mapper.alert.AlertMapper;
import com.gosun.isap.dao.mapper.alert.AlertsCountMapper;
import com.gosun.isap.warn.api.alert.model.enumeration.AlertStatus;
import com.gosun.isap.warn.api.alert.model.enumeration.AlertType;
import com.gosun.isap.warn.api.alert.service.AlertCommonService;
import com.gosun.isap.warn.api.alert.service.AlertStatisticsService;
import com.gosun.isap.warn.api.alert.service.UserService;
import com.gosun.isap.warn.api.alert.util.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>创建时间：2017-5-20 16:39</p>
 *
 * @author 娄存银
 * @version 1.0
 */
@Service
public class AlertStatisticsServiceImpl implements AlertStatisticsService{
    @Autowired
    private AlertsCountMapper countMapper;
    @Autowired
    private AlertMapper alertMapper;
    @Autowired
    private AlertCommonService commonService;

    @Autowired
    private UserService userService;

    private Date start;
    private Date end;
    private String parentDepartment;
    private List<String> childDepartments;

    @Override
    public void init(Date start,Date end,String parentDepartment,Long userId){
        childDepartments = userService.getChildCommunity(userId,parentDepartment);
        this.start = start;
        this.end = end;
    }

    @Override
    public void init(Date start, Date end, List<String> departmentIds){
        childDepartments = departmentIds;
        this.start = start;
        this.end = end;
    }

    @Override
    public void setDepartmentId(String departmentId,Long userId, boolean child) {
        if(child) {
            childDepartments = new ArrayList<>();
            childDepartments.add(departmentId);
        }else {
            if (userId == null || userService.isSupperAdmin(userId)) {
                childDepartments = commonService.getChildCommunities(departmentId,null);
            } else {
                childDepartments = commonService.getChildDepartmentsByUserId(departmentId, userId,null);
            }
        }
    }

    @Override
    public int needGuard() {
        return countMapper.countByTypeStatusIntervalAndDepartments(AlertType.getNeedProcessInterval(),null,start,end,childDepartments);
    }

    @Override
    public int guardProcessed() {
        return countMapper.countByTypeStatusIntervalAndDepartments(AlertType.getNeedProcessInterval(),AlertStatus.getGuardProcessed(),start,end,childDepartments);
    }

    @Override
    public Double getAverageConfirmTime() {
        return alertMapper.avgOfConfirmTimeByDeps(childDepartments,start,end);
    }

    @Override
    public Double getAverageUserResponseTime() {
        return alertMapper.avgOfUserResponseTimeByDeps(childDepartments,start,end);
    }

    @Override
    public Double getAverageGuardResponseTime() {
        return alertMapper.avgOfGuardResponseTimeByDeps(childDepartments,start,end);
    }

    @Override
    public Double getAverageUnprocessedAlerts() {
        return alertMapper.avgOfUnprocessedByDeps(childDepartments,start,end);
    }

    @Override
    public Double getAverageConfirmedAlerts() {
        return alertMapper.avgOfConfirmedAlertsByDeps(childDepartments,start,end);
    }

    @Override
    public int callback() {
        return successful()+notArrive();
    }

    @Override
    public int callbackAgain() {
        return alertMapper.countOfCallbackAgain(childDepartments,start,end);
    }

    private boolean invalidParams(){
        if(ListUtils.isEmpty(childDepartments)){
            return true;
        }
        return false;
    }

    @Override
    public int totalCount() {
        if(invalidParams()){
            return 0;
        }
        return alertMapper.countAlertsByDepartments(childDepartments,start,end);
    }

    @Override
    public int finished() {
        if(invalidParams()){
            return 0;
        }
        return alertMapper.countByStatusIntervalAndDeps(AlertStatus.getFinishedInterval(),childDepartments,start,end);
    }

    @Override
    public int processing() {
        if(invalidParams()){
            return 0;
        }
        return alertMapper.countByStatusIntervalAndDeps(AlertStatus.getProcessingInterval(),childDepartments,start,end);
    }

    @Override
    public int waitingProcess() {
        if(invalidParams()){
            return 0;
        }
        return alertMapper.countAlertsByStatusAndDeps(AlertStatus.UNPROCESSED.getStatus(),childDepartments,start,end);
    }

    @Override
    public int unprocessed() {
        if(invalidParams()){
            return 0;
        }
        return countMapper.countByTypeStatusDepartments(null,AlertStatus.FAILED_UNPROCESSED.getStatus(),start,end,childDepartments);
    }

    @Override
    public int falseAlerts() {
        if(invalidParams()){
            return 0;
        }
        return countMapper.countByTypeStatusIntervalAndDepartments(AlertType.getFalseAlertInterval(),null,start,end,childDepartments);
    }

    @Override
    public int abnormal() {
        if(invalidParams()){
            return 0;
        }
        return countMapper.countByTypeStatusDepartments(AlertType.ABNORMAL_ALERT.getType(),null,start,end,childDepartments);
    }

    @Override
    public int suspect() {
        if(invalidParams()){
            return 0;
        }
        return countMapper.countByTypeStatusDepartments(AlertType.NORMAL_ALERT.getType(),null,start,end,childDepartments);
    }

    @Override
    public int successful() {
        if(invalidParams()){
            return 0;
        }
        return countMapper.countByTypeStatusIntervalAndDepartments(AlertType.getNeedProcessInterval(),AlertStatus.getSuccessInterval(),start,end,childDepartments);
    }

    @Override
    public int failed() {
        if(invalidParams()){
            return 0;
        }
        return countMapper.countByTypeStatusIntervalAndDepartments(null,AlertStatus.getFailedInterval(),start,end,childDepartments);
    }

    @Override
    public int hasQuestionSuspect() {
        if(invalidParams()){
            return 0;
        }
        return countMapper.countQuestionedSuspectAlerts(start,end,childDepartments);
    }

    @Override
    public int waitingCallback() {
        if(invalidParams()){
            return 0;
        }
        return alertMapper.countAlertsByStatusAndDeps(AlertStatus.WAITING_CALLBACK.getStatus(),childDepartments,start,end);
    }

    @Override
    public int waitingCallbackAgain() {
        if(invalidParams()){
            return 0;
        }
        return alertMapper.countAlertsByStatusAndDeps(AlertStatus.WAITING_CALLBACK_AGAIN.getStatus(),childDepartments,start,end);
    }

    @Override
    public int noGuards() {
        if(invalidParams()){
            return 0;
        }
        return alertMapper.countAlertsByStatusAndDeps(AlertStatus.FAILED_NO_GUARD.getStatus(),childDepartments,start,end);
    }

    @Override
    public int guardOverTime() {
        if(invalidParams()){
            return 0;
        }
        return alertMapper.countAlertsByStatusAndDeps(AlertStatus.FAILED_GUARD_OVER_TIME.getStatus(),childDepartments,start,end);
    }

    @Override
    public int notArrive() {
        if(invalidParams()){
            return 0;
        }
        return alertMapper.countAlertsByStatusAndDeps(AlertStatus.FAILED_GUARD_NOT_ARRIVED.getStatus(),childDepartments,start,end);
    }

    @Override
    public int noCallback() {
        if(invalidParams()){
            return 0;
        }
        return alertMapper.countAlertsByStatusAndDeps(AlertStatus.FAILED_NO_CALLBACK.getStatus(),childDepartments,start,end);
    }


}
