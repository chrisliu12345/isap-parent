package com.gosun.isap.face.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.gosun.isap.authority.api.UserUtil;
import com.gosun.isap.common.ResponseResult;
import com.gosun.isap.dao.DataSourceContextHolder;
import com.gosun.isap.dao.mapper.face.ContactsMapper;
import com.gosun.isap.dao.mapper.face.customer.AlarminfoesMapperCustomer;
import com.gosun.isap.dao.mapper.face.customer.BlacklistsMapperCustomer;
import com.gosun.isap.dao.mapper.face.customer.TFaceServerMapperCustomer;
import com.gosun.isap.dao.mapper.face.customer.WhitelistsMapperCustomer;
import com.gosun.isap.dao.po.TUser;
import com.gosun.isap.dao.po.face.AlarmInfoParaBean;
import com.gosun.isap.dao.po.face.AlarmManInfoBean;
import com.gosun.isap.dao.po.face.Alarminfoes;
import com.gosun.isap.dao.po.face.BlacklistsExample;
import com.gosun.isap.dao.po.face.Contacts;
import com.gosun.isap.dao.po.face.Lists;
import com.gosun.isap.dao.po.face.TFaceServer;
import com.gosun.isap.dao.po.face.WhitelistsExample;
import com.gosun.isap.face.api.IStatisticsApi;
import com.gosun.isap.face.api.Bean.AlarmValue;
import com.gosun.isap.face.api.Config.ConfigConstants;
import com.gosun.isap.face.api.service.ContactsMapperService;
import com.gosun.isap.operlog.api.OperateType;
import com.gosun.isap.operlog.api.ServiceType;
import com.gosun.isap.operlog.api.annotation.SysOperateLog;

/**
 * 数据统计<br>
 * <p>
 * 创建时间：2017/05/04
 * </p>
 *
 * @author 王栋梁
 * @version 1.0
 */

public class Statistics implements IStatisticsApi {
	private static Logger logger = LoggerFactory.getLogger(Statistics.class);
	@Autowired
	private AlarminfoesMapperCustomer alarminfoesMapperCustomer;
	@Autowired
	private BlacklistsMapperCustomer blacklistsMapperCustome;
	@Autowired
	private WhitelistsMapperCustomer whitelistsMapperCustome;
	@Autowired
	private TFaceServerMapperCustomer faceServerMapperCustomer;
	@Autowired
	private ContactsMapperService contactsMapperService;
	@Autowired
	private ContactsMapper contactsMapper;

	/**
	 * <p>
	 * 名单数据统计
	 * </p>
	 * 
	 * @param listType
	 *            名单类型
	 * @param statisticsType
	 *            统计类型
	 * @param startTime
	 *            开始时间（可空）
	 * @param endTime
	 *            结束时间（可空）
	 * @param start
	 *            页索引
	 * @param limit
	 *            一页总数
	 * @return 响应数据
	 */

