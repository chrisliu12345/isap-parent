package com.gosun.isap.operlog.impl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.gosun.isap.common.ResponseResult;
import com.gosun.isap.dao.mapper.TOperlogMapper;
import com.gosun.isap.dao.po.TOperlog;
import com.gosun.isap.dao.po.TOperlogExample;
import com.gosun.isap.operlog.api.IOperateLogService;
import com.gosun.isap.operlog.api.OperateLog;
import com.gosun.isap.operlog.api.OperateLogApi;

import cn.jiguang.common.utils.StringUtils;

@SuppressWarnings({ "rawtypes", "unchecked" })
@Path("operatelog")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(ContentType.APPLICATION_JSON_UTF_8)
public class OperateLogApiImpl implements OperateLogApi {
	@Autowired
	private TOperlogMapper operLogMapper;

	@Autowired
	private IOperateLogService logger;

	@Override
	@GET
	@Path("logs")
	public ResponseResult getOperateLog(@QueryParam("start") int start, @QueryParam("limit") int limit,
			@QueryParam("operator") String operator, @QueryParam("operType") int operType,
			@QueryParam("serviceType") int serviceType, @QueryParam("startTime") String startTime,
			@QueryParam("endTime") String endTime, @QueryParam("ipAddress") String ipAddress,
			@QueryParam("success") String success, @QueryParam("description") String description) {

		ResponseResult response = ResponseResult.ok();

		TOperlogExample example = new TOperlogExample();

		example.setOffset(new Integer(start));
		example.setLimit(new Integer(limit));
		example.setOrderByClause("-oper_time");
		
		String tmp = "";

		TOperlogExample.Criteria criteria = example.createCriteria();

		if (!StringUtils.isEmpty(operator)) {
			tmp = "%";
			tmp += operator;
			tmp += "%";
			criteria.andOperNameLike(tmp);
		}

		if (0 != operType) {
			criteria.andOperTypeEqualTo((byte) operType);
		}

		if (0 != serviceType) {
			criteria.andServiceTypeEqualTo((byte) serviceType);
		}

		if ((!StringUtils.isEmpty(startTime)) && (!StringUtils.isEmpty(endTime))) {
			Date startDate, endDate;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			try {
				startDate = sdf.parse(startTime);
				endDate = sdf.parse(endTime);
				criteria.andOperTimeGreaterThanOrEqualTo(startDate);
				criteria.andOperTimeLessThanOrEqualTo(endDate);
			} catch (ParseException ex) {
				// TODO error process
			}
		}

		if (!StringUtils.isEmpty(ipAddress)) {
			tmp = "%";
			tmp += ipAddress;
			tmp += "%";
			criteria.andIpAddressLike(tmp);
		}

		if (!StringUtils.isEmpty(success)) {
			criteria.andOperResultEqualTo(Boolean.parseBoolean(success));
		}

		if (!StringUtils.isEmpty(description)) {
			tmp = "%";
			tmp += description;
			tmp += "%";
			criteria.andDescriptionLike(tmp);
		}

		// 查询符合条件的总记录数
		int totalNum = operLogMapper.countByExample(example);

		// 查询符合条件的记录
		List<TOperlog> operLoglist = operLogMapper.selectByExample(example);

		// 新建返回body的对象
		List<OperateLog> data = new ArrayList<OperateLog>();

		for (TOperlog eOperLog : operLoglist) {
			OperateLog element = new OperateLog();

			element.setDescription(eOperLog.getDescription());
			element.setFailureCause(eOperLog.getFailureCause());
			element.setIpAddress(eOperLog.getIpAddress());
			element.setOperator(eOperLog.getOperName());
			element.setOperType(eOperLog.getOperType());
			element.setServiceType(eOperLog.getServiceType());
			element.setSuccess(eOperLog.getOperResult());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time = sdf.format(eOperLog.getOperTime());
			element.setOperTime(time);

			data.add(element);
		}

		response.setBody(data);
		response.setTotal(totalNum);

		return response;
	}

	@Override
	@POST
	@Path("logs")
	public ResponseResult addLog(OperateLog operLog) {
		logger.addLog(operLog);
		return ResponseResult.ok();
	}
	
	@Path("exportlogs")
	@GET
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public Response  exportLog() {
		String fileName = "/var/lib/mysql-files/operlog.xls";
		String shName="/opt/export_operlog.sh";
		String command = "sh " + shName + " " + fileName;
		try{
			Process ps = Runtime.getRuntime().exec(command);
			ps.waitFor();
		}
		catch (Exception e) {
			return null;
		}
		
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ZipOutputStream zos = new ZipOutputStream(bos);
			byte[] buffer = new byte[2048];
			File file = new File(fileName);
		
			FileInputStream fileInputStream = new FileInputStream(file);
			zos.putNextEntry(new ZipEntry(file.getName()));
			int len = 0;
			while ((len = fileInputStream.read(buffer)) > 0){
				zos.write(buffer, 0, len);
			}
			zos.closeEntry();
			fileInputStream.close();
			
			zos.close();
			return Response.ok(bos.toByteArray(),"application/zip")
					.header("Content-Disposition", "attachment; filename=operlog.zip")
				.build();
		
		}catch (IOException e){
			return null;
		}
		
	}

}
