package com.gosun.isap.resource.api;

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
import com.gosun.isap.common.Idwrapper;
import com.gosun.isap.common.IdwrapperBat;
import com.gosun.isap.common.ResponseResult;
import com.gosun.isap.common.ResponseResult;
import com.gosun.isap.resource.api.wrapper.DepartmentWrapper;

@Path("department")
public interface IDepartmentApi {
	// 增加
	@POST
	@Path("departments")
	@Produces({ "application/json" })
	ResponseResult addDepartment(DepartmentWrapper departmentWrapper);

	// 刪除
	@DELETE
	@Path("departments")
	@Produces({ "application/json" })
	ResponseResult deleteDepartment(IdwrapperBat<String> ids);

	// 查看
	@GET
	@Path("departments/collection")
	@Produces({ "application/json" })
	ResponseResult getDepartmentPage(@QueryParam("fuzzyFiled") String fuzzyFiled,
			@QueryParam("fuzzyValue") String fuzzyValue, @QueryParam("start") int start, @QueryParam("limit") int limit,
			@QueryParam("sort") String sort,@QueryParam("communityFlag")int communityFlag);
			// ResponseResult getDepartmentPage(@QueryParam("content") String
			// content,
			// @QueryParam("start") int start,
			// @QueryParam("pageSize") int pageSize, @QueryParam("sort") String
			// sort);

	// 修改
	@PUT
	@Path("departments")
	@Produces({ "application/json" })
	ResponseResult updateDepartment(DepartmentWrapper departmentWrapper);

	// 根据parent获取所有子组织。
	@GET
	@Path("departments/list")
	@Produces({ "application/json" })
	ResponseResult getChildsFromDepartment(@QueryParam("parent") String parent);

	// 根据名字查询组织
	@GET
	@Path("departments/codes")
	@Produces({ "application/json" })
	ResponseResult getDepartByName(@QueryParam("name") String name);

	@GET
	@Path("departments/flagss")
	@Produces({ "application/json" })
	ResponseResult getDepartByFlag(@QueryParam("communityflag") Integer communityflag);

	@GET
	@Path("departments/flags")
	@Produces({ "application/json" })
	ResponseResult getDepartByFlag(@QueryParam("communityflag") byte communityflag, @QueryParam("start") int start,
			@QueryParam("limit") int limit, @QueryParam("sort") String sort);

	@GET
	@Path("departments/childList")
	@Produces({ "application/json" })
	ResponseResult getNextLevelListByParent(@QueryParam("parentId") String parentId);

	@DELETE
	@Path("departments/deletion/{id}")
	@Produces({ "application/json" })
	ResponseResult deleteAllChildByParentId(@PathParam("id") String id);
}
