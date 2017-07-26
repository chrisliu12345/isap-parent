package com.gosun.isap.authority.api.requestbody;

import java.util.Date;

import javax.validation.constraints.NotNull;

/**
 * 临时权限增加修改入参封装
 * 
 * @author lyf
 *
 */
public class TempAuthAddWapper {
	private int id;
	private int devId;
	private int authId;
	private Date endDate;// 截止时间
	private String userId;

	@NotNull
	public int getAuthId() {
		return authId;
	}

	public void setAuthId(int authId) {
		this.authId = authId;
	}

	@NotNull
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@NotNull(message = "userId不能为null")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@NotNull(message = "devId不能为null")
	public int getDevId() {
		return devId;
	}

	public void setDevId(int devId) {
		this.devId = devId;
	}

}
