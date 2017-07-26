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
import com.gosun.isap.face.api.Bean.ListGroupBean;

/**
 * 名单组管理API<br>
 * <p>
 * 对名单组进行增删改查的操作
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
public interface IListGroupApi {
	/**
	 * 增加名单组
	 * 
	 * @param listGroupBean
	 *            名单组信息
	 * @return 响应数据
	 */
	@POST
	@Path("listgroup")
	ResponseResult addListGroup(ListGroupBean listGroupBean);

	/**
	 * 删除名单组
	 * 
	 * @param listGroupBean
	 *            名单组信息
	 * @return 响应数据
	 */

	@DELETE
	@Path("listgroup")
	ResponseResult delListGroup(ListGroupBean listGroupBean);

	/**
	 * 更新名单组
	 * 
	 * @param listGroupBean
	 *            名单组信息
	 * @return 响应数据
	 */

	@PUT
	@Path("listgroup")
	ResponseResult updListGroup(ListGroupBean listGroupBean);

	/**
	 * 得到名单组
	 * 
	 * @param start
	 *            页索引
	 * @param limit
	 *            一页总数
	 * @return 响应数据
	 */

	@GET
	@Path("listgroup")
	ResponseResult getListGroup(@QueryParam("start") Integer start, @QueryParam("limit") Integer limit);

	/**
	 * 根据人员ID得到所属名单组
	 * 
	 * @param listID
	 *            人员ID
	 * @return 响应数据
	 */

	@GET
	@Path("listgroupbylistID")
	ResponseResult getListGroup(@QueryParam("listID") Integer listID);
}
