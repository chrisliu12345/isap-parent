package com.gosun.isap.operlog.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import com.gosun.isap.dao.mapper.TOperlogMapper;
import com.gosun.isap.dao.po.TOperlog;
import com.gosun.isap.operlog.api.IOperateLogService;
import com.gosun.isap.operlog.api.OperateLog;

public class OperateLogServiceImpl implements IOperateLogService {
	// @Autowired
	// private TUserMapper userMapper;
	@Autowired
	TOperlogMapper logMapper;

	@Override
	public boolean addLog(OperateLog operLog) {
		// TODO Auto-generated method stub

		TOperlog log = new TOperlog();
		log.setOperName(operLog.getOperator());
		log.setIpAddress(operLog.getIpAddress());
		Date date;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			date = (Date) sdf.parse(operLog.getOperTime());
			log.setOperTime(date);
		} catch (ParseException ex) {
			// TODO error process
		}
		log.setOperType((byte) operLog.getOperType());
		log.setServiceType((byte) operLog.getServiceType());
		log.setDescription(operLog.getDescription());
		log.setOperResult(operLog.isSuccess());
		log.setFailureCause(operLog.getFailureCause());
		int row = logMapper.insertSelective(log);
		return row > 0;
	}

}
