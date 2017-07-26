package com.gosun.isap.warn.api.alert.service;

import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 警情数量统计服务
 * <p>创建时间：2017/4/18 19:42</p>
 *
 * @author 娄存银
 * @version 1.0
 */
public interface AlertsCountService {
    /**
     * 根据部门统计未处理的警情数，不遍历部门下的子部门
     *
     * @param departmentIds 部门 id 列表
     * @return 未处理的警情数
     */
    int unprocessed(List<String> departmentIds);

    /**
     * 某个时间点到现在，用户确认的警情数
     *
     * @param start  开始时间
     * @param userId 用户 id
     * @return 警情数
     */
    int confirmedInResponseTime(Date start, long userId);

    /**
     * 某个时间段内用户确认的警情数
     *
     * @param start  开始时间
     * @param end    截止时间
     * @param userId 用户 id
     * @return 警情数
     */
    int falseAlertsInResponseTime(Date start, Date end, long userId);

    /**
     * 该班次该小区的出警次数
     *
     * @param departmentId 社区 id
     * @return 出警次数
     */
    int currentCountOfProcessed(String departmentId);

}
