package com.gosun.isap.user.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.apache.activemq.transport.LogWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.gosun.isap.authority.api.MenuPermissionConst;
import com.gosun.isap.authority.api.UserUtil;
import com.gosun.isap.common.BeanValidate;
import com.gosun.isap.common.IdwrapperBat;
import com.gosun.isap.common.OrderSqlUtil;
import com.gosun.isap.common.ResponseResult;
import com.gosun.isap.common.error.ErrorCode;
import com.gosun.isap.common.utils.Md5Utils;
import com.gosun.isap.dao.EnmuUtils;
import com.gosun.isap.dao.po.TUser;
import com.gosun.isap.dao.po.TUserExample;
import com.gosun.isap.dao.po.customer.TUserCustomer;
import com.gosun.isap.operlog.api.OperateType;
import com.gosun.isap.operlog.api.ServiceType;
import com.gosun.isap.operlog.api.annotation.SysOperateLog;
import com.gosun.isap.operlog.api.helper.OperateLogWriter;
import com.gosun.isap.user.api.IUserApi;
import com.gosun.isap.user.api.UserConstance;
import com.gosun.isap.user.api.UserStatus;
import com.gosun.isap.user.api.UserWrapper;
import com.gosun.isap.user.service.TUserService;

@SuppressWarnings("rawtypes")
@Path("user")
public class UserApiImpl implements IUserApi {
	@Autowired
	private TUserService userService;
	private static final Logger log = LoggerFactory.getLogger(UserApiImpl.class);

	@PUT
	@Path("users")
	@Produces({ "application/json" })
	@SysOperateLog(serviceType=ServiceType.CONFIG_USER,operateType=OperateType.CONFIG_MOD,description="更新用户")
	public ResponseResult updateUser(UserWrapper userWrapper) {
		ResponseResult responseResult = ResponseResult.ok();
		// 权限不足
		if (!UserUtil.hasPermission(MenuPermissionConst.USER_MANAGER)) {
			responseResult.setErrorEx(ErrorCode.ERR_AUTHORITY, null);
		} else {
			String label = EnmuUtils.getEnmuLabel(UserStatus.class, 1);
			// 校验参数
			String msg = BeanValidate.validateModel(userWrapper);
			if (userWrapper.getId() <= 0 || StringUtils.isNotEmpty(msg)) {
				responseResult.setErrorEx(ErrorCode.ERR_ILLEGAL_ARGUMENT, new String[] { msg });
			} else if (!userWrapper.getPassword().equals(userWrapper.getRePassword())) {
				responseResult.setError(UserConstance.PASSWORD_NOT_MATCH, null);
			} else {
				TUser user = userService.get(userWrapper.getId());
				if (null == user) {
					responseResult.setErrorEx(UserConstance.USER_NOT_FOUND, null);
				} else {
					user.setName(StringUtils.isNotEmpty(userWrapper.getUsername()) ? userWrapper.getUsername() : null);
					user.setPasswd(userWrapper.getPassword());
					user.setLoginName(userWrapper.getLoginName());
					user.setEmail(StringUtils.isNotEmpty(userWrapper.getEmail()) ? userWrapper.getEmail() : null);
					user.setMobile(StringUtils.isNotEmpty(userWrapper.getPhone()) ? userWrapper.getPhone() : null);
					user.setPriority((byte) userWrapper.getPriority());
					user.setStatus((byte) UserStatus.OUTLINE);
					userService.saveOrUpdate(user, userWrapper.getRoleId());
				}
			}
		}
		return responseResult;

	}

