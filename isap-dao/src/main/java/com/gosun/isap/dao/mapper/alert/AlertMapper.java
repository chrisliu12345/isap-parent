package com.gosun.isap.dao.mapper.alert;

import com.gosun.isap.dao.po.alert.Alert;
import com.gosun.isap.dao.po.alert.base.BaseAlertResource;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

import static com.gosun.isap.dao.mapper.alert.AlertMapperConst.*;

/**
 * 警情的操作接口
 * <p>创建时间：2017/5/8 14:07</p>
 *
 * @author 娄存银
 * @version 1.0
 */
public interface AlertMapper {
    /**
     * 根据警情 id 更新警情状态
     *
     * @param alertId id
     * @param status  状态
     * @return 行数
     */
    Integer updateStatus(@Param(ID) Long alertId, @Param(STATUS) Byte status);

    /**
     * 更新警情状态以及处理结束时间
     *
     * @param alertId    id
     * @param status     状态
     * @param finishTime 结束时间
     * @return 行数
     */
    Integer updateStatusAndFinishTime(
            @Param(ID) Long alertId,
            @Param(STATUS) Byte status,
            @Param(FINISH_TIME) Date finishTime);

    /**
     * 获取日志中的 referenceId
     *
     * @param alertId 警情 id
     * @param type    日志类型
     * @return referenceId
     */
    Long getAlertLogReferenceId(@Param(ALERT_ID) Long alertId, @Param(TYPE) Byte type);

    /**
     * 根据 id 获取警情
     *
     * @param id 警情 id
     * @return 警情信息
     */
    Alert selectAlertById(@Param(ID) Long id);

    /**
     * 获取小区上次出警时间
     *
     * @param departmentId 小区 id
     * @return 时间
     */
    Date latestProcessedTime(@Param(DEPARTMENT_ID) String departmentId);

    /**
     * 获取用户（社区组合）所有未处理的警情
     *
     * @param departmentIds 社区列表
     * @return 警情列表
     */
    List<Alert> listUnprocessedAlerts(@Param(DEPARTMENT_IDS) List<String> departmentIds,@Param(LIMIT) SqlLimit limit);
    /**
     * 统计未处理的警情数
     *
     * @param departmentIds 社区列表
     * @return 未处理警情数量
     */
    int countUnprocessedAlerts(@Param(DEPARTMENT_IDS) List<String> departmentIds);
    /**
     * 更新截止时间之前发生的警情状态为超时未处理
     *
     * @param date 截止时间
     * @return 行数
     */
    int updateAlertsAsOverTime(@Param(END) Date date);

    /**
     * 获取用户（社区组合）相应状态的警情列表
     *
     * @param status        状态
     * @param departmentIds 社区列表
     * @param start         开始时间
     * @param end           结束时间
     * @param orderBy       排序类型
     * @param limit         分页
     * @return 警情列表
     */
    List<Alert> listAlertsByStatusAndDeps(
            @Param(STATUS) Byte status,
            @Param(DEPARTMENT_IDS) List<String> departmentIds,
            @Param(START) Date start, @Param(END) Date end,
            @Param(ORDER_BY) List<String> orderBy,
            @Param(LIMIT) SqlLimit limit);

    /**
     * 统计警情条数
     *
     * @param status        状态
     * @param departmentIds 社区列表
     * @param start         开始时间
     * @param end           结束时间
     * @return 条数
     */
    Integer countAlertsByStatusAndDeps(
            @Param(STATUS) Byte status,
            @Param(DEPARTMENT_IDS) List<String> departmentIds,
            @Param(START) Date start,
            @Param(END) Date end);

    /**
     * 根据用户 id ,获取相应状态的警情（只能是已经确认过的警情）
     *
     * @param status  状态
     * @param userId  用户 id
     * @param start   开始时间
     * @param end     结束时间
     * @param orderBy 排序类型
     * @param limit   分页
     * @return 警情列表
     */
    List<Alert> listAlertsByStatusAndUserId(
            @Param(STATUS) Byte status,
            @Param(USER_ID) Long userId,
            @Param(START) Date start,
            @Param(END) Date end,
            @Param(ORDER_BY) List<String> orderBy,
            @Param(LIMIT) SqlLimit limit);

    /**
     * 统计警情条数
     *
     * @param status 状态
     * @param userId 用户 id
     * @param start  开始时间
     * @param end    结束时间
     * @return 条数
     */
    Integer countAlertsByStatusAndUserId(
            @Param(STATUS) Byte status,
            @Param(USER_ID) Long userId,
            @Param(START) Date start,
            @Param(END) Date end);

    /**
     * 获取用户（社区组合）相应状态区间的警情列表
     *
     * @param statusInterval 状态区间
     * @param departmentIds  社区列表
     * @param start          开始时间
     * @param end            结束时间
     * @param orderBy        排序类型
     * @param limit          分页
     * @return 警情列表
     */
    List<Alert> listByStatusIntervalAndDeps(
            @Param(STATUS_INTERVAL) Interval<Byte> statusInterval,
            @Param(DEPARTMENT_IDS) List<String> departmentIds,
            @Param(START) Date start, @Param(END) Date end,
            @Param(ORDER_BY) List<String> orderBy,
            @Param(LIMIT) SqlLimit limit);

