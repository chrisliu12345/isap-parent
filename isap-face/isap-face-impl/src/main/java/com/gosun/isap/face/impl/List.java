package com.gosun.isap.face.impl;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.gosun.isap.authority.api.UserUtil;
import com.gosun.isap.common.ResponseResult;
import com.gosun.isap.dao.DataSourceContextHolder;
import com.gosun.isap.dao.mapper.face.customer.BlacklistsMapperCustomer;
import com.gosun.isap.dao.mapper.face.customer.TFaceServerMapperCustomer;
import com.gosun.isap.dao.mapper.face.customer.WhitelistsMapperCustomer;
import com.gosun.isap.dao.po.TUser;
import com.gosun.isap.dao.po.face.Blacklists;
import com.gosun.isap.dao.po.face.Lists;
import com.gosun.isap.dao.po.face.TFaceServer;
import com.gosun.isap.dao.po.face.Whitelists;
import com.gosun.isap.face.api.IListApi;
import com.gosun.isap.face.api.Bean.ManInfoBean;
import com.gosun.isap.face.api.Config.ConfigConstants;
import com.gosun.isap.face.api.service.BlackWhiteListService;
import com.gosun.isap.operlog.api.OperateType;
import com.gosun.isap.operlog.api.ServiceType;
import com.gosun.isap.operlog.api.annotation.SysOperateLog;

/**
 * 人员名单管理<br>
 * <p>
 * 对名单进行增删改查的操作
 * </p>
 * <p>
 * 创建时间：2017/05/04
 * </p>
 *
 * @author 王栋梁
 * @version 1.0
 */

public class List implements IListApi {
	private static Logger logger = LoggerFactory.getLogger(GroupList.class);
	@Autowired
	private WhitelistsMapperCustomer whitelistsMapperCustomer;
	@Autowired
	private BlacklistsMapperCustomer blacklistsMapperCustomer;
	@Autowired
	private TFaceServerMapperCustomer faceServerMapperCustomer;
	@Autowired
	private BlackWhiteListService blackWhiteListService;


