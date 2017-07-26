package com.gosun.isap.warn.impl.guard.job;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.container.spring.SpringContainer;
import com.gosun.isap.common.utils.DateConvUtils;
import com.gosun.isap.dao.po.TGuardPlan;
import com.gosun.isap.dao.po.TGuardSingleTime;
import com.gosun.isap.dao.po.TGuardTimeTemplate;
import com.gosun.isap.dao.po.TGuardWeekTime;
import com.gosun.isap.warn.api.service.guard.TGuardTimeTemplateService;
import com.gosun.isap.warn.impl.guard.constants.TimeTemplateType;
import com.gosun.isap.warn.impl.guard.job.impl.JobData;
import com.gosun.isap.warn.impl.guard.job.impl.IJobScheduler;

/**
 * 自动布防工具类
 * 
 * @author liuzk
 *
 */
public class AutoGuardJobHelper {
	private static Logger logger = LoggerFactory.getLogger(AutoGuardJobHelper.class);

	enum JobOperateType {
		CREATE, // 创建任务
		PAUSE, // 暂停任务
		RESUME, // 恢复任务
		REMOVE, // 移除任务
		TRIGGER; // 手动触发任务
	}

	/**
	 * 创建布防计划
	 * 
	 * @param plan
	 *            布防计划
	 * @throws Exception
	 *             异常
	 */
	public static void createGuardJob(TGuardPlan plan) {
		logger.info("Create guard job for plan, planId " + plan.getId());
		controlEnableGuardJob(JobOperateType.CREATE, plan);
		controlDisableGuardJob(JobOperateType.CREATE, plan);
	}

	/**
	 * 删除布防计划
	 * 
	 * @param plan
	 *            布防计划
	 * @throws Exception
	 *             异常
	 */
	public static void deleteGuardJob(TGuardPlan plan) {
		logger.info("Delete guard job for plan, planId " + plan.getId());
		controlEnableGuardJob(JobOperateType.REMOVE, plan);
		controlDisableGuardJob(JobOperateType.REMOVE, plan);
	}

	/**
	 * 暂停布防计划
	 * 
	 * @param plan
	 *            布防计划
	 * @throws Exception
	 *             异常
	 */
	public static void pauseGuardJob(TGuardPlan plan) {
		logger.info("Pause guard job for plan, planId " + plan.getId());
		controlEnableGuardJob(JobOperateType.PAUSE, plan);
		controlDisableGuardJob(JobOperateType.PAUSE, plan);
	}

	/**
	 * 恢复布防任务
	 * 
	 * @param plan
	 *            布防计划
	 * @throws Exception
	 *             异常
	 */
	public static void resumeGuardJob(TGuardPlan plan) {
		logger.info("Resume guard job for plan, planId " + plan.getId());
		controlEnableGuardJob(JobOperateType.RESUME, plan);
		controlDisableGuardJob(JobOperateType.RESUME, plan);
	}

	/**
	 * 手动使能布防
	 * 
	 * @param plan
	 *            布防计划
	 * @throws Exception
	 *             异常
	 */
	public static void manualEnableGuard(TGuardPlan plan) {
		logger.info("Manual run <enableGuard> job for plan " + plan.getId() + " immediately");
		controlEnableGuardJob(JobOperateType.TRIGGER, plan);
	}

	/**
	 * 手动撤销布防
	 * 
	 * @param plan
	 *            布防计划
	 * @throws Exception
	 *             异常
	 */
	public static void manualDisableGuard(TGuardPlan plan) {
		logger.info("Manual run <disableGuard> job for plan " + plan.getId() + " immediately");
		controlDisableGuardJob(JobOperateType.TRIGGER, plan);
	}

