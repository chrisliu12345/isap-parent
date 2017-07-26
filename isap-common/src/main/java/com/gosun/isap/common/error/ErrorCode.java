package com.gosun.isap.common.error;

/**
 * 错误码
 * 
 * @author liuzk
 *
 */
public interface ErrorCode {
	/**
	 * 成功
	 */
	int ERR_OK = 0;
	/**
	 * 未知错误
	 */
	int ERR_UNKNOWN = 1;
	/**
	 * 系统错误
	 */
	int ERR_SYSTEM = 2;
	/**
	 * 非法参数错误
	 */
	int ERR_ILLEGAL_ARGUMENT = 3;
	/**
	 * 数据库记录不存在
	 */
	int ERR_RECORD_NOT_EXIST = 4;
	/**
	 * 数据库操作失败
	 */
	int ERR_DB_OPERATE_FAIL = 5;
	/**
	 * 权限 不足
	 */
	int ERR_AUTHORITY = 6;
	/**
	 * 调用本地sdk失败
	 */
	int ERR_CALL_NATIVE_SDK = 7;
	/**
	 * 接入网关状态异常
	 */
	int ERR_PROXY_MANAGER_STATE_EXCEPTION = 8;
}
