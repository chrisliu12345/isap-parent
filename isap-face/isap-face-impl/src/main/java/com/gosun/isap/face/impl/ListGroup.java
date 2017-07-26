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
import com.gosun.isap.dao.mapper.face.customer.BlacklistgroupsMapperCustomer;
import com.gosun.isap.dao.mapper.face.customer.TFaceServerMapperCustomer;
import com.gosun.isap.dao.po.TUser;
import com.gosun.isap.dao.po.face.Blacklistgroups;
import com.gosun.isap.dao.po.face.TFaceServer;
import com.gosun.isap.face.api.IListGroupApi;
import com.gosun.isap.face.api.Bean.ListGroupBean;
import com.gosun.isap.face.api.Config.ConfigConstants;
import com.gosun.isap.face.api.service.BlacklistgroupsService;
import com.gosun.isap.operlog.api.OperateType;
import com.gosun.isap.operlog.api.ServiceType;
import com.gosun.isap.operlog.api.annotation.SysOperateLog;

/**
 * 名单组管理<br>
 * <p>
 * 对名单组进行增删改查的操作
 * </p>
 * <p>
 * 创建时间：2017/05/03
 * </p>
 *
 * @author 王栋梁
 * @version 1.0
 */

public class ListGroup implements IListGroupApi {
	private static Logger logger = LoggerFactory.getLogger(CapFace.class);
	@Autowired
	private BlacklistgroupsMapperCustomer blacklistgroupsMapperCustomer;
	@Autowired
	private TFaceServerMapperCustomer faceServerMapperCustomer;
	@Autowired
	private BlacklistgroupsService blacklistgroupsService;

	/**
	 * 增加名单组
	 * 
	 * @param listGroupBean
	 *            名单组信息
	 * @return 响应数据
	 */
	@SysOperateLog(serviceType=ServiceType.LIST_GROUP_ADMINISTRATION,operateType=OperateType.CONFIG_ADD,description="名单组增加")
	public ResponseResult addListGroup(ListGroupBean listGroupBean) {
		// TODO 操作日志操作未完成
		// 响应数据的初始化
		ResponseResult response = ResponseResult.ok();
		logger.debug("addListGroup Start");
		// 取得人脸服务器IP
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
		// 数据库数据同步
		if (!ConfigConstants.ADMINISTRATORSID.equals(user.getId())) {
			faceServerList = faceServerMapperCustomer.selectByAdministratorID();
		}
		// 切换数据库
		DataSourceContextHolder.setDataSourceType(faceServerList.get(0));
		// 数据认证
		if (listGroupBean.getListGroupName().replace(ConfigConstants.SPACE, "").isEmpty()
				|| listGroupBean.getRemark().replace(ConfigConstants.SPACE, "").isEmpty()) {
			// 名单组名字与备注不能为空
			response.setErrorEx(ConfigConstants.EMPTY_DATA, null);
			return response;
		}
		// 数据设置
		Blacklistgroups blacklistgroups = new Blacklistgroups();
		blacklistgroups.setContactid(ConfigConstants.ADMINISTRATORSID.intValue());
		blacklistgroups.setName(listGroupBean.getListGroupName());
		blacklistgroups.setRemark(listGroupBean.getRemark());
		blacklistgroups.setState(listGroupBean.getState());
		blacklistgroups.setCreator(user.getId().toString());
		blacklistgroups.setCreatetime(new Date());
		blacklistgroups.setState(ConfigConstants.STATE_1);
		blacklistgroups.setUsedflag(ConfigConstants.USEDFLAG_1);
		for (int index = 0; index < faceServerList.size(); index++) {
			// 切换数据库
			DataSourceContextHolder.setDataSourceType(faceServerList.get(index));
			int re = blacklistgroupsService.saveListGroup(faceServerList, blacklistgroups);
			if (re == 1) {
				// 数据存在
				response.setErrorEx(ConfigConstants.ADD_DATA_EXISTS, null);
				break;
			}
		}

		logger.debug("addListGroup End");
		return response;
	}