	/**
	 * 控制布防任务
	 * 
	 * @param operType
	 *            操作类型
	 * @param plan
	 *            计划
	 * @throws Exception
	 */
	private static void controlEnableGuardJob(JobOperateType operType, TGuardPlan plan) {
		IJobScheduler scheduler = SpringContainer.getContext().getBean(IJobScheduler.class);

		String group = plan.getId().toString();
		String name = (GuardJobConstants.ALARM_ENABLE_JOB_NAME);

		try {
			switch (operType) {
			case CREATE: {
				Map<String, String> crons = new HashMap<String, String>();
				TGuardTimeTemplateService daoSrv = SpringContainer.getContext()
						.getBean(TGuardTimeTemplateService.class);

				TGuardTimeTemplate template = daoSrv.getTemplate(plan.getTemplateId());
				if (TimeTemplateType.SINGLE == template.getTemplateType()) {
					TGuardSingleTime time = daoSrv.getSingleTime(template.getId());

					Date time1 = time.getBeginTime1();
					Date time2 = time.getBeginTime2();
					Date time3 = time.getBeginTime3();
					Date time4 = time.getBeginTime4();

					// 获取5秒以后的时间
					// 如果配置的时间小于当前系统时间，quartz任务不会被调度，所以，这里比较一下时间，对于过期的时间，设置为当前时间，以便任务立即执行
					// 这里设置5秒后再执行
					Calendar nowTime = Calendar.getInstance();
					nowTime.add(Calendar.SECOND, 5);
					if (time1 != null) {
						Date jobTime = time1.before(nowTime.getTime()) ? nowTime.getTime() : time1;
						String cron = CronExpressionBuilder.build(DateConvUtils.convUDateToLocalDateTime(jobTime));
						crons.put(GuardJobConstants.BEGIN_TRIGGER_1, cron);
					}
					if (time2 != null) {
						Date jobTime = time2.before(nowTime.getTime()) ? nowTime.getTime() : time2;
						String cron = CronExpressionBuilder.build(DateConvUtils.convUDateToLocalDateTime(jobTime));
						crons.put(GuardJobConstants.BEGIN_TRIGGER_2, cron);
					}
					if (time3 != null) {
						Date jobTime = time3.before(nowTime.getTime()) ? nowTime.getTime() : time3;
						String cron3 = CronExpressionBuilder.build(DateConvUtils.convUDateToLocalDateTime(jobTime));
						crons.put(GuardJobConstants.BEGIN_TRIGGER_3, cron3);
					}
					if (time4 != null) {
						Date jobTime = time4.before(nowTime.getTime()) ? nowTime.getTime() : time4;
						String cron4 = CronExpressionBuilder.build(DateConvUtils.convUDateToLocalDateTime(jobTime));
						crons.put(GuardJobConstants.BEGIN_TRIGGER_4, cron4);
					}
				} else {
					TGuardWeekTime time = daoSrv.getWeekTime(template.getId());

					Date time1 = time.getBeginTime1();
					Date time2 = time.getBeginTime2();
					Date time3 = time.getBeginTime3();
					Date time4 = time.getBeginTime4();

					if (time1 != null) {
						String cron = CronExpressionBuilder
								.build(DateConvUtils.convUDateToLocalTime(time.getBeginTime1()), time.getWeekdays());
						crons.put(GuardJobConstants.BEGIN_TRIGGER_1, cron);
					}
					if (time2 != null) {
						String cron = CronExpressionBuilder
								.build(DateConvUtils.convUDateToLocalTime(time.getBeginTime2()), time.getWeekdays());
						crons.put(GuardJobConstants.BEGIN_TRIGGER_2, cron);
					}
					if (time3 != null) {
						String cron = CronExpressionBuilder
								.build(DateConvUtils.convUDateToLocalTime(time.getBeginTime3()), time.getWeekdays());
						crons.put(GuardJobConstants.BEGIN_TRIGGER_3, cron);
					}
					if (time4 != null) {
						String cron = CronExpressionBuilder
								.build(DateConvUtils.convUDateToLocalTime(time.getBeginTime4()), time.getWeekdays());
						crons.put(GuardJobConstants.BEGIN_TRIGGER_4, cron);
					}
				}

				JobData job = new JobData();
				job.setGroup(group);
				job.setName(name);
				job.setBeanClass(GuardJobConstants.ALARM_ENABLE_JOB_BEAN);
				job.setDescription("告警布防调度任务");
				for (Map.Entry<String, String> entry : crons.entrySet()) {
					logger.info("Trigger:" + entry.getKey() + ", cron:" + entry.getValue());
					job.getCrons().put(entry.getKey(), entry.getValue());
				}

				// 保存任务对应的计划
				job.getData().put(GuardJobConstants.GUARD_PLAN, plan);

				scheduler.addJob(job);
				break;
			}
			case PAUSE: {
				scheduler.pauseJob(name, group);
				break;
			}
			case RESUME: {
				scheduler.resumeJob(name, group);
				break;
			}
			case REMOVE: {
				scheduler.deleteJob(name, group);
				break;
			}
			case TRIGGER: {
				scheduler.runAJobNow(name, group);
				break;
			}
			}
		} catch (ClassNotFoundException e) {
			logger.error("Job class not found " + e.getMessage());
			throw new RuntimeException(e);
		} catch (SchedulerException e) {
			logger.warn("Job scheduler error " + e.getMessage());
		}
	}

