package com.gosun.isap.warn.impl.alert.export.converter;

import com.gosun.isap.warn.api.alert.model.enumeration.AlertStatus;
import com.gosun.isap.warn.impl.alert.export.base.MapConverter;

import java.util.Map;

/**
 * 未完成出警记录数据转换。
 * <p>创建时间：2017-5-31 10:23</p>
 *
 * @author 娄存银
 * @version 1.0
 */
public class UnfinishedConverter implements MapConverter {
    private static final String STATUS = "status";
    private static final String FAILED_REASON = "failedReason";
    private static final String OVER_TIME = "保安超时出警";

    @Override
    public void convert(Map<String, Object> map) {
        if(map == null){
            return;
        }
        if (map.get(FAILED_REASON) == null) {
            Integer status = (Integer) map.get(STATUS);
            if(status != null) {
                AlertStatus alertStatus = AlertStatus.get(status.byteValue());
                if(alertStatus != null && alertStatus == AlertStatus.FAILED_GUARD_OVER_TIME){
                    map.put(FAILED_REASON,OVER_TIME);
                }
            }
        }
    }
}
