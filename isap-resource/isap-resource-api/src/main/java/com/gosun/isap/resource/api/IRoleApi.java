package com.gosun.isap.resource.api;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.ibatis.session.RowBounds;

import com.gosun.isap.common.Idwrapper;
import com.gosun.isap.common.IdwrapperBat;
import com.gosun.isap.common.ResponseResult;

import com.gosun.isap.dao.po.TDevAuthdef;
import com.gosun.isap.dao.po.TMenuAuthdef;
import com.gosun.isap.dao.po.TRole;
import com.gosun.isap.dao.po.TRoleDevAuth;
import com.gosun.isap.dao.po.TRoleExample;
import com.gosun.isap.dao.po.TRoleMenuAuth;
import com.gosun.isap.dao.po.customer.TRoleCustomer;
import com.gosun.isap.resource.api.wrapper.AddRoleReceivePara;

@Path("role")
public interface IRoleApi {

	@GET
	@Path("roles/collection")
	@Produces({ "application/json" })
	ResponseResult<List<TRole>> queryRole(@QueryParam("start") int start, @QueryParam("limit") int limit,
			@QueryParam("sort") String sort);

	@GET
	@Path("roles")
	@Produces({ "application/json" })
	ResponseResult<List<TRole>> getRolePage(@QueryParam("fuzzyField") String fuzzyField,
			@QueryParam("fuzzyValue") String fuzzyValue, @QueryParam("start") int start, @QueryParam("limit") int limit,
			@QueryParam("sort") String sort);

	@DELETE
	@Path("roles")
	@Produces({ "application/json" })
	ResponseResult deleteRole(IdwrapperBat<Integer> ids);

	@POST
	@Path("roles/addMenuAuth")
	@Produces({ "application/json" })
	ResponseResult addRoleMenuAuth(AddRoleReceivePara receivePara);

	@POST
	@Path("roles/addDevAuth")
	@Produces({ "application/json" })
	ResponseResult addRoleDevAuth(AddRoleReceivePara receivePara);
	
	/*
	 * @GET
	 * 
	 * @Path("roles")
	 * 
	 * @Produces({ "application/json" }) public
	 * ResponseResult<List<TRoleCustomer>> getAllRoleAndAuth(
	 * 
	 * @QueryParam("id") Long id);
	 */

	@GET
	@Path("roles/updateMenuAuth")
	@Produces({ "application/json" })
	public ResponseResult updateRoleMenuAuth(AddRoleReceivePara receivePara);
	
	@GET
	@Path("roles/updateDevAuth")
	@Produces({ "application/json" })
	public ResponseResult updateRoleDevAuth(AddRoleReceivePara receivePara);
	
	
	@GET
	@Path("roles/auth")
	@Produces({ "application/json" })
   public ResponseResult findMenuAuth(@QueryParam("id") long id);
	
	@GET
	@Path("roles/devAuth")
	@Produces({ "application/json" })
   public ResponseResult findDevAuth(@QueryParam("id") long id, @QueryParam("devId") String devId);
}
