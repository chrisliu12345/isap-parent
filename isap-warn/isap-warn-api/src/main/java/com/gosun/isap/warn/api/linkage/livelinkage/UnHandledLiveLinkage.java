package com.gosun.isap.warn.api.linkage.livelinkage;

import java.util.Date;

/**
 * 未处理的告警联动实况
 * 
 * @author lucf
 *
 */
public class UnHandledLiveLinkage {

	/**
	 * 执行联动的相机编码
	 */
	private String cameraCode;

	/**
	 * 执行联动的用户编码
	 */
	private String userCode;

	/**
	 * 实况播放窗格索引
	 */
	private Integer paneIndex;

	/**
	 * 产生联动的时间
	 */
	private Date occuredTime;

	/**
	 * 联动实况的有效时长
	 */
	private int validTime;

	public void setCameraCode(String cameraCode) {
		this.cameraCode = cameraCode;
	}

	public String getCameraCode() {
		return this.cameraCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserCode() {
		return this.userCode;
	}

	public void setPaneIndex(Integer paneIndex) {
		this.paneIndex = paneIndex;
	}

	public Integer getPaneIndex() {
		return this.paneIndex;
	}

	public void setOccuredTime(Date occuredTime) {
		this.occuredTime = occuredTime;
	}

	public Date getOccuredTime() {
		return this.occuredTime;
	}

	public void setValidTime(int validTime) {
		this.validTime = validTime;
	}

	public int getValidTime() {
		return this.validTime;
	}
}
