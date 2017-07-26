package com.gosun.isap.user.api;

public class UserConstance {
	public static final int UPDATE_DATA_INVALIDATE = 8001;// 数据校验失败
	public static final int UPDATE_LOGINNAME_NOT_NULL = 8002;// 更新数据不能没有id
	public static final int UPDATE_PASSWORD_NOT_NULL = 8003;// 更新数据不能没有id
	public static final int USER_NOT_FOUND = 8002;// 用户没有找到
	public static final int PASSWORD_NOT_RIGHT = 8003;//密码错误
	public static final int PASSWORD_NOT_MATCH = 8004;//密码不匹配
	public static final int LOGIN_NAME_REPEAT = 8005;//登录名重复
}
