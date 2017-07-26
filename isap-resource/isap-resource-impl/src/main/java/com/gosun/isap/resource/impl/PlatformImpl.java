package com.gosun.isap.resource.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import com.gosun.isap.resource.api.wrapper.DeviceType;

import javax.ws.rs.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.container.spring.SpringContainer;
import com.gosun.isap.common.BeanValidate;
import com.gosun.isap.common.IdwrapperBat;
import com.gosun.isap.common.OrderSqlUtil;
import com.gosun.isap.common.ResponseResult;
import com.gosun.isap.common.error.ErrorCode;
import com.gosun.isap.dao.po.TPlat;
import com.gosun.isap.dao.po.TPlatExample;
import com.gosun.isap.dao.mapper.TDeviceMapper;
import com.gosun.isap.dao.po.TDevice;
import com.gosun.isap.dao.po.TDeviceExample;
import com.gosun.isap.resource.api.IPlatformApi;
import com.gosun.isap.resource.api.PlatformWrapper;
import com.gosun.isap.resource.api.constants.ResourceErrorCode;
import com.gosun.isap.resource.api.service.TDeviceService;
import com.gosun.isap.resource.api.service.TPlatformService;
import com.gosun.isap.resource.api.wrapper.ContentCommand;
import com.gosun.isap.operlog.api.OperateType;
import com.gosun.isap.operlog.api.ServiceType;
import com.gosun.isap.operlog.api.annotation.SysOperateLog;
import com.gosun.isap.operlog.api.helper.OperateLogWriter;
import com.gosun.isap.proxy.api.backend.IProxyCache;
import com.gosun.isap.proxy.api.backend.NewProxyException;
import com.gosun.isap.proxy.api.backend.ProxyManagerStateException;
import com.gosun.isap.proxy.api.backend.ProxyNotFoundException;
import com.gosun.isap.proxy.api.instance.CallNativeSdkException;
import com.gosun.isap.proxy.api.instance.ProxyProperty;
import com.gosun.isap.proxy.api.sdk.Device;
import com.gosun.isap.proxy.api.sdk.constants.PlatformType;

@SuppressWarnings("rawtypes")
@Path("platform")
public class PlatformImpl implements IPlatformApi {
	private static Logger logger = LoggerFactory.getLogger(PlatformImpl.class);

	/**
	 * 根部门id
	 */
	private static final String ROOT_DEPARTMENT_ID = "0";

	@Autowired
	private TPlatformService platformservice;
	@Autowired
	private TDeviceService deviceservice;
	@Autowired
	private TDeviceMapper deviceMapper;

	public ResponseResult queryPlatform() {

		ResponseResult responseResult = ResponseResult.ok();
		responseResult.setBody(platformservice.findList(new TPlatExample()));
		// OperateLogWriter.success(ServiceType.CONFIG_DEV,
		// OperateType.CONFIG_QUERY, "查询平台成功");
		return responseResult;
	}

