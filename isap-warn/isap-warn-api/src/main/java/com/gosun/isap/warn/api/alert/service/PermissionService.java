package com.gosun.isap.warn.api.alert.service;

/**
 * <p>创建时间：2017/5/12 16:35</p>
 *
 * @author 娄存银
 * @version 1.0
 */
public interface PermissionService {

    /**
     * 判断是否为已经登录的超级管理员用户
     *
     * @param userId 用户 id
     * @param token  token
     * @return 是否为已经登录的超级管理员
     */
    boolean isLoggedSupperAdmin(Long userId, String token);

    /**
     * 判断用户是否已经登录
     *
     * @param userId 用户 id
     * @param token  token
     * @return 是否登录
     */
    boolean isUserLoggedIn(Long userId, String token);

    /**
     * 判断用户是否有查看警情的权限
     *
     * @param userId  用户 id
     * @param alertId 警情 id
     * @return 是否有权限
     */
    boolean checkAlertPermission(Long userId, Long alertId);

    /**
     * 判断用户是否有部门的权限
     *
     * @param userId       用户 id
     * @param departmentId 部门 id
     * @return 是否有权限
     */
    boolean checkDepartmentPermission(Long userId, String departmentId);

    /**
     * 判断用户是否有警情列表的权限
     *
     * @param userId 用户 id
     * @return 是否有权限
     */
    boolean checkAlertListPermission(Long userId);

    /**
     * 判断用户是否有警情处理的权限
     *
     * @param userId 用户 id
     * @return 是否有权限
     */
    boolean checkAlertProcessPermission(Long userId);

    /**
     * 判断用户是否有警情处理权限
     *
     * @param userId 用户 id
     * @return 是否有权限
     */
    boolean checkGuardManagementPermission(Long userId);

    /**
     * 判断用户是否有查看保安信息的权限
     *
     * @param userId  用户 id
     * @param guardId 保安 id
     * @return 是否有权限
     */
    boolean checkGuardPermission(Long userId, Long guardId);

    /**
     * 判断用户是否有查看保安信息的权限
     *
     * @param userId  用户 id
     * @param groupId 保安组 id
     * @return 是否有权限
     */
    boolean checkGroupPermission(Long userId,Long groupId);
}
