package com.gosun.isap.ptz.api;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;

import com.gosun.isap.common.ResponseResult;
import com.gosun.isap.common.ResponseResult;
import com.gosun.isap.proxy.api.sdk.Preset;

@Path("ptz")
public interface IPtzControlApi {

	@PUT
	@Path("ptzcmdcontrols")
	@Produces("application/json")
	ResponseResult ptzCmdControl(@Context HttpServletRequest request, PtzCmdReq ptzCmdReq);
	@PUT
	@Path("presetcontrols")
	@Produces("application/json")
	ResponseResult gotoPreset(@Context HttpServletRequest request, PtzPresetReq ptzPresetReq);
	@POST
	@Path("presetcontrols")
	@Produces("application/json")
	ResponseResult addPreset(@Context HttpServletRequest request,PtzPresetReq ptzPresetReq);
	@DELETE
	@Path("presetcontrols")
	@Produces("application/json")
	ResponseResult deletePreset(@Context HttpServletRequest request,PtzPresetReq ptzPresetReq);
	@GET
	@Path("presetcontrols")
	@Produces("application/json")
	ResponseResult<List<Preset>> getPresetList(@Context HttpServletRequest request,@QueryParam("deviceid")String deviceId);
}
