package com.gosun.isap.warn.impl.alarm.service;

import java.util.List;

import javax.jms.Message;
import javax.jms.TextMessage;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gosun.isap.common.utils.JsonUtils;
import com.gosun.isap.dao.mapper.TGuardPlanResMapper;
import com.gosun.isap.dao.po.TDevice;
import com.gosun.isap.dao.po.TGuardPlanRes;
import com.gosun.isap.dao.po.TGuardPlanResExample;
import com.gosun.isap.proxy.api.sdk.AlarmEvent;
import com.gosun.isap.resource.api.service.TDeviceService;
import com.gosun.isap.warn.impl.guard.constants.AlarmGuardStatus;

@Aspect
@Component
public class AlarmFilterAspect {
	@Autowired
	TDeviceService deviceService;
	@Autowired
	TGuardPlanResMapper guardPlanResMapper;

	@Pointcut(value = "execution(* com.gosun.isap.warn.impl.alarm.service.AlarmMessageProcessor.onMessage(..))")
	public void pointCut() {

	}

	@Around(value = "pointCut()")
	private void aroundAdvice(ProceedingJoinPoint pjp) throws Throwable {
		// 获取消息参数
		Message msg = null;
		for (Object o : pjp.getArgs()) {
			if (o instanceof Message) {
				msg = (Message) o;
				break;
			}
		}

		boolean isValidAlarm = false;
		if (null != msg) {
			TextMessage textMsg = (TextMessage) msg;
			AlarmEvent event = JsonUtils.json2pojo(textMsg.getText(), AlarmEvent.class);
			TDevice dev = deviceService.queryPlatDeviceByCode(event.getPlatId(), event.deviceCode);
			if (null != dev) {
				TGuardPlanResExample example = new TGuardPlanResExample();
				TGuardPlanResExample.Criteria criteria = example.createCriteria();
				criteria.andDevIdEqualTo(dev.getId());
				criteria.andAlarmTypeEqualTo((long) event.alarmType);

				List<TGuardPlanRes> resList = guardPlanResMapper.selectByExample(example);
				if (null != resList && !resList.isEmpty()) {
					// 布防状态是打开时，才进行处理
					if (AlarmGuardStatus.ON == resList.get(0).getGuardStatus()) {
						isValidAlarm = true;
					}
				}
			}
		}

		if (isValidAlarm) {
			pjp.proceed();
		}
	}

}
