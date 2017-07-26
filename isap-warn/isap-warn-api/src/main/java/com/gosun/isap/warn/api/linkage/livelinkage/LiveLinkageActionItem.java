package com.gosun.isap.warn.api.linkage.livelinkage;

import java.io.Serializable;

/**
 * 实况联动执行单元
 * 
 * @author lucf
 *
 */
public class LiveLinkageActionItem implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long actionID;

	private String cameraCode;

	private String userCode;

	private Integer paneIndex;

	public LiveLinkageActionItem(Long actionID, String cameraCode, String userCode, Integer paneIndex) {
		this.actionID = actionID;
		this.cameraCode = cameraCode;
		this.userCode = userCode;
		this.paneIndex = paneIndex;
	}

	public void setActionID(Long actionID) {
		this.actionID = actionID;
	}

	public Long getActionID() {
		return this.actionID;
	}

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
}
