package com.gosun.isap.operlog.api;

public enum QueryType {
	USER_NAME(1),
	/** 操作用户 */
	OPERATE_TYPE(2),
	/** 操作类型 */
	OPERATE_SERVICE_TYPE(3),
	/** 服务类型 */
	OPERATE_TIME(4),
	/** 操作时间 */
	OPERATE_IP_ADDRESS(5),
	/** 操作者IP地址 */
	OPERATE_OBJECT(6),
	/** 操作对象 */
	OPERATE_RESULT(7),
	/** 操作结果 */
	OPERATE_DESCRIPTION(8);
	/** 操作描述 */
	int type;

	QueryType(int type) {
		this.type = type;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}
