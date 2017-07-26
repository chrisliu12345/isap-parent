package com.gosun.isap.warn.impl.guard.constants;

/**
 * 布防错误码
 * 
 * @author liuzk
 *
 */
public interface GuardErrorCode {
	/**
	 * 时间模板已存在
	 */
	int TIME_TEMPLATE_ALREADY_EXISTS = 9000;
	/**
	 * 布防计划正在运行中
	 */
	int GUARD_PLAN_IS_RUNNING = 9001;
	/**
	 * 时间模板和布防计划存在绑定关系
	 */
	int TIME_TEMPLATE_BIND_GUARD_PLAN = 9002;
	/**
	 * 设备已经配置了布防计划
	 */
	int DEVICE_ALREADY_IN_GUARD = 9003;
	/**
	 * 创建布防任务失败
	 */
	int CREATE_GUARD_JOB_FAIL = 9004;
	/**
	 * 删除布防任务失败
	 */
	int DELETE_GUARD_JOB_FAIL = 9005;
	/**
	 * 启动布防任务失败
	 */
	int START_GUARD_JOB_FAIL = 9006;
	/**
	 * 停止布防任务失败
	 */
	int STOP_GUARD_JOB_FAIL = 9007;
}
