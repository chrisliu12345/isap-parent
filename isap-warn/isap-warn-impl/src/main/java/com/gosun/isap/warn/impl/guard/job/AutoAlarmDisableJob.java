package com.gosun.isap.warn.impl.guard.job;

import java.util.List;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.alibaba.dubbo.container.spring.SpringContainer;
import com.gosun.isap.dao.mapper.TGuardPlanResMapper;
import com.gosun.isap.dao.po.TGuardPlan;
import com.gosun.isap.dao.po.TGuardPlanRes;
import com.gosun.isap.warn.api.service.guard.TGuardPlanService;
import com.gosun.isap.warn.impl.guard.constants.AlarmGuardStatus;
import com.gosun.isap.warn.impl.guard.job.impl.IJobScheduler;
import com.gosun.isap.warn.impl.guard.job.impl.JobData;

/**
 * 告警撤销任务
 * 
 * @author liuzk
 *
 */
public class AutoAlarmDisableJob implements Job {
	private static Logger logger = LoggerFactory.getLogger(AutoAlarmDisableJob.class);

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		JobDataMap dataMap = context.getJobDetail().getJobDataMap();
		JobData property = (JobData) dataMap.get(IJobScheduler.JOB_DATA);

		TGuardPlanResMapper guardPlanResMapper = SpringContainer.getContext().getBean(TGuardPlanResMapper.class);

		// 获取待处理的资源
		List<TGuardPlanRes> resList = getPendingRes(property);
		if (null == resList) {
			return;
		}

		// 更新资源的布防状态
		for (TGuardPlanRes res : resList) {
			try {
				res.setGuardStatus(AlarmGuardStatus.OFF);
				guardPlanResMapper.updateByPrimaryKeySelective(res);
				logger.debug("Disable alarm " + res.getAlarmType() + " for device " + res.getDevId());
			} catch (Exception e) {
				// 产生异常时，只记录错误日志，继续处理其他的
				logger.error("Failed to update device guard status", e);
			}
		}
	}

	private List<TGuardPlanRes> getPendingRes(JobData property) {
		TGuardPlanService planService = SpringContainer.getContext().getBean(TGuardPlanService.class);

		TGuardPlan tmpPlan = (TGuardPlan) property.getData().get(GuardJobConstants.GUARD_PLAN);
		if (null == tmpPlan) {
			return null;
		}
		return planService.getGuardPlanRes(tmpPlan.getId());
	}
}
