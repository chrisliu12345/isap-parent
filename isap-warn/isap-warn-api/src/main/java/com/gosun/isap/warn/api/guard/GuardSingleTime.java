package com.gosun.isap.warn.api.guard;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class GuardSingleTime implements Serializable {

	private Long templateId;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date beginTime1;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date endTime1;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date beginTime2;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date endTime2;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date beginTime3;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date endTime3;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date beginTime4;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date endTime4;

	private static final long serialVersionUID = 1L;

	public Long getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Long templateId) {
		this.templateId = templateId;
	}

	public Date getBeginTime1() {
		return beginTime1;
	}

	public void setBeginTime1(Date beginTime1) {
		this.beginTime1 = beginTime1;
	}

	public Date getEndTime1() {
		return endTime1;
	}

	public void setEndTime1(Date endTime1) {
		this.endTime1 = endTime1;
	}

	public Date getBeginTime2() {
		return beginTime2;
	}

	public void setBeginTime2(Date beginTime2) {
		this.beginTime2 = beginTime2;
	}

	public Date getEndTime2() {
		return endTime2;
	}

	public void setEndTime2(Date endTime2) {
		this.endTime2 = endTime2;
	}

	public Date getBeginTime3() {
		return beginTime3;
	}

	public void setBeginTime3(Date beginTime3) {
		this.beginTime3 = beginTime3;
	}

	public Date getEndTime3() {
		return endTime3;
	}

	public void setEndTime3(Date endTime3) {
		this.endTime3 = endTime3;
	}

	public Date getBeginTime4() {
		return beginTime4;
	}

	public void setBeginTime4(Date beginTime4) {
		this.beginTime4 = beginTime4;
	}

	public Date getEndTime4() {
		return endTime4;
	}

	public void setEndTime4(Date endTime4) {
		this.endTime4 = endTime4;
	}
}
