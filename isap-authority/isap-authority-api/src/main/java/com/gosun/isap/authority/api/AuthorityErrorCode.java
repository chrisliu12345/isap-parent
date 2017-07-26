package com.gosun.isap.authority.api;

public class AuthorityErrorCode {
	public static final Integer LOGIN_NAME_OR_PASSWORD_OR_IP_NULL = 1001;// 登陆名或者密码为空
	public static final Integer LOGIN_NAME_ERR = 1002;// 登陆名未找到
	public static final Integer USER_LOCKED = 1003;// 用户锁定
	public static final Integer PASSWORD_ERR = 1004;// 密码错误
	public static final Integer DATA_VALIDATE_ERR = 1005; // 数据校验有误
	public static final Integer SESSION_TIME_OUT = 1006; // session过期
	public static final Integer IP_NOT_NULL = 1007; // session过期
	public static final Integer ONLY_ADMIN_CAN_ACCESS=1008;//只有管理员才能访问该资源
	public static final Integer SERVER_NOT_START_SUCCESS = 1009;// 服务器还没有启动成功
}