	@Transactional
	@SysOperateLog(serviceType = ServiceType.CONFIG_DEV, operateType = OperateType.CONFIG_ADD, description = "添加平台")
	public ResponseResult addPlatform(PlatformWrapper platformwrapper) {
		ResponseResult responseResult = ResponseResult.ok();

		String msg = BeanValidate.validateModel(platformwrapper);
		if (StringUtils.isNotEmpty(msg)) {
			responseResult.setErrorEx(ErrorCode.ERR_ILLEGAL_ARGUMENT, null);
			return responseResult;
		}

		// 添加平台
		TPlat plat = new TPlat();
		plat.setVendorType(platformwrapper.getVendorType());
		plat.setAccessType(platformwrapper.getAccessType());
		plat.setName(platformwrapper.getPlatName());
		plat.setAccessIpAddress(platformwrapper.getIpAddreaccess());
		plat.setLoginUser(platformwrapper.getLoginUser());
		plat.setLoginPasswd(platformwrapper.getLoginPasswd());
		plat.setAccessPort(Integer.valueOf((platformwrapper.getPort())));
		plat.setStatus((byte) 0);
		plat.setDescription("123");

		// 校验IP地址是否重复
		TPlatExample example = new TPlatExample();
		TPlatExample.Criteria cri = example.createCriteria();
		cri.andAccessIpAddressEqualTo(platformwrapper.getIpAddreaccess());
		List<TPlat> Lplat = platformservice.findList(example);
		if (null != Lplat && Lplat.size() > 0) {
			responseResult.setError(ErrorCode.ERR_DB_OPERATE_FAIL, "ip地址冲突");
			return responseResult;
		}

		platformservice.addPlat(plat);

		// 创建proxy
		try {
			IProxyCache proxyCache = SpringContainer.getContext().getBean(IProxyCache.class);
			ProxyProperty property = new ProxyProperty();
			property.setProxyId(String.valueOf(plat.getId()));
			property.setAccessIpAddress(platformwrapper.getIpAddreaccess());
			property.setAccessPassword(platformwrapper.getLoginPasswd());
			property.setAccessPort(Integer.valueOf((platformwrapper.getPort())));
			property.setAccessUser(platformwrapper.getLoginUser());
			property.setPlatformType(PlatformType.valueOf(platformwrapper.getVendorType()));// 平台类型？？？
			proxyCache.newProxy(property);
		} catch (ProxyManagerStateException e) {
			// 如果是proxy manager状态异常，则不返回错误，等到manager启动后再自己启动
			return responseResult;
		} catch (NewProxyException e) {
			logger.error(e.getMessage());
			throw new RuntimeException(e);
		}

		return responseResult;
	}

	@Transactional
	@SysOperateLog(serviceType = ServiceType.CONFIG_DEV, operateType = OperateType.CONFIG_DEL, description = "删除平台")
	public ResponseResult deletePlatform(IdwrapperBat<Long> ID) {
		ResponseResult responseResult = ResponseResult.ok();

		if (ID.getId().size() < 0) {
			responseResult.setErrorEx(ErrorCode.ERR_ILLEGAL_ARGUMENT, null);
			return responseResult;
		}

		for (Long id : ID.getId()) {

			IProxyCache proxyCache = SpringContainer.getContext().getBean(IProxyCache.class);
			try {
				proxyCache.destroyProxy(String.valueOf(id));
			} catch (ProxyManagerStateException e) {
				// 删除失败时，不返回错误（支持离线删除)
				logger.error(e.getMessage());
			}
			// 删除平台
			platformservice.deletePlat(id);
		}
		return responseResult;
	}

	@Transactional
	@SysOperateLog(serviceType = ServiceType.CONFIG_DEV, operateType = OperateType.CONFIG_MOD, description = "修改平台")
	public ResponseResult updatePlatform(PlatformWrapper platformwrapper) {
		ResponseResult responseResult = ResponseResult.ok();

		String msg = BeanValidate.validateModel(platformwrapper);
		if (StringUtils.isNotEmpty(msg)) {
			responseResult.setErrorEx(ErrorCode.ERR_ILLEGAL_ARGUMENT, null);
			return responseResult;
		}

		// 更新平台
		TPlat plat = new TPlat();
		plat.setId(platformwrapper.getID());
		plat.setVendorType(platformwrapper.getVendorType());
		plat.setAccessType(platformwrapper.getAccessType());
		plat.setName(platformwrapper.getPlatName());
		plat.setAccessIpAddress(platformwrapper.getIpAddreaccess());
		plat.setLoginUser(platformwrapper.getLoginUser());
		plat.setLoginPasswd(platformwrapper.getLoginPasswd());
		plat.setAccessPort(Integer.valueOf((platformwrapper.getPort())));
		plat.setStatus((byte) 0);
		plat.setDescription("1234567");
		platformservice.updatePlat(plat);

		IProxyCache proxyCache = SpringContainer.getContext().getBean(IProxyCache.class);

		ProxyProperty property = new ProxyProperty();
		property.setProxyId(String.valueOf(plat.getId()));
		property.setAccessIpAddress(platformwrapper.getIpAddreaccess());
		property.setAccessPassword(platformwrapper.getLoginPasswd());
		property.setAccessPort(Integer.valueOf((platformwrapper.getPort())));
		property.setAccessUser(platformwrapper.getLoginUser());
		property.setPlatformType(PlatformType.valueOf(platformwrapper.getVendorType()));

		try {
			proxyCache.modifyProxy(property);
		} catch (ProxyManagerStateException e) {
			// proxy manager状态异常时，不返回错误
			logger.error(e.getMessage());
		}

		return responseResult;
	}

