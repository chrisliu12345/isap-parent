package com.gosun.isap.warn.api.alert.to;

import java.util.Date;
import java.util.List;

/**
 * 警情确认数据结构
 * <p>创建时间：2017/5/16 8:55</p>
 *
 * @author 娄存银
 * @version 1.0
 */
public class AlertConfirm implements ValidBean {
    private Date startTime;
    private Date endTime;
    private Byte alertType;
    private Long alertId;
    private Long userId;
    private List<CallGuard> guards;
    private boolean processFailed;

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Byte getAlertType() {
        return alertType;
    }

    public void setAlertType(Byte alertType) {
        this.alertType = alertType;
    }

    public Long getAlertId() {
        return alertId;
    }

    public void setAlertId(Long alertId) {
        this.alertId = alertId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<CallGuard> getGuards() {
        return guards;
    }

    public void setGuards(List<CallGuard> guards) {
        this.guards = guards;
    }

    public boolean isProcessFailed() {
        return processFailed;
    }

    public void setProcessFailed(boolean processFailed) {
        this.processFailed = processFailed;
    }

    @Override
    public String[] getNullableFields() {
        return new String[]{"guards","processFailed"};
    }
}
