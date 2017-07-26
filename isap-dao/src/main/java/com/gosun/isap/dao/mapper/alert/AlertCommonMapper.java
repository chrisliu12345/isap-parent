package com.gosun.isap.dao.mapper.alert;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

import static com.gosun.isap.dao.mapper.alert.AlertMapperConst.*;

/**
 * 基础功能的实现。
 * 包括对用户角色、部门、设备、权限的一些查询接口。
 * <p>创建时间：2017/5/9 9:01</p>
 *
 * @author 娄存银
 * @version 1.0
 */
public interface AlertCommonMapper {
    /**
     * 根据用户 id 获取用户名
     *
     * @param userId 用户 id
     * @return 用户名
     */
    String getUserName(@Param(ID) Long userId);

    /**
     * 根据设备获取 department id
     *
     * @param deviceId 设备 id
     * @return department id
     */
    String getDeviceDepartment(@Param(DEVICE_ID) String deviceId);

    /**
     * 根据警情获取设备 id
     *
     * @param alertId 警情 id
     * @return 设备 id
     */
    String getAlertDeviceId(@Param(ALERT_ID) Long alertId);

    /**
     * 根据警情获取所在部门 id
     *
     * @param alertId 警情 id
     * @return 部门 id
     */
    String getAlertDepartmentId(@Param(ALERT_ID) Long alertId);

    /**
     * 根据设备获取出警时间限制
     *
     * @param deviceId 设备 id
     * @return 出警时间限制
     */
    Integer getLimitTime(@Param(DEVICE_ID) String deviceId);

    /**
     * 获取用户绑定的角色
     *
     * @param userId 用户 id
     * @return 角色 id 列表
     */
    List<Long> getRoles(@Param(USER_ID) Long userId);

    /**
     * 获取用户角色关联的部门
     *
     * @param userId 用户 id
     * @param limit  分页
     * @return 部门 id 列表
     */
    List<String> getCommunitiesByUser(@Param(USER_ID) Long userId, @Param(LIMIT) SqlLimit limit);

    /**
     * 获取部门下的设备列表
     *
     * @param departmentIds 部门列表
     * @param limit         分页
     * @return 设备列表
     */
    List<String> getDevicesByDepartments(@Param(IDS) List<String> departmentIds, @Param(LIMIT) SqlLimit limit);

    /**
     * 查看用户（角色组合）是否绑定相应的部门
     *
     * @param departmentId 部门 id
     * @param roleIds      角色 id 列表
     * @return 角色 id ，未绑定的角色id 为空
     */
    Integer checkDepartmentRole(@Param(DEPARTMENT_ID) String departmentId, @Param(IDS) List<Long> roleIds);

    /**
     * 获取所有设备所属的部门
     *
     * @param limit 分页
     * @return 部门 id 列表
     */
    List<String> getAllCommunities(@Param(LIMIT) SqlLimit limit);

    /**
     * 获取所有设备 id
     *
     * @param limit 分页
     * @return 设备 id 列表
     */
    List<String> getAllDevice(@Param(LIMIT) SqlLimit limit);

    /**
     * 查看用户（角色组合）是否有菜单的操作权限
     *
     * @param roles  角色列表
     * @param menuId 菜单 id
     * @return 角色菜单绑定 id ，未绑定的 id 为空
     */
    Long checkMenuPermission(@Param(ROLE_IDS) List<Long> roles, @Param(MENU_ID) Long menuId);

    /**
     * 获取所有包含设备的子部门
     *
     * @param parentId 部门 id
     * @param limit    分页数据
     * @return 子部门 id 列表
     */
    List<String> getChildCommunities(@Param(PARENT_ID) String parentId, @Param(LIMIT) SqlLimit limit);

    /**
     * 统计含有设备的子部门数量
     *
     * @param departmentId 部门 id
     * @return 子部门数量
     */
    Integer countChildCommunities(@Param(PARENT_ID) String departmentId);

    /**
     * 获取用户可以查看的含有设备的子部门
     *
     * @param parentId 父部门 id
     * @param userId   用户 id
     * @param limit    分页
     * @return 子部门 id 列表
     */
    List<String> getChildDepartmentsByUserId(
            @Param(PARENT_ID) String parentId,
            @Param(USER_ID) Long userId,
            @Param(LIMIT) SqlLimit limit);

    /**
     * 统计用户可以查看的含有设备的子部门数量
     *
     * @param parentId 父部门 id
     * @param userId   用户 id
     * @return 子部门数量
     */
    Integer countChildDepartmentsByUserId(@Param(PARENT_ID) String parentId, @Param(USER_ID) Long userId);

    /**
     * 获取部门名称
     *
     * @param id 部门 id
     * @return 部门名称
     */
    String getDepartmentName(@Param(DEPARTMENT_ID) String id);

    /**
     * 获取父部门名称
     *
     * @param id 部门 id
     * @return 父部门名称
     */
    String getParentDepartmentName(@Param(DEPARTMENT_ID) String id);

    /**
     * 根据部门 id 获取相关保安组绑定社区的数量（前提：一个社区只能分到一个保安组下）
     *
     * @param id 部门 id
     * @return 相关社区数量
     */
    Integer getRelationDepartmentCount(@Param(DEPARTMENT_ID) String id);

    /**
     * 统计部门下的保安数量
     *
     * @param id 部门 id
     * @return 保安数量
     */
    Integer countDepartmentGuards(@Param(DEPARTMENT_ID) String id);

    /**
     * 根据关键字查询相关保安组
     *
     * @param key   关键字
     * @param limit 分页
     * @return 保安组信息
     */
    List<Map<String, Object>> suggestGuardGroup(@Param(KEY) String key, @Param(LIMIT) SqlLimit limit);

    /**
     * 根据关键字查询社区
     *
     * @param key   关键字
     * @param limit 分页
     * @return 社区相关保安组信息
     */
    List<Map<String, Object>> suggestGuardGroupByDepartment(@Param(KEY) String key, @Param(LIMIT) SqlLimit limit);

    /**
     * 根据关键字查询社区
     *
     * @param key   关键字
     * @param limit 分页
     * @return 下级社区相关保安组信息
     */
    List<Map<String, Object>> suggestGuardGroupByChildDep(@Param(KEY) String key, @Param(LIMIT) SqlLimit limit);

    /**
     * 根据关键字查询社区
     *
     * @param key   关键字
     * @param limit 分页
     * @return 社区信息
     */
    List<Map<String, Object>> suggestDepartment(@Param(KEY) String key, @Param(LIMIT) SqlLimit limit);

    /**
     * 根据关键字查询社区
     *
     * @param key   关键字
     * @param limit 分页
     * @return 下级社区信息
     */
    List<Map<String, Object>> suggestChildDepartment(@Param(KEY) String key, @Param(LIMIT) SqlLimit limit);

    /**
     * @param menuName 菜单名称
     * @return 菜单 id
     */
    Long getMenuId(@Param(NAME) String menuName);

    /**
     * @param departmentId 部门 id
     * @return 第一个设备 id
     */
    String getFirstDeviceId(@Param(DEPARTMENT_ID) String departmentId);

    /**
     * @param departmentId 部门 id
     * @return 第一个子部门 id
     */
    String getFirstChildDepartmentId(@Param(DEPARTMENT_ID) String departmentId);

    /**
     * @param userId 用户 id
     * @return 用户绑定的部门
     */
    List<String> getUserDepartments(@Param(USER_ID) Long userId);

    String hasChildDepartment(@Param(USER_ID) Long userId,@Param(DEPARTMENT_ID) String departmentId);
}