	public ResponseResult listStatistics(Integer listType, Integer statisticsType, String startTime, String endTime,
			Integer start, Integer limit) {
		ResponseResult response = ResponseResult.ok();
		logger.debug("listStatistics Start");
		SimpleDateFormat sdf = new SimpleDateFormat(ConfigConstants.TIME_FORMAT.trim());
		int totalRow = 0;
		// 取得人脸服务器
		TUser user = UserUtil.getCurrentUser();
		java.util.List<TFaceServer> faceServerList = new ArrayList<TFaceServer>();
		if (ConfigConstants.ADMINISTRATORSID.equals(user.getId())) {
			faceServerList = faceServerMapperCustomer.selectByAdministratorID();
		} else {
			faceServerList = faceServerMapperCustomer.selectByUserID(user.getId());
		}
		if (faceServerList == null || faceServerList.isEmpty()) {
			// 社区没有绑定人脸服务器
			response.setErrorEx(ConfigConstants.FACE_SERVER_DEPARTMENT_NOT_EXISTS, null);
			return response;
		}
		// 切换数据库
		DataSourceContextHolder.setDataSourceType(faceServerList.get(0));

		List<Lists> listsList = new ArrayList<Lists>();
		if (ConfigConstants.WHITELIST.equals(listType)) {
			WhitelistsExample whitelistsExample = new WhitelistsExample();
			WhitelistsExample.Criteria criteriaCriteria = whitelistsExample.createCriteria();

			if (!ConfigConstants.ADMINISTRATORSID.equals(user.getId())) {
				criteriaCriteria.andCreatorEqualTo(user.getId().toString());
			}
			whitelistsExample.setOffset(start);
			whitelistsExample.setLimit(limit);

			if (ConfigConstants.STATISTICS_TOTAL.equals(statisticsType)) {
				totalRow = whitelistsMapperCustome.countByExample(whitelistsExample);
				listsList = whitelistsMapperCustome.selectByExample(whitelistsExample);
			} else if (ConfigConstants.STATISTICS_ADD.equals(statisticsType)) {
				try {
					// 设置时间条件
					if ((startTime != null && !startTime.isEmpty()) && (endTime != null && !endTime.isEmpty())) {
						criteriaCriteria.andCreatetimeBetween(sdf.parse(startTime), sdf.parse(endTime));
					} else if ((startTime == null || startTime.isEmpty()) && (endTime != null && !endTime.isEmpty())) {
						criteriaCriteria.andCreatetimeLessThanOrEqualTo(sdf.parse(endTime));
					} else if ((startTime != null && !startTime.isEmpty()) && (endTime == null || endTime.isEmpty())) {
						criteriaCriteria.andCreatetimeGreaterThanOrEqualTo(sdf.parse(startTime));
					}
				} catch (java.text.ParseException e) {
					// 时间类型转换异常
					logger.error(e.getMessage());
					response.setErrorEx(ConfigConstants.TIME_TYPE_CONVERSION_ERR, null);
					return response;
				}
				totalRow = whitelistsMapperCustome.countByExample(whitelistsExample);
				listsList = whitelistsMapperCustome.selectByExample(whitelistsExample);
			} else {
				/*
				 * totalRow =
				 * whitelistsMapper.countByExample(whitelistsExample);
				 * whitelists = whitelistsMapper.selectByExampleWithRowbounds(
				 * whitelistsExample, rowBounds);
				 */
			}
			response.setTotal(totalRow);
			response.addExtend("listType", ConfigConstants.WHITELIST);
			response.setBody(listsList);
		} else {
			BlacklistsExample blacklistsExample = new BlacklistsExample();
			BlacklistsExample.Criteria blacklistsCriteria = blacklistsExample.createCriteria();
			if (!ConfigConstants.ADMINISTRATORSID.equals(user.getId())) {
				blacklistsCriteria.andCreatorEqualTo(user.getId().toString());
			}
			blacklistsExample.setOffset(start);
			blacklistsExample.setLimit(limit);

			if (ConfigConstants.STATISTICS_TOTAL.equals(statisticsType)) {
				totalRow = blacklistsMapperCustome.countByExample(blacklistsExample);
				listsList = blacklistsMapperCustome.selectByExample(blacklistsExample);
			} else {
				try {
					// 设置时间条件
					if ((startTime != null && !startTime.isEmpty()) && (endTime != null && !endTime.isEmpty())) {
						blacklistsCriteria.andCreatetimeBetween(sdf.parse(startTime), sdf.parse(endTime));
					} else if ((startTime == null || startTime.isEmpty()) && (endTime != null && !endTime.isEmpty())) {
						blacklistsCriteria.andCreatetimeLessThanOrEqualTo(sdf.parse(endTime));
					} else if ((startTime != null && !startTime.isEmpty()) && (endTime == null || endTime.isEmpty())) {
						blacklistsCriteria.andCreatetimeGreaterThanOrEqualTo(sdf.parse(startTime));
					}
				} catch (java.text.ParseException e) {
					// 时间类型转换异常
					logger.error(e.getMessage());
					response.setErrorEx(ConfigConstants.TIME_TYPE_CONVERSION_ERR, null);
					return response;
				}
				totalRow = blacklistsMapperCustome.countByExample(blacklistsExample);
				listsList = blacklistsMapperCustome.selectByExample(blacklistsExample);
			}
			response.setTotal(totalRow);
			response.addExtend("listType", ConfigConstants.BLACKLIST);
			response.setBody(listsList);
		}
		logger.debug("listStatistics End");
		return response;
	}

	/**
	 * <p>
	 * 告警数据统计
	 * </p>
	 * 
	 * @param alarmInfoParaBean
	 *            告警统计条件
	 * @return 响应数据
	 */

	public ResponseResult alarmStatistics(AlarmInfoParaBean alarmInfoParaBean) {
		ResponseResult response = ResponseResult.ok();
		List<AlarmManInfoBean> alarmManInfoBeanList = new ArrayList<AlarmManInfoBean>();

		TFaceServer faceServer = faceServerMapperCustomer.selectByDepartmentID(alarmInfoParaBean.getDepartmentID());
		// 切换数据库
		DataSourceContextHolder.setDataSourceType(faceServer);
		int total = 0;
		int alarmTotal = 0;
		// 时间如果为空（“”），就设置成null
		if (alarmInfoParaBean.getEndTime() == null || alarmInfoParaBean.getEndTime().isEmpty()) {
			alarmInfoParaBean.setEndTime(null);
		}
		if (alarmInfoParaBean.getStartTime() == null || alarmInfoParaBean.getStartTime().isEmpty()) {
			alarmInfoParaBean.setStartTime(null);
		}
		if (alarmInfoParaBean.getCameraID() == null || alarmInfoParaBean.getCameraID().isEmpty()) {
			alarmInfoParaBean.setCameraID(null);
		}

		alarmTotal = alarminfoesMapperCustomer.countAlarm(alarmInfoParaBean);
		total = alarminfoesMapperCustomer.countAlarmStatistics(alarmInfoParaBean);
		alarmManInfoBeanList = alarminfoesMapperCustomer.selectAlarmStatistics(alarmInfoParaBean);
		response.setTotal(total);
		response.addExtend("alarmTotal", alarmTotal);
		response.setBody(alarmManInfoBeanList);
		return response;
	}

