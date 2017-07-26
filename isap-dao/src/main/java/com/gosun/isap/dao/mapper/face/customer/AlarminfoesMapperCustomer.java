package com.gosun.isap.dao.mapper.face.customer;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gosun.isap.dao.po.face.AlarmInfoParaBean;
import com.gosun.isap.dao.po.face.AlarmManInfoBean;
import com.gosun.isap.dao.po.face.Alarminfoes;

public interface AlarminfoesMapperCustomer {
	List<Alarminfoes> selectBylistID(AlarmInfoParaBean alarmInfoParaBean);

	int countBylistID(AlarmInfoParaBean alarmInfoParaBean);
	
	List<AlarmManInfoBean> selectAlarmStatistics(AlarmInfoParaBean alarmInfoParaBean);

	int countAlarmStatistics(AlarmInfoParaBean alarmInfoParaBean);

	int countAlarm(AlarmInfoParaBean alarmInfoParaBean);

	List<Alarminfoes> selectByDepartmentID(@Param("lastime") Date lastime,
			@Param("nowtime") Date nowtime, @Param("creator") String creator);
	
	List<Alarminfoes> selectBycreator(@Param("start") Integer start,
			@Param("limit") Integer limit, @Param("creator") String creator);
	
}
