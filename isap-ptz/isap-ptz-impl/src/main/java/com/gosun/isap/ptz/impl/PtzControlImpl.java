package com.gosun.isap.ptz.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.container.spring.SpringContainer;
import com.gosun.isap.authority.api.UserUtil;
import com.gosun.isap.common.ResponseResult;
import com.gosun.isap.dao.po.TDevice;
import com.gosun.isap.dao.po.TUser;
import com.gosun.isap.operlog.api.OperateType;
import com.gosun.isap.operlog.api.ServiceType;
import com.gosun.isap.operlog.api.helper.OperateLogWriter;
import com.gosun.isap.proxy.api.backend.IProxyCache;
import com.gosun.isap.proxy.api.backend.ProxyWrapper;
import com.gosun.isap.proxy.api.instance.CallNativeSdkException;
import com.gosun.isap.proxy.api.sdk.Preset;
import com.gosun.isap.proxy.api.sdk.PtzControlParam;
import com.gosun.isap.ptz.api.ContentCommand;

import com.gosun.isap.ptz.api.IPtzControlApi;
import com.gosun.isap.ptz.api.PtzCmdReq;
import com.gosun.isap.ptz.api.PtzPresetReq;
import com.gosun.isap.resource.api.service.TDeviceService;


@SuppressWarnings("rawtypes")
@Path("ptz")
public class PtzControlImpl implements IPtzControlApi {
	private static PtzParam ptzParam = null;
	@Autowired
	private TDeviceService deviceService;

	public void ptzProxyExcute(TDevice device, PtzCmdReq ptzCmdReq) throws CallNativeSdkException {
		IProxyCache proxyCache = SpringContainer.getContext().getBean(IProxyCache.class);
		ProxyWrapper proxy = proxyCache.getProxy(String.valueOf(device.getPlatId()));
		// proxy.getSdkAdapter().SndPtzDirection(req);
		if (ptzCmdReq.getCmd() == ContentCommand.DIRECTION_CONTROL_CMD) {
			PtzControlParam param = new PtzControlParam();
			param.deviceId = device.getCode();
			param.cmdType = ptzCmdReq.getOperation();
			param.param1 = ptzCmdReq.getStep();

			proxy.getSdkAdapter().ptzControl(param);

		} else if (ptzCmdReq.getCmd() == ContentCommand.SCENE_CONTROL_CMD) {
			PtzControlParam param = new PtzControlParam();
			param.deviceId = device.getCode();
			param.param1 = ptzCmdReq.getStep();
			switch (ptzCmdReq.getOperation()) {
			case ContentCommand.PTZ_ZOOMIN: {
				// param.PtzCmdType.valueof(req.getOperation());
				param.cmdType = PtzControlParam.PtzCmdType.PTZ_ZOOMIN;
			}
				break;
			case ContentCommand.PTZ_FOCUSNEAR: {
				param.cmdType = PtzControlParam.PtzCmdType.PTZ_FOCUSNEAR;
			}
				break;
			case ContentCommand.PTZ_BRTUP: {
				param.cmdType = PtzControlParam.PtzCmdType.PTZ_BRTUP;
			}
				break;
			case ContentCommand.PTZ_ZOOMOUT: {
				param.cmdType = PtzControlParam.PtzCmdType.PTZ_ZOOMOUT;
			}
				break;
			case ContentCommand.PTZ_FOCUSFAR: {
				param.cmdType = PtzControlParam.PtzCmdType.PTZ_FOCUSFAR;
			}
				break;
			case ContentCommand.PTZ_BRTDOWN: {
				param.cmdType = PtzControlParam.PtzCmdType.PTZ_BRTDOWN;
			}
				break;
			default:
				break;
			}

			proxy.getSdkAdapter().ptzControl(param);

		} else if (ptzCmdReq.getCmd() == ContentCommand.STOP_ALL_CMD) {
			PtzControlParam param = new PtzControlParam();
			param.deviceId = device.getCode();
			param.param1 = ptzCmdReq.getStep();
			param.cmdType = PtzControlParam.PtzCmdType.PTZ_STP;
			if (ptzCmdReq.getOperation() >= 1 && ptzCmdReq.getOperation() <= ContentCommand.PTZ_DIRECTION) {
				param.param2 = ptzCmdReq.getOperation();
			}

			else {
				switch (ptzCmdReq.getOperation()) {
				case ContentCommand.PTZ_ZOOMIN: {
					// param.PtzCmdType.valueof(req.getOperation());
					param.param2 = PtzControlParam.PtzCmdType.PTZ_ZOOMIN;
				}
					break;
				case ContentCommand.PTZ_FOCUSNEAR: {
					param.param2 = PtzControlParam.PtzCmdType.PTZ_FOCUSNEAR;
				}
					break;
				case ContentCommand.PTZ_BRTUP: {
					param.param2 = PtzControlParam.PtzCmdType.PTZ_BRTUP;
				}
					break;
				case ContentCommand.PTZ_ZOOMOUT: {
					param.param2 = PtzControlParam.PtzCmdType.PTZ_ZOOMOUT;
				}
					break;
				case ContentCommand.PTZ_FOCUSFAR: {
					param.param2 = PtzControlParam.PtzCmdType.PTZ_FOCUSFAR;
				}
					break;
				case ContentCommand.PTZ_BRTDOWN: {
					param.param2 = PtzControlParam.PtzCmdType.PTZ_BRTDOWN;
				}
					break;
				default:
					break;
				}
			}
			proxy.getSdkAdapter().ptzControl(param);
		}
	}

