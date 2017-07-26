package com.gosun.isap.authority.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.rpc.RpcContext;
import com.gosun.isap.authority.api.AuthorityErrorCode;
import com.gosun.isap.authority.api.IAuthorityApi;
import com.gosun.isap.authority.api.LoginType;
import com.gosun.isap.authority.api.MenuPermissionConst;
import com.gosun.isap.authority.api.UserInfo;
import com.gosun.isap.authority.api.UserUtil;
import com.gosun.isap.authority.api.requestbody.LoginEntity;
import com.gosun.isap.authority.api.requestbody.TempAuthAddWapper;
import com.gosun.isap.authority.api.responsebody.LoginBody;
import com.gosun.isap.authority.api.service.TDevAuthService;
import com.gosun.isap.authority.api.service.TMenuAuthService;
import com.gosun.isap.authority.api.service.TUserDeviceAuthService;
import com.gosun.isap.common.BeanValidate;
import com.gosun.isap.common.IdwrapperBat;
import com.gosun.isap.common.ResponseResult;
import com.gosun.isap.common.error.ErrorCode;
import com.gosun.isap.common.error.IMessageTemplate;
import com.gosun.isap.dao.po.TDevAuthdef;
import com.gosun.isap.dao.po.TMenuAuthdef;
import com.gosun.isap.dao.po.TUser;
import com.gosun.isap.dao.po.TUserDeviceAuth;
import com.gosun.isap.operlog.api.OperateType;
import com.gosun.isap.operlog.api.ServiceType;
import com.gosun.isap.operlog.api.annotation.SysOperateLog;
import com.gosun.isap.operlog.api.helper.OperateLogWriter;
import com.gosun.isap.user.service.TUserService;

@Path("authorization")
public class AuthorityApiImpl implements IAuthorityApi {
	@Autowired
	private TUserService userService;
	@Autowired
	private TMenuAuthService menuAuthService;
	@Autowired
	private TDevAuthService deviceAuthService;
	@Autowired
	private TUserDeviceAuthService userDeviceAuthService;
	private static final int SIGN_FAIL_LIMIT = 5;// 登录失败次数上限
	private static final int SIGN_LIMIT_TIME = 30;// 限制不能登录期限30分钟
	private static final String DEV_AUTHS_KEY = "devAuthList";
	private static final String USER_ID_KEY = "currentUserId";
	private static final String MENU_AUTHS_KEY = "menuAuthList";
	private static final String USER_NAME_KEY = "userLoginName";
	@Autowired
	private IMessageTemplate template;

