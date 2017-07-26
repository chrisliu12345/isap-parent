package com.gosun.isap.warn.impl.alert.export.converter;

import com.gosun.isap.warn.api.alert.model.enumeration.AlertStatus;
import com.gosun.isap.warn.api.alert.model.enumeration.AlertType;
import com.gosun.isap.warn.impl.alert.export.base.MapConverter;

import java.util.Map;

/**
 * 警情记录表数据转换。
 * <p>创建时间：2017-5-31 10:17</p>
 *
 * @author 娄存银
 * @version 1.0
 */
public class AlertLogConverter implements MapConverter {
    private static final String WHERE_AND_WHAT = "whereAndWhat";
    private static final String IS_FAILED = "isFailed";
    private static final String IS_ARRIVED = "isArrived";
    private static final String IS_QUESTION_SUSPECT = "isQuestionSuspect";
    private static final String IS_GUARD_OVER_TIME = "isGuardOverTime";
    private static final String FAILED_REASON = "failedReason";

    private static final String DEPARTMENT_NAME = "departmentName";
    private static final String ALERT_TYPE = "alertType";

    private static final String ALERT_STATUS = "alertStatus";
    private static final String FIND_SUSPECT = "发现可疑人员";
    private static final String YES = "√";
    private static final String NO = "×";

    @Override
    public void convert(Map<String, Object> map) {
        if(map == null){
            return;
        }

        String departmentName = (String) map.get(DEPARTMENT_NAME);
        Integer alertType = (Integer) map.get(ALERT_TYPE);
        String description = null;
        if (alertType != null) {
            if (alertType == AlertType.ABNORMAL_ALERT.getType()) {
                description =  departmentName + AlertType.ABNORMAL_ALERT.getDescription();
            } else if (alertType == AlertType.NORMAL_ALERT.getType()) {
                description = departmentName + FIND_SUSPECT;
            }
            map.put(WHERE_AND_WHAT,description);
        }else {

            map.put(WHERE_AND_WHAT,departmentName);
        }

        Integer status = (Integer) map.get(ALERT_STATUS);
        if (status != null) {
            if (AlertStatus.isFailed(status.byteValue())) {
                map.put(IS_FAILED,NO);
            }
        }


        Boolean isArrived = (Boolean) map.get(IS_ARRIVED);
        if (isArrived != null && isArrived) {
            map.put(IS_ARRIVED,YES);
        }

        Boolean isQuestionSuspect = (Boolean) map.get(IS_QUESTION_SUSPECT);
        if (isQuestionSuspect != null && isQuestionSuspect) {
            map.put(IS_QUESTION_SUSPECT,YES);
        }else {
            map.remove(IS_QUESTION_SUSPECT);
        }

        Boolean isGuardOverTime = (Boolean) map.get(IS_GUARD_OVER_TIME);
        if (isGuardOverTime != null && isGuardOverTime) {
            map.put(IS_GUARD_OVER_TIME,NO);
        }else {
            map.remove(IS_GUARD_OVER_TIME);
        }

        String reason = (String) map.get(FAILED_REASON);
        if(reason == null){
            status = (Integer) map.get(ALERT_STATUS);
            if(AlertStatus.isFailed(status.byteValue())){
                AlertStatus alertStatus =AlertStatus.get(status.byteValue());
                if(alertStatus != null) {
                    map.put(FAILED_REASON,alertStatus.getDescription());
                }
            }
        }
    }
}
