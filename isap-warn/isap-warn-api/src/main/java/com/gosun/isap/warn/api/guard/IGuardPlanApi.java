package com.gosun.isap.warn.api.guard;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.gosun.isap.common.IdwrapperBat;
import com.gosun.isap.common.ResponseResult;

/**
 * 布防计划 REST API
 * 
 * @author liuzk
 *
 */
@SuppressWarnings("rawtypes")
@Path("warn/guard/plans")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
public interface IGuardPlanApi {

	/**
	 * @api {post} /warn/guard/plans addGuardPlan
	 * @apiGroup guard
	 * @apiName addGuardPlan
	 * @apiVersion 1.0.0
	 * @apiDescription 添加布防计划
	 * 
	 */
	@POST
	public ResponseResult addGuardPlan(GuardPlanWrapper wrapper);

	/**
	 * @api {delete} /warn/guard/plans deleteGuardPlan
	 * @apiGroup guard
	 * @apiName deleteGuardPlan
	 * @apiVersion 1.0.0
	 * @apiDescription 删除布防计划
	 */
	@DELETE
	public ResponseResult deleteGaurdPlan(IdwrapperBat<Long> planIds);

	/**
	 * @api {put} /warn/guard/plans modifyGuardPlan
	 * @apiGroup guard
	 * @apiName modifyGuardPlan
	 * @apiVersion 1.0.0
	 * @apiDescription 修改布防计划
	 */
	@PUT
	public ResponseResult modifyGuardPlan(GuardPlanWrapper wrapper);

	/**
	 * @api {get} /warn/guard/plans getGuardPlan
	 * @apiGroup guard
	 * @apiName getGuardPlan
	 * @apiVersion 1.0.0
	 * @apiDescription 获取布防计划信息
	 */
	@GET
	public ResponseResult<GuardPlanWrapper> getGuardPlan(@QueryParam("id") Long planId);

	/**
	 * @api {put} /warn/guard/plans/start startGuardPlan
	 * @apiGroup guard
	 * @apiName startGuardPlan
	 * @apiVersion 1.0.0
	 * @apiDescription 启动布防计划
	 */
	@PUT
	@Path("start")
	public ResponseResult startGuardPlan(IdwrapperBat<Long> planIds);

	/**
	 * @api {put} /war/guard/plans/stop stopGuardPlan
	 * @apiGroup guard
	 * @apiName stopGuardPlan
	 * @apiVersion 1.0.0
	 * @apiDescription 停止布防计划
	 */
	@PUT
	@Path("stop")
	public ResponseResult stopGuardPlan(IdwrapperBat<Long> planIds);

	/**
	 * @api {get} /war/guard/plans listGuardPlans
	 * @apiGroup guard
	 * @apiName listGuardPlans
	 * @apiVersion 1.0.0
	 * @apiDescription 查询布防计划列表信息
	 * 
	 * @apiParam {Number} start 分页开始
	 * @apiParam {Number} limit=100 每页数量
	 * @apiParam {String} sort 排序
	 * 
	 */
	@GET
	@Path("list")
	public ResponseResult<List<GuardPlan>> listGuardPlans(@QueryParam("start") Integer start,
			@QueryParam("limit") Integer limit, @QueryParam("sort") String sort,
			@QueryParam("fuzzyField") String fuzzyField, @QueryParam("fuzzyValue") String fuzzyValue);
}
