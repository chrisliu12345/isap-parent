package com.gosun.isap.warn.impl.guard.job;

/**
 * 布防任务常量
 * 
 * @author liuzk
 *
 */
public interface GuardJobConstants {
	/**
	 * begintime1
	 */
	String BEGIN_TRIGGER_1 = "beginTime1";
	/**
	 * begintime2
	 */
	String BEGIN_TRIGGER_2 = "beginTime2";
	/**
	 * begintime3
	 */
	String BEGIN_TRIGGER_3 = "beginTime3";
	/**
	 * begintime4
	 */
	String BEGIN_TRIGGER_4 = "beginTime4";

	/**
	 * endtime1
	 */
	String END_TRIGGER_1 = "endTime1";
	/**
	 * endtime2
	 */
	String END_TRIGGER_2 = "endTime2";
	/**
	 * endtime3
	 */
	String END_TRIGGER_3 = "endTime3";
	/**
	 * endtime4
	 */
	String END_TRIGGER_4 = "endTime4";

	/**
	 * 告警使能任务名称
	 */
	String ALARM_ENABLE_JOB_NAME = "alarmEnableJob";
	/**
	 * 告警撤销任务名称
	 */
	String ALARM_DISABLE_JOB_NAME = "alarmDisableJob";
	/**
	 * 告警使能任务bean class
	 */
	String ALARM_ENABLE_JOB_BEAN = "com.gosun.isap.warn.impl.guard.job.AutoAlarmEnableJob";
	/**
	 * 告警撤销任务bean class
	 */
	String ALARM_DISABLE_JOB_BEAN = "com.gosun.isap.warn.impl.guard.job.AutoAlarmDisableJob";

	/**
	 * 布防计划
	 */
	String GUARD_PLAN = "GUARD_PLAN";
}
