package com.gosun.isap.warn.api.alert.model.enumeration;

import com.gosun.isap.dao.po.alert.base.BaseAlertLogTemplate;
import com.gosun.isap.dao.mapper.alert.Interval;
import com.gosun.isap.warn.api.alert.util.helper.AlertLogHelper;

/**
 * 警情日志类型。
 * 可以添加类型，不要修改日志类型的定义，必须修改时，要更新数据库以及，注意类型区间的修改
 * 处理与处理中：0-9
 * 处理成功：10-19
 * 处理失败：20 以上
 * <p>
 * <p>创建时间：2017/5/6 16:26</p>
 *
 * @author 娄存银
 * @version 1.0
 */
public enum AlertLogType {

    /**
     * 已经被确认，需要处理，过度状态
     */
    NEED_PROCESS(0),
    /**
     * 联系保安成功
     */
    CALL_GUARD_SUCCESS(1),
    /**
     * 联系保安失败
     */
    CALL_GUARD_FAILED(2),
    /**
     * 保安到达现场
     */
    GUARDS_ARRIVED(3),

    /**
     * 保安回电
     */
    GUARDS_CALLBACK_AGAIN(10),
    /**
     * 保安询问可疑人员
     */
    GUARDS_QUESTIONED_SUSPECT(11),
    /**
     * 没有二次回电
     */
    NOT_CALLBACK_AGAIN(12),

    /**
     * 联系保安失败
     */
    CALL_GUARDS_FAILED(20),
    /**
     * 保安未到达现场
     */
    GUARDS_NOT_ARRIVED(21),
    /**
     * 没有回电
     */
    NOT_CALLBACK(22);

    // 类型筛选区间
    private static final Interval<Byte> FINISHED_LIMIT = new Interval<>(null, (byte) (GUARDS_CALLBACK_AGAIN.getType() - 1));
    private static final Interval<Byte> FAILED_LIMIT = new Interval<>(null, (byte) (CALL_GUARDS_FAILED.getType() - 1));
    private static final Interval<Byte> PROCESSING_LIMIT = new Interval<>(GUARDS_CALLBACK_AGAIN.getType(), null);

    private byte type;
    private BaseAlertLogTemplate template;


    AlertLogType(int type) {
        this.type = (byte) type;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    /**
     * 获取警情日志模板
     *
     * @return 模板
     */
    public BaseAlertLogTemplate getTemplate() {
        if (template == null) {
            template = AlertLogHelper.getTemplate(type);
        }

        return template;
    }

    /**
     * 已经完成的日志类型区间
     *
     * @return 区间
     */
    public static Interval<Byte> getFinishedInterval() {
        return FINISHED_LIMIT;
    }

    /**
     * 失败的日志类型区间
     *
     * @return 区间
     */
    public static Interval<Byte> getFailedInterval() {
        return FAILED_LIMIT;
    }

    /**
     * 处理中的日志类型区间
     *
     * @return 区间
     */
    public static Interval<Byte> getProcessingInterval() {
        return PROCESSING_LIMIT;
    }
}
