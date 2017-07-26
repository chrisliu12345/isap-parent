package com.gosun.isap.face.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.gosun.isap.common.ResponseResult;
import com.gosun.isap.dao.po.face.ListFilterParaBean;
import com.gosun.isap.face.api.Bean.CapFaceParaBean;
import com.gosun.isap.face.api.Bean.ListFilterInfoBean;
/**
 * 抓拍人脸相关的操作<br>
 *     <p>名单对比的误报</p>
 *     <p>根据检索条件查询抓拍人脸</p>
 *     <p>根据照片信息得到特征值</p>
 * <p>创建时间：2017/05/03</p>
 *
 * @author 王栋梁
 * @version 1.0
 */

@Path("blackwhitelist")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
public interface ICapFaceApi {
	/**
	 * <p>
	 * 名单对比的误报
	 * </p>
	 * 
	 * @param listFilterInfoBean 误报信息
	 * @return 响应数据
	 */
	@POST
	@Path("listfilter")
	ResponseResult listFilter(ListFilterInfoBean listFilterInfoBean);

	/**
	 * <p>
	 * 误报恢复
	 * </p>
	 * 
	 * @param listFilterInfoBean 误报信息
	 * @return 响应数据
	 */

	@DELETE
	@Path("listfilter")
	ResponseResult listFilterRecovery(ListFilterInfoBean listFilterInfoBean);
	
	/**
	 * <p>
	 * 误报查询
	 * </p>
	 * 
	 * @param listFilterParaBean 误报信息
	 * @return 响应数据
	 */

	@POST
	@Path("getlistfilter")
	ResponseResult getListFilter(ListFilterParaBean listFilterParaBean);
	
	/**
	 * <p>
	 * 根据检索条件查询抓拍人脸
	 * </p>
	 * 
	 * @param capFaceParaBean
	 *            抓拍人脸的检索条件信息
	 * @return 响应数据
	 */
	@POST
	@Path("getcapface")
	ResponseResult getCapFace(CapFaceParaBean capFaceParaBean);

}
