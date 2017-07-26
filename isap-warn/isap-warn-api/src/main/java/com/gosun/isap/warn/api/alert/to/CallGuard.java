package com.gosun.isap.warn.api.alert.to;

/**
 * 联系保安数据结构
 * <p>创建时间：2017/5/16 9:41</p>
 *
 * @author 娄存银
 * @version 1.0
 */
public class CallGuard{
    private Long guardId;
    private Boolean callSuccess;
    private Byte failedType;
    private String failedReason;

    public Long getGuardId() {
        return guardId;
    }

    public void setGuardId(Long guardId) {
        this.guardId = guardId;
    }

    public Boolean getCallSuccess() {
        return callSuccess;
    }

    public void setCallSuccess(Boolean callSuccess) {
        this.callSuccess = callSuccess;
    }

    public Byte getFailedType() {
        return failedType;
    }

    public void setFailedType(Byte failedType) {
        this.failedType = failedType;
    }

    public String getFailedReason() {
        return failedReason;
    }

    public void setFailedReason(String failedReason) {
        this.failedReason = failedReason;
    }

    public boolean isValidSuccessData(){
        return guardId != null && callSuccess != null && callSuccess;
    }

    public boolean isValidFailedData(){
        return guardId != null && callSuccess != null && !callSuccess && failedType != null;
    }


}
