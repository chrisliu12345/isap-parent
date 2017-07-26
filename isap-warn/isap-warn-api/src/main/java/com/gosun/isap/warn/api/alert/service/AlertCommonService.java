package com.gosun.isap.warn.api.alert.service;

import com.gosun.isap.dao.mapper.alert.SqlLimit;

import java.util.List;
import java.util.Map;

/**
 * 通用功能封装 Service 。
 * <p>创建时间：2017-6-3 8:43</p>
 *
 * @author 娄存银
 * @version 1.0
 */
public interface AlertCommonService {
    /**
     * 根据用户 id 获取用户名
     *
     * @param userId 用户 id
     * @return 用户名
     */
    String getUserName(Long userId);

    /**
     * @param token 令牌
     * @return 用户 id
     */
    Long getUserId(String token);

    /**
     * 根据设备获取 department id
     *
     * @param deviceId 设备 id
     * @return department id
     */
    String getDeviceDepartment(String deviceId);

    /**
     * @param alertId 警情 id
     * @return 设备 id
     */
    String getAlertDeviceId(Long alertId);

    /**
     * @param alertId 警情 id
     * @return 获取警情所在社区
     */
    String getAlertDepartmentId(Long alertId);

    /**
     * 根据设备获取出警时间限制
     *
     * @param deviceId 设备 id
     * @return 出警时间限制
     */
    Integer getLimitTime(String deviceId);

    /**
     * 获取用户绑定的角色
     *
     * @param userId 用户 id
     * @return 角色 id 列表
     */
    List<Long> getRoles(Long userId);

    /**
     * 根据用户 id 获取包含设备的部门 id 列表
     *
     * @param userId 用户 id
     * @param limit  分页数据
     * @return 部门 id 列表
     */
    List<String> getCommunitiesByUser(Long userId, SqlLimit limit);

    /**
     * 获取部门下的设备列表
     *
     * @param departmentIds 部门列表
     * @param limit         分页
     * @return 设备列表
     */
    List<String> getDevicesByDepartments(List<String> departmentIds, SqlLimit limit);

    /**
     * 查看用户（角色组合）是否绑定相应的部门
     *
     * @param departmentId 部门 id
     * @param roleIds      角色 id 列表
     * @return 角色 id ，未绑定的角色id 为空
     */
    Integer checkDepartmentRole(String departmentId, List<Long> roleIds);

    /**
     * 获取所有设备所属的部门
     *
     * @param limit 分页
     * @return 部门 id 列表
     */
    List<String> getAllCommunities(SqlLimit limit);

    /**
     * 获取所有设备 id
     *
     * @param limit 分页
     * @return 设备 id 列表
     */
    List<String> getAllDevice(SqlLimit limit);

    /**
     * 查看用户（角色组合）是否有菜单的操作权限
     *
     * @param userId 用户 id
     * @param menuId 菜单 id
     * @return 角色菜单绑定 id ，未绑定的 id 为空
     */
    Long checkMenuPermission(Long userId, Long menuId);

    /**
     * 查看用户（角色组合）是否有菜单的操作权限
     *
     * @param userId   用户 id
     * @param menuName 菜单名称
     * @return 角色菜单绑定 id ，未绑定的 id 为空
     */
    Long checkMenuPermission(Long userId, String menuName);


    /**
     * 获取部门下含有设备的部门 id 列表
     *
     * @param parentId 部门 id
     * @param limit    分页
     * @return 部门 id 列表
     */
    List<String> getChildCommunities(String parentId, SqlLimit limit);

    /**
     * @param departmentId 部门 id
     * @return 部门下含有设备的部门数量
     */
    Integer countChildCommunities(String departmentId);

    /**
     * 获取用户可以查看的子部门 id 列表
     *
     * @param parentId 部门 id
     * @param userId   用户 id
     * @param limit    分页
     * @return 部门 id 列表
     */
    List<String> getChildDepartmentsByUserId(String parentId, Long userId, SqlLimit limit);

    /**
     * @param parentId 部门 id
     * @param userId   用户 id
     * @return 用户可以查看的子部门数量
     */
    Integer countChildDepartmentsByUserId(String parentId, Long userId);


    /**
     * @param id 部门 id
     * @return 部门名称
     */
    String getDepartmentName(String id);

    /**
     * @param id 部门 id
     * @return 父部门名称
     */
    String getParentDepartmentName(String id);

    /**
     * @param id 部门 id
     * @return 绑定到同一个保安组的部门
     */
    Integer getRelationDepartmentCount(String id);

    /**
     * @param id 部门 id
     * @return 部门相关的保安数量
     */
    Integer countDepartmentGuards(String id);

    /**
     * 根据关键字查询保安组列表
     *
     * @param key 关键字
     * @return 保安组列表
     */
    List<Map<String, Object>> suggestGuardGroup(String key);

    /**
     * 根据关键字查询部门
     *
     * @param key 关键字
     * @return 部门列表
     */
    List<Map<String, Object>> suggestDepartment(String key);

    /**
     * 根据菜单名获取菜单 id
     *
     * @param menuName 菜单名称
     * @return 菜单 id
     */
    Long getMenuId(String menuName);

    /**
     * @param departmentId 部门 ID
     * @return 判断部门是不是 community
     */
    boolean isCommunity(String departmentId);

    /**
     * @return 根部门 id
     */
    String rootDepartment();

    /**
     * @param userId 用户 id
     * @return 获取用户绑定的小区列表
     */
    List<String> getUserDepartments(Long userId);
    /**
     * @param userId       用户 id
     * @param departmentId 判断用户是否有 department 的权限
     * @return 有无权限
     */
    boolean hasDepartmentPermission(Long userId, String departmentId);

}