	// cmd 0 方向控制 操作参数operation：1-8，上，下，左，右，左上，左下，右上，右下
	// cmd 1为云镜控制 操作参数operation://9-14，变倍+，变焦+，光圈+，变倍-，变焦-，光圈-
	@PUT
	@Path("ptzcmdcontrols")
	@Produces("application/json")
	public ResponseResult ptzCmdControl(@Context HttpServletRequest request, PtzCmdReq ptzCmdReq) {

		ResponseResult response = ResponseResult.ok();

		String session = request.getSession().getId();
		
		
		if (ptzParam == null) {

			TDevice device = deviceService.findDeviceById((ptzCmdReq.getDeviceId()));
			TUser user = UserUtil.getCurrentUser();
			if (device != null) {

				try {
					ptzProxyExcute(device, ptzCmdReq);
					OperateLogWriter.success(ServiceType.PTZ_CONTROL, OperateType.USER_START, "ptz 控制操作");
				} catch (CallNativeSdkException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					response.setError(ContentCommand.ERROR_CALL_SDK, ContentCommand.MSG_CALL_SDK_ERROR);
					OperateLogWriter.fail(ServiceType.PTZ_CONTROL, OperateType.USER_STOP, "ptz 控制操作",
							ContentCommand.MSG_CALL_SDK_ERROR);
				}
				// 发送cmd命令到 proxy模块
				// SndPtzDirection(req);
			} else {
				response.setErrorEx(ContentCommand.ERROR_GET_DEVICE, null);
				OperateLogWriter.fail(ServiceType.PTZ_CONTROL, OperateType.USER_STOP, "ptz 控制操作",
						ContentCommand.MSG_DEVICE_ERROR);
				return response;
			}
			// 第一次操作将用户信息和用户id保存到全局变量内存中
			ptzParam = new PtzParam();

			int prio = Integer.valueOf(user.getPriority());
			ptzParam.setPriority(prio);
			ptzParam.setDatetime(new java.util.Date());
			ptzParam.setUserId(user.getId());
			ptzParam.setSessionId(session);
		} else {
			TUser user = UserUtil.getCurrentUser();
			int prio = Integer.valueOf(user.getPriority());

			// 同一用户同一电脑
			if (user.getId().equals(ptzParam.getUserId()) && session.equals(ptzParam.getSessionId())) {
				TDevice device = deviceService.findDeviceById(ptzCmdReq.getDeviceId());
				if (device != null) {
					try {
						ptzProxyExcute(device, ptzCmdReq);
						OperateLogWriter.success(ServiceType.PTZ_CONTROL, OperateType.USER_START, "ptz 控制操作");
					} catch (CallNativeSdkException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						response.setError(ContentCommand.ERROR_CALL_SDK, ContentCommand.MSG_CALL_SDK_ERROR);
						OperateLogWriter.fail(ServiceType.PTZ_CONTROL, OperateType.USER_STOP, "ptz 控制操作",
								ContentCommand.MSG_CALL_SDK_ERROR);
					}

					ptzParam.setDatetime(new java.util.Date());
				} else {
					response.setErrorEx(ContentCommand.ERROR_GET_DEVICE, null);
					OperateLogWriter.fail(ServiceType.PTZ_CONTROL, OperateType.USER_STOP, "ptz 控制操作",
							ContentCommand.MSG_DEVICE_ERROR);
					return response;
				}
			}
			// 同一用户 不同电脑 比较最后的操作时间 如果时间跨度大于5s那么就抢占操作，否则提示无权操作
			else if (user.getId().equals(ptzParam.getUserId()) && !session.equals(ptzParam.getSessionId())) {
				java.util.Date date = new java.util.Date();
				long timespan = (date.getTime() - ptzParam.getDatetime().getTime()) / ContentCommand.MILLISECOND;
				if (timespan > ContentCommand.TIMEINTERVAL) {
					TDevice device = deviceService.findDeviceById(ptzCmdReq.getDeviceId());
					if (device != null) {
						try {
							ptzProxyExcute(device, ptzCmdReq);
							OperateLogWriter.success(ServiceType.PTZ_CONTROL, OperateType.USER_START, "ptz 控制操作");
						} catch (CallNativeSdkException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							response.setError(ContentCommand.ERROR_CALL_SDK, ContentCommand.MSG_CALL_SDK_ERROR);
							OperateLogWriter.fail(ServiceType.PTZ_CONTROL, OperateType.USER_STOP, "ptz 控制操作",
									ContentCommand.MSG_CALL_SDK_ERROR);
						}

						ptzParam.setDatetime(new java.util.Date());
						ptzParam.setSessionId(session);
					} else {
						response.setErrorEx(ContentCommand.ERROR_GET_DEVICE, null);
						OperateLogWriter.fail(ServiceType.PTZ_CONTROL, OperateType.USER_STOP, "ptz 控制操作",
								ContentCommand.MSG_DEVICE_ERROR);
						return response;
					}
				} else {
					response.setErrorEx(ContentCommand.ERROR_OPERATE_AUTH, null);
					OperateLogWriter.fail(ServiceType.PTZ_CONTROL, OperateType.USER_STOP, "ptz 控制操作",
							ContentCommand.MSG_OPERATE_ERROR);
					return response;
				}
			}
			// 如果为不同的用户 需要判断用户级别级别不同的按优先级高的进行抢占 发送 级别相同的按时间来确定发送
			else if (!user.getId().equals(ptzParam.getUserId())) {
				if (prio < ptzParam.getPriority()) {
					TDevice device = deviceService.findDeviceById(ptzCmdReq.getDeviceId());
					if (device != null) {
						try {
							ptzProxyExcute(device, ptzCmdReq);
							OperateLogWriter.success(ServiceType.CONFIG_PTZ, OperateType.USER_START, "ptz 控制操作");
						} catch (CallNativeSdkException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							response.setError(ContentCommand.ERROR_CALL_SDK, ContentCommand.MSG_CALL_SDK_ERROR);
							OperateLogWriter.fail(ServiceType.PTZ_CONTROL, OperateType.USER_STOP, "ptz 控制操作",
									ContentCommand.MSG_CALL_SDK_ERROR);
						}

						ptzParam.setDatetime(new java.util.Date());
						ptzParam.setSessionId(session);
						ptzParam.setPriority(prio);
						ptzParam.setUserId(user.getId());

					} else {
						response.setErrorEx(ContentCommand.ERROR_GET_DEVICE, null);
						OperateLogWriter.fail(ServiceType.PTZ_CONTROL, OperateType.USER_STOP, "ptz 控制操作",
								ContentCommand.MSG_DEVICE_ERROR);
						return response;
					}
				}
				// 如果优先级相同 则按时间来确定
				else if (prio == ptzParam.getPriority()) {
					java.util.Date date = new java.util.Date();
					long timespan = (date.getTime() - ptzParam.getDatetime().getTime()) / ContentCommand.MILLISECOND;
					if (timespan > ContentCommand.TIMEINTERVAL) {
						TDevice device = deviceService.findDeviceById(ptzCmdReq.getDeviceId());
						if (device != null) {
							try {
								ptzProxyExcute(device, ptzCmdReq);
								OperateLogWriter.success(ServiceType.PTZ_CONTROL, OperateType.USER_START, "ptz 控制操作");
							} catch (CallNativeSdkException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								response.setError(ContentCommand.ERROR_CALL_SDK, ContentCommand.MSG_CALL_SDK_ERROR);
								OperateLogWriter.fail(ServiceType.PTZ_CONTROL, OperateType.USER_STOP, "ptz 控制操作",
										ContentCommand.MSG_CALL_SDK_ERROR);
							}

							ptzParam.setDatetime(new java.util.Date());
							ptzParam.setSessionId(session);
							ptzParam.setPriority(prio);
							ptzParam.setUserId(user.getId());

						} else {
							response.setErrorEx(ContentCommand.ERROR_GET_DEVICE, null);
							OperateLogWriter.fail(ServiceType.PTZ_CONTROL, OperateType.USER_STOP, "ptz 控制操作",
									ContentCommand.MSG_DEVICE_ERROR);
							return response;
						}
					} else {
						response.setErrorEx(ContentCommand.ERROR_OPERATE_AUTH, null);
						OperateLogWriter.fail(ServiceType.PTZ_CONTROL, OperateType.USER_STOP, "ptz 控制操作",
								ContentCommand.MSG_OPERATE_ERROR);
						return response;
					}
				} else {
					//ptz过10s清除内存数据 
					if(ptzParam != null) {
						java.util.Date date = new java.util.Date();
						long timespan = (date.getTime() - ptzParam.getDatetime().getTime()) / ContentCommand.MILLISECOND;
						if(timespan > 10) {
							ptzParam = null;
							return response;
						}
						
					}
					response.setErrorEx(ContentCommand.ERROR_OPERATE_AUTH, null);
					OperateLogWriter.fail(ServiceType.PTZ_CONTROL, OperateType.USER_STOP, "ptz 控制操作",
							ContentCommand.MSG_OPERATE_ERROR);
					return response;
				}

			}
		}
		return response;
	}

