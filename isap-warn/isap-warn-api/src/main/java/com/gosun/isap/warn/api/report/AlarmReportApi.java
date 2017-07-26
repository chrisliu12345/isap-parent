package com.gosun.isap.warn.api.report;

import java.util.List;

import com.gosun.isap.common.ResponseResult;

/**
 * 告警上报API
 * 
 * @author lucf
 *
 */
public interface AlarmReportApi {

	/**
	 * 获取实时告警信息
	 * 
	 * @return 实时告警结果
	 */
	ResponseResult<List<AlarmRealInfo>> getRealTimeAlarmInfo(String sort);

	/**
	 * 获取历史告警信息
	 * 
	 * @param start
	 *            起始偏移量
	 * @param limit
	 *            分页数目
	 * @param alarmType
	 *            告警类型
	 * @param alarmLevel
	 *            告警级别
	 * @param alarmConfirm
	 *            告警确认状态
	 * @param alarmStartTime
	 *            起始时间
	 * @param alarmEndTime
	 *            结束时间
	 * @param sort
	 *            排序参数
	 * @return 历史告警结果
	 */
	ResponseResult<List<AlarmHistoryInfo>> getHistoryAlarmInfo(Integer start, Integer limit, Integer alarmType, Integer alarmLevel,
			Integer alarmConfirm, String alarmStartTime, String alarmEndTime, String sort, String fuzzyField, String fuzzyValue);

	/**
	 * 确认告警信息
	 * 
	 * @param ackInfo
	 *            告警确认信息
	 * @return 确认成功或失败的结果
	 */
	ResponseResult confirmAlarmInfo(AlarmAckInfo ackInfo);
}