	public ResponseResult<List<TDevice>> queryDevice(String platid, String start, String limit, String sort,
			String fuzzyField, String fuzzyValue) {
		ResponseResult<List<TDevice>> responseResult = ResponseResult.ok();

		IProxyCache proxyCache = SpringContainer.getContext().getBean(IProxyCache.class);

		// 从基础平台查询所有设备
		List<Device> deviceList = null;
		try {
			deviceList = proxyCache.getProxy(platid).getSdkAdapter().queryAllDevices();
		} catch (ProxyNotFoundException e) {
			logger.error(e.getMessage());
			responseResult.setErrorEx(ResourceErrorCode.ERR_PROXY_NOT_EXIST, null);
			return responseResult;
		} catch (CallNativeSdkException e) {
			logger.error(e.getMessage());
			responseResult.setErrorEx(ErrorCode.ERR_CALL_NATIVE_SDK, null);
			return responseResult;
		}

		// 如果基础平台没有设备，则直接返回成功
		if (null == deviceList || deviceList.isEmpty()) {
			return responseResult;
		}

		long platid1 = Long.parseLong(platid);
		List<TDevice> newDevs = new ArrayList<TDevice>();

		for (Device dev : deviceList) {
			TDevice tdev = new TDevice();
			UUID uuid = UUID.randomUUID();
			tdev.setId(uuid.toString());
			tdev.setCode(dev.deviceCode);
			tdev.setDevType(dev.deviceType);
			tdev.setPlatId(platid1);
			tdev.setNetStatus((byte) 0);
			tdev.setLocationX(Double.toString(dev.latitude));
			tdev.setLocationY(Double.toString(dev.lonitude));
			tdev.setName(dev.alias);
			tdev.setStatus((byte) dev.online);
			tdev.setParent(dev.parentDeviceCode);
			tdev.setChannelType(dev.channelType);
			tdev.setChannelSubType(dev.chnanelSubType);
			tdev.setDepartmentId(ROOT_DEPARTMENT_ID);
			newDevs.add(tdev);
		}

		// 查询平台下以前的所有设备
		List<TDevice> origDevs = new ArrayList<>();
		TDeviceExample example = new TDeviceExample();
		TDeviceExample.Criteria Criteria = example.createCriteria();
		Criteria.andPlatIdEqualTo(platid1);
		origDevs = deviceservice.queryDevice(example);

		for (TDevice d : newDevs) {// 从平台获取到的数据
			boolean exist = false;

			for (TDevice origDev : origDevs) {// 从数据库中的数据
				if (origDev.getCode().equals(d.getCode())) {
					if (d.getStatus() != origDev.getStatus()) {
						deviceservice.updateDevStatus(origDev.getId(), d.getStatus());
					}
					exist = true;
					break;
				}
			}

			if (!exist) {
				TDevice tdevOne = new TDevice();
				UUID uuid = UUID.randomUUID();
				tdevOne.setId(uuid.toString());
				tdevOne.setCode(d.getCode());
				if (d.getDevType() != 0) {
					switch (d.getDevType()) {
					case 100:
						tdevOne.setDevType(DeviceType.DEVICE_TYPE_NVR);// NVR
						break;
					case 101:
						tdevOne.setDevType(DeviceType.DEVICE_TYPE_ENCODER);// 主设备编码器
						break;
					case 102:
						tdevOne.setDevType(DeviceType.DEVICE_TYPE_DVR);// 主设备DVR
						break;
					case 103:
						tdevOne.setDevType(DeviceType.DEVICE_TYPE_IPC);// 主设备IPC
						break;
					}
				} else {
					switch (d.getChannelSubType()) {
					case 2:
						tdevOne.setDevType(DeviceType.CHANNEL_TYPE_ENC_NORMAL);// 枪机
						break;
					case 3:
						tdevOne.setDevType(DeviceType.CHANNEL_TYPE_ENC_SD);// 球机
						break;
					case 4:
						tdevOne.setDevType(DeviceType.CHANNEL_TYPE_ENC_HALFSD);// 半球机
						break;
					case 0:
						tdevOne.setDevType(DeviceType.DEVICE_TYPE_UNKNOWN);// 未知设备
						break;
					}
				}
				tdevOne.setPlatId(platid1);
				tdevOne.setNetStatus((byte) 0);
				tdevOne.setLocationX(d.getLocationX());
				tdevOne.setLocationY(d.getLocationY());
				tdevOne.setName(d.getName());
				tdevOne.setStatus(d.getStatus());
				tdevOne.setParent(d.getParent());
				tdevOne.setChannelType(d.getChannelType());
				tdevOne.setChannelSubType(d.getChannelSubType());
				// 添加到根部门
				tdevOne.setDepartmentId(ROOT_DEPARTMENT_ID);
				deviceservice.insertDev(tdevOne);
			}
		}

		for (TDevice origDevT : origDevs) {// 数据库中的数据
			boolean existT = false;
			for (TDevice de : newDevs) {// 从平台获取到的数据
				if (origDevT.getCode().equals(de.getCode())) {
					existT = true;
					break;
				}
			}

			if (!existT) {
				platformservice.updateDevice(origDevT.getId());
			}
		}

		List<TDevice> devist1 = new ArrayList<>();
		TDeviceExample example1 = new TDeviceExample();
		TDeviceExample.Criteria Criteria1 = example1.createCriteria();
		Criteria1.andPlatIdEqualTo(platid1);

		if (!StringUtils.isBlank(fuzzyField) && !StringUtils.isBlank(fuzzyValue)) {
			Criteria1.andGeneralLike(fuzzyField, "%" + fuzzyValue + "%");
		}
		if (StringUtils.isNotEmpty(sort)) {
			example1.setOrderByClause(OrderSqlUtil.getOrderSqlStringBySort(sort));
		}

		example1.setLimit(Integer.parseInt(limit));
		example1.setOffset(Integer.parseInt(start));
		int total = deviceMapper.countByExample(example1);
		if (total > 0) {
			responseResult.setTotal(total);
			devist1 = deviceservice.queryDevice(example1);
			responseResult.setBody(devist1);
		}

		return responseResult;
	}

