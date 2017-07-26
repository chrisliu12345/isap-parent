package com.gosun.isap.resource.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.gosun.isap.common.OrderSqlUtil;
import com.gosun.isap.common.ResponseResult;
import com.gosun.isap.common.error.ErrorCode;
import com.gosun.isap.dao.mapper.TDeviceMapper;
import com.gosun.isap.dao.po.TDevice;
import com.gosun.isap.dao.po.TDeviceExample;
import com.gosun.isap.operlog.api.OperateType;
import com.gosun.isap.operlog.api.ServiceType;
import com.gosun.isap.operlog.api.helper.OperateLogWriter;
import com.gosun.isap.resource.api.IDeviceApi;
import com.gosun.isap.resource.api.service.TDeviceService;
import com.gosun.isap.resource.api.wrapper.ContentCommand;
import com.gosun.isap.resource.api.wrapper.DeviceParentment;
import com.gosun.isap.resource.api.wrapper.DeviceUpdate;

@Path("device")
public class DeviceApiImpl implements IDeviceApi {

	@Autowired
	private TDeviceService deviceService;

	@Autowired
	private TDeviceMapper deviceMapper;

	public ResponseResult updataDevice(DeviceUpdate devUpdate) {
		ResponseResult responseResult = ResponseResult.ok();
		String deviceId = devUpdate.getId();
		String deviceName = devUpdate.getName();
		String description = devUpdate.getDescription();
		if ( StringUtils.isEmpty(deviceName)|| StringUtils.isEmpty(deviceId)) {
			responseResult.setErrorEx(ErrorCode.ERR_ILLEGAL_ARGUMENT, null);
			OperateLogWriter.fail(ServiceType.CONFIG_DEV, OperateType.CONFIG_MOD, "设备修改",
					ContentCommand.MSG_UPDATE_ERROR);//修改
		}
		else
		{
			deviceService.updateDeviceName(deviceId, deviceName,description);
			OperateLogWriter.success(ServiceType.CONFIG_DEV, OperateType.CONFIG_MOD, "设备修改");
		}
		return responseResult;
	}

	// 查询组织下设备 分页 排序 模糊查询
	public ResponseResult<List<TDevice>> queryAuthAndDevice(String departid, String start, String limit, String sort,
			String fuzzyField, String fuzzyValue) {
		ResponseResult responseResult = ResponseResult.ok();

		if (StringUtils.isEmpty(departid)) {
			responseResult.setErrorEx(ErrorCode.ERR_ILLEGAL_ARGUMENT, null);				
			OperateLogWriter.fail(ServiceType.CONFIG_DEV, OperateType.CONFIG_QUERY, "查询组织下的设备",
			ContentCommand.MSG_FINDBYNAME_ERROR);//修改		
			return responseResult;
		}

		List<TDevice> devicetList = new ArrayList<>();
		TDeviceExample example = new TDeviceExample();
		TDeviceExample.Criteria Criteria = example.createCriteria();
		Criteria.andDepartmentIdEqualTo(departid);
		Criteria.andNetStatusEqualTo((byte) 1);
		Criteria.andChannelSubTypeBetween(2,4);
		if (!StringUtils.isBlank(fuzzyField) && !StringUtils.isBlank(fuzzyValue)) {
			Criteria.andGeneralLike(fuzzyField, "%" + fuzzyValue + "%");
		}
		if (StringUtils.isNotEmpty(sort)) {
			example.setOrderByClause(OrderSqlUtil.getOrderSqlStringBySort(sort));
		}
		/*
		 * if(StringUtils.isNotEmpty(fuzzyField)) {
		 * 
		 * if(StringUtils.isNotEmpty(fuzzyValue)) { if(
		 * fuzzyField.equals("name")) {
		 * Criteria.andNameLike("%"+fuzzyValue+"%");
		 * 
		 * } else if(fuzzyField.equals("code")) {
		 * Criteria.andCodeLike("%"+fuzzyValue+"%"); } else
		 * if(fuzzyField.equals("type")) {
		 * Criteria.andDevTypeLike("%"+fuzzyValue+"%"); } } }
		 * 
		 * if(StringUtils.isNotEmpty(sort)) {
		 * 
		 * if(true==sort.equals("name")) {
		 * example.setOrderByClause("name  ASC"); } else
		 * if(true==sort.equals("-name")) {
		 * example.setOrderByClause("name DESC"); } else
		 * if(true==sort.equals("type")) {
		 * example.setOrderByClause("dev_type ASC"); } else
		 * if(true==sort.equals("-type")) {
		 * example.setOrderByClause("dev_type DESC"); } else
		 * if(true==sort.equals("net_status")) {
		 * example.setOrderByClause("net_status ASC"); } else
		 * if(sort.equals("-net_status")) {
		 * example.setOrderByClause("net_status DESC"); } else
		 * if(true==sort.equals("status")) {
		 * example.setOrderByClause("status ASC"); } else
		 * if(true==sort.equals("-status")) {
		 * example.setOrderByClause("status DESC"); }
		 * 
		 * }
		 */
		example.setLimit(Integer.parseInt(limit));
		example.setOffset(Integer.parseInt(start));
		int total = deviceMapper.countByExample(example);
		if (total > 0) {
			responseResult.setTotal(total);
			devicetList = deviceService.queryPlatdevice(example);// 返回的是通过查询条件查询组织下设备
			OperateLogWriter.success(ServiceType.CONFIG_DEV, OperateType.CONFIG_QUERY, "查询组织下的设备成功");
			responseResult.setBody(devicetList);
		}

		return responseResult;
	}

