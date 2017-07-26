package com.gosun.isap.warn.impl.report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.text.SimpleDateFormat;
import java.text.ParseException;

import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.gosun.isap.common.ResponseResult;
import com.gosun.isap.common.error.ErrorCode;
import com.gosun.isap.warn.api.report.AlarmAckInfo;
import com.gosun.isap.warn.api.report.AlarmConfirmType;
import com.gosun.isap.warn.api.report.AlarmHistoryInfo;
import com.gosun.isap.warn.api.report.AlarmRealInfo;
import com.gosun.isap.warn.api.report.AlarmReportApi;
import com.gosun.isap.warn.api.report.SortStringAnalysis;
import com.gosun.isap.warn.api.service.alarm.TAlarmRecordService;
import com.gosun.isap.warn.api.report.AlarmAckInfo.RecordID;
import com.gosun.isap.operlog.api.OperateType;
import com.gosun.isap.operlog.api.ServiceType;
import com.gosun.isap.operlog.api.annotation.SysOperateLog;
import com.gosun.isap.operlog.api.helper.OperateLogWriter;
import com.gosun.isap.resource.api.service.TDeviceService;
import com.gosun.isap.dao.po.TAlarmRecord;
import com.gosun.isap.dao.po.TAlarmRecordExample;
import com.gosun.isap.dao.po.TDevice;
import com.gosun.isap.dao.po.alarm.AlarmRecordExampleExtend;
import com.gosun.isap.dao.po.alarm.AlarmRecordExtend;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 告警信息API实现类
 * 
 * @author lucf
 *
 */
@Path("alarm/report")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(ContentType.APPLICATION_JSON_UTF_8)
public class AlarmReportApiImpl implements AlarmReportApi {

	private static Logger logger = LoggerFactory.getLogger(AlarmReportApiImpl.class);

	@Autowired
	private TDeviceService deviceService;

	@Autowired
	private TAlarmRecordService alarmRecordService;

	@Override
	@GET
	@Path("realTimeInfos")
	public ResponseResult<List<AlarmRealInfo>> getRealTimeAlarmInfo(@QueryParam("sort") String sort) {

		ResponseResult<List<AlarmRealInfo>> response = ResponseResult.ok();

		// 实时告警信息查询无需构造查询条件
		TAlarmRecordExample example = new TAlarmRecordExample();

		// 实时告警信息查询默认返回最新的50条告警
		example.setOffset(0);
		example.setLimit(50);
		
		if (StringUtils.isEmpty(sort) == false) {
			Map<String, String> sortMap = new HashMap<String, String>();
			sortMap.put("deviceID", "t_alarm_record.dev_id");
			sortMap.put("alarmType", "t_alarm_record.alarm_type");
			sortMap.put("alarmFlag", "t_alarm_record.flag");
			sortMap.put("alarmLevel", "t_alarm_record.alarm_level");
			sortMap.put("alarmTime", "t_alarm_record.alarm_time");
			sortMap.put("alarmConfirm", "t_alarm_record.confirm");

			SortStringAnalysis analysis = new SortStringAnalysis(sortMap);
			String orderByClause = analysis.translate(sort);

			example.setOrderByClause(orderByClause);
		}

		List<TAlarmRecord> recordList = alarmRecordService.getAlarmInfo(example);

		List<AlarmRealInfo> data = new ArrayList<AlarmRealInfo>();

		for (TAlarmRecord e : recordList) {
			long recordID = e.getId().longValue();
			String deviceID = e.getDevId();
			int alarmType = e.getAlarmType().intValue();
			int alarmFlag = e.getFlag().intValue();
			int alarmLevel = e.getAlarmLevel().intValue();
			int alarmConfirm = e.getConfirm().intValue();

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String alarmTime = sdf.format(e.getAlarmTime());

			TDevice deviceInfo = deviceService.findDeviceById(e.getDevId());

			AlarmRealInfo element = new AlarmRealInfo(recordID, deviceID, deviceInfo.getName(), alarmType, alarmFlag,
					alarmLevel, alarmTime, alarmConfirm);

			data.add(element);
		}

		int num = data.size();

		response.setBody(data);
		response.setTotal(num);

		return response;
	}

