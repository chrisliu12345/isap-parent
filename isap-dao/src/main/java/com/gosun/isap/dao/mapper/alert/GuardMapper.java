package com.gosun.isap.dao.mapper.alert;

import com.gosun.isap.dao.po.alert.Guard;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.gosun.isap.dao.mapper.alert.AlertMapperConst.*;

/**
 * <p>创建时间：2017/5/9 19:02</p>
 *
 * @author 娄存银
 * @version 1.0
 */
public interface GuardMapper {
    /**
     * 删除保安
     *
     * @param id id
     * @return 行数
     */
    Integer deleteById(@Param(ID) Long id);

    /**
     * 根据 id 删除保安
     *
     * @param ids 保安 id 列表
     * @return 行数
     */
    Integer deleteByIds(@Param(IDS) List<Long> ids);

    /**
     * @param keyword 关键字
     * @param orderBy 排序
     * @param limit   分页
     * @return 列表
     */
    List<Guard> listGuards(
            @Param(KEY) String keyword,
            @Param(ORDER_BY) List<String> orderBy,
            @Param(LIMIT) SqlLimit limit);

    /**
     * @param keyword 关键字
     * @return 数量
     */
    List<Guard> countGuards(@Param(KEY) String keyword);

    /**
     * 根据保安组获取保安
     *
     * @param groupId 保安组 id
     * @param orderBy 排序
     * @param limit   分页
     * @return 保安列表
     */
    List<Guard> listGuardsByGroup(
            @Param(GROUP_ID) Long groupId,
            @Param(KEY) String keyword,
            @Param(ORDER_BY) List<String> orderBy,
            @Param(LIMIT) SqlLimit limit);

    /**
     * 统计保安组下的保安数
     *
     * @param groupId 保安组 id
     * @return 保安数
     */
    Integer countGuardsByGroup(
            @Param(KEY) String keyword,
            @Param(GROUP_ID) Long groupId);

    /**
     * 获取保安组下的保安
     *
     * @param groupId 保安组 id 列表
     * @param orderBy 排序
     * @param limit   分页
     * @return 保安
     */
    List<Guard> listGuardsByGroups(
            @Param(GROUP_IDS) List<Long> groupId,
            @Param(KEY) String keyword,
            @Param(ORDER_BY) List<String> orderBy,
            @Param(LIMIT) SqlLimit limit);

    /**
     * 根据保安组统计保安数
     *
     * @param groupId 保安组 id 列表
     * @return 保安组数量
     */
    Integer countGuardsByGroups(
            @Param(KEY) String keyword,
            @Param(GROUP_IDS) List<Long> groupId);

    /**
     * 获取小区保安
     *
     * @param departmentId 小区 id
     * @return 保安 id 列表
     */
    List<Long> getGroupIdByDepartment(@Param(ID) String departmentId);

    /**
     * 根据部门获取绑定的保安组
     *
     * @param departmentIds 社区 id 列表
     * @return 保安组 id 列表
     */
    List<Long> getGroupIdByDepartments(@Param(IDS) List<String> departmentIds);

    /**
     * 获取保安信息
     *
     * @param id id
     * @return 保安信息
     */
    Guard selectById(@Param(ID) Long id);

    /**
     * 获取保安处理的警情列表
     *
     * @param guardId 保安id
     * @param start   开始时间
     * @param end     结束时间
     * @param orderBy 排序
     * @param limit   分页
     * @return 警情列表
     */
    List<Long> listAlertIds(
            @Param(GUARD_ID) Long guardId,
            @Param(START) Date start,
            @Param(END) Date end,
            @Param(ORDER_BY) List<String> orderBy,
            @Param(LIMIT) SqlLimit limit);

    /**
     * 获取未完成出警 id 列表
     *
     * @param guardId 保安 id
     * @param start   开始时间
     * @param end     结束时间
     * @param limit   分页
     * @return id 列表
     */
    List<Long> listUnfinishedAlertsId(
            @Param(GUARD_ID) Long guardId,
            @Param(START) Date start,
            @Param(END) Date end,
            @Param(LIMIT) SqlLimit limit);

    /**
     * @param guardId 保安 id 列表
     * @param start   开始时间
     * @param end     结束时间
     * @return
     */
    int countUnfinishedAlerts(
            @Param(GUARD_ID) Long guardId,
            @Param(START) Date start,
            @Param(END) Date end);