	/**
	 * 根据名单ID得到告警具体信息
	 * 
	 * @param alarmInfoParaBean
	 *            条件信息
	 * @return 响应数据
	 */
	public ResponseResult getAlarmInfo(AlarmInfoParaBean alarmInfoParaBean) {
		ResponseResult response = ResponseResult.ok();
		int total = 0;
		List<Alarminfoes> alarminfoesList = new ArrayList<Alarminfoes>();

		TFaceServer faceServer = faceServerMapperCustomer.selectByDepartmentID(alarmInfoParaBean.getDepartmentID());
		// 切换数据库
		DataSourceContextHolder.setDataSourceType(faceServer);

		// 时间如果为空（“”），就设置成null
		if (alarmInfoParaBean.getEndTime() == null || alarmInfoParaBean.getEndTime().isEmpty()) {
			alarmInfoParaBean.setEndTime(null);
		}
		if (alarmInfoParaBean.getStartTime() == null || alarmInfoParaBean.getStartTime().isEmpty()) {
			alarmInfoParaBean.setStartTime(null);
		}
		if (alarmInfoParaBean.getCameraID() == null || alarmInfoParaBean.getCameraID().isEmpty()) {
			alarmInfoParaBean.setCameraID(null);
		}

		total = alarminfoesMapperCustomer.countBylistID(alarmInfoParaBean);
		alarminfoesList = alarminfoesMapperCustomer.selectBylistID(alarmInfoParaBean);

		response.setTotal(total);
		response.setBody(alarminfoesList);
		response.addExtend("departmentID", alarmInfoParaBean.getDepartmentID());
		return response;
	}

	/**
	 * <p>
	 * 编辑阈值
	 * </p>
	 * 
	 * @param alarmValue
	 *            阈值（告警相似度）
	 * @return 响应数据
	 */
	@SysOperateLog(serviceType=ServiceType.FACE_SERVER,operateType=OperateType.CONFIG_MOD,description="阈值更新")
	public ResponseResult updAlarmValue(AlarmValue alarmValue) {
		ResponseResult response = ResponseResult.ok();

		TUser user = UserUtil.getCurrentUser();
		java.util.List<TFaceServer> faceServerList = new ArrayList<TFaceServer>();
		if (ConfigConstants.ADMINISTRATORSID.equals(user.getId())) {
			faceServerList = faceServerMapperCustomer.selectByAdministratorID();
		} else {
			faceServerList = faceServerMapperCustomer.selectByUserID(user.getId());
		}
		if (faceServerList == null || faceServerList.isEmpty()) {
			// 社区没有绑定人脸服务器
			response.setErrorEx(ConfigConstants.FACE_SERVER_DEPARTMENT_NOT_EXISTS, null);
			return response;
		}
		Pattern pattern = Pattern.compile(ConfigConstants.REGEX_SIMILARITY);
		Matcher matcher = pattern.matcher(alarmValue.getValue());
		if (!matcher.matches()) {
			// 相似度不正确
			response.setErrorEx(ConfigConstants.SIMILARITY_ERR, null);
			return response;
		}

		for (int index = 0; index < faceServerList.size(); index++) {
			// 切换数据库
			DataSourceContextHolder.setDataSourceType(faceServerList.get(index));
			contactsMapperService.updateAlarmValue(faceServerList, alarmValue.getValue(), user.getId());
		}
		return response;
	}

	/**
	 * <p>
	 * 取得阈值
	 * </p>
	 * 
	 * @return 响应数据
	 */
	public ResponseResult getAlarmValue() {
		ResponseResult response = ResponseResult.ok();
		String[] params = new String[2]; // 错误信息

		TUser user = UserUtil.getCurrentUser();
		java.util.List<TFaceServer> faceServerList = new ArrayList<TFaceServer>();
		if (ConfigConstants.ADMINISTRATORSID.equals(user.getId())) {
			faceServerList = faceServerMapperCustomer.selectByAdministratorID();
		} else {
			faceServerList = faceServerMapperCustomer.selectByUserID(user.getId());
		}
		if (faceServerList == null || faceServerList.isEmpty()) {
			// 社区没有绑定人脸服务器
			response.setErrorEx(ConfigConstants.FACE_SERVER_DEPARTMENT_NOT_EXISTS, null);
			return response;
		}
		// 切换数据库
		DataSourceContextHolder.setDataSourceType(faceServerList.get(0));
		// 取得数据库数据
		Contacts contacts = contactsMapper.selectByPrimaryKey(ConfigConstants.ADMINISTRATORSID.intValue());
		AlarmValue alarmValue = new AlarmValue();
		alarmValue.setValue(contacts.getAlarmvalue().toString());
		response.setBody(alarmValue);

		return response;
	}
}
