package com.gosun.isap.dao.mapper.alarm;

import java.util.List;

import com.gosun.isap.dao.po.alarm.AlarmRecordExampleExtend;
import com.gosun.isap.dao.po.alarm.AlarmRecordExtend;

public interface AlarmRecordExtendMapper {
	int countByExample(AlarmRecordExampleExtend example);
	
	List<AlarmRecordExtend> selectByExample(AlarmRecordExampleExtend example);
}