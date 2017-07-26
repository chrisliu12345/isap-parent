package com.gosun.isap.warn.api.alert.rest;

import com.gosun.isap.dao.po.alert.DetailGuardGroup;
import com.gosun.isap.dao.po.alert.GuardGroup;
import com.gosun.isap.dao.po.alert.bean.StringIdNameBean;
import com.gosun.isap.warn.api.alert.model.ResponseData;

import java.util.List;
import java.util.Map;

/**
 * 保安组管理服务。
 * <p>创建时间：2017-5-19 12:00</p>
 *
 * @author 娄存银
 * @version 1.0
 */
public interface GuardGroupManageRestService extends RestConst {
    /**
     * @param departmentId 部门 id
     * @param sort         排序
     * @param start        分页
     * @param limit        分页
     * @param userId       用户 id
     * @return 保安组列表
     */
    ResponseData<List<GuardGroup>> listGuardGroup(String departmentId, String sort, Integer start, Integer limit, Long userId);

    /**
     * @param group  GuardGroup 数据
     * @param userId 用户 id
     * @return 请求结果
     */
    ResponseData addGuardGroup(GuardGroup group, Long userId);

    /**
     * @param groupId 保安组 id
     * @param group   GuardGroup 数据
     * @param userId  用户 id
     * @return 请求结果
     */
    ResponseData updateGuardGroup(Long groupId, GuardGroup group, Long userId);

    /**
     * @param groupId 保安组 id
     * @param userId  用户 id
     * @return 请求结果
     */
    ResponseData deleteGuardGroup(Long groupId, Long userId);

    /**
     * @param id     保安组 id
     * @param userId 用户 id
     * @return 保安组信息
     */
    ResponseData<DetailGuardGroup> getGuardGroup(Long id, Long userId);

    /**
     * 根据部门获取绑定的保安组
     *
     * @param departmentId 部门 id
     * @param userId       用户 id
     * @return ResponseData
     */
    ResponseData<GuardGroup> getGuardGroup(String departmentId, Long userId);

    /**
     * 取消绑定保安组与部门
     *
     * @param departmentId 部门 id
     * @param groupId      保安组 id
     * @param userId       用户 id
     * @return ResponseData
     */
    ResponseData unbindDepartment(String departmentId, Long groupId, Long userId);

    /**
     * 取消绑定保安组与部门
     *
     * @param departmentIds 部门 id
     * @param groupId       保安组 id
     * @param userId        用户 id
     * @return ResponseData
     */
    ResponseData unbindDepartment(List<String> departmentIds, Long groupId, Long userId);

    /**
     * 绑定保安组与部门
     *
     * @param departmentId 部门 id
     * @param userId       用户 id
     * @param groupId      保安组 id
     * @return ResponseData
     */
    ResponseData bindDepartment(String departmentId, Long groupId, Long userId);

    /**
     * @param groupId 小组 id
     * @param userId  用户 id
     * @return 绑定的部门
     */
    ResponseData<List<StringIdNameBean>> groupDepartments(Long groupId, Long userId);

    /**
     * @param map    id 列表
     * @param userId 用户 id
     * @return 结果
     */
    ResponseData<Integer> deleteGroups(Map<String, List<Long>> map, Long userId);
}
