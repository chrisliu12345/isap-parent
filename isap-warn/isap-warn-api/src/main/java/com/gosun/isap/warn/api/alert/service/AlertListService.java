package com.gosun.isap.warn.api.alert.service;

import com.gosun.isap.dao.mapper.alert.Interval;
import com.gosun.isap.dao.mapper.alert.SqlLimit;
import com.gosun.isap.dao.po.alert.Alert;

import java.util.Date;
import java.util.List;

/**
 * 获取警情列表服务
 * <p>创建时间：2017/4/24 9:27</p>
 *
 * @author 娄存银
 * @version 1.0
 */
public interface AlertListService {
    /**
     * 获取部门下全部警情列表，可以根据 status 筛选
     *
     * @param userId       用户 id
     * @param departmentId 部门 id
     * @param status       状态
     * @param startTime    开始时间
     * @param endTime      结束时间
     * @param orderBy      排序
     * @param limit        limit 条件
     * @return 警情列表
     */
    List<Alert> listAlerts(Long userId, String departmentId, Byte status, Date startTime, Date endTime, List<String> orderBy, SqlLimit limit);

    /**
     * 统计部门下全部警情数量，可以根据 status 筛选
     *
     * @param userId       用户 id
     * @param departmentId 部门 id
     * @param status       状态
     * @param start        开始时间
     * @param end          结束时间
     * @return 数量
     */
    int countAlerts(Long userId, String departmentId, Byte status, Date start, Date end);

    /**
     * 获取部门下全部警情列表，可以根据 status 筛选
     *
     * @param userId         用户 id
     * @param departmentId   部门 id
     * @param statusInterval 状态
     * @param startTime      开始时间
     * @param endTime        结束时间
     * @param orderBy        排序
     * @param limit          limit 条件
     * @return 警情列表
     */
    List<Alert> listAlerts(Long userId, String departmentId, Interval<Byte> statusInterval, Date startTime, Date endTime, List<String> orderBy, SqlLimit limit);

    /**
     * 统计部门下全部警情数量，可以根据 status 筛选
     *
     * @param userId         用户 id
     * @param departmentId   部门 id
     * @param statusInterval 状态
     * @param start          开始时间
     * @param end            结束时间
     * @return 数量
     */
    int countAlerts(Long userId, String departmentId, Interval<Byte> statusInterval, Date start, Date end);


    /**
     * 获取部门列表下的所有警情列表
     *
     * @param departmentIds 部门 id 列表
     * @param status        状态
     * @param startTime     开始时间
     * @param endTime       截止时间
     * @param orderBy       排序
     * @param limit         分页
     * @return 警情列表
     */
    List<Alert> listAlerts(List<String> departmentIds, Byte status, Date startTime, Date endTime, List<String> orderBy, SqlLimit limit);


    /**
     * @param departmentIds 部门 id 列表
     * @param status        状态
     * @param startTime     开始时间
     * @param endTime       截止时间
     * @return 警情数
     */
    int countAlerts(List<String> departmentIds, Byte status, Date startTime, Date endTime);

    /**
     * 获取部门列表下的所有警情列表
     *
     * @param departmentIds 部门 id 列表
     * @param statusInterval        状态
     * @param startTime     开始时间
     * @param endTime       截止时间
     * @param orderBy       排序
     * @param limit         分页
     * @return 警情列表
     */
    List<Alert> listAlerts(List<String> departmentIds,  Interval<Byte> statusInterval, Date startTime, Date endTime, List<String> orderBy, SqlLimit limit);


    /**
     * @param departmentIds 部门 id 列表
     * @param statusInterval        状态
     * @param startTime     开始时间
     * @param endTime       截止时间
     * @return 警情数
     */
    int countAlerts(List<String> departmentIds,  Interval<Byte> statusInterval, Date startTime, Date endTime);

    /**
     * 获取未处理的警情
     *
     * @param userId 用户 id
     * @param limit  分页
     * @return 未处理的警情列表
     */
    List<Alert> listUnprocessedAlerts(long userId, SqlLimit limit);

    int countUnprocessedAlerts(long userId);

    /**
     * 获取等待保安回电的警情，需要处理回电超时警情
     *
     * @param userId 用户 id
     * @param limit  分页
     * @return 警情列表
     */
    List<Alert> listWaitingCallbackAlerts(long userId, SqlLimit limit);

    /**
     * @param userId 用户 id
     * @return 警情数量
     */
    int countWaitingCallbackAlerts(long userId);

    /**
     * 获取等待保安再次回电的警情，需要处理超时警情
     *
     * @param userId 用户 id
     * @param limit  分页
     * @return 警情列表
     */
    List<Alert> listWaitingCallbackAgainAlerts(long userId, SqlLimit limit);

    /**
     * @param userId 用户 id
     * @return 警情数量
     */
    int countWaitingCallbackAgainAlerts(Long userId);

    /**
     * 获取处理完成的警情
     *
     * @param userId  用户 id
     * @param limit   分页选项
     * @param start   开始时间
     * @param end     截止时间
     * @param orderBy 排序
     * @return 警情列表
     */
    List<Alert> listFinishedAlerts(long userId, Date start, Date end, List<String> orderBy, SqlLimit limit);

    /**
     * @param userId 用户 id
     * @param start  开始时间
     * @param end    截止时间
     * @return 处理完成的警情数量
     */
    int countFinishedAlerts(long userId, Date start, Date end);
}
