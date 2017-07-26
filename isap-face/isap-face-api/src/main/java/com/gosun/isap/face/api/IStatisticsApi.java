package com.gosun.isap.face.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.gosun.isap.common.ResponseResult;
import com.gosun.isap.dao.po.face.AlarmInfoParaBean;
import com.gosun.isap.face.api.Bean.AlarmValue;

/**
 * 数据统计API<br>
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
public interface IStatisticsApi {
	/**
	 * <p>
	 * 名单数据统计
	 * </p>
	 * 
	 * @param listType
	 *            名单类型
	 * @param statisticsType
	 *            统计类型
	 * @param startTime
	 *            开始时间（可空）
	 * @param endTime
	 *            结束时间（可空）
	 * @param start
	 *            页索引
	 * @param limit
	 *            一页总数
	 * @return 响应数据
	 */
	@GET
	@Path("liststatistics")
	ResponseResult listStatistics(@QueryParam("listType") Integer listType,
			@QueryParam("statisticsType") Integer statisticsType, @QueryParam("startTime") String startTime,
			@QueryParam("endTime") String endTime, @QueryParam("start") Integer start,
			@QueryParam("limit") Integer limit);

	/**
	 * <p>
	 * 告警数据统计
	 * </p>
	 * 
	 * @param alarmInfoParaBean
	 *            统计条件
	 * @return 响应数据
	 */
	@POST
	@Path("alarmstatistics")
	ResponseResult alarmStatistics(AlarmInfoParaBean alarmInfoParaBean);

	/**
	 * 根据名单ID得到告警具体信息
	 * 
	 * @param alarmInfoParaBean
	 *            检索条件
	 * @return 响应数据
	 */
	@POST
	@Path("alarminfo")
	ResponseResult getAlarmInfo(AlarmInfoParaBean alarmInfoParaBean);

	/**
	 * <p>
	 * 编辑阈值
	 * </p>
	 * 
	 * @param alarmValue
	 *            阈值（告警相似度）
	 * @return 响应数据
	 */
	@PUT
	@Path("alarmvalue")
	ResponseResult updAlarmValue(AlarmValue alarmValue);

	/**
	 * <p>
	 * 取得阈值
	 * </p>
	 * 
	 * @return 响应数据
	 */
	@GET
	@Path("alarmvalue")
	ResponseResult getAlarmValue();

	/**
	 * <p>
	 * 得到FTP服务器信息
	 * </p>
	 * 
	 * @return 响应数据
	 */
	/* List<FtpConfigBean> getFtpConfig(); */
}
