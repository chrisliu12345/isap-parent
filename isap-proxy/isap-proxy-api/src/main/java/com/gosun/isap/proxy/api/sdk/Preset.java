package com.gosun.isap.proxy.api.sdk;

import java.io.Serializable;

/**
 * 预置位
 * 
 * @author liuzk
 *
 */
public class Preset implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 预置位编号id
	 */
	public int presetId;
	/**
	 * 预置位名称
	 */
	public String name;

	Preset(int presetId, String presetName) {
		this.presetId = presetId;
		this.name = presetName;
	}

	public int getPresetId() {
		return presetId;
	}

	public void setPresetId(int presetId) {
		this.presetId = presetId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
