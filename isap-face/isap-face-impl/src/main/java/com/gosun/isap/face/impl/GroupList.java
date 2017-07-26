package com.gosun.isap.face.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.gosun.isap.authority.api.UserUtil;
import com.gosun.isap.common.ResponseResult;
import com.gosun.isap.dao.DataSourceContextHolder;
import com.gosun.isap.dao.mapper.face.customer.TFaceServerMapperCustomer;
import com.gosun.isap.dao.po.TUser;
import com.gosun.isap.dao.po.face.TFaceServer;
import com.gosun.isap.face.api.IGroupListApi;
import com.gosun.isap.face.api.Bean.GroupListBean;
import com.gosun.isap.face.api.Config.ConfigConstants;
import com.gosun.isap.face.api.service.GroupBlacklistsService;
import com.gosun.isap.operlog.api.OperateType;
import com.gosun.isap.operlog.api.ServiceType;
import com.gosun.isap.operlog.api.annotation.SysOperateLog;

/**
 * 建立名单与名单组的关系<br>
 * <p>
 * 创建时间：2017/05/03
 * </p>
 *
 * @author 王栋梁
 * @version 1.0
 */

public class GroupList implements IGroupListApi {
	private static Logger logger = LoggerFactory.getLogger(GroupList.class);
	@Autowired
	private GroupBlacklistsService groupBlacklistsService;
	@Autowired
	private TFaceServerMapperCustomer faceServerMapperCustomer;

	/**
	 * <p>
	 * 将人员名单添加到名单组中
	 * </p>
	 * 
	 * @param groupListBean
	 *            人员与名单组ID
	 * @return 响应数据
	 */
	@SysOperateLog(serviceType = ServiceType.LIST_GROUP_ADMINISTRATION, operateType = OperateType.CONFIG_ADD, description = "名单添加到名单组")
	public ResponseResult addGroupList(GroupListBean groupListBean) {
		// TODO 操作日志操作未完成
		// 响应数据的初始化
		ResponseResult response = ResponseResult.ok();
		logger.debug("addGroupList Strat");
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
		for (int index = 0; index < faceServerList.size(); index++) {
			// 切换数据库
			DataSourceContextHolder.setDataSourceType(faceServerList.get(index));
			int re = groupBlacklistsService.saveGroupList(groupListBean.getListGroupIDList(),
					groupListBean.getListIDList(), faceServerList);
			if (re == -1) {
				// 数据不存在，名单增加失败
				response.setErrorEx(ConfigConstants.ADD_LISTGROUP_ERR, null);
				return response;
			}
		}
		logger.debug("addGroupList End");
		return response;
	}

	/**
	 * <p>
	 * 将人员名单从名单组中删除
	 * </p>
	 * 
	 * @param groupListBean
	 *            人员与名单组ID
	 * @return 响应数据
	 */
	@SysOperateLog(serviceType = ServiceType.LIST_GROUP_ADMINISTRATION, operateType = OperateType.CONFIG_ADD, description = "名单从名单组中删除")
	public ResponseResult delGroupList(GroupListBean groupListBean) {
		// TODO 操作日志操作未完成
		// 响应数据的初始化
		ResponseResult response = ResponseResult.ok();
		logger.debug("delGroupList Strat");
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
		for (int index = 0; index < faceServerList.size(); index++) {
			// 切换数据库
			DataSourceContextHolder.setDataSourceType(faceServerList.get(index));
			groupBlacklistsService.delectGroupList(groupListBean.getListGroupIDList(), groupListBean.getListIDList(),
					faceServerList);
		}
		logger.debug("delGroupList End");
		return response;
	}
}
