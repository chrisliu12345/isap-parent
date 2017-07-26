package com.gosun.isap.warn.api.alert.model.enumeration;

import com.gosun.isap.dao.mapper.alert.Interval;

/**
 * 警情类型
 * <p>创建时间：2017/5/6 15:48</p>
 *
 * @author 娄存银
 * @version 1.0
 */
public enum AlertType {
    /**
     * 误报
     */
    FALSE_ALERT(0, "误报"),
    /**
     * 人为误报
     */
    FALSE_ALERT_MANUAL(1, "人为误报"),
    /**
     * 可疑出警
     */
    NORMAL_ALERT(10, "可疑出警"),
    /**
     * 异常出警
     */
    ABNORMAL_ALERT(11, "异常出警");

    // 类型筛选区间
    private static final Interval<Byte> FALSE_ALERT_LIMIT = new Interval<>(NORMAL_ALERT.getType(), null);
    private static final Interval<Byte> NEED_PROCESS_LIMIT = new Interval<>(null, (byte) (NORMAL_ALERT.getType() - 1));

    private byte type;
    private String description;

    AlertType(int type, String description) {
        this.type = (byte) type;
        this.description = description;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 根据 type 值获取警情类型
     *
     * @param type 类型值
     * @return AlertType type 不存在返回 null
     */
    public static AlertType get(byte type) {
        for (AlertType alertType : AlertType.values()) {
            if (type == alertType.getType()) {
                return alertType;
            }
        }
        return null;
    }

    /**
     * 根据 type 值获取警情类型描述
     *
     * @param type 类型值
     * @return 描述
     */
    public static String getDescription(byte type) {
        AlertType alertType = get(type);
        return alertType == null ? null : alertType.getDescription();
    }

    public static Interval<Byte> getFalseAlertInterval() {
        return FALSE_ALERT_LIMIT;
    }

    public static Interval<Byte> getNeedProcessInterval() {
        return NEED_PROCESS_LIMIT;
    }

    /**
     * 判断警情类型是否为误报
     *
     * @param type 类型值
     * @return 是否误报
     */
    public static boolean isFalseAlert(byte type) {
        return type < NORMAL_ALERT.getType();
    }
}
