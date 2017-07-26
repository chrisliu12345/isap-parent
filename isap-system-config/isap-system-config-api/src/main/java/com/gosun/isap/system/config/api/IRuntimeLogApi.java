package com.gosun.isap.system.config.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.gosun.isap.common.ResponseResult;

/**
 * 运行日志api接口
 * 
 * @author liuzk
 *
 */
@Path("runtime")
public interface IRuntimeLogApi {

	/**
	 * @api {get} /runtime/logs/{level} modifyRuntimeLogLevel
	 * @apiGroup system-config
	 * @apiVersion 1.0.0
	 * @apiDescription 修改运行日志等级
	 * 
	 * @apiParam {String} [level=debug] 日志等级
	 */
	@GET
	@Path("logs/{level}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	ResponseResult modifyLogLevel(@PathParam("level") String level);
}