	@POST
	@Path("login")
	@Produces(MediaType.APPLICATION_JSON)
	@SysOperateLog(serviceType=ServiceType.LOGIN,operateType=OperateType.USER_LOGIN,description="用户登录")
	public ResponseResult<LoginBody> login(LoginEntity loginEntity, @Context HttpServletRequest request, @Context ServletContext context) {
		ResponseResult<LoginBody> responseResult = ResponseResult.ok();

		// 如果服务还未启动完成，直接返回失败
		if (1 != AuthorityModule.AUTH_MODUL_START_FLAG) {
			responseResult.setError(AuthorityErrorCode.SERVER_NOT_START_SUCCESS, "服务器正在启动中，请稍后重试!");
			return responseResult;
		}
		if (StringUtils.isNotEmpty(BeanValidate.validateModel(loginEntity))) {
			responseResult.setErrorEx(AuthorityErrorCode.DATA_VALIDATE_ERR, null);
		} else if (loginEntity.getLoginType().equals(LoginType.WEB_LOGIN_FLAG) && StringUtils.isBlank(loginEntity.getIp())) {
			responseResult.setErrorEx(AuthorityErrorCode.IP_NOT_NULL, new String[] { "ip" });
		} else {
			if (StringUtils.isBlank(loginEntity.getIp())) {
				loginEntity.setIp(getRemoteHost(request));
			}
			TUser user = userService.findByLoginName(loginEntity.getLoginName());
			UserInfo userInfo=new UserInfo(user,loginEntity.getIp());
			if (null == user) {
				responseResult.setErrorEx(AuthorityErrorCode.LOGIN_NAME_ERR, null);
			} else {
				Date nowDate = new Date();
				if (user.getLockStartTime() != null && user.getLockEndTime() != null && nowDate.before(user.getLockEndTime()) && nowDate.after(user.getLockStartTime())) {
					responseResult.setErrorEx(AuthorityErrorCode.USER_LOCKED, null);
				} else if (!loginEntity.getPassword().equalsIgnoreCase(user.getPasswd())) { // 更新内存中该用户的失败登陆次数
					if (null != context.getAttribute(loginEntity.getLoginName())) {
						int fail_num = (int) context.getAttribute(loginEntity.getLoginName());
						fail_num++;
						context.setAttribute(loginEntity.getLoginName(), fail_num); // 锁定该用户30分钟
						if (fail_num >= SIGN_FAIL_LIMIT) {
							user.setLockStartTime(nowDate);
							Calendar calendar = Calendar.getInstance();
							calendar.setTime(nowDate);
							calendar.add(Calendar.MINUTE, SIGN_LIMIT_TIME);
							user.setLockEndTime(calendar.getTime());
							userService.saveOrUpdate(user);
						}
					} else {
						context.setAttribute(loginEntity.getLoginName(), 1);
					}
					responseResult.setErrorEx(AuthorityErrorCode.PASSWORD_ERR, null);
				} else {
					// 清空
					context.removeAttribute(loginEntity.getLoginName());
					// 登陆成功设置session中的用户信息
					request.getSession().setAttribute(LoginBody.USER_IFNO_KEY, userInfo); //
					LoginBody loginBody = new LoginBody(UserUtil.getDeviceAuthListVOList(), user.getId(), UserUtil.getMenuPermissionList(), user.getLoginName(), request.getSession().getId());
					responseResult.setBody(loginBody);
				}
			}
		}
		return responseResult;
	}

	@GET
	@Path("menuAuths/collection")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseResult<List<TMenuAuthdef>> menuAuthList(@QueryParam("parentId") int parentId) {
		ResponseResult<List<TMenuAuthdef>> responseResult = ResponseResult.ok();
		// 目前只有顶级管理员才能访问该api
		if (!UserUtil.hasPermission(MenuPermissionConst.RESOURCE)) {
			responseResult.setErrorEx(ErrorCode.ERR_AUTHORITY, null);
		} else {
			List<TMenuAuthdef> menuAuthdefs = menuAuthService.fetchAllByparentId(new ArrayList<TMenuAuthdef>(), Long.valueOf(parentId));
			responseResult.setBody(menuAuthdefs);
		}
		return responseResult;
		
	}

	@GET
	@Path("deviceAuths/collection")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseResult<List<TDevAuthdef>> deviceAuthList(@QueryParam("deviceType") int deviceType) {
		ResponseResult<List<TDevAuthdef>> responseResult = ResponseResult.ok();
		if (!UserUtil.hasPermission(MenuPermissionConst.RESOURCE)) {
			responseResult.setErrorEx(ErrorCode.ERR_AUTHORITY, null);
		} else {
			if (UserUtil.getCurrentUser().getId() == 1) {
				responseResult.setBody(deviceAuthService.findAll());
			} else {
				List<TDevAuthdef> devAuthdefs = deviceAuthService.findByDeviceType(deviceType);
				responseResult.setBody(devAuthdefs);
			}
		}
		return responseResult;
	}

