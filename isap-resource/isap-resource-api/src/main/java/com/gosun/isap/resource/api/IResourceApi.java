package com.gosun.isap.resource.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.gosun.isap.common.ResponseResult;

@Path("resource")
@Produces("application/json")
@SuppressWarnings("rawtypes")
public interface IResourceApi {

	/**
	 * @api {get} /resource/resources getResourceList
	 * @apiName getResourceList
	 * @apiGroup resource
	 * @apiVersion 1.0.0
	 * @apiDescription 分页获取某个组织下的资源
	 * 
	 * @apiParam {String} departmentId 部门id，取值：当传入空时，表示查询根节点，当传入*时，表示匹配任意目录，当传入其他时，表示匹配给定的部门
	 * @apiParam {Number} start 分页开始
	 * @apiParam {Number} limit 每页数量
	 * @apiParam {String} [sort] 排序规则
	 * @apiParam {String} [fuzzyField] 模糊匹配字段
	 * @apiParam {String} [fuzzyValue] 模糊匹配值
	 */
	@GET
	@Path("resources")
	ResponseResult getResourceList(@QueryParam("departmentId") String departmentId, @QueryParam("start") Integer start,
			@QueryParam("limit") Integer limit, @QueryParam("sort") String sort,
			@QueryParam("fuzzyField") String fuzzyField, @QueryParam("fuzzyValue") String fuzzyValue);
}