	@Override
	@GET
	@Path("historyInfos")
	public ResponseResult<List<AlarmHistoryInfo>> getHistoryAlarmInfo(@QueryParam("start") Integer start,
			@QueryParam("limit") Integer limit, @QueryParam("alarmType") Integer alarmType,
			@QueryParam("alarmLevel") Integer alarmLevel, @QueryParam("alarmConfirm") Integer alarmConfirm,
			@QueryParam("alarmStartTime") String alarmStartTime, @QueryParam("alarmEndTime") String alarmEndTime,
			@QueryParam("sort") String sort, @QueryParam("fuzzyField") String fuzzyField, @QueryParam("fuzzyValue") String fuzzyValue) {

		ResponseResult<List<AlarmHistoryInfo>> response = ResponseResult.ok();

		AlarmRecordExampleExtend example = new AlarmRecordExampleExtend();

		example.setOffset(start);
		example.setLimit(limit);

		if (null != alarmType) {
			example.setAlarmType(alarmType);
		}

		if (null != alarmLevel) {
			example.setAlarmLevel(alarmLevel);
		}

		if (null != alarmConfirm) {
            example.setAlarmConfirm(alarmConfirm);
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		if (StringUtils.isEmpty(alarmStartTime) == false) {
			Date startDate;
			try {
				startDate = sdf.parse(alarmStartTime);
				example.setAlarmStartTime(startDate);
			} catch (ParseException ex) {
				logger.error("Invalid start time format.");
				response.setError(ErrorCode.ERR_ILLEGAL_ARGUMENT, "Invalid start time format.");
				return response;
			}
		}

		if (StringUtils.isEmpty(alarmEndTime) == false) {
			Date endDate;
			try {
				endDate = sdf.parse(alarmEndTime);
				example.setAlarmEndTime(endDate);
			} catch (ParseException ex) {
				logger.error("Invalid end time format.");
				response.setError(ErrorCode.ERR_ILLEGAL_ARGUMENT, "Invalid end time format.");
				return response;
			}
		}

		if (StringUtils.isEmpty(sort) == false) {
			Map<String, String> sortMap = new HashMap<String, String>();
			sortMap.put("deviceID", "t_alarm_record.dev_id");
			sortMap.put("alarmType", "t_alarm_record.alarm_type");
			sortMap.put("alarmFlag", "t_alarm_record.flag");
			sortMap.put("alarmLevel", "t_alarm_record.alarm_level");
			sortMap.put("alarmTime", "t_alarm_record.alarm_time");
			sortMap.put("alarmConfirm", "t_alarm_record.confirm");

			SortStringAnalysis analysis = new SortStringAnalysis(sortMap);
			String orderByClause = analysis.translate(sort);

			example.setOrderByClause(orderByClause);
		}
		
		if (StringUtils.isEmpty(fuzzyField) == false && StringUtils.isEmpty(fuzzyValue) == false)
		{
			if (fuzzyField.equals("deviceName"))
			{
				example.setFuzzyField("t_device.name");
				example.setFuzzyValue(fuzzyValue);
			}
			else
			{
				logger.error("Invalid fuzzyField:" + fuzzyField);
				response.setError(ErrorCode.ERR_ILLEGAL_ARGUMENT, "Invalid fuzzyField:" + fuzzyField);
				return response;
			}
		}

		// 获取符合条件的告警记录总数
		int totalNum = alarmRecordService.getAlarmRecordCountExtend(example);

		// 获取符合条件的告警记录
		List<AlarmRecordExtend> recordList = alarmRecordService.getAlarmInfoExtend(example);

		List<AlarmHistoryInfo> data = new ArrayList<AlarmHistoryInfo>();

		for (AlarmRecordExtend e : recordList) {
			long recordID = e.getId().longValue();
			String deviceID = e.getDevId();
			int type = e.getAlarmType().intValue();
			int flag = e.getFlag().intValue();
			int level = e.getAlarmLevel().intValue();
			int confirm = e.getConfirm().intValue();
			String checkUser = e.getCheckUser();
			String suggestion = e.getSuggestion();
			String name = e.getName();

			String time = sdf.format(e.getAlarmTime());

			String checkTime = null;
			if (null != e.getCheckTime()) {
				checkTime = sdf.format(e.getCheckTime());
			}

			AlarmHistoryInfo element = new AlarmHistoryInfo(recordID, deviceID, name, type, flag, level,
					time, confirm, checkUser, checkTime, suggestion);

			data.add(element);
		}

		response.setBody(data);
		response.setTotal(totalNum);

		return response;
	}

	@Override
	@POST
	@Path("acknowledgements")
	@SysOperateLog(serviceType = ServiceType.ALARM_CONFIRM, operateType = OperateType.CONFIG_MOD, description = "确认告警")
	public ResponseResult confirmAlarmInfo(AlarmAckInfo ackInfo) {

		ResponseResult response = ResponseResult.ok();

		List<RecordID> recordList = ackInfo.getRecordObj();

		for (RecordID e : recordList) {
			TAlarmRecord record = alarmRecordService.getAlarmInfoByKey(e.getRecordID());
			if (record == null) {
				logger.error("Fail to find record.");
				response.setError(ErrorCode.ERR_RECORD_NOT_EXIST, "Fail to find record.");
				return response;
			}

			record.setCheckUser(ackInfo.getCheckUser());
			record.setSuggestion(ackInfo.getSuggestion());
			record.setConfirm(new Byte(AlarmConfirmType.Checked.getValue()));

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				Date checkTime = sdf.parse(ackInfo.getCheckTime());
				record.setCheckTime(checkTime);
			} catch (ParseException ex) {
				logger.error("Invalid check time format.");
				response.setError(ErrorCode.ERR_ILLEGAL_ARGUMENT, "Invalid check time format.");
				return response;
			}

			if (0 != alarmRecordService.updateAlarmInfo(record)) {
				logger.error("Fail to update alarm info with confirm info.");
				response.setError(ErrorCode.ERR_DB_OPERATE_FAIL, "Fail to update alarm info with confirm info.");
				return response;
			}
		}

		return response;
	}
}
