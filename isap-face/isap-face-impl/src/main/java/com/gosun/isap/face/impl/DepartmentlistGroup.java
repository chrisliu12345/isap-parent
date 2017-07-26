package com.gosun.isap.face.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.gosun.isap.authority.api.UserUtil;
import com.gosun.isap.common.ResponseResult;
import com.gosun.isap.dao.DataSourceContextHolder;
import com.gosun.isap.dao.mapper.TDeviceMapper;
import com.gosun.isap.dao.mapper.face.TFaceServerMapper;
import com.gosun.isap.dao.mapper.face.customer.BlacklistgroupsMapperCustomer;
import com.gosun.isap.dao.mapper.face.customer.CamerasMapperCustomer;
import com.gosun.isap.dao.mapper.face.customer.FaceTDepartmentMapperCustomer;
import com.gosun.isap.dao.mapper.face.customer.HostsMapperCustomer;
import com.gosun.isap.dao.mapper.face.customer.TFaceServerMapperCustomer;
import com.gosun.isap.dao.po.TDepartment;
import com.gosun.isap.dao.po.TDevice;
import com.gosun.isap.dao.po.TDeviceExample;
import com.gosun.isap.dao.po.TUser;
import com.gosun.isap.dao.po.face.Blacklistgroups;
import com.gosun.isap.dao.po.face.Cameras;
import com.gosun.isap.dao.po.face.Hosts;
import com.gosun.isap.dao.po.face.TFaceServer;
import com.gosun.isap.dao.po.face.TFaceServerExample;
import com.gosun.isap.face.api.IDepartmentlistGroupApi;
import com.gosun.isap.face.api.Bean.CameraDeviceBean;
import com.gosun.isap.face.api.Bean.DepartmentListGroupBean;
import com.gosun.isap.face.api.Bean.DeviceFaceServerListBean;
import com.gosun.isap.face.api.Config.ConfigConstants;
import com.gosun.isap.face.api.service.HostBlacklistGroupsService;
import com.gosun.isap.operlog.api.OperateType;
import com.gosun.isap.operlog.api.ServiceType;
import com.gosun.isap.operlog.api.annotation.SysOperateLog;

/**
 * 角色名单组管理<br>
 * <p>
 * 创建时间：2017/05/04
 * </p>
 *
 * @author 王栋梁
 * @version 1.0
 */

public class DepartmentlistGroup implements IDepartmentlistGroupApi {
	private static Logger logger = LoggerFactory.getLogger(DepartmentlistGroup.class);
	@Autowired
	private BlacklistgroupsMapperCustomer blacklistgroupsMapperCustomer;
	@Autowired
	private HostsMapperCustomer hostsMapperCustomer;
	@Autowired
	private FaceTDepartmentMapperCustomer departmentMapperCustomer;
	@Autowired
	private TDeviceMapper deviceMapper;
	@Autowired
	private CamerasMapperCustomer camerasMapperCustomer;
	@Autowired
	private TFaceServerMapperCustomer faceServerMapperCustomer;
	@Autowired
	private TFaceServerMapper faceServerMapper;
	@Autowired
	private HostBlacklistGroupsService hostBlacklistGroupsService;

	/**
	 * 增加部门名单组
	 * 
	 * @param departmentListGroupBean
	 *            角色名单组信息
	 * @return 响应数据
	 */
	@SysOperateLog(serviceType = ServiceType.LIST_GROUP_ADMINISTRATION, operateType = OperateType.CONFIG_ADD, description = "组织与名单组关联")
	public ResponseResult addDepartmentListGroup(DepartmentListGroupBean departmentListGroupBean) {
		// TODO 操作日志操作未完成
		// 响应数据的初始化
		ResponseResult response = ResponseResult.ok();
		logger.debug("addDepartmentListGroup Start");
		// 取得人脸服务器IP
		TUser user = UserUtil.getCurrentUser();
		List<TFaceServer> faceServerList = new ArrayList<TFaceServer>();
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
		// 数据库数据同步
		if (!ConfigConstants.ADMINISTRATORSID.equals(user.getId())) {
			faceServerList = faceServerMapperCustomer.selectByAdministratorID();
		}
		for (int faceServerListIndex = 0; faceServerListIndex < faceServerList.size(); faceServerListIndex++) {
			// 切换数据源
			DataSourceContextHolder.setDataSourceType(faceServerList.get(faceServerListIndex));
			int re = hostBlacklistGroupsService.saveDepartmentListGroup(departmentListGroupBean.getDepartmentIDList(),
					departmentListGroupBean.getListGroupIDList(), faceServerList);
			if (re != 0) {
				// 数据插入失败
				response.setErrorEx(ConfigConstants.ADD_DEPARTMENT_LISTGROUP_ERR, null);
				break;
			}
		}
		logger.debug("addDepartmentListGroup End");
		return response;
	}