    /**
     * 统计多个保安的数据
     *
     * @param guards 保安 id 列表
     * @param start  开始时间
     * @param end    结束时间
     * @param limit  分页
     * @return 警情 id 列表
     */
    List<Long> listGuardsUnfinishedAlertsId(
            @Param(IDS) List<Long> guards,
            @Param(START) Date start,
            @Param(END) Date end,
            @Param(LIMIT) SqlLimit limit);

    /**
     * @param guards 保安 id 列表
     * @param start  开始时间
     * @param end    结束时间
     * @return
     */
    int countGuardsUnfinishedAlerts(
            @Param(IDS) List<Long> guards,
            @Param(START) Date start,
            @Param(END) Date end);

    /**
     * 获取未完成警情信息
     *
     * @param ids alertId 列表
     * @return 未完成警情信息
     */
    List<Map<String, Object>> listUnfinishedAlerts(@Param(IDS) List<Long> ids);

    /**
     * @param guardId 保安 id
     * @param start   开始时间
     * @param end     结束时间
     * @param limit   分页
     * @return 警情 id 列表
     */
    List<Long> listQuestionedAlertIds(
            @Param(GUARD_ID) Long guardId,
            @Param(START) Date start,
            @Param(END) Date end,
            @Param(LIMIT) SqlLimit limit);

    /**
     * @param guardId 保安 id
     * @param start   开始时间
     * @param end     结束时间
     * @return 询问可疑人员次数
     */
    int countQuestionedAlerts(
            @Param(GUARD_ID) List<Long> guardId,
            @Param(START) Date start,
            @Param(END) Date end);


    /**
     * @param ids   保安 id
     * @param start 开始时间
     * @param end   结束时间
     * @param limit 分页
     * @return 警情 id 列表
     */
    List<Long> listGuardsQuestionedAlertIds(
            @Param(IDS) List<Long> ids,
            @Param(START) Date start,
            @Param(END) Date end,
            @Param(LIMIT) SqlLimit limit);

    /**
     * @param guards 保安 id 列表
     * @param start  开始时间
     * @param end    结束时间
     * @return 询问可疑人员次数
     */
    int countGuardsQuestionedAlerts(
            @Param(IDS) List<Long> guards,
            @Param(START) Date start,
            @Param(END) Date end);

    /**
     * 统计保安出警次数
     *
     * @param guardId 保安 id
     * @param start   开始时间
     * @param end     结束时间
     * @return 出警次数
     */
    Integer countAlerts(@Param(GUARD_ID) Long guardId, @Param(START) Date start, @Param(END) Date end);

    /**
     * 统计保安出警失败次数
     *
     * @param guardId 保安 id
     * @param start   开始时间
     * @param end     结束时间
     * @return 出警失败次数
     */
    int countFailed(@Param(GUARD_ID) Long guardId, @Param(START) Date start, @Param(END) Date end);

    /**
     * @param guardId 保安 id
     * @param start   开始时间
     * @param end     截止时间
     * @return
     */
    int countQuestioned(@Param(GUARD_ID) Long guardId, @Param(START) Date start, @Param(END) Date end);

    /**
     * @param guardId 保安 id
     * @param start   开始时间
     * @param end     截止时间
     * @return
     */
    int countQuestionedConfirmed(@Param(GUARD_ID) Long guardId, @Param(START) Date start, @Param(END) Date end);

    /**
     * 根据警情 id 获取出警保安信息
     *
     * @param alertId 警情 id
     * @return 保安信息
     */
    Guard getGuardByAlertId(@Param(ALERT_ID) Long alertId);

    /**
     * 改变保安分组
     *
     * @param guardId 保安 id
     * @param groupId 小组 id
     * @return 行数
     */
    Integer changeGroup(@Param(GUARD_ID) Long guardId, @Param(GROUP_ID) Long groupId);

    /**
     * @param ids     保安 id 列表
     * @param groupId 保安组 id
     * @return 行数
     */
    Integer changeGuardsGroup(@Param(IDS) List<Long> ids, @Param(GROUP_ID) Long groupId);

    /**
     * @param key   关键字
     * @param limit 分页
     * @return 相关保安
     */
    List<Guard> search(@Param(KEY) String key, @Param(LIMIT) SqlLimit limit);

    /**
     * @param alertIds 警情 id
     * @return 可疑人员询问确认数据
     */
    List<Map<String, Object>> listQuestionedAlerts(@Param(IDS) List<Long> alertIds);

    List<Guard> listAllGuards(@Param(ORDER_BY) List<String> orderBy, SqlLimit limit);
}