	// 查询未划归设备 分页
	public ResponseResult<List<TDevice>> queryBranchDevice(String sort, String start, String limit, String departmentid,
			String fuzzyField, String fuzzyValue) {
		// TODO Auto-generated method stub
		ResponseResult responseResult = ResponseResult.ok();
		
		
		if ( StringUtils.isEmpty(departmentid)) {
			responseResult.setErrorEx(ErrorCode.ERR_ILLEGAL_ARGUMENT, null);
			OperateLogWriter.fail(ServiceType.CONFIG_DEV, OperateType.CONFIG_MOD, "设备未划归",
					ContentCommand.MSG_FINDBYNAME_ERROR);//修改
		}
		else
		{
		
		List<TDevice> devicetList = new ArrayList<>();
		TDeviceExample example = new TDeviceExample();
		TDeviceExample.Criteria Criteria = example.createCriteria();
		Criteria.andDepartmentIdEqualTo(departmentid);
		Criteria.andNetStatusEqualTo((byte) 1);
		Criteria.andChannelSubTypeBetween(2,4);

		if (!StringUtils.isBlank(fuzzyField) && !StringUtils.isBlank(fuzzyValue)) {
			Criteria.andGeneralLike(fuzzyField, "%" + fuzzyValue + "%");
		}
		if (StringUtils.isNotEmpty(sort)) {
			example.setOrderByClause(OrderSqlUtil.getOrderSqlStringBySort(sort));
		}

		example.setLimit(Integer.parseInt(limit));
		example.setOffset(Integer.parseInt(start));
		int total = deviceMapper.countByExample(example);
		if (total > 0) {
			responseResult.setTotal(total);
			devicetList = deviceService.queryNoBranchDevice(example);// 返回的是list
//			OperateLogWriter.success(ServiceType.CONFIG_DEV, OperateType.CONFIG_QUERY, "设备未划归");
			responseResult.setBody(devicetList);
		}
		}
		return responseResult;
	}

