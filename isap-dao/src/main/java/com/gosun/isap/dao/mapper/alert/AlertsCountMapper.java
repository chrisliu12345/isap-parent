package com.gosun.isap.dao.mapper.alert;

import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

import static com.gosun.isap.dao.mapper.alert.AlertMapperConst.*;

/**
 * <p>创建时间：2017/5/4 9:05</p>
 *
 * @author 娄存银
 * @version 1.0
 */
public interface AlertsCountMapper {
    /**
     * 统计用户（社区组合）相应状态的警情数，主要用于统计未处理的以及处理中的
     *
     * @param departmentIds 社区 ID 列表
     * @param status 状态
     * @return  数量
     */
    Integer countByDepartmentsAndStatus(@Param(IDS) List<String> departmentIds, @Param(STATUS) Byte status);

    /**
     * 统计社区上报的警情总数
     *
     * @param departmentIds 社区 id
     * @param start 开始时间 为空不设置时间下限
     * @param end 结束时间 为空不设置时间上限
     * @return 数量
     */
    Integer totalCountByDepartments(@Param(IDS) List<String> departmentIds ,@Param(START) Date start, @Param(END) Date end);

    /**
     * 统计到现在为止确认的警情数
     *
     * @param userId 用户 id
     * @param start 开始时间 为空不设置时间下限
     * @param end 结束时间 为空时 代表现在
     * @return 数量
     */
    Integer countConfirmedInResponseTime(@Param(START) Date start,@Param(END) Date end, @Param(USER_ID) Long userId);

    /**
     *时间段内处理为误报的警情数
     *
     * @param start 开始时间 为空不统计
     * @param end 结束时间 为空表示到当前时间
     * @param userId 用户 id
     * @return 数量
     */
    Integer countFalseInResponseTime(@Param(START) Date start, @Param(END) Date end, @Param(USER_ID) Long userId);

    /**
     * 根据警情类型、状态、社区以及时间段统计警情数量
     *
     * @param type 警情类型
     * @param status 警情状态
     * @param start 开始时间 为空不设限制
     * @param end 结束时间 为空不设限制
     * @param departmentId 社区 id
     * @return 数量
     */
    Integer countByTypeStatusDepartment(@Param(TYPE) Byte type, @Param(STATUS) Byte status, @Param(START) Date start, @Param(END) Date end, @Param(DEPARTMENT_ID) String departmentId);

    /**
     * 根据警情类型、状态、社区以及时间段统计警情数量
     *
     * @param type 警情类型
     * @param status 警情状态
     * @param start 开始时间 为空，不设置时间统计下限
     * @param end 结束时间 为空，不设置时间统计上限
     * @param departmentIds 社区 id 不能为空，否则会抛异常
     * @return 数量
     */
    Integer countByTypeStatusDepartments(@Param(TYPE) Byte type,@Param(STATUS) Byte status, @Param(START) Date start, @Param(END) Date end, @Param(IDS) List<String> departmentIds);
    /**
     * 根据警情 类型、状态区间 以及社区时间段 统计警情数量
     *
     * @param typeInterval 类型区间 ，区间为空代表所有类型
     * @param statusInterval 状态区间，区间为空代表所有状态
     * @param start 开始时间 为空，不设置时间统计下限
     * @param end 结束时间 为空，不设置时间统计上限
     * @param departmentId 社区 id
     * @return 数量
     */
    Integer countByTypeStatusSectionAndDepartment(@Param(TYPE_INTERVAL) Interval typeInterval, @Param(STATUS_INTERVAL) Interval statusInterval, @Param(START) Date start, @Param(END) Date end, @Param(DEPARTMENT_ID) String departmentId);

    /**
     * 根据警情 类型、状态区间 以及社区时间段 统计警情数量
     *
     * @param typeInterval 类型区间 ，区间为空代表所有类型
     * @param statusInterval 状态区间，区间为空代表所有状态
     * @param start 开始时间 为空，不设置时间统计下限
     * @param end 结束时间 为空，不设置时间统计上限
     * @param departmentIds 社区 id 不能为空，否则会抛异常
     * @return 数量
     */
    Integer countByTypeStatusIntervalAndDepartments(@Param(TYPE_INTERVAL) Interval typeInterval, @Param(STATUS_INTERVAL) Interval statusInterval, @Param(START) Date start, @Param(END) Date end, @Param(IDS) List<String> departmentIds);

    /**
     * 统计某个时间段内用户（小区组）未处理的警情数
     * @param start 开始时间 为空不做限制
     * @param end 结束时间 为空代表当前时间
     * @param departmentIds 社区列表
     * @return 数量
     */
    Integer countUnprocessedAlerts(@Param(START) Date start,@Param(END) Date end,@Param(DEPARTMENT_IDS) List<String> departmentIds);

    int countQuestionedSuspectAlerts(@Param(START) Date start, @Param(END) Date end, @Param(DEPARTMENT_IDS) List<String> childDepartments);
}
