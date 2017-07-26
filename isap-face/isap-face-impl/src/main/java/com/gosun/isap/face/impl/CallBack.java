package com.gosun.isap.face.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.gosun.isap.authority.api.UserUtil;
import com.gosun.isap.common.ResponseResult;
import com.gosun.isap.dao.DataSourceContextHolder;
import com.gosun.isap.dao.mapper.face.customer.AlarminfoesMapperCustomer;
import com.gosun.isap.dao.mapper.face.customer.CapfacesMapperCustomer;
import com.gosun.isap.dao.mapper.face.customer.TFaceServerMapperCustomer;
import com.gosun.isap.dao.po.TUser;
import com.gosun.isap.dao.po.face.Alarminfoes;
import com.gosun.isap.dao.po.face.Capfaces;
import com.gosun.isap.dao.po.face.TFaceServer;
import com.gosun.isap.face.api.ICallBack;
import com.gosun.isap.face.api.Config.ConfigConstants;

/**
 * 抓拍人脸相关的反馈操作<br>
 * <p>
 * 名单对比的误报
 * </p>
 * <p>
 * 根据检索条件查询抓拍人脸
 * </p>
 * <p>
 * 创建时间：2017/05/03
 * </p>
 *
 * @author 王栋梁
 * @version 1.0
 */

public class CallBack implements ICallBack {
	private static Logger logger = LoggerFactory.getLogger(CallBack.class);
	// 前一次进入时间
	private static Long lastFaceRunTime = new Long(0);
	private static Long lastAlarmRunTime = new Long(0);
	@Autowired
	private CapfacesMapperCustomer capfacesMapperCustomer;
	@Autowired
	private AlarminfoesMapperCustomer alarminfoesMapperCustomer;
	@Autowired
	private TFaceServerMapperCustomer faceServerMapperCustomer;

	/**
	 * <p>
	 * 抓拍人脸上传
	 * </p>
	 * 
	 * @param deviceID
	 *            设备ID
	 * @return 响应数据
	 */
	public ResponseResult faceAdd(String deviceID) {
		ResponseResult response = ResponseResult.ok();
		logger.debug("faceAdd Start");
		TFaceServer faceServer = faceServerMapperCustomer.selectByDeviceID(deviceID);
		if (faceServer == null) {
			response.setErrorEx(ConfigConstants.FACE_SERVER_DEPARTMENT_NOT_EXISTS, null);
			return response;
		}
		if (lastFaceRunTime == 0) {
			// 第一次进来
			lastFaceRunTime = new Date().getTime();
			return response;
		}
		// 切换数据库
		logger.debug("DataSource Switch");
		DataSourceContextHolder.setDataSourceType(faceServer);
		// 查询时间设置
		Date nowtime = new Date();
		Date lastime = new java.util.Date(lastFaceRunTime);

		logger.debug("selectByDepartmentID Start");
		/*List<Capfaces> capfacesList = capfacesMapperCustomer.selectByCameraID(deviceID, lastime, nowtime);*/
		List<Capfaces> capfacesList = capfacesMapperCustomer.selectByCameraIDTo10(deviceID);
		logger.debug("selectByDepartmentID End");

		response.setBody(capfacesList);
		lastFaceRunTime = nowtime.getTime();
		logger.debug("faceAdd End");
		return response;
	}

	/**
	 * <p>
	 * 告警上报
	 * </p>
	 * @param start
	 *            页索引
	 * @param limit
	 *            一页总数
	 * @return 响应数据
	 */
	public ResponseResult alarmAdd(Integer start,Integer limit) {
		ResponseResult response = ResponseResult.ok();
		logger.debug("alarmAdd Start");
		// 人脸系统信息取得
		TUser user = UserUtil.getCurrentUser();
		java.util.List<TFaceServer> faceServerList = new ArrayList<TFaceServer>();
		if (ConfigConstants.ADMINISTRATORSID.equals(user.getId())) {
			faceServerList = faceServerMapperCustomer.selectByAdministratorID();
		} else {
			faceServerList = faceServerMapperCustomer.selectByUserID(user.getId());
		}
		List<Alarminfoes> alarminfoes = new ArrayList<Alarminfoes>();
		Date nowtime = new Date();

		for (int index = 0; index < faceServerList.size(); index++) {
			// 切换数据库
			DataSourceContextHolder.setDataSourceType(faceServerList.get(index));
			if (lastAlarmRunTime == 0) {
				// 第一次进来
				lastAlarmRunTime = new Date().getTime();
				return response;
			}
			String creator = new String();
			if (!ConfigConstants.ADMINISTRATORSID.equals(user.getId())) {
				creator = user.getId().toString();
			} else {
				creator = null;
			}
			// 查询时间设置
			Date lastime = new java.util.Date(lastAlarmRunTime);
/*			List<Alarminfoes> addAlarminfoes = alarminfoesMapperCustomer.selectByDepartmentID(lastime, nowtime,
					creator);*/
			List<Alarminfoes> addAlarminfoes = alarminfoesMapperCustomer.selectBycreator(start, limit,creator);
			alarminfoes.addAll(addAlarminfoes);
		}
		response.setBody(alarminfoes);
		lastAlarmRunTime = nowtime.getTime();
		logger.debug("alarmAdd End");
		return response;
	}
}