	// 设备划归
	public ResponseResult addDevice(DeviceParentment devparent) {
		// TODO Auto-generated method stub
		List<String> devicetList = new ArrayList<>();
		devicetList = devparent.getId();
		String branchid = devparent.getParentment();
		ResponseResult responseResult = ResponseResult.ok();
		
		if ( StringUtils.isEmpty(branchid)|| devicetList.size() < 0) {
			responseResult.setErrorEx(ErrorCode.ERR_ILLEGAL_ARGUMENT, null);
			OperateLogWriter.fail(ServiceType.CONFIG_DEV, OperateType.CONFIG_MOD, "设备划归",
					ContentCommand.MSG_UPDATE_ERROR);//修改
		}
		else
		{
		
		if (devicetList.size() == 0 || branchid == null || StringUtils.isEmpty(branchid)) {
			responseResult.setError(ErrorCode.ERR_ILLEGAL_ARGUMENT, null);
			return responseResult;
		}

		TDevice device = new TDevice();
		device.setParent(branchid);
		deviceService.updateDevice(devicetList, branchid);
		OperateLogWriter.success(ServiceType.CONFIG_DEV, OperateType.CONFIG_MOD, "设备划归");
		}
		return responseResult;
	}

	// 查询平台设备并排序
	public ResponseResult<List<TDevice>> queryPlatDeviceOrder(String sort, String start, String limit, String platid,
			String fuzzyField, String fuzzyValue)// 查询平台设备并排序
	{
		ResponseResult responseResult = ResponseResult.ok();
		List<TDevice> devicetList = new ArrayList<>();
		TDeviceExample example = new TDeviceExample();
		TDeviceExample.Criteria Criteria = example.createCriteria();
		Criteria.andPlatIdEqualTo(Long.parseLong(platid));

		if (!StringUtils.isBlank(fuzzyField) && !StringUtils.isBlank(fuzzyValue)) {
			Criteria.andGeneralLike(fuzzyField, "%" + fuzzyValue + "%");
		}
		if (StringUtils.isNotEmpty(sort)) {
			example.setOrderByClause(OrderSqlUtil.getOrderSqlStringBySort(sort));
		}

		example.setLimit(Integer.parseInt(limit));// 条数
		example.setOffset(Integer.parseInt(start));// 从多少条开始
		int total = deviceMapper.countByExample(example);
		if (total > 0) {
			responseResult.setTotal(total);

			devicetList = deviceService.queryPlatdevice(example);// 返回的是list
			responseResult.setBody(devicetList);
		}

		return responseResult;
	}

	// 通过查询条件查询组织下设备并排序
	public ResponseResult<List<TDevice>> queryDeviceOrder(String sort, String start, String limit, String devicename,
			String departmentid)// 查询平台设备并排序
	{
		ResponseResult responseResult = ResponseResult.ok();
		if (StringUtils.isEmpty(departmentid)) {
			responseResult.setErrorEx(ErrorCode.ERR_ILLEGAL_ARGUMENT, null);
			OperateLogWriter.fail(ServiceType.CONFIG_DEV, OperateType.CONFIG_QUERY, "查询组织下的设备",
					ContentCommand.MSG_FINDBYNAME_ERROR);//修改
		} else {
		List<TDevice> devicetList = new ArrayList<>();
		TDeviceExample example = new TDeviceExample();
		TDeviceExample.Criteria Criteria = example.createCriteria();

		if (!StringUtils.isBlank("name") && !StringUtils.isBlank(devicename)) {
			Criteria.andGeneralLike("name", "%" + devicename + "%");
		}
		if (StringUtils.isNotEmpty(sort)) {
			example.setOrderByClause(OrderSqlUtil.getOrderSqlStringBySort(sort));
		}

		example.setLimit(Integer.parseInt(limit));// 条数
		example.setOffset(Integer.parseInt(start));// 从多少条开始

		int total = deviceService.countByExample(example);
		if (total > 0) {
			devicetList = deviceService.queryPlatdevice(example);// 返回的是通过查询条件查询组织下设备并排序
			OperateLogWriter.success(ServiceType.CONFIG_DEV, OperateType.CONFIG_QUERY, "查询组织下的设备成功");
			responseResult.setBody(devicetList);
		}
		}
		return responseResult;
	}
}
