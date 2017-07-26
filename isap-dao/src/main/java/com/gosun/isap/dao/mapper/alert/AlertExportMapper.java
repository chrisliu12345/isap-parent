package com.gosun.isap.dao.mapper.alert;

import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.gosun.isap.dao.mapper.alert.AlertMapperConst.*;

/**
 * 数据导出 mapper 。
 * 数据导出提供两种查询方式，一种是根据用户 id 查询统计数据，一种是根据部门 id 列表查询统计数据。
 * <p>创建时间：2017-5-25 11:36</p>
 *
 * @author 娄存银
 * @version 1.0
 */
public interface AlertExportMapper {
    /**
     * 根据时间用户获取出警记录
     *
     * @param userId 用户 id
     * @param start  开始时间
     * @param end    结束时间
     * @param limit  分页
     * @return 出警记录
     */
    List<Map<String, Object>> getAlertLogByUser(
            @Param(USER_ID) Long userId,
            @Param(START) Date start,
            @Param(END) Date end,
            @Param(LIMIT) SqlLimit limit);

    /**
     * 根据时间用户统计出警记录条数
     *
     * @param userId 用户 id
     * @param start  开始时间
     * @param end    结束时间
     * @return 出警记录条数
     */
    int countAlertLogByUser(@Param(USER_ID) Long userId, @Param(START) Date start, @Param(END) Date end);

    /**
     * 更加部门列表获取出警记录
     *
     * @param departments 部门
     * @param start       开始时间
     * @param end         结束时间
     * @param limit       分页
     * @return 出警记录
     */
    List<Map<String, Object>> getAlertLogByDepartments(
            @Param(DEPARTMENT_IDS) List<String> departments,
            @Param(START) Date start,
            @Param(END) Date end,
            @Param(LIMIT) SqlLimit limit);

    /**
     * 统计警情记录条数
     *
     * @param departments 部门 id 列表
     * @param start       开始时间
     * @param end         结束时间
     * @return 出警记录条数
     */
    int countAlertLogByDepartments(
            @Param(DEPARTMENT_IDS) List<String> departments,
            @Param(START) Date start,
            @Param(END) Date end);

    /**
     * 根据用户获取未完成出警记录
     *
     * @param userId 用户 id
     * @param start  开始时间
     * @param end    结束时间
     * @param limit  分页
     * @return 未完成出警记录
     */
    List<Map<String, Object>> getUnfinishedAlertsByUser(
            @Param(USER_ID) Long userId,
            @Param(START) Date start,
            @Param(END) Date end,
            @Param(LIMIT) SqlLimit limit);

    /**
     * 根据用户统计未完成出警记录条数
     *
     * @param userId 用户 id
     * @param start  开始时间
     * @param end    结束时间
     * @return 未完成出警记录条数
     */
    int countUnfinishedAlertsByUser(@Param(USER_ID) Long userId, @Param(START) Date start, @Param(END) Date end);

    /**
     * 根据部门获取未完成出警记录
     *
     * @param departments 部门 id 列表
     * @param start       开始时间
     * @param end         结束时间
     * @param limit       分页
     * @return 未完成出警记录
     */
    List<Map<String, Object>> getUnfinishedAlertsByDeps(
            @Param(DEPARTMENT_IDS) List<String> departments,
            @Param(START) Date start,
            @Param(END) Date end,
            @Param(LIMIT) SqlLimit limit);

    /**
     * 获取未完成出警记录条数
     *
     * @param departments 部门 id 列表
     * @param start       开始时间
     * @param end         结束时间
     * @return 未完成出警记录条数
     */
    int countUnfinishedAlertsByDeps(
            @Param(DEPARTMENT_IDS) List<String> departments,
            @Param(START) Date start,
            @Param(END) Date end);

    /**
     * 根据用户获取警情详情
     *
     * @param userId 用户 id
     * @param start  开始时间
     * @param end    结束时间
     * @param limit  分页
     * @return 警情详情记录
     */
    List<Map<String, Object>> getAlertDetailLogByUser(
            @Param(USER_ID) Long userId,
            @Param(START) Date start,
            @Param(END) Date end,
            @Param(LIMIT) SqlLimit limit);

