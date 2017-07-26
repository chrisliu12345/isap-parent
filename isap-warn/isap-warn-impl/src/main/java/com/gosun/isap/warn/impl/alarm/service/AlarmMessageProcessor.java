package com.gosun.isap.warn.impl.alarm.service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.gosun.isap.common.utils.JsonUtils;
import com.gosun.isap.proxy.api.sdk.AlarmEvent;
import com.gosun.isap.warn.api.report.AlarmData;
import com.gosun.isap.warn.api.report.AlarmInnerServiceApi;

/**
 * 告警订阅消息处理类
 * 
 * @author lucf
 *
 */
public class AlarmMessageProcessor implements MessageListener {
	private static Logger logger = LoggerFactory.getLogger(AlarmMessageProcessor.class);

	// 告警内部服务
	@Autowired
	private AlarmInnerServiceApi alarmInnerService;

	@Override
	public void onMessage(Message message) {
		TextMessage msg = (TextMessage) message;
		String jsonStr = null;
		AlarmEvent eventData = null;

		try {
			jsonStr = msg.getText();
		} catch (JMSException e) {
			logger.error(e.getErrorCode());
			return;
		}

		// 反序列化
		try {
			eventData = JsonUtils.json2pojo(jsonStr, AlarmEvent.class);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return;
		}

		AlarmData alarmData = new AlarmData(eventData.getPlatId(), eventData.getDeviceCode(), eventData.getAlarmType(),
				eventData.getAlarmFlag(), eventData.getAlarmData());

		alarmInnerService.pushAlarmInfo(alarmData);
	}

	/*
	 * @Override public void onMessage(Message message) { // TODO Auto-generated
	 * method stub
	 * 
	 * TextMessage msg = (TextMessage) message; String jsonStr = null; AlarmData
	 * alarmData = null;
	 * 
	 * try { jsonStr = msg.getText(); } catch (JMSException e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); }
	 * 
	 * // 反序列化 try { alarmData = JsonUtils.json2pojo(jsonStr, AlarmData.class);
	 * } catch (Exception e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); }
	 * 
	 * alarmInnerService.pushAlarmInfo(alarmData); }
	 */
}
