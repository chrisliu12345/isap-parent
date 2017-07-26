package com.gosun.isap.warn.api.alert.model.enumeration;

/**
 * 警情资源类型。
 * <p>创建时间：2017/5/10 14:56</p>
 *
 * @author 娄存银
 * @version 1.0
 */
public enum AlertResourceType {
    /**
     * 警情发生时的截图
     */
    GENERATE_PICTURE(0),
    /**
     * 保安到达现场截图
     */
    GUARD_ARRIVE_PICTURE(1),
    /**
     * 询问可疑人员图片
     */
    SUSPECT_PICTURE(2);

    private byte type;

    AlertResourceType(int type) {
        this.type = (byte) type;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }
}
