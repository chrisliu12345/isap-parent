package com.gosun.isap.warn.impl.guard.job.impl;

import org.quartz.SchedulerException;

/**
 * 任务调度服务
 * 
 * @author liuzk
 *
 */
public interface IJobScheduler {
	/**
	 * job data
	 */
	String JOB_DATA = "jobData";

	/**
	 * 增加一个任务
	 * 
	 * @param job
	 *            任务dto
	 * @throws SchedulerException
	 *             调度异常
	 * @throws ClassNotFoundException
	 *             class没有找到
	 */
	void addJob(JobData job) throws SchedulerException, ClassNotFoundException;

	/**
	 * 获取job
	 * 
	 * @param name
	 *            名称
	 * @param group
	 *            组
	 * @return job dto
	 * @throws SchedulerException
	 *             调度异常
	 */
	JobData getJob(String name, String group) throws SchedulerException;

	/**
	 * 暂停job
	 * 
	 * @param name
	 *            任务名称
	 * @param group
	 *            组
	 * @throws SchedulerException
	 *             調度異常
	 */
	void pauseJob(String name, String group) throws SchedulerException;

	/**
	 * 恢复job
	 * 
	 * @param name
	 *            名称
	 * @param group
	 *            组
	 * @throws SchedulerException
	 *             调度异常
	 */
	void resumeJob(String name, String group) throws SchedulerException;

	/**
	 * 删除job
	 * 
	 * @param name
	 *            名称
	 * @param group
	 *            组
	 * @throws SchedulerException
	 *             调度异常
	 */
	void deleteJob(String name, String group) throws SchedulerException;

	/**
	 * 运行一个任务
	 * 
	 * @param name
	 *            名称
	 * @param group
	 *            组
	 * @throws SchedulerException
	 *             调度异常
	 */
	void runAJobNow(String name, String group) throws SchedulerException;

	/**
	 * 启动
	 * 
	 * @throws SchedulerException
	 *             调度异常
	 */
	void start() throws SchedulerException;
}