    /**
     * 统计警情条数
     *
     * @param statusInterval 状态区间
     * @param departmentIds  社区列表
     * @param start          开始时间
     * @param end            结束时间
     * @return 条数
     */
    Integer countByStatusIntervalAndDeps(
            @Param(STATUS_INTERVAL) Interval<Byte> statusInterval,
            @Param(DEPARTMENT_IDS) List<String> departmentIds,
            @Param(START) Date start,
            @Param(END) Date end);

    /**
     * 根据用户 id ,获取相应状态区间的警情（只能是已经确认过的警情）
     *
     * @param statusInterval 状态区间
     * @param userId         用户 id
     * @param start          开始时间
     * @param end            结束时间
     * @param orderBy        排序类型
     * @param limit          分页
     * @return 警情列表
     */
    List<Alert> listByStatusIntervalAndUserId(
            @Param(STATUS_INTERVAL) Interval<Byte> statusInterval,
            @Param(USER_ID) Long userId, @Param(START) Date start,
            @Param(END) Date end, @Param(ORDER_BY) List<String> orderBy,
            @Param(LIMIT) SqlLimit limit);

    /**
     * 统计警情条数
     *
     * @param statusInterval 状态区间
     * @param userId         用户 id
     * @param start          开始时间
     * @param end            结束时间
     * @return 条数
     */
    Integer countByStatusIntervalAndUserId(
            @Param(STATUS_INTERVAL) Interval<Byte> statusInterval,
            @Param(USER_ID) Long userId,
            @Param(START) Date start,
            @Param(END) Date end);

    /**
     * 获取用户（社区组合）的所有警情
     *
     * @param departmentIds 社区列表
     * @param start         开始时间
     * @param end           结束时间
     * @param orderBy       排序类型
     * @param limit         分页
     * @return 警情列表
     */
    List<Alert> listAlertsByDepartments(
            @Param(DEPARTMENT_IDS) List<String> departmentIds,
            @Param(START) Date start,
            @Param(END) Date end,
            @Param(ORDER_BY) List<String> orderBy,
            @Param(LIMIT) SqlLimit limit);

    /**
     * 统计警情条数
     *
     * @param departmentIds 社区列表
     * @param start         开始时间
     * @param end           结束时间
     * @return 条数
     */
    Integer countAlertsByDepartments(
            @Param(DEPARTMENT_IDS) List<String> departmentIds,
            @Param(START) Date start,
            @Param(END) Date end);

    /**
     * 根据警情 id  获取警情
     *
     * @param ids id 列表
     * @return 警情列表
     */
    List<Alert> listAlerts(@Param(IDS) List<Long> ids);

    /**
     * 获取警情资源列表
     *
     * @param id 警情 id
     * @return 资源列表
     */
    List<BaseAlertResource> getResources(@Param(ID) Long id);

    /**
     * 根据警情 id ,资源类型获取最大的 index
     *
     * @param id   警情id
     * @param type 资源类型
     * @return 最大 index
     */
    Integer getResourceIndex(@Param(ID) Long id, @Param(TYPE) Byte type);

    /**
     * 统计警情平均确认时间
     *
     * @param departmentIds 部门
     * @param start         开始时间
     * @param end           结束时间
     * @return 警情平均确认时间
     */
    Double avgOfConfirmTimeByDeps(
            @Param(DEPARTMENT_IDS) List<String> departmentIds,
            @Param(START) Date start,
            @Param(END) Date end);

    /**
     * 统计用户平均反应时间
     *
     * @param departmentIds 部门
     * @param start         开始时间
     * @param end           结束时间
     * @return 用户平均反应时间
     */
    Double avgOfUserResponseTimeByDeps(
            @Param(DEPARTMENT_IDS) List<String> departmentIds,
            @Param(START) Date start,
            @Param(END) Date end);

    /**
     * 统计保安到达现场时间平均值
     *
     * @param departmentIds 部门
     * @param start         开始时间
     * @param end           结束时间
     * @return 保安到达现场时间平均值
     */
    Double avgOfGuardResponseTimeByDeps(
            @Param(DEPARTMENT_IDS) List<String> departmentIds,
            @Param(START) Date start,
            @Param(END) Date end);

    /**
     * 警情发生时平均未处理警情数量
     *
     * @param departmentIds 部门 id
     * @param start         开始时间
     * @param end           结束时间
     * @return 平均未处理警情数
     */
    Double avgOfUnprocessedByDeps(
            @Param(DEPARTMENT_IDS) List<String> departmentIds,
            @Param(START) Date start,
            @Param(END) Date end);

    /**
     * 警情发生到确认完成平均确认警情数量
     *
     * @param departmentIds 部门 id
     * @param start         开始时间
     * @param end           结束时间
     * @return 平均数量
     */
    Double avgOfConfirmedAlertsByDeps(
            @Param(DEPARTMENT_IDS) List<String> departmentIds,
            @Param(START) Date start,
            @Param(END) Date end);

    /**
     * 统计二次回电的数量
     *
     * @param childDepartments 部门 id
     * @param start            开始时间
     * @param end              结束时间
     * @return 二次回电数量
     */
    int countOfCallbackAgain(
            @Param(DEPARTMENT_IDS) List<String> childDepartments,
            @Param(START) Date start,
            @Param(END) Date end);

}
