package com.gosun.isap.warn.api.alert.model.enumeration;

import com.gosun.isap.dao.mapper.alert.Interval;

/**
 * 警情处理状态
 * <p>创建时间：2017/5/6 15:48</p>
 *
 * @author 娄存银
 * @version 1.0
 */
public enum AlertStatus {

    /**
     * 未处理
     */
    UNPROCESSED(0, "未处理"),
    /**
     * 等待分配保安
     */
    WAITING_PROCESS(1, "等待分配保安"),
    /**
     * 等待保安到达现场
     */
    WAITING_CALLBACK(2, "等待保安到达现场"),
    /**
     * 等待二次回电
     */
    WAITING_CALLBACK_AGAIN(3, "等待二次回电"),

    /**
     * 处理成功
     */
    PROCESSED_SUCCESS(10, "处理成功"),

    /**
     * 处理失败
     */
    FAILED_GUARD_OVER_TIME(20, "保安超时，处理失败"),
    /**
     * 处理失败
     */
    FAILED_GUARD_NOT_ARRIVED(21, "保安未到达现场，处理失败"),

    /**
     * 保安 30 分钟内未回电
     */
    FAILED_NO_CALLBACK(25, "保安 30 分钟内未回电"),
    /**
     * 没有联系到保安，处理失败
     */
    FAILED_NO_GUARD(26, "没有联系到保安，处理失败"),

    /**
     * 30分钟内未处理
     */
    FAILED_UNPROCESSED(30, "30分钟内未处理");

    private static final Interval<Byte> UNPROCESSED_LIMIT = new Interval<>(WAITING_PROCESS.getStatus(), null);
    private static final Interval<Byte> PROCESSING_LIMIT = new Interval<>(PROCESSED_SUCCESS.getStatus(), UNPROCESSED.getStatus());
    private static final Interval<Byte> FINISHED_LIMIT = new Interval<>(null, (byte) (PROCESSED_SUCCESS.getStatus() - 1));
    private static final Interval<Byte> SUCCESS_LIMIT = new Interval<>(FAILED_GUARD_OVER_TIME.getStatus(), (byte) (PROCESSED_SUCCESS.getStatus() - 1));
    private static final Interval<Byte> FAILED_LIMIT = new Interval<>(null, (byte) (FAILED_GUARD_OVER_TIME.getStatus() - 1));
    private static final Interval<Byte> GUARD_PROCESSED = new Interval<>(FAILED_NO_CALLBACK.getStatus(), (byte) (PROCESSED_SUCCESS.getStatus() - 1));

    private byte status;
    private String description;

    AlertStatus(int status, String description) {
        this.status = (byte) status;
        this.description = description;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 根据值获取警情状态
     *
     * @param status 状态值
     * @return 警情状态
     */
    public static AlertStatus get(byte status) {
        for (AlertStatus alertStatus : AlertStatus.values()) {
            if (status == alertStatus.getStatus()) {
                return alertStatus;
            }
        }
        return null;
    }

    /**
     * 根据状态值判断是否处理失败
     *
     * @param status 状态值
     * @return 是否处理失败
     */
    public static boolean isFailed(byte status) {
        return status > FAILED_GUARD_OVER_TIME.getStatus() - 1;
    }

    /**
     * 根据状态值判断是否到达
     *
     * @param status 状态值
     * @return 是否到达
     */
    public static boolean isArrive(byte status) {
        return status > FAILED_GUARD_OVER_TIME.getStatus() - 1;
    }


    /**
     * 获取警情状态描述
     *
     * @param status 状态值
     * @return 描述
     */
    public static String getDescription(byte status) {
        AlertStatus alertStatus = get(status);
        return alertStatus == null ? null : alertStatus.getDescription();
    }

    public static Interval<Byte> getUnprocessedInterval() {
        return UNPROCESSED_LIMIT;
    }

    public static Interval<Byte> getProcessingInterval() {
        return PROCESSING_LIMIT;
    }

    public static Interval<Byte> getSuccessInterval() {
        return SUCCESS_LIMIT;
    }

    public static Interval<Byte> getFailedInterval() {
        return FAILED_LIMIT;
    }

    public static Interval<Byte> getFinishedInterval() {
        return FINISHED_LIMIT;
    }

    public static Interval<Byte> getGuardProcessed() {
        return GUARD_PROCESSED;
    }
}
