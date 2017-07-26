package com.gosun.isap.operlog.api.helper;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gosun.isap.authority.api.UserUtil;
import com.gosun.isap.dao.po.TUser;
import com.gosun.isap.operlog.api.IOperateLogService;
import com.gosun.isap.operlog.api.OperateLog;

@Component
public class OperateLogWriter {
	private static IOperateLogService operLogger;

	@Autowired(required = true)
	public void setOperLogger(IOperateLogService operLogger) {
		OperateLogWriter.operLogger = operLogger;
	}

	public static void success(OperateLog log) {
		success(log.getServiceType(), log.getOperType(), log.getDescription());
	}

	public static void fail(OperateLog log) {
		fail(log.getServiceType(), log.getOperType(), log.getDescription(), log.getFailureCause());
	}

	public static void success(int srvType, int operType, String operDesc) {
		writeLog(srvType, operType, operDesc, true, null);
	}

	public static void fail(int srvType, int operType, String operDesc, String failureCause) {
		writeLog(srvType, operType, operDesc, false, failureCause);
	}

	private static void writeLog(int srvType, int operType, String operDesc, boolean success, String failureCause) {
		TUser currUser = UserUtil.getCurrentUser();
		if (null != currUser) {
			OperateLog operLog = new OperateLog();
			operLog.setOperator(currUser.getName());
			operLog.setIpAddress(UserUtil.getCurrentUserIp());
			operLog.setOperType(operType);
			operLog.setServiceType(srvType);
			operLog.setDescription(operDesc);
			operLog.setSuccess(success);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time = sdf.format(new Date());
			operLog.setOperTime(time);
			if (!success && null != failureCause) {
				operLog.setFailureCause(failureCause);
			}
			operLogger.addLog(operLog);
		}
	}
}
