package com.gosun.isap.warn.api.service.alarm;

import java.util.List;

import com.gosun.isap.dao.po.TAlarmRecord;
import com.gosun.isap.dao.po.TAlarmType;
import com.gosun.isap.dao.po.alarm.AlarmRecordExampleExtend;
import com.gosun.isap.dao.po.alarm.AlarmRecordExtend;
import com.gosun.isap.dao.po.TAlarmRecordExample;

/**
 * 告警记录数据库服务接口
 * 
 * @author lucf
 *
 */
public interface TAlarmRecordService {

	/**
	 * 新增告警记录
	 * 
	 * @param record
	 *            告警记录
	 * @return 执行结果
	 */
	int addAlarmRecord(TAlarmRecord record);

	/**
	 * 获取告警记录数目
	 * 
	 * @param example
	 *            查询条件
	 * @return 执行结果
	 */
	int getAlarmRecordCount(TAlarmRecordExample example);

	/**
	 * 获取告警记录数目（含设备名称）
	 * 
	 * @param example
	 *            查询条件
	 * @return
	 */
	int getAlarmRecordCountExtend(AlarmRecordExampleExtend example);

	/**
	 * 获取告警记录信息
	 * 
	 * @param example
	 *            查询条件
	 * @return 查询结果
	 */
	List<TAlarmRecord> getAlarmInfo(TAlarmRecordExample example);

	/**
	 * 获取感觉记录信息（含设备名称）
	 * 
	 * @param example
	 *            查询条件
	 * @return 查询结果
	 */
	List<AlarmRecordExtend> getAlarmInfoExtend(AlarmRecordExampleExtend example);

	/**
	 * 根据告警记录ID获取告警信息
	 * 
	 * @param id
	 *            告警编号
	 * @return 查询结果
	 */
	TAlarmRecord getAlarmInfoByKey(Long id);

	/**
	 * 更新告警记录
	 * 
	 * @param record
	 *            告警数据
	 * @return 执行结果
	 */
	int updateAlarmInfo(TAlarmRecord record);

	/**
	 * 根据告警类型获取告警级别
	 * 
	 * @param alarmType
	 *            告警类型
	 * @return 查询结果
	 */
	TAlarmType getAlarmLevel(Long alarmType);
}