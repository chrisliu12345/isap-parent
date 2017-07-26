package com.gosun.isap.authority.api;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.gosun.isap.authority.api.requestbody.LoginEntity;
import com.gosun.isap.authority.api.requestbody.TempAuthAddWapper;
import com.gosun.isap.authority.api.responsebody.LoginBody;
import com.gosun.isap.common.IdwrapperBat;
import com.gosun.isap.common.ResponseResult;
import com.gosun.isap.dao.po.TMenuAuthdef;

@Path("authorization")
public interface IAuthorityApi {
	@POST
	@Path("login")
	@Produces(MediaType.APPLICATION_JSON)
	ResponseResult<LoginBody> login(LoginEntity loginEntity, @Context HttpServletRequest request,
			@Context ServletContext context);

	@GET
	@Path("menuAuths/collection")
	@Produces(MediaType.APPLICATION_JSON)
	ResponseResult<List<TMenuAuthdef>> menuAuthList(@QueryParam("parentId") int parentId);

	@GET
	@Path("deviceAuths/collection")
	@Produces(MediaType.APPLICATION_JSON)
	ResponseResult deviceAuthList(int deviceType);

	@PUT
	@Path("tempAuths")
	@Produces(MediaType.APPLICATION_JSON)
	ResponseResult tempAuthAdd(TempAuthAddWapper tempAuthAddWapper);

	@POST
	@Path("tempAuths")
	@Produces(MediaType.APPLICATION_JSON)
	ResponseResult tempAuthUpdate(TempAuthAddWapper tempAuthAddWapper);

	@DELETE
	@Path("tempAuths")
	@Produces(MediaType.APPLICATION_JSON)
	ResponseResult tempAuthDelete(IdwrapperBat<Integer> ids);

	@POST
	@Path("logout")
	@Produces(MediaType.APPLICATION_JSON)
	ResponseResult logout();
}