	/**
	 * 删除部门名单组
	 * 
	 * @param departmentListGroupBean
	 *            部门名单组信息
	 * @return 响应数据
	 */
	@SysOperateLog(serviceType = ServiceType.LIST_GROUP_ADMINISTRATION, operateType = OperateType.CONFIG_DEL, description = "组织与名单组关联解除")
	public ResponseResult delDepartmentListGroup(DepartmentListGroupBean departmentListGroupBean) {
		// TODO 操作日志操作未完成
		// 响应数据的初始化
		ResponseResult response = ResponseResult.ok();
		logger.debug("delDepartmentListGroup Start");
		// 取得人脸服务器IP
		TUser user = UserUtil.getCurrentUser();
		List<TFaceServer> faceServerList = new ArrayList<TFaceServer>();
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
		// 数据库数据同步
		if (!ConfigConstants.ADMINISTRATORSID.equals(user.getId())) {
			faceServerList = faceServerMapperCustomer.selectByAdministratorID();
		}
		for (int faceServerListIndex = 0; faceServerListIndex < faceServerList.size(); faceServerListIndex++) {
			// 切换数据源
			DataSourceContextHolder.setDataSourceType(faceServerList.get(faceServerListIndex));
			int re = hostBlacklistGroupsService.delectDepartmentListGroup(departmentListGroupBean.getDepartmentIDList(),
					departmentListGroupBean.getListGroupIDList(), faceServerList);
			if (re != 0) {
				// 社区与名单组关联解除失败
				response.setErrorEx(ConfigConstants.DEL_DEPARTMENT_LISTGROUP_ERR, null);
			}
		}
		logger.debug("delDepartmentListGroup End");
		return response;
	}

	/**
	 * 根据名单组得到部门信息
	 * 
	 * @param listGroupID
	 *            名单组ID
	 * @return 响应数据
	 */
	public ResponseResult getDepartment(Integer listGroupID) {
		// TODO 操作日志与异常未完成
		logger.debug("getDepartment Start");
		ResponseResult response = ResponseResult.ok();
		List<Hosts> departmentInfoList = new ArrayList<Hosts>();
		List<Hosts> addDepartmentInfoList = new ArrayList<Hosts>();
		TUser user = UserUtil.getCurrentUser();
		List<TFaceServer> faceServerList = new ArrayList<TFaceServer>();
		if (ConfigConstants.ADMINISTRATORSID.equals(user.getId())) {
			faceServerList = faceServerMapperCustomer.selectByAdministratorID();
		} else {
			faceServerList = faceServerMapperCustomer.selectByUserID(user.getId());
		}

		for (int index = 0; index < faceServerList.size(); index++) {
			// 切换数据库
			DataSourceContextHolder.setDataSourceType(faceServerList.get(index));
			addDepartmentInfoList = hostsMapperCustomer.selectRoleByListGroupID(listGroupID);
			departmentInfoList.addAll(addDepartmentInfoList);
		}
		response.setBody(departmentInfoList);
		logger.debug("getDepartment End");
		return response;
	}

	/**
	 * 根据部门ID得到名单组信息
	 * 
	 * @param departmentID
	 *            部门ID
	 * @return 响应数据
	 */
	public ResponseResult getListGroup(String departmentID) {
		// TODO 操作日志与异常未完成
		ResponseResult response = ResponseResult.ok();
		logger.debug("getListGroup Start");
		List<Blacklistgroups> blacklistgroupsList = new ArrayList<Blacklistgroups>();
		TFaceServer faceServer = faceServerMapperCustomer.selectByDepartmentID(departmentID);
		// 切换数据库
		DataSourceContextHolder.setDataSourceType(faceServer);

		blacklistgroupsList = blacklistgroupsMapperCustomer.selectListGroupByDepartmentID(departmentID);
		response.setBody(blacklistgroupsList);
		logger.debug("getListGroup End");
		return response;
	}

	/**
	 * 得到社区列表
	 * 
	 * @return 响应数据
	 */
	public ResponseResult getDepartmentList() {
		// TODO 操作日志与异常未完成
		ResponseResult response = ResponseResult.ok();
		logger.debug("getDepartmentList Start");
		List<TDepartment> departmentList = new ArrayList<TDepartment>();

		TUser user = UserUtil.getCurrentUser();
		if (ConfigConstants.ADMINISTRATORSID.equals(user.getId())) {
			departmentList = departmentMapperCustomer.selectByAdministratorID(ConfigConstants.CAPFACE_CAMERAS_TYPE);
		} else {
			departmentList = departmentMapperCustomer.selectByUserID(ConfigConstants.CAPFACE_CAMERAS_TYPE,
					user.getId());
		}

		response.setBody(departmentList);
		logger.debug("getDepartmentList End");
		return response;
	}

