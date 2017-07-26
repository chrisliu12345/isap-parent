package com.gosun.isap.warn.api.alert.service;

import com.gosun.isap.dao.mapper.alert.SqlLimit;
import com.gosun.isap.dao.po.alert.DetailGuardGroup;
import com.gosun.isap.dao.po.alert.base.BaseGuard;
import com.gosun.isap.dao.po.alert.base.BaseGuardGroup;
import com.gosun.isap.dao.po.alert.GuardGroup;
import com.gosun.isap.dao.po.alert.bean.StringIdNameBean;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>创建时间：2017/5/9 19:34</p>
 *
 * @author 娄存银
 * @version 1.0
 */
public interface GuardGroupService {
    /**
     * 添加保安组
     *
     * @param guardGroup 保安组对象
     * @return 操作结果
     */
    boolean addGuardGroup(BaseGuardGroup guardGroup);

    /**
     * 更新保安组信息
     *
     * @param guardGroup 保安组对象
     * @return 操作结果
     */
    boolean updateGuardGroup(BaseGuardGroup guardGroup);

    /**
     * 删除保安组
     *
     * @param id 保安组 id
     * @return 操作结果
     */
    boolean deleteGuardGroup(long id);

    /**
     * 批量删除保安组
     *
     * @param ids 保安组 id
     * @return 删除的行数
     */
    int deleteGuardGroup(List<Long> ids);

    /**
     * 保安组绑定社区
     *
     * @param groupId      保安组 id
     * @param departmentId 社区 id
     * @return 操作结果
     */
    boolean bindDepartment(long groupId, String departmentId,Long userId);

    /**
     * 保安组绑定社区
     *
     * @param groupId       保安组 id
     * @param departmentIds 社区 id 列表
     * @return 绑定的社区个数
     */
    int bindDepartment(long groupId, List<String> departmentIds,Long userId);

    /**
     * 保安组与社区解除绑定
     *
     * @param groupId      保安组 id
     * @param departmentId 社区 id
     * @return 操作结果
     */
    boolean unbindDepartment(long groupId, String departmentId);

    /**
     * 保安组与社区解除绑定
     *
     * @param groupId       保安组 id
     * @param departmentIds 社区 id 列表
     * @return 解除绑定的社区个数
     */
    int unbindDepartment(long groupId, List<String> departmentIds);

    /**
     * 解除保安组所有绑定的小区
     *
     * @param groupId 保安组 id
     * @return 解除绑定的社区个数
     */
    int unbindDepartment(long groupId);

    /**
     * 获取保安组详情
     *
     * @param id 保安组 id
     * @return 保安组详细信息
     */
    DetailGuardGroup getById(long id);

    /**
     * 获取社区列表
     *
     * @param groupId 保安组 id
     * @return 社区信息列表
     */
    List<StringIdNameBean> getDepartmentByGroupId(long groupId);

    /**
     * 根据保安组 id 获取相关保安
     *
     * @param groupId 保安组 id
     * @return 保安列表
     */
    List<BaseGuard> getGuardByGroupId(long groupId);

    /**
     * 根据社区 id 获取相关的保安组 id
     *
     * @param departmentId 社区 id
     * @return 保安组 id 列表
     */
    List<Long> listGroupId(String departmentId);


    /**
     * 根据子部门 id 列表获取相关的保安组 id
     *
     * @param departmentId 社区 id
     * @return 保安组 id 列表
     */
    List<Long> listChildGroupId(String departmentId);

    /**
     * 根据保安组 id 列表，获取保安组
     *
     * @param groupIds id 列表
     * @return 保安组列表
     */
    List<GuardGroup> listGuardGroupById(List<Long> groupIds);

    /**
     * 根据社区 ID 获取保安组
     *
     * @param departmentId 社区 id
     * @return 保安组列表
     */
    List<GuardGroup> listGuardGroupByDepartmentId(String departmentId);

    /**
     * 根据社区 ID 获取保安组
     *
     * @param departmentIds 社区 id 列表
     * @return 保安组列表
     */
    List<GuardGroup> listGuardGroupByDepartmentId(List<String> departmentIds);

    /**
     * 获取全部保安组列表
     *
     * @param orderBy 排序依据
     * @param limit   条数
     * @return 保安组列表
     */
    List<GuardGroup> listGuardGroup(List<String> orderBy, SqlLimit limit);

    /**
     * 统计保安组的数量
     *
     * @return 保安组数量
     */
    int countGuardGroup();

    /**
     * @param departmentId 部门 id
     * @return 保安组信息
     */
    GuardGroup getBindGuardGroup(String departmentId);

    /**
     * @param groupId 保安组id
     * @return 绑定的部门 id 列表
     */
    List<String> listDepartmentIds(Long groupId);

    /**
     * @param groupId 保安组 id
     * @param start   开始时间
     * @param end     结束时间
     * @param limit   分页
     * @return 可疑人员询问确认数据
     */
    List<Map<String, Object>> questionedConfirm(long groupId, Date start, Date end, SqlLimit limit);

    /**
     * @param groupId 保安组 id
     * @param start   开始时间
     * @param end     结束时间
     * @return 可疑人员询问确认数据条数
     */
    int countQuestionedConfirm(long groupId, Date start, Date end);

    /**
     * @param groupId 保安组 id
     * @param start   开始时间
     * @param end     结束时间
     * @param limit   分页
     * @return 未完成出警
     */
    List<Map<String, Object>> unfinishedAlerts(long groupId, Date start, Date end, SqlLimit limit);

    /**
     * @param groupId 保安组 id
     * @param start   开始时间
     * @param end     结束时间
     * @return 未完成出警数
     */
    int countUnfinishedAlerts(long groupId, Date start, Date end);
}
