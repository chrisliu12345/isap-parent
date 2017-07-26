package com.gosun.isap.warn.api.linkage;

/**
 * 告警联动类型定义 (注：类型定义从1~127，其余无效)
 * 
 * @author lucf
 *
 */
public enum LinkageType {

	InvalidLinkage("invalid linkage", "无效联动", (byte) 0, (byte) 0), LiveLinkage("live to pane", "告警联动实况", (byte) 1,
			(byte) 3);

	private final String name;
	private final String description;
	private final byte value;
	private final byte paramNum;

	LinkageType(String name, String description, byte value, byte paramNum) {
		this.name = name;
		this.description = description;
		this.value = value;
		this.paramNum = paramNum;
	}

	public String getName() {
		return this.name;
	}

	public String getDescription() {
		return this.description;
	}

	public byte getValue() {
		return this.value;
	}

	public byte getParamNum() {
		return this.paramNum;
	}
}