	// 预置位的添加 操作
	@POST
	@Path("presetcontrols")
	@Produces("application/json")
	public ResponseResult addPreset(@Context HttpServletRequest request, PtzPresetReq ptzPresetReq) {

		ResponseResult response = ResponseResult.ok();

		// String username = (String)
		// request.getSession().getAttribute(ContentCommand.CURRENT_USER_KEY);
		TDevice device = deviceService.findDeviceById(ptzPresetReq.getDeviceId());
		if (device == null) {
			response.setErrorEx(ContentCommand.ERROR_GET_DEVICE, null);
			OperateLogWriter.fail(ServiceType.CONFIG_PTZ, OperateType.CONFIG_ADD, "添加预置位操作",
					ContentCommand.MSG_DEVICE_ERROR);
		} else {
			IProxyCache proxyCache = SpringContainer.getContext().getBean(IProxyCache.class);
			ProxyWrapper proxy = proxyCache.getProxy(String.valueOf(device.getPlatId()));
			PtzControlParam param = new PtzControlParam();
			param.cmdType = PtzControlParam.PtzCmdType.PTZ_SET_PRESET;
			param.deviceId = device.getCode();
			param.param2 = ptzPresetReq.getPresetId();
			param.szName = ptzPresetReq.getName();
			try {
				System.out.println("param.cmdType:" + param.cmdType + "param.deviceId:" + param.deviceId
						+ "param.param2" + param.param2 + "param.szName" + param.szName);
				proxy.getSdkAdapter().ptzControl(param);
				OperateLogWriter.success(ServiceType.CONFIG_PTZ, OperateType.CONFIG_ADD, "添加预置位操作");

			} catch (CallNativeSdkException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				response.setError(ContentCommand.ERROR_CALL_SDK, ContentCommand.MSG_CALL_SDK_ERROR);
				OperateLogWriter.fail(ServiceType.CONFIG_PTZ, OperateType.CONFIG_ADD, "添加预置位操作",
						ContentCommand.MSG_CALL_SDK_ERROR);
			}
			// SndPztControl(req);
			// 更新数据库

		}

		return response;
	}

