package com.gosun.isap.warn.impl.service.alarm;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.gosun.isap.dao.mapper.TAlarmRecordMapper;
import com.gosun.isap.dao.mapper.TAlarmTypeMapper;
import com.gosun.isap.dao.mapper.alarm.AlarmRecordExtendMapper;
import com.gosun.isap.dao.po.TAlarmRecord;
import com.gosun.isap.dao.po.TAlarmRecordExample;
import com.gosun.isap.dao.po.TAlarmType;
import com.gosun.isap.dao.po.alarm.AlarmRecordExampleExtend;
import com.gosun.isap.dao.po.alarm.AlarmRecordExtend;
import com.gosun.isap.warn.api.service.alarm.TAlarmRecordService;

/**
 * 告警记录数据库服务实现
 * 
 * @author lucf
 *
 */
@Service
public class TAlarmRecordServiceImpl implements TAlarmRecordService {

	@Autowired
	private TAlarmRecordMapper alarmRecordMapper;

	@Autowired
	private AlarmRecordExtendMapper alarmRecordExtendMapper;

	@Autowired
	private TAlarmTypeMapper alarmTypeMapper;

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public int addAlarmRecord(TAlarmRecord record) {
		if (1 == alarmRecordMapper.insert(record)) {
			return 0;
		} else {
			return -1;
		}
	}

	@Override
	public int getAlarmRecordCount(TAlarmRecordExample example) {
		return alarmRecordMapper.countByExample(example);
	}

	@Override
	public int getAlarmRecordCountExtend(AlarmRecordExampleExtend example) {
		return alarmRecordExtendMapper.countByExample(example);
	}

	@Override
	public List<TAlarmRecord> getAlarmInfo(TAlarmRecordExample example) {
		return alarmRecordMapper.selectByExample(example);
	}

	@Override
	public List<AlarmRecordExtend> getAlarmInfoExtend(AlarmRecordExampleExtend example) {
		return alarmRecordExtendMapper.selectByExample(example);
	}

	@Override
	public TAlarmRecord getAlarmInfoByKey(Long id) {
		return alarmRecordMapper.selectByPrimaryKey(id);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public int updateAlarmInfo(TAlarmRecord record) {
		if (1 == alarmRecordMapper.updateByPrimaryKey(record)) {
			return 0;
		} else {
			return -1;
		}
	}

	@Override
	public TAlarmType getAlarmLevel(Long alarmType) {
		return alarmTypeMapper.selectByPrimaryKey(alarmType);
	}
}
