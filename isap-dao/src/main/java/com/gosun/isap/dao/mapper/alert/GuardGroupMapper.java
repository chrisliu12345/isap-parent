package com.gosun.isap.dao.mapper.alert;

import com.gosun.isap.dao.po.alert.GuardGroup;
import com.gosun.isap.dao.po.alert.bean.StringIdNameBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

import static com.gosun.isap.dao.mapper.alert.AlertMapperConst.*;

/**
 * <p>创建时间：2017/5/10 9:43</p>
 *
 * @author 娄存银
 * @version 1.0
 */
public interface GuardGroupMapper {
    /**
     * 删除保安组
     *
     * @param id 社区 id
     * @return 行数
     */
    Integer deleteGuardGroup(@Param(ID) Long id);

    /**
     * 删除多个保安组
     *
     * @param ids 保安组 id 列表
     * @return 行数
     */
    Integer deleteGuardGroups(@Param(IDS) List<Long> ids);

    /**
     * 将保安组与社区解除绑定
     *
     * @param groupId      保安组 id
     * @param departmentId 社区 id
     * @return 行数
     */
    Integer unbindDepartment(@Param(GROUP_ID) Long groupId, @Param(DEPARTMENT_ID) String departmentId);

    /**
     * 将保安组与多个社区解绑
     *
     * @param groupId       保安组 id
     * @param departmentIds 部门 id 列表
     * @return 行数
     */
    Integer unbindDepartments(@Param(GROUP_ID) Long groupId, @Param(IDS) List<String> departmentIds);

    /**
     * 解绑保安组所有的社区
     *
     * @param groupId 保安组 id
     * @return 行数
     */
    Integer clearDepartments(@Param(GROUP_ID) Long groupId);

    /**
     * 根据保安组 id 获取保安组列表
     *
     * @param ids ids
     * @return 保安组列表
     */
    List<GuardGroup> listByIds(@Param(IDS) List<Long> ids);

    /**
     * 根据社区获取保安组
     *
     * @param id 社区 id
     * @return 保安组 id 列表
     */
    GuardGroup getGroupByDepartment(@Param(DEPARTMENT_ID) String id);

    /**
     * 根据部门 id 列表获取保安组 id 列表
     *
     * @param departmentIds 部门 id 列表
     * @return 保安组 id 列表
     */
    List<Long> listGroupIdByDepartmentIds(@Param(IDS) List<String> departmentIds);

    /**
     * 获取所有的保安组列表
     *
     * @param orderBy 排序
     * @param limit   分页
     * @return 列表
     */
    List<GuardGroup> listGuardGroup(@Param(ORDER_BY) List<String> orderBy, @Param(LIMIT) SqlLimit limit);

    /**
     * 统计保安组数量
     *
     * @return 数量
     */
    Integer countGuardGroup();

    /**
     * 根据 id 获取保安组
     *
     * @param id id
     * @return 保安组信息
     */
    GuardGroup selectById(@Param(ID) Long id);

    /**
     * 根据保安组获取小区信息
     *
     * @param groupId 保安组 id
     * @return 小区信息
     */
    List<StringIdNameBean> listDepartmentByGroupId(@Param(GROUP_ID) Long groupId);

    /**
     * @param groupId 保安组 id
     * @return 绑定的部门 id
     */
    List<String> listDepartmentIds(@Param(GROUP_ID) Long groupId);

    /**
     * @param groupId 保安组 id
     * @return 保安组下的保安 id
     */
    List<Long> listGuardIds(@Param(GROUP_ID) Long groupId);

    /**
     * @return 所有 id
     */
    List<Long> listAllIds();

    /**
     * @param userId        用户 id
     * @param departmentIds 部门 id
     * @return 用户创建的 group id
     */
    List<Long> listUserCreated(@Param(USER_ID) Long userId, @Param(IDS) List<String> departmentIds);

    List<Long> listGroups(@Param(DEPARTMENT_ID) String departmentId);
}
