package com.gosun.isap.face.api.Bean;
/**
 * 信息统计条件<br>
 * <p>创建时间：2017/05/02</p>
 *
 * @author 王栋梁
 * @version 1.0
 */
public class TatisticsParaBean {

	private Integer listType;					//名单类型(1.黑名单 2.白名单)
	private Integer statisticsType;		//统计类型(1.总人数 2.新增人数 3.未出现人数)
	private String startTime;				//开始时间
	private String endTime;					//结束时间
	private Integer start;						//页索引
	private Integer limit;						//一页总数
	
	public Integer getListType() {
		return listType;
	}
	public void setListType(Integer listType) {
		this.listType = listType;
	}
	public Integer getStatisticsType() {
		return statisticsType;
	}
	public void setStatisticsType(Integer statisticsType) {
		this.statisticsType = statisticsType;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
}
