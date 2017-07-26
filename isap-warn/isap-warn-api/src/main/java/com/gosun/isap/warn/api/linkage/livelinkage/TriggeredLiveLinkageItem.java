package com.gosun.isap.warn.api.linkage.livelinkage;

import java.io.Serializable;

/**
 * 触发的实况联动项目
 * 
 * @author lucf
 *
 */
public class TriggeredLiveLinkageItem implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 相机编码
	 */
	private String cameraCode;

	/**
	 * 窗格编号
	 */
	private Integer paneIndex;

	public TriggeredLiveLinkageItem() {
	};

	public TriggeredLiveLinkageItem(String cameraCode, Integer paneIndex) {
		this.cameraCode = cameraCode;
		this.paneIndex = paneIndex;
	}

	public String getCameraCode() {
		return cameraCode;
	}

	public void setCameraCode(String cameraCode) {
		this.cameraCode = cameraCode;
	}

	public Integer getPaneIndex() {
		return paneIndex;
	}

	public void setPaneIndex(Integer paneIndex) {
		this.paneIndex = paneIndex;
	}
}
