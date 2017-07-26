package com.gosun.isap.resource.api;

import java.util.List;

import javax.ws.rs.Consumes;

//import java.util.List;

//import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
//import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
//import javax.ws.rs.QueryParam;
//import javax.ws.rs.core.Context;
import javax.ws.rs.QueryParam;

import com.gosun.isap.common.IdwrapperBat;
import com.gosun.isap.common.ResponseResult;

import com.gosun.isap.common.ResponseResult;
import com.gosun.isap.dao.po.TDevice;
import com.gosun.isap.dao.po.TPlat;
import com.gosun.isap.proxy.api.backend.NewProxyException;
import com.gosun.isap.proxy.api.instance.CallNativeSdkException;
import com.gosun.isap.proxy.api.sdk.Device;

@Path("platform")
public interface IPlatformApi {

	@GET
	@Path("platforms")
	@Produces("application/json")
	ResponseResult<List<TPlat>> queryPlatform();// 查询平台

	@POST
	@Path("platforms")
	@Produces("application/json")
	@Consumes("application/json")
	// ResponseResult addPlatform(String ID);//添加平台 封装一个类
	ResponseResult addPlatform(PlatformWrapper platformWrapper);

	@DELETE
	@Path("platforms")
	@Produces("application/json")
	ResponseResult deletePlatform(IdwrapperBat<Long> ID);// 删除平台

	// updatePlatform
	@PUT
	@Path("platforms")
	@Produces("application/json")
	ResponseResult updatePlatform(PlatformWrapper platformWrapper);// 更新平台
	// queryDevice

	@GET
	@Path("platforms/dev")
	@Produces("application/json")
	ResponseResult<List<TDevice>> queryDevice(@QueryParam("platid") String platid, @QueryParam("start") String start,
			@QueryParam("limit") String limit, @QueryParam("sort") String sort,
			@QueryParam("fuzzyField") String fuzzyField, @QueryParam("fuzzyValue") String fuzzyValue);

	@POST
	@Path("platforms/line")
	@Produces("application/json")
	ResponseResult deviceNetIn(IdwrapperBat<String> ID);// 设备入网

	@DELETE
	@Path("platforms/line")
	@Produces("application/json")
	@Consumes("application/json")
	ResponseResult deviceNetOut(IdwrapperBat<String> ID);// 设备退网
	
	
	@DELETE
	@Path("platforms/deldev")
	@Produces("application/json")
	@Consumes("application/json")
	ResponseResult delDev(IdwrapperBat<String> ID);// 设备退网


}
