package com.gosun.isap.warn.impl.guard.job;

import java.util.List;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.alibaba.dubbo.container.spring.SpringContainer;
import com.gosun.isap.dao.mapper.TDeviceMapper;
import com.gosun.isap.dao.mapper.TGuardPlanResMapper;
import com.gosun.isap.dao.po.TDevice;
import com.gosun.isap.dao.po.TGuardPlan;
import com.gosun.isap.dao.po.TGuardPlanRes;
import com.gosun.isap.proxy.api.backend.IProxyCache;
import com.gosun.isap.proxy.api.backend.ProxyNotFoundException;
import com.gosun.isap.proxy.api.instance.CallNativeSdkException;
import com.gosun.isap.proxy.api.instance.ISdkAdapter;
import com.gosun.isap.proxy.api.sdk.AlarmConfigParam;
import com.gosun.isap.warn.api.service.guard.TGuardPlanService;
import com.gosun.isap.warn.impl.guard.constants.AlarmGuardStatus;
import com.gosun.isap.warn.impl.guard.constants.GuardPlanStatus;
import com.gosun.isap.warn.impl.guard.job.impl.IJobScheduler;
import com.gosun.isap.warn.impl.guard.job.impl.JobData;

/**
 * 告警布防任务
 * 
 * @author liuzk
 *
 */
public class AutoAlarmEnableJob implements Job {
	private static Logger logger = LoggerFactory.getLogger(AutoAlarmEnableJob.class);

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
			res.setGuardStatus(AlarmGuardStatus.ON);
			try {
				guardPlanResMapper.updateByPrimaryKeySelective(res);
				logger.debug("Enable alarm " + res.getAlarmType() + " for device " + res.getDevId());
			} catch (Exception e) {
				// 产生异常时，只记录错误日志，继续处理其他的
				logger.error("Failed to update device guard status", e);
			}
		}

		/**
		 * 大华没有布防概念，此处不需要调用sdk下发
		 */
		// 由于下发需要调用sdk，sdk可能会比较慢，所以启动一个线程来进行分发
		// distributeAlarm(resList);
	}

	private List<TGuardPlanRes> getPendingRes(JobData property) {
		TGuardPlanService planService = SpringContainer.getContext().getBean(TGuardPlanService.class);

		TGuardPlan tmpPlan = (TGuardPlan) property.getData().get(GuardJobConstants.GUARD_PLAN);
		if (null == tmpPlan) {
			return null;
		}

		// 只有当计划状态是运行中的时候，才处理
		TGuardPlan plan = planService.getGuardPlan(tmpPlan.getId());
		if (plan.getStatus() != GuardPlanStatus.RUNNING) {
			logger.info("Guard plan " + plan.getId() + " is not runing, ignore schedule");
			return null;
		}

		return planService.getGuardPlanRes(tmpPlan.getId());
	}

	/**
	 * 分发告警
	 * 
	 * @param resList
	 *            待处理的资源列表
	 */
	private void distributeAlarm(List<TGuardPlanRes> resList) {
		if (resList.isEmpty()) {
			return;
		}

		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				TDeviceMapper deviceMapper = SpringContainer.getContext().getBean(TDeviceMapper.class);
				IProxyCache proxyCache = SpringContainer.getContext().getBean(IProxyCache.class);

				for (TGuardPlanRes res : resList) {
					TDevice dev = deviceMapper.selectByPrimaryKey(res.getDevId());
					if (null == dev) {
						continue;
					}

					String platId = dev.getPlatId().toString();
					try {
						ISdkAdapter sdk = proxyCache.getProxy(platId).getSdkAdapter();

						AlarmConfigParam param = new AlarmConfigParam();
						param.alarmDeviceId = dev.getCode();
						param.alarmType = (int) (long) res.getAlarmType();
						param.alarmInputNo = -1; // 所有
						param.videoNo = -1; // 所有

						sdk.enableAlaram(param);
					} catch (ProxyNotFoundException e) {
						logger.warn(e.getMessage());
						continue;
					} catch (CallNativeSdkException e) {
						logger.error(e.getMessage());
						continue;
					}
				}
			}

		});
		t.start();
	}
}
