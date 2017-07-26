package com.gosun.isap.face.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.gosun.isap.common.ResponseResult;

/**
 * 抓拍人脸相关的反馈操作<br>
 *     <p>名单对比的误报</p>
 *     <p>根据检索条件查询抓拍人脸</p>
 * <p>创建时间：2017/05/03</p>
 *
 * @author 王栋梁
 * @version 1.0
 */
@Path("blackwhitelist")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
public interface ICallBack {

	/**
	 * <p>
	 * 抓拍人脸的记录
	 * </p>
	 * 
	 * @param deviceID 设备ID
	 * @return 响应数据
	 */
	@GET
	@Path("faceadd")
	ResponseResult faceAdd(@QueryParam("deviceID")String deviceID);

	/**
	 * <p>
	 * 告警上报
	 * </p>
	 * 
	 * @param start
	 *            页索引
	 * @param limit
	 *            一页总数
	 * @return 响应数据
	 */
	@GET
	@Path("alarmadd")
	ResponseResult alarmAdd(@QueryParam("start")Integer start,@QueryParam("limit")Integer limit);
}