	@POST
	@Path("users")
	@Produces({ "application/json" })
	@SysOperateLog(serviceType=ServiceType.CONFIG_USER,operateType=OperateType.CONFIG_ADD,description="添加用户")
	public ResponseResult addUser(UserWrapper userWrapper) {
		ResponseResult responseResult = ResponseResult.ok();
		if (!UserUtil.hasPermission(MenuPermissionConst.USER_MANAGER)) {
			responseResult.setErrorEx(ErrorCode.ERR_AUTHORITY, null);
		} else {
			String msg = BeanValidate.validateModel(userWrapper);
			if (StringUtils.isNotEmpty(msg)) {
				responseResult.setErrorEx(ErrorCode.ERR_ILLEGAL_ARGUMENT, new String[] { msg });
			} else if (!userWrapper.getPassword().equals(userWrapper.getRePassword())) {
				responseResult.setErrorEx(UserConstance.PASSWORD_NOT_MATCH, null);
			}
			// 如果登陆名相同的话不能添加
			else if (null != userService.findByLoginName(userWrapper.getLoginName())) {
				responseResult.setErrorEx(UserConstance.LOGIN_NAME_REPEAT, null);
			} else {
				TUser user = new TUser();
				user.setName(StringUtils.isNotEmpty(userWrapper.getUsername()) ? userWrapper.getUsername() : null);
				user.setPasswd(userWrapper.getPassword());
				user.setLoginName(userWrapper.getLoginName());
				user.setEmail(StringUtils.isNotEmpty(userWrapper.getEmail()) ? userWrapper.getEmail() : null);
				user.setMobile(StringUtils.isNotEmpty(userWrapper.getPhone()) ? userWrapper.getPhone() : null);
				user.setPriority((byte) userWrapper.getPriority());
				user.setStatus(UserStatus.OUTLINE);
				user.setLockStartTime(new Date());
				user.setLockEndTime(new Date());
				userService.saveOrUpdate(user, userWrapper.getRoleId());
			}
		}
		return responseResult;
	}

	@DELETE
	@Path("users")
	@Produces({ "application/json" })
	@SysOperateLog(serviceType=ServiceType.CONFIG_USER,operateType=OperateType.CONFIG_DEL,description="删除用户")
	public ResponseResult deleteUser(IdwrapperBat<Integer> ids) {
		ResponseResult responseResult = ResponseResult.ok();
		if (!UserUtil.hasPermission(MenuPermissionConst.USER_MANAGER)) {
			responseResult.setErrorEx(ErrorCode.ERR_AUTHORITY, null);
		} else {
			if (ids == null || ids.getId().size() < 0) {
				responseResult.setErrorEx(ErrorCode.ERR_ILLEGAL_ARGUMENT, null);
			} else {
				List<Long> intIds = new ArrayList<>();
				for (Integer idwapper : ids.getId()) {
					intIds.add((long) idwapper);
				}
				userService.batchDelete(intIds);
			}
		}
		return responseResult;
	}

	@GET
	@Path("users/collection")
	@Produces({ "application/json" })
	public ResponseResult getUserPage(@QueryParam("fuzzyField") String fuzzyField,
			@QueryParam("fuzzyValue") String fuzzyValue, @QueryParam("start") int start, @QueryParam("limit") int limit,
			@QueryParam("sort") String sort) {
		ResponseResult responseResult = ResponseResult.ok();
		if (!UserUtil.hasPermission(MenuPermissionConst.USER_MANAGER)) {
			responseResult.setErrorEx(ErrorCode.ERR_AUTHORITY, null);
		} else {
			if (limit <= 0) {
				responseResult.setError(ErrorCode.ERR_ILLEGAL_ARGUMENT, null);
			} else {
				TUserExample example = new TUserExample();
				TUserExample.Criteria criteria = example.createCriteria();
				example.setLimit(limit);
				example.setOffset(start);
				if (!StringUtils.isBlank(fuzzyField) && !StringUtils.isBlank(fuzzyValue)) {
					criteria.andGeneralLike(fuzzyField, "%" + fuzzyValue + "%");
				}
				if (StringUtils.isNotEmpty(sort)) {
					example.setOrderByClause(OrderSqlUtil.getOrderSqlStringBySort(sort));
				}
				List<TUserCustomer> users = userService.findList(example);
				responseResult.setBody(users);
				responseResult.setTotal(userService.countByExample(example));
			}
		}
		return responseResult;
	}

	@POST
	@Path("passwords")
	@Produces({ "application/json" })
	public ResponseResult validateUserOldPassword(UserWrapper userWrapper) {
		ResponseResult responseResult = ResponseResult.ok();
		if (!UserUtil.hasPermission(MenuPermissionConst.USER_MANAGER)) {
			responseResult.setErrorEx(ErrorCode.ERR_AUTHORITY, null);
		} else {
			if (StringUtils.isEmpty(userWrapper.getPassword()) || userWrapper.getId() <= 0) {
				responseResult.setErrorEx(ErrorCode.ERR_ILLEGAL_ARGUMENT, new String[] { "password或者id" });
			} else {
				TUser user = userService.get(userWrapper.getId());
				if (null == user) {
					responseResult.setErrorEx(UserConstance.USER_NOT_FOUND, null);
				} else if (!user.getPasswd().equals(userWrapper.getPassword())) {
					responseResult.setErrorEx(UserConstance.PASSWORD_NOT_RIGHT, null);
				}
			}
		}
		return responseResult;
	}

}