	@Transactional
	@SysOperateLog(serviceType = ServiceType.CONFIG_DEV, operateType = OperateType.CONFIG_MOD, description = "设备入网")
	public ResponseResult deviceNetIn(IdwrapperBat<String> ID) {
		ResponseResult<List<Device>> responseResult = ResponseResult.ok();
		if (null == ID || ID.getId() == null || ID.getId().size() == 0) {
			responseResult.setErrorEx(ErrorCode.ERR_ILLEGAL_ARGUMENT, null);
			return responseResult;
		}

		platformservice.netIn(ID.getId());
		return responseResult;
	}

	@Transactional
	@SysOperateLog(serviceType = ServiceType.CONFIG_DEV, operateType = OperateType.CONFIG_MOD, description = "设备退网")
	public ResponseResult deviceNetOut(IdwrapperBat<String> ID) {
		ResponseResult<List<Device>> responseResult = ResponseResult.ok();
		if (null == ID || ID.getId() == null || ID.getId().size() == 0) {
			responseResult.setErrorEx(ErrorCode.ERR_ILLEGAL_ARGUMENT, null);
			return responseResult;
		}

		platformservice.netOut(ID.getId());
		return responseResult;
	}

	@Transactional
	@SysOperateLog(serviceType = ServiceType.CONFIG_DEV, operateType = OperateType.CONFIG_DEL, description = "手动删除设备")
	public ResponseResult delDev(IdwrapperBat<String> ID) {

		ResponseResult<List<Device>> responseResult = ResponseResult.ok();
		if (null == ID || ID.getId() == null || ID.getId().size() == 0) {
			responseResult.setErrorEx(ErrorCode.ERR_ILLEGAL_ARGUMENT, null);
			return responseResult;
		}
		String message = "";
		for (int index = 0; index < ID.getId().size(); index++) {

			try {
				platformservice.delDevice(ID.getId().get(index));
			} catch (Exception e) {
				message = message + ID.getId().get(index) + ";";
			}

		}
		// +"关联告警需手动删除"
		if (StringUtils.isNotEmpty(message)) {
			message = message + "关联告警需手动删除";
			responseResult.setError(ErrorCode.ERR_DB_OPERATE_FAIL, message);
		}
		return responseResult;
	}
}