	/**
	 * 删除名单组
	 * 
	 * @param listGroupBean
	 *            名单组信息
	 * @return 响应数据
	 */
	@SysOperateLog(serviceType=ServiceType.LIST_GROUP_ADMINISTRATION,operateType=OperateType.CONFIG_DEL,description="名单组删除")
	public ResponseResult delListGroup(ListGroupBean listGroupBean) {
		// TODO 操作日志操作未完成
		// 响应数据的初始化
		ResponseResult response = ResponseResult.ok();
		logger.debug("delListGroup Start");
		// 取得人脸服务器IP
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
		// 数据库数据同步
		if (!ConfigConstants.ADMINISTRATORSID.equals(user.getId())) {
			faceServerList = faceServerMapperCustomer.selectByAdministratorID();
		}
		for (int index = 0; index < faceServerList.size(); index++) {
			// 切换数据库
			DataSourceContextHolder.setDataSourceType(faceServerList.get(index));
			int re = blacklistgroupsService.deleteListGroup(faceServerList, listGroupBean.getListGroupID());
			switch (re) {
			case 1:
				// 名单存在名单组中
				response.setErrorEx(ConfigConstants.LIST_EXISTS_LISTGROUP, null);
				break;
			case 2:
				// 删除数据不存在
				response.setErrorEx(ConfigConstants.DEL_DATA_NOT_EXISTS, null);
				break;
			default:
				// 成功
				break;
			}
			if (re != 0) {
				break;
			}
		}
		logger.debug("delListGroup End");
		return response;
	}

	/**
	 * 更新名单组
	 * 
	 * @param listGroupBean
	 *            名单组信息
	 * @return 响应数据
	 */
	@SysOperateLog(serviceType=ServiceType.LIST_GROUP_ADMINISTRATION,operateType=OperateType.CONFIG_DEL,description="名单组更新")
	public ResponseResult updListGroup(ListGroupBean listGroupBean) {
		// TODO 操作日志操作未完成
		// 响应数据的初始化
		ResponseResult response = ResponseResult.ok();
		logger.debug("updListGroup Start");
		// 取得人脸服务器IP
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
		// 数据库数据同步
		if (!ConfigConstants.ADMINISTRATORSID.equals(user.getId())) {
			faceServerList = faceServerMapperCustomer.selectByAdministratorID();
		}
		Blacklistgroups blacklistgroups = new Blacklistgroups();

		// 更新数据
		blacklistgroups.setId(listGroupBean.getListGroupID());
		blacklistgroups.setName(listGroupBean.getListGroupName());
		blacklistgroups.setRemark(listGroupBean.getRemark());
		blacklistgroups.setState(listGroupBean.getState());
		blacklistgroups.setCreator(user.getId().toString());
		blacklistgroups.setLastedittime(new Date());

		for (int index = 0; index < faceServerList.size(); index++) {
			// 切换数据库
			DataSourceContextHolder.setDataSourceType(faceServerList.get(index));

			int re = blacklistgroupsService.updateListGroup(faceServerList, blacklistgroups);

			switch (re) {
			case 1:
				// 数据存在
				response.setErrorEx(ConfigConstants.UPD_DATA_EXISTS, null);
				break;
			case 2:
				// 更新数据不存在
				response.setErrorEx(ConfigConstants.UPD_DATA_NOT_EXISTS, null);
				break;
			default:
				// 成功
				break;
			}
			if (re != 0) {
				break;
			}
		}

		logger.debug("updListGroup End");
		return response;
	}

	/**
	 * 得到名单组
	 * 
	 * @param start
	 *            页索引
	 * @param limit
	 *            一页总数
	 * @return 响应数据
	 */
	public ResponseResult getListGroup(Integer start, Integer limit) {
		// TODO 操作日志与异常未完成
		ResponseResult response = ResponseResult.ok();
		logger.debug("getListGroup Start");
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

		List<Blacklistgroups> blacklistgroupsList = new ArrayList<Blacklistgroups>();
		int totalRow = 0;

		String creator = null;
		if (!ConfigConstants.ADMINISTRATORSID.equals(user.getId())) {
			creator = user.getId().toString();
		}
		totalRow = blacklistgroupsMapperCustomer.countToGetListGroup(creator);
		blacklistgroupsList = blacklistgroupsMapperCustomer.selectToGetListGroup(start, limit, creator);
		response.setBody(blacklistgroupsList);
		response.setTotal(totalRow);
		logger.debug("getListGroup End");
		return response;
	}

	/**
	 * 根据人员ID得到所属名单组
	 * 
	 * @param listID
	 *            人员ID
	 * @return 响应数据
	 */
	public ResponseResult getListGroup(Integer listID) {
		ResponseResult response = ResponseResult.ok();
		logger.debug("getListGroupBylistID Strat");
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
		// 取得数据
		List<Blacklistgroups> blacklistgroupsList = new ArrayList<Blacklistgroups>();
		blacklistgroupsList = blacklistgroupsMapperCustomer.selectlistGroupBylistID(listID);
		response.setBody(blacklistgroupsList);
		logger.debug("getListGroupBylistID End");
		return response;
	}
}