	/**
	 * 控制撤防任务
	 * 
	 * @param operType
	 *            操作类型
	 * @param plan
	 *            计划
	 * @throws Exception
	 */
	public static void controlDisableGuardJob(JobOperateType operType, TGuardPlan plan) {
		IJobScheduler scheduler = SpringContainer.getContext().getBean(IJobScheduler.class);

		String group = plan.getId().toString();
		String name = (GuardJobConstants.ALARM_DISABLE_JOB_NAME);

		try {
			switch (operType) {
			case CREATE: {
				Map<String, String> crons = new HashMap<String, String>();
				TGuardTimeTemplateService daoSrv = SpringContainer.getContext()
						.getBean(TGuardTimeTemplateService.class);

				TGuardTimeTemplate template = daoSrv.getTemplate(plan.getTemplateId());
				if (TimeTemplateType.SINGLE == template.getTemplateType()) {
					TGuardSingleTime time = daoSrv.getSingleTime(template.getId());

					Date time1 = time.getEndTime1();
					Date time2 = time.getEndTime2();
					Date time3 = time.getEndTime3();
					Date time4 = time.getEndTime4();

					// 获取5秒以后的时间
					// 如果配置的时间小于当前系统时间，quartz任务不会被调度，所以，这里比较一下时间，对于过期的时间，设置为当前时间，以便任务立即执行
					// 这里设置5秒后再执行
					Calendar nowTime = Calendar.getInstance();
					nowTime.add(Calendar.SECOND, 5);
					if (time1 != null) {
						Date jobTime = time1.before(nowTime.getTime()) ? nowTime.getTime() : time1;
						String cron = CronExpressionBuilder.build(DateConvUtils.convUDateToLocalDateTime(jobTime));
						crons.put(GuardJobConstants.END_TRIGGER_1, cron);
					}
					if (time2 != null) {
						Date jobTime = time2.before(nowTime.getTime()) ? nowTime.getTime() : time2;
						String cron = CronExpressionBuilder.build(DateConvUtils.convUDateToLocalDateTime(jobTime));
						crons.put(GuardJobConstants.END_TRIGGER_2, cron);
					}
					if (time3 != null) {
						Date jobTime = time3.before(nowTime.getTime()) ? nowTime.getTime() : time3;
						String cron = CronExpressionBuilder.build(DateConvUtils.convUDateToLocalDateTime(jobTime));
						crons.put(GuardJobConstants.END_TRIGGER_3, cron);
					}
					if (time4 != null) {
						Date jobTime = time4.before(nowTime.getTime()) ? nowTime.getTime() : time4;
						String cron = CronExpressionBuilder.build(DateConvUtils.convUDateToLocalDateTime(jobTime));
						crons.put(GuardJobConstants.END_TRIGGER_4, cron);
					}
				} else {
					TGuardWeekTime time = daoSrv.getWeekTime(template.getId());

					Date time1 = time.getEndTime1();
					Date time2 = time.getEndTime2();
					Date time3 = time.getEndTime3();
					Date time4 = time.getEndTime4();

					// 获取5秒以后的时间
					// 如果配置的时间小于当前系统时间，quartz任务不会被调度，所以，这里比较一下时间，对于过期的时间，设置为当前时间，以便任务立即执行
					// 这里设置5秒后再执行
					Calendar nowTime = Calendar.getInstance();
					nowTime.add(Calendar.SECOND, 5);
					if (time1 != null) {
						String cron = CronExpressionBuilder.build(DateConvUtils.convUDateToLocalTime(time1),
								time.getWeekdays());
						crons.put(GuardJobConstants.END_TRIGGER_1, cron);
					}
					if (time2 != null) {
						String cron = CronExpressionBuilder.build(DateConvUtils.convUDateToLocalTime(time2),
								time.getWeekdays());
						crons.put(GuardJobConstants.END_TRIGGER_2, cron);
					}
					if (time3 != null) {
						String cron = CronExpressionBuilder.build(DateConvUtils.convUDateToLocalTime(time3),
								time.getWeekdays());
						crons.put(GuardJobConstants.END_TRIGGER_3, cron);
					}
					if (time4 != null) {
						String cron = CronExpressionBuilder.build(DateConvUtils.convUDateToLocalTime(time4),
								time.getWeekdays());
						crons.put(GuardJobConstants.END_TRIGGER_4, cron);
					}
				}

				JobData job = new JobData();
				job.setGroup(group);
				job.setName(name);
				job.setBeanClass(GuardJobConstants.ALARM_DISABLE_JOB_BEAN);
				job.setDescription("告警撤防调度任务");
				for (Map.Entry<String, String> entry : crons.entrySet()) {
					logger.info("Trigger:" + entry.getKey() + ", cron:" + entry.getValue());
					job.getCrons().put(entry.getKey(), entry.getValue());
				}

				// 保存任务对应的计划
				job.getData().put(GuardJobConstants.GUARD_PLAN, plan);

				scheduler.addJob(job);
				break;
			}
			case PAUSE: {
				scheduler.pauseJob(name, group);
				break;
			}
			case RESUME: {
				scheduler.resumeJob(name, group);
				break;
			}
			case REMOVE: {
				scheduler.deleteJob(name, group);
				break;
			}
			case TRIGGER: {
				scheduler.runAJobNow(name, group);
				break;
			}
			}
		} catch (ClassNotFoundException e) {
			logger.error("Job class not found " + e.getMessage());
			throw new RuntimeException(e);
		} catch (SchedulerException e) {
			logger.warn("Job scheduler error " + e.getMessage());
		}
	}
}
