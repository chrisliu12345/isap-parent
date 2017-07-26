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

@SuppressWarnings("rawtypes")
@Path("warn/guard/timeTemplates")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
public interface ITimeTemplateApi {

	/**
	 * @api {post} /warn/guard/timeTemplates/single addSingleTimeTemplate
	 * @apiGroup guard
	 * @apiName addSingleTimeTemplate
	 * @apiVersion 1.0.0
	 * @apiDescription 添加单时间模板
	 */
	@POST
	@Path("single")
	public ResponseResult addSingleTimeTemplate(SingleTimeTemplateWrapper wrapper);

	/**
	 * @api {put} /warn/guard/timeTemplates/single addSingleTimeTemplate
	 * @apiGroup guard
	 * @apiName modifySingleTimeTemplate
	 * @apiVersion 1.0.0
	 * @apiDescription 修改单时间模板
	 */
	@PUT
	@Path("single")
	public ResponseResult modifySingleTimeTemplate(SingleTimeTemplateWrapper wrapper);

	/**
	 * @api {post} /warn/guard/timeTemplates/week addWeekTimeTemplate
	 * @apiGroup guard
	 * @apiName addWeekTimeTemplate
	 * @apiVersion 1.0.0
	 * @apiDescription 添加循环时间模板
	 */
	@POST
	@Path("week")
	public ResponseResult addWeekTimeTemplate(WeekTimeTemplateWrapper wrapper);

	/**
	 * @api {put} /warn/guard/timeTemplates/week addWeekTimeTemplate
	 * @apiGroup guard
	 * @apiName modifyWeekTimeTemplate
	 * @apiVersion 1.0.0
	 * @apiDescription 添加循环时间模板
	 */
	@PUT
	@Path("week")
	public ResponseResult modifyWeekTimeTemplate(WeekTimeTemplateWrapper wrapper);

	/**
	 * @api {delete} /warn/guard/timeTemplates deleteTimeTemplate
	 * @apiGroup guard
	 * @apiName deleteTimeTemplate
	 * @apiVersion 1.0.0
	 * @apiDescription 删除时间模板
	 */
	@DELETE
	public ResponseResult deleteTimeTemplate(IdwrapperBat<Long> templateIds);

	/**
	 * @api {get} /warn/guard/timeTemplates/single getSingleTimeTemplate
	 * @apiGroup guard
	 * @apiName getSingleTimeTemplate
	 * @apiVersion 1.0.0
	 * @apiDescription 获取单时间模板
	 * 
	 * @apiParam {Number} id 时间模板ID
	 */
	@GET
	@Path("single")
	public ResponseResult<SingleTimeTemplateWrapper> getSingleTimeTemplate(@QueryParam("id") Long templateId);

	/**
	 * @api {get} /warn/guard/timeTemplates/week getWeekTimeTemplate
	 * @apiGroup guard
	 * @apiName getWeekTimeTemplate
	 * @apiVersion 1.0.0
	 * @apiDescription 获取重复周期时间模板
	 * 
	 * @apiParam {Number} [id] 时间模板ID
	 */
	@GET
	@Path("week")
	public ResponseResult<WeekTimeTemplateWrapper> getWeekTimeTemplate(@QueryParam("id") Long templateId);

	/**
	 * @api {get} /warn/guard/timeTemplates listTimeTemplate
	 * @apiGroup guard
	 * @apiName listTimeTemplate
	 * @apiVersion 1.0.0
	 * @apiDescription 获取时间模板列表信息
	 *
	 * @apiParam {Number} start 分页开始
	 * @apiParam {Number} limit 每页数量
	 * @apiParam {String} [sort] 排序规则
	 */
	@GET
	public ResponseResult<List<GuardTimeTemplate>> listTimeTemplate(@QueryParam("start") Integer start,
			@QueryParam("limit") Integer limit, @QueryParam("sort") String sort,
			@QueryParam("fuzzyField") String fuzzyField, @QueryParam("fuzzyValue") String fuzzyValue);
}
