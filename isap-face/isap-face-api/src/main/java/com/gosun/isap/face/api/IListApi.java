package com.gosun.isap.face.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.gosun.isap.common.ResponseResult;
import com.gosun.isap.face.api.Bean.ManInfoBean;

/**
 * 人员名单管理API<br>
 * <p>
 * 对名单进行增删改查的操作
 * </p>
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
public interface IListApi {

	/**
	 * 删除人员信息
	 * 
	 * @param manInfoBean
	 *            人员信息
	 * @return 响应数据
	 */
	@DELETE
	@Path("list")
	ResponseResult delList(ManInfoBean manInfoBean);

	/**
	 * 更新人员信息
	 * 
	 * @param manInfoBean
	 *            人员信息
	 * @return 响应数据
	 */
	@PUT
	@Path("list")
	ResponseResult updList(ManInfoBean manInfoBean);

	/**
	 * 得到人员信息
	 * 
	 * @param manInfoBean
	 *            人员信息
	 * @return 响应数据
	 */

	@POST
	@Path("getlist")
	ResponseResult getList(ManInfoBean manInfoBean);

	/**
	 * 根据名单组ID得到所属人员信息
	 * 
	 * @param listGroupID
	 *            名单组ID
	 * @param start
	 *            页索引
	 * @param limit
	 *            一页总数
	 * @return 响应数据
	 */

	@GET
	@Path("listbylistgroupID")
	ResponseResult getList(@QueryParam("listGroupID") Integer listGroupID, @QueryParam("start") Integer start,
			@QueryParam("limit") Integer limit);

	/**
	 * 身份证认证
	 * 
	 * @param idCode身份证号
	 * @return
	 */
	public boolean idCodeAuthentication(String idCode);

	/**
	 * 数据认证
	 * 
	 * @param name
	 *            名字
	 * @param reason
	 *            理由
	 * @return
	 */
	public int stringCodeAuthentication(String name, String reason);
}
