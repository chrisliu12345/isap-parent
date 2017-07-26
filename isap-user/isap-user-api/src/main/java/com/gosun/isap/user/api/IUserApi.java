package com.gosun.isap.user.api;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.gosun.isap.common.Idwrapper;
import com.gosun.isap.common.IdwrapperBat;
import com.gosun.isap.common.ResponseResult;

@Path("user")
public interface IUserApi {

	@POST
	@Path("users")
	@Produces({ "application/json" })
	ResponseResult updateUser(UserWrapper userWrapper);

	@PUT
	@Path("users")
	@Produces({ "application/json" })
	ResponseResult addUser(UserWrapper userWrapper);

	@DELETE
	@Path("users")
	@Produces({ "application/json" })
	ResponseResult deleteUser(IdwrapperBat<Integer> ids);

	@GET
	@Path("users/collection")
	@Produces({ "application/json" })
	ResponseResult getUserPage(@QueryParam("fuzzyFiled") String fuzzyFiled, @QueryParam("fuzzyValue") String fuzzyValue,
			@QueryParam("start") int start, @QueryParam("limit") int limit, @QueryParam("sort") String sort);

	@POST
	@Path("passwords")
	@Produces({ "application/json" })
	ResponseResult validateUserOldPassword(UserWrapper userWrapper);

}
