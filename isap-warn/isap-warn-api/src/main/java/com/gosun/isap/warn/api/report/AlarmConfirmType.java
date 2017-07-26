package com.gosun.isap.warn.api.report;

public enum AlarmConfirmType {

	UnChecked((byte) 1, "未确认"), Checked((byte) 2, "已确认");

	private byte value;
	private String description;

	AlarmConfirmType(byte value, String description) {
		this.value = value;
		this.description = description;
	}

	public byte getValue() {
		return this.value;
	}

	public String getDescription() {
		return this.description;
	}
}