	/**
	 * 得到设备列表
	 * 
	 * @param departmentID
	 *            部门ID
	 * @return 响应数据
	 */
	public ResponseResult getDeviceList(String departmentID) {
		// TODO 操作日志与异常未完成
		ResponseResult response = ResponseResult.ok();
		logger.debug("getDeviceList Start");
		List<TDevice> deviceList = new ArrayList<TDevice>();
		TDeviceExample deviceExample = new TDeviceExample();
		TDeviceExample.Criteria criteria = deviceExample.createCriteria();
		criteria.andDepartmentIdEqualTo(departmentID);
		criteria.andDescriptionEqualTo(ConfigConstants.CAPFACE_CAMERAS_TYPE.toString());

		deviceList = deviceMapper.selectByExample(deviceExample);
		response.setBody(deviceList);
		logger.debug("getDeviceList End");
		return response;
	}

	/**
	 * 得到设备与人脸服务器列表
	 * 
	 * @param departmentID
	 *            部门ID
	 * @return 响应数据
	 */
	public ResponseResult getDeviceFaceServerList(String departmentID) {
		// TODO 操作日志与异常未完成
		ResponseResult response = ResponseResult.ok();
		logger.debug("getDeviceFaceServerList Start");
		List<Cameras> camerasList = new ArrayList<Cameras>();
		List<TDevice> deviceList = new ArrayList<TDevice>();
		List<TFaceServer> faceServerList = new ArrayList<TFaceServer>();
		CameraDeviceBean cameraDeviceBean = new CameraDeviceBean();
		List<CameraDeviceBean> cameraDeviceBeanList = new ArrayList<CameraDeviceBean>();
		DeviceFaceServerListBean deviceFaceServerListBean = new DeviceFaceServerListBean();
		TDeviceExample deviceExample = new TDeviceExample();
		TDeviceExample.Criteria deviceExampleCriteria = deviceExample.createCriteria();

		List<String> deviceIDList = new ArrayList<>();
		TFaceServer faceServer = faceServerMapperCustomer.selectByDepartmentID(departmentID);
		if (faceServer != null) {
			// 切换数据库
			DataSourceContextHolder.setDataSourceType(faceServer);
			// 取得人脸服务器已有相机信息
			camerasList = camerasMapperCustomer.selectbyDepartmentID(departmentID);

			for (int i = 0; i < camerasList.size(); i++) {
				cameraDeviceBean = new CameraDeviceBean();
				deviceIDList.add(camerasList.get(i).getRemark());

				cameraDeviceBean.setId(camerasList.get(i).getRemark());
				cameraDeviceBean.setName(camerasList.get(i).getName());
				cameraDeviceBean.setFaceCode(camerasList.get(i).getCode());
				cameraDeviceBean.setDescription(camerasList.get(i).getAddress());
				cameraDeviceBean.setLocationX(camerasList.get(i).getX().toString());
				cameraDeviceBean.setLocationY(camerasList.get(i).getY().toString());
				cameraDeviceBean.setFaceFalg(ConfigConstants.FACEFALG_1);
				cameraDeviceBeanList.add(cameraDeviceBean);
			}
		}

		deviceExampleCriteria.andDepartmentIdEqualTo(departmentID);
		deviceExampleCriteria.andDescriptionEqualTo(ConfigConstants.CAPFACE_CAMERAS_TYPE.toString());
		if (deviceIDList != null && !deviceIDList.isEmpty()) {
			deviceExampleCriteria.andIdNotIn(deviceIDList);
		}
		DataSourceContextHolder.clear();
		// 取得社区下的卡口相机
		deviceList = deviceMapper.selectByExample(deviceExample);

		for (int i = 0; i < deviceList.size(); i++) {
			cameraDeviceBean = new CameraDeviceBean();
			cameraDeviceBean.setId(deviceList.get(i).getId());
			cameraDeviceBean.setName(deviceList.get(i).getName());
			cameraDeviceBean.setFaceCode(deviceList.get(i).getCode());
			cameraDeviceBean.setDescription(deviceList.get(i).getDescription());
			cameraDeviceBean.setLocationX(deviceList.get(i).getLocationX());
			cameraDeviceBean.setLocationY(deviceList.get(i).getLocationY());
			cameraDeviceBean.setFaceFalg(ConfigConstants.FACEFALG_0);
			cameraDeviceBeanList.add(cameraDeviceBean);
		}

		TFaceServerExample example = new TFaceServerExample();
		faceServerList = faceServerMapper.selectByExample(example);
		faceServer = faceServerMapperCustomer.selectByDepartmentID(departmentID);

		if (faceServer != null) {
			deviceFaceServerListBean.setBindingID(faceServer.getId());
		}
		deviceFaceServerListBean.setFaceServerList(faceServerList);
		deviceFaceServerListBean.setDeviceList(cameraDeviceBeanList);
		response.setBody(deviceFaceServerListBean);
		logger.debug("getDeviceFaceServerList End");
		return response;
	}
}