    /**
     * 根据用户统计警情详情记录条数
     *
     * @param userId 用户 id
     * @param start  开始时间
     * @param end    结束时间
     * @return 记录条数
     */
    int countAlertDetailLogByUser(@Param(USER_ID) Long userId, @Param(START) Date start, @Param(END) Date end);

    /**
     * 通过部门 id 列表查询警情详情记录
     *
     * @param departments 部门 id 列表
     * @param start       开始时间
     * @param end         结束时间
     * @param limit       分页
     * @return 警情详情记录
     */
    List<Map<String, Object>> getAlertDetailLogByDepartments(
            @Param(DEPARTMENT_IDS) List<String> departments,
            @Param(START) Date start,
            @Param(END) Date end,
            @Param(LIMIT) SqlLimit limit);

    /**
     * 统计警情详情记录条数
     *
     * @param departments 部门 id 列表
     * @param start       开始时间
     * @param end         结束时间
     * @return 条数
     */
    int countAlertDetailLogByDeps(
            @Param(DEPARTMENT_IDS) List<String> departments,
            @Param(START) Date start,
            @Param(END) Date end);

    /**
     * 获取未确认询问可疑人员记录
     *
     * @param userId 用户 id
     * @param start  开始时间
     * @param end    结束时间
     * @param limit  分页
     * @return 未确认询问可疑人员记录
     */
    List<Map<String, Object>> getQuestionSuspectByUser(
            @Param(USER_ID) Long userId,
            @Param(START) Date start,
            @Param(END) Date end,
            @Param(LIMIT) SqlLimit limit);

    /**
     * 统计未确认询问可疑人员记录条数
     *
     * @param userId 用户 id
     * @param start  开始时间
     * @param end    结束时间
     * @return 条数
     */
    int countQuestionSuspectByUser(@Param(USER_ID) Long userId, @Param(START) Date start, @Param(END) Date end);

    /**
     * 获取未确认询问可疑人员记录
     *
     * @param departments 部门 id 列表
     * @param start       开始时间
     * @param end         结束时间
     * @param limit       分页
     * @return 未确认询问可疑人员记录
     */
    List<Map<String, Object>> getQuestionSuspectByDeps(
            @Param(DEPARTMENT_IDS) List<String> departments,
            @Param(START) Date start,
            @Param(END) Date end,
            @Param(LIMIT) SqlLimit limit);

    /**
     * 统计未确认询问可疑人员记录条数
     *
     * @param departments 部门 id 列表
     * @param start       开始时间
     * @param end         结束时间
     * @return 条数
     */
    int countQuestionSuspectByDeps(
            @Param(DEPARTMENT_IDS) List<String> departments,
            @Param(START) Date start,
            @Param(END) Date end);

    /**
     * 获取警情处置记录
     *
     * @param userId 用户 id
     * @param start  开始时间
     * @param end    结束时间
     * @param limit  分页
     * @return 警情处置记录
     */
    List<Map<String, Object>> getGuardProcessedAlertsByUser(
            @Param(USER_ID) Long userId,
            @Param(START) Date start,
            @Param(END) Date end,
            @Param(LIMIT) SqlLimit limit);

    /**
     * 统计警情处置记录条数
     *
     * @param userId 用户 id
     * @param start  开始时间
     * @param end    结束时间
     * @return 条数
     */
    int countGuardProcessedByUser(@Param(USER_ID) Long userId, @Param(START) Date start, @Param(END) Date end);

    /**
     * 获取警情处置记录
     *
     * @param departments 部门 id 列表
     * @param start       开始时间
     * @param end         结束时间
     * @param limit       分页
     * @return 警情处置记录
     */
    List<Map<String, Object>> getGuardProcessedByDepartment(
            @Param(DEPARTMENT_IDS) List<String> departments,
            @Param(START) Date start,
            @Param(END) Date end,
            @Param(LIMIT) SqlLimit limit);

    /**
     * 统计警情处置记录
     *
     * @param departments 部门 id 列表
     * @param start       开始时间
     * @param end         结束时间
     * @return 警情处置记录
     */
    int countGuardProcessedByDep(
            @Param(DEPARTMENT_IDS) List<String> departments,
            @Param(START) Date start,
            @Param(END) Date end);
}