	@PUT
	@Path("tempAuths")
	@Produces(MediaType.APPLICATION_JSON)
	@SysOperateLog(serviceType=ServiceType.TEMP_AUTH,operateType=OperateType.CONFIG_MOD,description="临时权限修改")
	public ResponseResult tempAuthAdd(TempAuthAddWapper tempAuthAddWapper) {
		ResponseResult responseResult = ResponseResult.ok();
		if (!UserUtil.hasPermission(MenuPermissionConst.RESOURCE)) {
			responseResult.setErrorEx(ErrorCode.ERR_AUTHORITY, null);
		} else {
			String msg = BeanValidate.validateModel(tempAuthAddWapper);
			if (StringUtils.isNotEmpty(msg)) {
				responseResult.setErrorEx(ErrorCode.ERR_ILLEGAL_ARGUMENT, null);
			} else {
				TUserDeviceAuth userDeviceAuth = new TUserDeviceAuth();
				userDeviceAuth.setEndDate(tempAuthAddWapper.getEndDate());
				userDeviceAuth.setUserId(Long.valueOf(tempAuthAddWapper.getUserId()));
				userDeviceAuth.setAuthId(Long.valueOf(tempAuthAddWapper.getAuthId()));
				userDeviceAuthService.save(userDeviceAuth);
			}
		}
		return responseResult;
	}

	@POST
	@Path("tempAuths")
	@Produces(MediaType.APPLICATION_JSON)
	@SysOperateLog(serviceType=ServiceType.TEMP_AUTH,operateType=OperateType.CONFIG_ADD,description="临时权限添加")
	public ResponseResult tempAuthUpdate(TempAuthAddWapper tempAuthAddWapper) {
		ResponseResult responseResult = ResponseResult.ok();
		if (!UserUtil.hasPermission(MenuPermissionConst.RESOURCE)) {
			responseResult.setErrorEx(ErrorCode.ERR_AUTHORITY, null);
		} else {
			if (tempAuthAddWapper.getId() <= 0) {
				responseResult.setErrorEx(ErrorCode.ERR_ILLEGAL_ARGUMENT, null);
			} else {
				TUserDeviceAuth userDeviceAuth = new TUserDeviceAuth();
				userDeviceAuth.setEndDate(null != tempAuthAddWapper.getEndDate() ? tempAuthAddWapper.getEndDate() : null);
				userDeviceAuth.setUserId(null != Long.valueOf(tempAuthAddWapper.getUserId()) ? Long.valueOf(tempAuthAddWapper.getUserId()) : null);
				userDeviceAuth.setAuthId(null != Long.valueOf(tempAuthAddWapper.getAuthId()) ? Long.valueOf(tempAuthAddWapper.getUserId()) : null);
				userDeviceAuthService.update(userDeviceAuth);
			}
		}
		return responseResult;
	}

	/**
	 * 批量删除
	 * 
	 * @param ids
	 * @return
	 */
	@DELETE
	@Path("tempAuths")
	@Produces(MediaType.APPLICATION_JSON)
	@SysOperateLog(serviceType=ServiceType.TEMP_AUTH,operateType=OperateType.CONFIG_DEL,description="临时权限删除")
	public ResponseResult tempAuthDelete(IdwrapperBat<Integer> ids) {
		ResponseResult responseResult = ResponseResult.ok();
		if (!UserUtil.hasPermission(MenuPermissionConst.RESOURCE)) {
			responseResult.setErrorEx(ErrorCode.ERR_AUTHORITY, null);
		} else {
			if (null == ids || ids.getId() == null || ids.getId().size() <= 0) {
				responseResult.setErrorEx(ErrorCode.ERR_ILLEGAL_ARGUMENT, null);
			} else {
				List<Long> intIds = new ArrayList<>();
				for (Integer idwapper : ids.getId()) {
					intIds.add((long) idwapper);
				}
				userDeviceAuthService.batchDelete(intIds);
			}
		}
		return responseResult;
	}

	@POST
	@Path("logout")
	@Produces(MediaType.APPLICATION_JSON)
	@SysOperateLog(operateType=OperateType.USER_LOGOUT,serviceType=ServiceType.LOGOUT,description="退出当前账号")
	public ResponseResult logout() {
		ResponseResult responseResult = ResponseResult.ok();
		OperateLogWriter.success(OperateType.USER_LOGOUT, ServiceType.LOGOUT, "退出当前账号");
		RpcContext context = RpcContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context.getRequest();
		request.getSession().removeAttribute(LoginBody.USER_IFNO_KEY);
		return responseResult;
	}

	public String getRemoteHost(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
	}
}
