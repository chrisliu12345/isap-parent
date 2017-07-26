package com.gosun.isap.warn.api.alert.model.enumeration;

/**
 * 联系保安失败原因
 * <p>创建时间：2017/5/8 13:38</p>
 *
 * @author 娄存银
 * @version 1.0
 */
public enum  CallFailedReason {
    /**
     * 不值班
     */
    OFF_WORK(0,"不值班"),
    /**
     * 无人接听
     */
    NO_ANSWER(1,"无人接听"),
    /**
     * 关机
     */
    POWER_OFF(2,"关机"),
    /**
     * 停机
     */
    OUT_OF_SERVICE(3,"停机"),
    /**
     * 其他
     */
    OTHER(4,"其他");

    private byte type;
    private String reason;

    CallFailedReason(int type,String reason){
        this.type = (byte) type;
        this.reason = reason;
    }

    public byte getType() {
        return type;
    }

    public String getReason() {
        return reason;
    }

    /**
     * 获取联系失败原因
     * @param type 失败类型
     * @return CallFailedReason
     */
    public static CallFailedReason getReason(byte type){
        CallFailedReason[] reasons = CallFailedReason.values();
        for (CallFailedReason reason:reasons) {
            if(reason.getType() == type){
                return reason;
            }
        }
        return OTHER;
    }
}
