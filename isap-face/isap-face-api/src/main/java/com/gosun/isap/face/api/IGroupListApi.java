package com.gosun.isap.face.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.gosun.isap.common.ResponseResult;
import com.gosun.isap.face.api.Bean.GroupListBean;

/**
 * 建立名单与名单组的关系<br>
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
public interface IGroupListApi {
	/**
	 * <p>
	 * 将人员名单添加到名单组中
	 * </p>
	 * 
	 * @param groupListBean
	 *            人员与名单组ID
	 * @return 响应数据
	 */
	@POST
	@Path("grouplist")
	ResponseResult addGroupList(GroupListBean groupListBean);

	/**
	 * <p>
	 * 将人员名单从名单组中删除
	 * </p>
	 * 
	 * @param groupListBean
	 *            人员与名单组ID
	 * @return 响应数据
	 */

	@DELETE
	@Path("grouplist")
	ResponseResult delGroupList(GroupListBean groupListBean);

}
