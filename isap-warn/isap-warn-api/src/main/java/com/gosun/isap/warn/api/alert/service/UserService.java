package com.gosun.isap.warn.api.alert.service;

import com.gosun.isap.dao.mapper.alert.SqlLimit;

import java.util.List;

/**
 * 用户服务，封装外部 api ,对内提供服务
 * <p>创建时间：2017/5/3 11:56</p>
 *
 * @author 娄存银
 * @version 1.0
 */
public interface UserService {
    /**
     * 根据上下文信息，获取用户 id
     *
     * @param token token
     * @return 用户 id
     */
    Long getUserId(String token);

    /**
     * 根据用户 id 获取 role id
     *
     * @param userId 用户 id
     * @return 角色 id 列表
     */
    List<Long> getRoles(Long userId);

    /**
     * 根据id 获取用户名
     *
     * @param userId 用户 id
     * @return 用户名
     */
    String getUserName(Long userId);

    /**
     * 获取用户信息
     *
     * @param userId 用户 id
     * @return 用户信息
     */
    String getUserInfo(Long userId);

    /**
     * @param userId 用户 id
     * @return 用户绑定的社区列表
     */
    List<String> getUserDepartment(Long userId);

    /**
     * 根据用户 id ,查看相关联的社区，社区为最底层的部门
     *
     * @param userId 用户 id
     * @return 社区 id 列表
     */
    List<String> getCommunities(Long userId);

    /**
     * 获取用户有权限的部门 id 列表
     *
     * @param userId 用户 id
     * @param limit  分页
     * @return 部门 id 列表
     */
    List<String> getCommunities(Long userId, SqlLimit limit);

    /**
     * 根据用户 id ,查看相关联的设备
     *
     * @param userId 用户 id
     * @return 设备列表
     */
    List<String> getDevices(Long userId);

    /**
     * @param userId 用户 id
     * @param limit  分页
     * @return 设备 id 列表
     */
    List<String> getDevices(Long userId, SqlLimit limit);

    /**
     * 查看部门下的子部门
     *
     * @param userId   用户 id
     * @param parentId 部门 id
     * @return 子部门 id 列表
     */
    List<String> getChildCommunity(Long userId, String parentId);

    /**
     * 查看部门下的子部门
     *
     * @param userId   用户 id
     * @param parentId 部门 id
     * @param limit    分页
     * @return 子部门 id 列表
     */
    List<String> getChildCommunity(Long userId, String parentId, SqlLimit limit);

    /**
     * @param userId   用户 id
     * @param parentId 部门 id
     * @return 子部门数量
     */
    int countChildCommunity(Long userId, String parentId);

    /**
     * @param userId 用户 id
     * @return 是否为超级用户
     */
    boolean isSupperAdmin(Long userId);


    /**
     * @param userId 用户 id
     * @return 用户可以查看的所有保安组
     */
    List<Long> getGuardGroups(long userId);

    /**
     * @param userId 用户 id
     * @return 用户可以查看的所有保安组
     */
    List<Long> getGuardGroups(long userId, String departmentId);

    /**
     * @param userId  用户 id
     * @param groupId 判断用户是否有 group 的权限
     * @return 有无权限
     */
    boolean hasGroupPermission(Long userId, Long groupId);

    /**
     * @param userId       用户 id
     * @param departmentId 判断用户是否有 department 的权限
     * @return 有无权限
     */
    boolean hasDepartmentPermission(Long userId, String departmentId);

    /**
     * @param userId  用户 id
     * @param guardId 保安 id
     * @return 有无权限
     */
    boolean hasGuardPermission(Long userId, Long guardId);

    /**
     * @param userId  用户 id
     * @param alertId 警情 id
     * @return 有无权限
     */
    boolean hasAlertPermission(Long userId, Long alertId);

    boolean hasMenuPermission(Long userId,String menuName);

    boolean hasMenuPermission(Long userId,Long menuId);

}
