package com.gosun.isap.resource.api;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;

import com.gosun.isap.common.IdwrapperBat;
import com.gosun.isap.common.ResponseResult;
import com.gosun.isap.dao.po.TDevice;
import com.gosun.isap.resource.api.wrapper.DeviceParentment;
import com.gosun.isap.resource.api.wrapper.DeviceUpdate;
import com.gosun.isap.resource.api.wrapper.DevicesWrapper;

@Path("device")
public interface IDeviceApi {

	@POST
	@Path("devices")
	@Produces("application/json")
	ResponseResult addDevice(DeviceParentment devparent);

	/*
	 * @DELETE
	 * 
	 * @Path("devices/deldevice")
	 * 
	 * @Produces("application/json") ResponseResult
	 * deldevice(@QueryParam("deviceid") String deviceid);//组织下删除设备
	 */

	@PUT
	@Path("devices")
	@Produces("application/json")
	ResponseResult updataDevice(DeviceUpdate devupdate);// 修改设备

	@GET
	@Path("devices/qdev")
	@Produces("application/json")
	ResponseResult<List<TDevice>> queryAuthAndDevice(@QueryParam("departid") String departid,
			@QueryParam("start") String start, @QueryParam("limit") String limit, @QueryParam("sort") String sort,
			@QueryParam("fuzzyField") String fuzzyField, @QueryParam("fuzzyValue") String fuzzyValue);// 根据组织刷新设备（查询组织下的设备）???

	@GET
	@Path("devices/nostatu")
	@Produces("application/json")
	ResponseResult<List<TDevice>> queryBranchDevice(@QueryParam("sort") String sort, @QueryParam("start") String start,
			@QueryParam("limit") String limit, @QueryParam("departmentid") String departmentid,
			@QueryParam("fuzzyField") String fuzzyField, @QueryParam("fuzzyValue") String fuzzyValue);// 查询未划归设备

	/*
	 * @GET
	 * 
	 * @Path("devices/qid")
	 * 
	 * @Produces("application/json") ResponseResult
	 * querydeviceid(@QueryParam("deviceid") String deviceid);//通过id查询设备
	 */
	@GET
	@Path("devices/qplatdev")
	@Produces("application/json")
	ResponseResult<List<TDevice>> queryPlatDeviceOrder(@QueryParam("sort") String sort,
			@QueryParam("start") String start, @QueryParam("limit") String limit, @QueryParam("platid") String platid,
			@QueryParam("fuzzyField") String fuzzyField, @QueryParam("fuzzyValue") String fuzzyValue);// 查询平台设备并排序

	// 根据结果来查询
	@GET
	@Path("devices/devorder")
	@Produces("application/json")
	ResponseResult<List<TDevice>> queryDeviceOrder(@QueryParam("sort") String sort, @QueryParam("start") String start,
			@QueryParam("limit") String limit, @QueryParam("devicename") String devcice,
			@QueryParam("departmentid") String departmentid);// 查询平台设备并排序

}
