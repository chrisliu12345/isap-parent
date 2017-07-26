package com.gosun.isap.face.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.gosun.isap.common.ResponseResult;
import com.gosun.isap.face.api.Bean.DepartmentListGroupBean;

/**
 * 角色名单组管理API<br>
 * <p>
 * 创建时间：2017/05/03
 * </p>
 *
 * @author 王栋梁
 * @version 1.0
 */
@Path("blackwhitelist")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
public interface IDepartmentlistGroupApi {
	/**
	 * 增加角色名单组
	 * 
	 * @param departmentListGroupBean
	 *            角色名单组信息
	 * @return 响应数据
	 */
	@POST
	@Path("departmentlistgroup")
	ResponseResult addDepartmentListGroup(DepartmentListGroupBean departmentListGroupBean);

	/**
	 * 删除角色名单组
	 * 
	 * @param departmentListGroupBean
	 *            角色名单组信息
	 * @return 响应数据
	 */
	@DELETE
	@Path("departmentlistgroup")
	ResponseResult delDepartmentListGroup(DepartmentListGroupBean departmentListGroupBean);

	/**
	 * 根据名单组得到部门信息
	 * 
	 * @param listGroupID
	 *            名单组ID
	 * @return 响应数据
	 */

	@GET
	@Path("department")
	ResponseResult getDepartment(@QueryParam("listGroupID") Integer listGroupID);

	/**
	 * 根据部门ID得到名单组信息
	 * 
	 * @param departmentID
	 *            部门ID
	 * @return 响应数据
	 */

	@GET
	@Path("listGroupInfo")
	ResponseResult getListGroup(@QueryParam("departmentID") String departmentID);

	/**
	 * 得到社区列表
	 * 
	 * @return 响应数据
	 */

	@GET
	@Path("departmentList")
	ResponseResult getDepartmentList();

	/**
	 * 得到设备列表
	 * 
	 * @param departmentID
	 *            部门ID
	 * @return 响应数据
	 */

	@GET
	@Path("deviceList")
	ResponseResult getDeviceList(@QueryParam("departmentID") String departmentID);

	/**
	 * 得到设备与人脸服务器列表
	 * 
	 * @param departmentID
	 *            部门ID
	 * @return 响应数据
	 */

	@GET
	@Path("devicefaceserverlist")
	ResponseResult getDeviceFaceServerList(@QueryParam("departmentID") String departmentID);
}