	// 预置位的删除操作
	@DELETE
	@Path("presetcontrols")
	@Produces("application/json")
	public ResponseResult deletePreset(@Context HttpServletRequest request, PtzPresetReq ptzPresetReq) {

		ResponseResult response = ResponseResult.ok();

		// String username = (String)
		// request.getSession().getAttribute(ContentCommand.CURRENT_USER_KEY);
		TDevice device = deviceService.findDeviceById(ptzPresetReq.getDeviceId());
		if (device == null) {
			response.setErrorEx(ContentCommand.ERROR_GET_DEVICE, null);
			OperateLogWriter.fail(ServiceType.CONFIG_PTZ, OperateType.CONFIG_DEL, "删除预置位操作",
					ContentCommand.MSG_DEVICE_ERROR);
		} else {
			IProxyCache proxyCache = SpringContainer.getContext().getBean(IProxyCache.class);
			ProxyWrapper proxy = proxyCache.getProxy(String.valueOf(device.getPlatId()));
			PtzControlParam param = new PtzControlParam();
			param.cmdType = PtzControlParam.PtzCmdType.PTZ_CLE_PRESET;
			param.deviceId = device.getCode();
			param.param2 = ptzPresetReq.getPresetId();
			try {
				proxy.getSdkAdapter().ptzControl(param);
				OperateLogWriter.success(ServiceType.CONFIG_PTZ, OperateType.CONFIG_DEL, "删除预置位操作");
			} catch (CallNativeSdkException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				response.setError(ContentCommand.ERROR_CALL_SDK, ContentCommand.MSG_CALL_SDK_ERROR);
				OperateLogWriter.fail(ServiceType.CONFIG_PTZ, OperateType.CONFIG_DEL, "删除预置位操作",
						ContentCommand.MSG_CALL_SDK_ERROR);
			}
			// SndPztControl(req);
			// 更新数据库
		}
		return response;
	}

