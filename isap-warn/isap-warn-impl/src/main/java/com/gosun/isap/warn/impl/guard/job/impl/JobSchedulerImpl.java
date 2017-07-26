package com.gosun.isap.warn.impl.guard.job.impl;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * job调度服务实现
 * 
 * @author liuzk
 *
 */
public class JobSchedulerImpl implements IJobScheduler {
	private static Logger logger = LoggerFactory.getLogger(JobSchedulerImpl.class);

	@Autowired
	private Scheduler scheduler;

	@Override
	@SuppressWarnings("rawtypes")
	public void addJob(JobData job) throws SchedulerException, ClassNotFoundException {
		if (job == null) {
			throw new IllegalArgumentException("Job is null");
		}
		if (StringUtils.isEmpty(job.getName())) {
			throw new IllegalArgumentException("Job name is null or empty");
		}
		if (StringUtils.isEmpty(job.getGroup())) {
			throw new IllegalArgumentException("Job group is null or empty");
		}
		if (job.getCrons().isEmpty()) {
			throw new IllegalArgumentException("Job cron expression is empty");
		}
		if (StringUtils.isEmpty(job.getBeanClass())) {
			throw new IllegalArgumentException("Job class bean is null or empty");
		}

		logger.info(scheduler + " Create new job, jobInfo " + job);

		String name = job.getName();
		String group = job.getGroup();

		Class cls = Class.forName(job.getBeanClass());
		JobDetail jobDetail = JobBuilder.newJob(cls).withIdentity(name, group).storeDurably().build();

		// 保存任务属性
		jobDetail.getJobDataMap().put(IJobScheduler.JOB_DATA, job);
		scheduler.addJob(jobDetail, true);

		// 创建多个触发器
		for (Map.Entry<String, String> entry : job.getCrons().entrySet()) {
			String cronId = entry.getKey();
			String cronExpr = entry.getValue();

			TriggerKey triggerKey = TriggerKey.triggerKey(cronId + "@" + name, group);
			CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
			if (null == trigger) {
				// 创建cron触发器，过期的立即执行
				CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpr);
				trigger = TriggerBuilder.newTrigger().withIdentity(triggerKey).forJob(jobDetail)
						.withSchedule(scheduleBuilder).build();
				scheduler.scheduleJob(trigger);
			} else {
				// 创建cron触发器，过期的立即执行
				CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpr);
				trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
				scheduler.rescheduleJob(triggerKey, trigger);
			}
		}
	}

	@Override
	public void pauseJob(String name, String group) throws SchedulerException {
		JobKey jobKey = JobKey.jobKey(name, group);
		scheduler.pauseJob(jobKey);
	}

	@Override
	public void resumeJob(String name, String group) throws SchedulerException {
		JobKey jobKey = JobKey.jobKey(name, group);
		scheduler.resumeJob(jobKey);
	}

	@Override
	public void deleteJob(String name, String group) throws SchedulerException {
		JobKey jobKey = JobKey.jobKey(name, group);
		scheduler.deleteJob(jobKey);
	}

	@Override
	public void runAJobNow(String name, String group) throws SchedulerException {
		JobKey jobKey = JobKey.jobKey(name, group);
		scheduler.triggerJob(jobKey);
	}

	@Override
	public JobData getJob(String name, String group) throws SchedulerException {
		JobKey jobKey = JobKey.jobKey(name, group);
		JobDetail jobDetail = scheduler.getJobDetail(jobKey);
		Object obj = jobDetail.getJobDataMap().get(IJobScheduler.JOB_DATA);
		if (obj instanceof JobData) {
			return (JobData) obj;
		} else {
			return null;
		}
	}

	@Override
	public void start() throws SchedulerException {
		if (!scheduler.isStarted()) {
			scheduler.start();
		}
	}
}