	/**
	 * 删除人员信息
	 * 
	 * @param manInfoBean
	 *            人员信息
	 * @return 响应数据
	 */
	@SysOperateLog(serviceType=ServiceType.LIST_ADMINISTRATION,operateType=OperateType.CONFIG_DEL,description="名单删除")
	public ResponseResult delList(ManInfoBean manInfoBean) {
		ResponseResult response = ResponseResult.ok();
		logger.debug("delList Strat");
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

			int re = blackWhiteListService.deleteBlackWhiteList(faceServerList, manInfoBean.getListType(),
					manInfoBean.getListID());
			switch (re) {
			case 1:
				// 名单存在名单组中
				response.setErrorEx(ConfigConstants.LIST_EXISTS_LISTGROUP, null);
				break;
			case 2:
				// 数据不存在
				response.setErrorEx(ConfigConstants.DEL_DATA_NOT_EXISTS, null);
				break;
			case 0:
				// 成功
				break;
			default:
				break;
			}
			if (re != 0) {
				break;
			}
		}
		logger.debug("delList End");
		return response;
	}

	/**
	 * 更新人员信息
	 * 
	 * @param manInfoBean
	 *            人员信息
	 * @return 响应数据
	 */
	@SysOperateLog(serviceType=ServiceType.LIST_ADMINISTRATION,operateType=OperateType.CONFIG_MOD,description="名单更新")
	public ResponseResult updList(ManInfoBean manInfoBean) {
		ResponseResult response = ResponseResult.ok();
		logger.debug("updList Strat");
		// TODO 操作日志操作未完成
		// 取得人脸服务器IP
		int re = 0;
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
		Whitelists whitelists = new Whitelists(); // 数据库白名单信息
		Blacklists blacklists = new Blacklists(); // 数据库白名单信息
		// 身份证认证
		boolean bre = idCodeAuthentication(manInfoBean.getIDCard());
		if (bre == false) {
			// 身份证认证出错
			response.setErrorEx(ConfigConstants.UPD_IDCODE_ERR, null);
			return response;
		}
		// 数据认证
		re = stringCodeAuthentication(manInfoBean.getName(), manInfoBean.getReason());
		if (re != 0) {
			// 名单名字与备注不能为空
			response.setErrorEx(ConfigConstants.DATA_ERR, null);
			return response;
		}
		// 名单类型判断
		if (ConfigConstants.BLACKLIST.equals(manInfoBean.getListType())) {

			// 数据设置
			blacklists.setId(manInfoBean.getListID());
			blacklists.setLastedittime(new Date());
			// TODO 最后修改者
			// blacklists.setLasteditor();
			blacklists.setIdcard(manInfoBean.getIDCard());
			blacklists.setImgurl(manInfoBean.getFaceURL());
			blacklists.setName(manInfoBean.getName());
			blacklists.setReason(manInfoBean.getReason());
			// 备注
			// blacklists.setRemark();
			blacklists.setSex(manInfoBean.getSex());
			blacklists.setState(ConfigConstants.STATE_1);
			blacklists.setUsedflag(ConfigConstants.USEDFLAG_0);
			// 将人员信息更新到数据库中
			for (int index = 0; index < faceServerList.size(); index++) {
				// 切换数据库
				DataSourceContextHolder.setDataSourceType(faceServerList.get(index));
				re = blackWhiteListService.updateBlackList(faceServerList, blacklists);
				if (re != 0) {
					break;
				}
			}
		} else if (ConfigConstants.WHITELIST.equals(manInfoBean.getListType())) {

			// 数据设置
			whitelists.setId(manInfoBean.getListID());

			whitelists.setLastedittime(new Date());
			// TODO 最后修改者
			// whitelists.setLasteditor();
			whitelists.setIdcard(manInfoBean.getIDCard());
			whitelists.setImgurl(manInfoBean.getFaceURL());
			whitelists.setName(manInfoBean.getName());
			whitelists.setReason(manInfoBean.getReason());
			// 备注
			// whitelists.setRemark();
			whitelists.setSex(manInfoBean.getSex());
			whitelists.setState(ConfigConstants.STATE_1);
			whitelists.setUsedflag(ConfigConstants.USEDFLAG_0);
			// 将人员信息更新到数据库中
			for (int index = 0; index < faceServerList.size(); index++) {
				// 切换数据库
				DataSourceContextHolder.setDataSourceType(faceServerList.get(index));
				re = blackWhiteListService.updateWhiteList(faceServerList, whitelists);
				if (re != 0) {
					break;
				}
			}
		}
		if (re == 1) {
			// 数据不存在
			response.setErrorEx(ConfigConstants.UPD_DATA_NOT_EXISTS, null);
		} else if (re == 2) {
			// 更新身份证与数据库冲突
			response.setErrorEx(ConfigConstants.IDCODE_ERR, null);
		}
		logger.debug("updList End");
		return response;
	}

	/**
	 * 得到人员信息
	 * 
	 * @param manInfoBean
	 *            人员信息
	 * @return 响应数据
	 */
	public ResponseResult getList(ManInfoBean manInfoBean) {
		ResponseResult response = ResponseResult.ok();
		logger.debug("getList Start");
		// TODO 操作日志操作未完成
		int totalRow = 0;
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
		// 超级管理员
		String creator = null;
		if (!ConfigConstants.ADMINISTRATORSID.equals(user.getId())) {
			creator = user.getId().toString();
		}
		// 名单类型判断
		java.util.List<Lists> listsList = new ArrayList<Lists>();
		// sql检索条件
		String name = null;
		String idCard = null;
		Integer sex = null;
		// sql检索条件设置
		if (manInfoBean.getName() != null && !manInfoBean.getName().isEmpty()) {
			name = ConfigConstants.PER_CENT + manInfoBean.getName() + ConfigConstants.PER_CENT;
		}
		if (manInfoBean.getIDCard() != null && !manInfoBean.getIDCard().isEmpty()) {
			idCard = ConfigConstants.PER_CENT + manInfoBean.getIDCard() + ConfigConstants.PER_CENT;
		}
		if (manInfoBean.getSex() != null) {
			sex = manInfoBean.getSex();
		}

		if (ConfigConstants.BLACKLIST.equals(manInfoBean.getListType())) {
			// sql执行
			totalRow = blacklistsMapperCustomer.countToGetlist(name, idCard, sex, creator);
			listsList = blacklistsMapperCustomer.selectToGetlist(name, idCard, sex, manInfoBean.getStart(),
					manInfoBean.getLimit(), creator);
			// 返回
			response.setBody(listsList);
			response.addExtend("listType", ConfigConstants.BLACKLIST);
			response.setTotal(totalRow);
		} else {
			// sql执行
			totalRow = whitelistsMapperCustomer.countToGetlist(name, idCard, sex, creator);
			listsList = whitelistsMapperCustomer.selectToGetlist(name, idCard, sex, manInfoBean.getStart(),
					manInfoBean.getLimit(), creator);

			response.setBody(listsList);
			response.addExtend("listType", ConfigConstants.WHITELIST);
			response.setTotal(totalRow);
		}
		logger.debug("getList End");
		return response;
	}

	/**
	 * 根据名单组ID得到所属人员信息
	 * 
	 * @param listGroupID
	 *            名单组ID
	 * @param start
	 *            页索引
	 * @param limit
	 *            页总数
	 * @return 响应数据
	 */
	public ResponseResult getList(Integer listGroupID, Integer start, Integer limit) {
		// TODO 操作日志操作未完成
		// 响应数据的初始化
		ResponseResult response = ResponseResult.ok();
		logger.debug("getListBylistGroupID Start");
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
		java.util.List<Lists> blacklistsList = new ArrayList<Lists>();
		int totalRow = 0;
		totalRow = blacklistsMapperCustomer.countByListGroupID(listGroupID);
		blacklistsList = blacklistsMapperCustomer.selectByListGroupID(listGroupID, start, limit);

		response.setBody(blacklistsList);
		response.setTotal(totalRow);
		logger.debug("getListBylistGroupID End");
		return response;
	}

	/**
	 * 根据名单组ID得到所属人员信息
	 * 
	 * @param idCode
	 *            身份证ID
	 * @return false 身份证不正确 true 身份证正确
	 */
	public boolean idCodeAuthentication(String idCode) {
		String regEX = new String();
		if (idCode == null || idCode.isEmpty()) {
			return false;
		}
		if (ConfigConstants.IDCODE_LENGTH_15.equals(idCode.length())) {
			regEX = ConfigConstants.IDCODE_REGEX_15;
		} else if (ConfigConstants.IDCODE_LENGTH_18.equals(idCode.length())) {
			regEX = ConfigConstants.IDCODE_REGEX_18;
		} else {
			return false;
		}
		Pattern pattern = Pattern.compile(regEX);
		Matcher matcher = pattern.matcher(idCode);
		return matcher.matches();
	}

	/**
	 * 数据认证
	 * 
	 * @param name
	 *            名字
	 * @param reason
	 *            理由
	 * @return
	 */
	public int stringCodeAuthentication(String name, String reason) {

		if (name.isEmpty() || reason.replace(ConfigConstants.SPACE, "").isEmpty()) {
			// 数据不能为空
			return 1;
		}
		if (name.indexOf(ConfigConstants.SPACE) != -1) {
			// 名字不能有空格
			return 2;
		}
		try {
			if (name.getBytes("utf-8").length > 20) {
				return 3;
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
			return 4;
		}
		return 0;
	}
}
