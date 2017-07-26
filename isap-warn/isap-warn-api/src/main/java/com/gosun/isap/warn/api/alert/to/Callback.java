package com.gosun.isap.warn.api.alert.to;

/**
 * 保安回电数据结构
 * <p>创建时间：2017/5/16 9:49</p>
 *
 * @author 娄存银
 * @version 1.0
 */
public class Callback implements ValidBean{
    private Long userId;
    private Long guardId;
    private Long alertId;
    private String content;
    private boolean arrived;
    private boolean questionSuspect;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getGuardId() {
        return guardId;
    }

    public void setGuardId(Long guardId) {
        this.guardId = guardId;
    }

    public Long getAlertId() {
        return alertId;
    }

    public void setAlertId(Long alertId) {
        this.alertId = alertId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getArrived() {
        return arrived;
    }

    public void setArrived(Boolean arrived) {
        this.arrived = arrived;
    }

    public Boolean getQuestionSuspect() {
        return questionSuspect;
    }

    public void setQuestionSuspect(Boolean questionSuspect) {
        this.questionSuspect = questionSuspect;
    }

    @Override
    public String[] getNullableFields() {
        return new String[]{
                "content","questionSuspect"
        };
    }
}
