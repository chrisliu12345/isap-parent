package com.gosun.isap.dao.po.alert;

import com.gosun.isap.dao.mapper.alert.OrderByBuilder;
import com.gosun.isap.dao.po.alert.base.BaseAlert;

import java.util.List;

/**
 * 警情
 * <p>创建时间：2017/5/11 9:05</p>
 *
 * @author 娄存银
 * @version 1.0
 */
public class Alert extends BaseAlert {
    /**
     * 默认排序规则
     */
    public static final List<String> DEFAULT_ORDER_BY;
    /**
     * 按警情确认时间排序
     */
    public static final List<String> CONFIRM_ORDER_BY;
    /**
     * 按处理结束时间排序
     */
    public static final List<String> FINISHED_ORDER_BY;

    static {
        DEFAULT_ORDER_BY = new OrderByBuilder()
                .add(ADD_TIME, true)
                .build();

        CONFIRM_ORDER_BY = new OrderByBuilder()
                .add(CONFIRM_TIME, true)
                .build();

        FINISHED_ORDER_BY = new OrderByBuilder()
                .add(FINISH_TIME, true)
                .build();
    }

    private String deviceName;
    private String deviceBriefName;
    private String deviceNameSpell;
    private String departmentName;
    private Long guardId;
    private String guardName;
    private String guardPhone;

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceBriefName() {
        return deviceBriefName;
    }

    public void setDeviceBriefName(String deviceBriefName) {
        this.deviceBriefName = deviceBriefName;
    }

    public String getDeviceNameSpell() {
        return deviceNameSpell;
    }

    public void setDeviceNameSpell(String deviceNameSpell) {
        this.deviceNameSpell = deviceNameSpell;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Long getGuardId() {
        return guardId;
    }

    public void setGuardId(Long guardId) {
        this.guardId = guardId;
    }

    public String getGuardName() {
        return guardName;
    }

    public void setGuardName(String guardName) {
        this.guardName = guardName;
    }

    public String getGuardPhone() {
        return guardPhone;
    }

    public void setGuardPhone(String guardPhone) {
        this.guardPhone = guardPhone;
    }

    @Override
    public String toString() {
        return "Alert{" +
                "deviceName='" + deviceName + '\'' +
                ", deviceBriefName='" + deviceBriefName + '\'' +
                ", deviceNameSpell='" + deviceNameSpell + '\'' +
                ", departmentName='" + departmentName + '\'' +
                "} " + super.toString();
    }
}
