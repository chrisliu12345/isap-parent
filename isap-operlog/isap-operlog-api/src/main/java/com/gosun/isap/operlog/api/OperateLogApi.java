package com.gosun.isap.operlog.api;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.gosun.isap.common.ResponseResult;

@SuppressWarnings("rawtypes")
public interface OperateLogApi {

	/** 增加一条操作日志 */
	public ResponseResult addLog(OperateLog operLog);

	/** 获取操作日志信息 */
	public ResponseResult getOperateLog(int start, int limit, String operName, int operType, int serviceType,
			String startTime, String endTime, String ipAddress, String operResult, String description);
	/** 导出操作日志 */
	@Path("exportlogs")
	@GET
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public Response exportLog();
}
