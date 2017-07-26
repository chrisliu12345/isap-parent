package com.gosun.isap.proxy.api.sdk.constants;

public enum PlatformType {
	/**
	 * 海康
	 */
	HIKVISION(0),
	/**
	 * 大华
	 */
	DAHUA(1),
	/**
	 * 柯达
	 */
	KEDA(2),
	/**
	 * 宇视
	 */
	UNIVIEW(3);

	private int type;

	PlatformType(int type) {
		this.type = type;
	}

	public int getType() {
		return type;
	}

	/**
	 * 枚举和数字转换
	 * 
	 * @param type
	 *            平台类型
	 * @return 凭他类型枚举
	 */
	public static PlatformType valueOf(int type) {
		switch (type) {
		case 0:
			return HIKVISION;
		case 1:
			return DAHUA;
		case 2:
			return KEDA;
		case 3:
			return UNIVIEW;
		default:
			return DAHUA;
		}
	}

	/**
	 * 转成string类型
	 */
	public String toString() {
		switch (type) {
		case 0:
			return "hikvision";
		case 1:
			return "dahua";
		case 2:
			return "keda";
		case 3:
			return "uniview";
		default:
			return "";
		}
	}
}
