package com.gosun.isap.warn.impl.alert.export.converter;

import com.gosun.isap.warn.api.alert.model.enumeration.AlertStatus;
import com.gosun.isap.warn.api.alert.model.enumeration.AlertType;
import com.gosun.isap.warn.impl.alert.export.base.MapConverter;

import java.util.Map;

/**
 * 出警详情表数据转换。
 * <p>创建时间：2017-5-31 9:46</p>
 *
 * @author 娄存银
 * @version 1.0
 */
public class AlertDetailLogConverter implements MapConverter {
    private static final String ALERT_DESCRIPTION = "alertDescription";
    private static final String IS_FAILED = "isFailed";
    private static final String IS_CALLBACK = "isCallback";
    private static final String CALLBACK_TIME = "callbackTime";
    private static final String IS_QUESTION_SUSPECT = "isQuestionSuspect";
    private static final String IS_PROCESSED = "isProcessed";
    private static final String ALERT_TYPE = "alertType";
    private static final String GUARD_RESPONSE_TIME = "guardResponseTime";
    private static final String IS_CALLBACK_AGAIN = "isCallbackAgain";

    private static final String ALERT_STATUS = "alertStatus";
    private static final String YES = "是";
    private static final String NO = "否";
    private static final String HAS_DONE = "已做";

    private static final String NOT_DONE = "未做";
    private static final String NORMAL = "正常";

    private static final String ABNORMAL = "异常";

    private static final String FIND_SUSPECT = "发现可疑人员";
    private static final String ABNORMAL_ALERT = "发现异常情况";

    private static final int MINUTE_TO_SECOND = 60;
    @Override
    public void convert(Map<String, Object> map) {
        if(map == null){
            return;
        }

        Integer status = (Integer) map.get(ALERT_STATUS);
        if (status != null) {
            if (AlertStatus.isFailed(status.byteValue())) {
                map.put(IS_FAILED,NO);
            }else {
                map.put(IS_FAILED,YES);
            }
        }else {
            map.put(IS_FAILED,NO);
        }

        Boolean isQuestionSuspect = (Boolean) map.get(IS_QUESTION_SUSPECT);
        if (isQuestionSuspect != null && isQuestionSuspect) {
            map.put(IS_QUESTION_SUSPECT,YES);
        }else {
            map.put(IS_QUESTION_SUSPECT,NO);
        }

        Integer type = (Integer) map.get(ALERT_TYPE);
        if(type != null) {
            AlertType alertType = AlertType.get(type.byteValue());
            if (alertType == AlertType.NORMAL_ALERT) {
                map.put(ALERT_DESCRIPTION,FIND_SUSPECT) ;
            } else {
                map.put(ALERT_DESCRIPTION,ABNORMAL_ALERT) ;
            }
        }

        Boolean isArrived = (Boolean) map.get(IS_CALLBACK);
        if (isArrived != null && isArrived) {
            map.put(IS_PROCESSED,HAS_DONE);
        }else {
            map.put(IS_PROCESSED,NOT_DONE);
        }

        if (map.get(CALLBACK_TIME) != null) {
            map.put(IS_CALLBACK,NORMAL);
        }else {
            map.put(IS_CALLBACK,ABNORMAL);
        }

        Integer responseTime = (Integer) map.get(GUARD_RESPONSE_TIME);
        if(responseTime != null){
            map.put(GUARD_RESPONSE_TIME,responseTime/ MINUTE_TO_SECOND);
        }

        Boolean callbackAgain = (Boolean) map.get(IS_CALLBACK_AGAIN);
        if(callbackAgain != null && callbackAgain){
            map.put(IS_CALLBACK_AGAIN,YES);
        }else {
            map.put(IS_CALLBACK_AGAIN,NO);
        }
    }
}