	// 预置位的查找，先调用查询接口到基础平台上查找，再同步数据库中的预置位列表，返回json列表
	@GET
	@Path("presetcontrols")
	@Produces("application/json")
	public ResponseResult<List<Preset>> getPresetList(@Context HttpServletRequest request,
			@QueryParam("deviceid") String deviceId) {
		ResponseResult<List<Preset>> response = ResponseResult.ok();

		// String userName = (String)
		// request.getSession().getAttribute(ContentCommand.CURRENT_USER_KEY);
		TDevice device = deviceService.findDeviceById(deviceId);
		if (device == null) {
			response.setErrorEx(ContentCommand.ERROR_GET_DEVICE, null);
			OperateLogWriter.fail(ServiceType.CONFIG_PTZ, OperateType.CONFIG_QUERY, "获取预置位操作",
					ContentCommand.MSG_DEVICE_ERROR);
		} else {
			IProxyCache proxyCache = SpringContainer.getContext().getBean(IProxyCache.class);
			ProxyWrapper proxy = proxyCache.getProxy(String.valueOf(device.getPlatId()));
			List<Preset> presetList;
			try {
				presetList = proxy.getSdkAdapter().queryPtzPreset(device.getCode());
				response.setBody(presetList);
				OperateLogWriter.success(ServiceType.CONFIG_PTZ, OperateType.CONFIG_QUERY, "获取预置位操作");
			} catch (CallNativeSdkException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				response.setError(ContentCommand.ERROR_CALL_SDK, ContentCommand.MSG_CALL_SDK_ERROR);
				OperateLogWriter.fail(ServiceType.CONFIG_PTZ, OperateType.CONFIG_QUERY, "获取预置位操作",
						ContentCommand.MSG_CALL_SDK_ERROR);
			}

			// 更新数据库 列表
		}
		// res2.setErr("success");
		return response;
	}
	
	// 预置位的GOTO 操作
		@PUT
		@Path("presetcontrols")
		@Produces("application/json")
		public ResponseResult gotoPreset(@Context HttpServletRequest request, PtzPresetReq ptzPresetReq) {

			ResponseResult response = ResponseResult.ok();

			// String username = (String)
			// request.getSession().getAttribute(ContentCommand.CURRENT_USER_KEY);
			TDevice device = deviceService.findDeviceById(ptzPresetReq.getDeviceId());
			if (device == null) {
				response.setErrorEx(ContentCommand.ERROR_GET_DEVICE, null);
				
			} else {
				IProxyCache proxyCache = SpringContainer.getContext().getBean(IProxyCache.class);
				ProxyWrapper proxy = proxyCache.getProxy(String.valueOf(device.getPlatId()));
				PtzControlParam param = new PtzControlParam();
				param.cmdType = PtzControlParam.PtzCmdType.PTZ_GOTO_PRESET;
				param.deviceId = device.getCode();
				param.param2 = ptzPresetReq.getPresetId();
				param.szName = ptzPresetReq.getName();
				try {
					System.out.println("param.cmdType:" + param.cmdType + "param.deviceId:" + param.deviceId
							+ "param.param2" + param.param2 + "param.szName" + param.szName);
					proxy.getSdkAdapter().ptzControl(param);

				} catch (CallNativeSdkException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					response.setError(ContentCommand.ERROR_CALL_SDK, ContentCommand.MSG_CALL_SDK_ERROR);
				}
				// SndPztControl(req);
				// 更新数据库

			}

			return response;
		}
}
