package com.gosun.isap.dao.mapper.alert;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 测试模块用到的 mapper
 * <p>创建时间：2017/5/9 9:01</p>
 *
 * @author 娄存银
 * @version 1.0
 */
public interface AlertTestMapper {

    /**
     * 获取一个可用的 deviceId
     *
     * @return deviceId
     */
    String getDeviceId();

    /**
     * 获取一个可用的 departmentId
     *
     * @return departmentId
     */
    String getDepartmentId();

    /**
     * 获取一个用户 id
     *
     * @return 用户 id
     */
    Long getUserId();

    /**
     * 获取一个保安 id
     *
     * @return 保安 id
     */
    Long getGuardId();

    /**
     * 获取一个保安组 id
     *
     * @return 保安组 id
     */
    Long getGuardGroupId();

    /**
     *
     * @param parentId 父部门 id
     * @return 子部门 id
     */
    List<String> getChildDepartment(@Param("parentId") String parentId);
}
