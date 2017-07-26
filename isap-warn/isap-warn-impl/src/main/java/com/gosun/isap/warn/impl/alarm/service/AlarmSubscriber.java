package com.gosun.isap.warn.impl.alarm.service;

import javax.jms.JMSException;
import javax.jms.MessageListener;

import com.gosun.isap.common.mc.MessageSubscriber;
import com.gosun.isap.proxy.api.instance.ProxyEventTopic;

/**
 * 告警订阅类
 * 
 * @author lucf
 *
 */
public class AlarmSubscriber {
	private MessageListener processor;
	private MessageSubscriber subscriber;

	public MessageSubscriber getSubscriber() {
		return subscriber;
	}

	public void setSubscriber(MessageSubscriber subscriber) {
		this.subscriber = subscriber;
	}

	public MessageListener getProcessor() {
		return processor;
	}

	public void setProcessor(MessageListener processor) {
		this.processor = processor;
	}

	/**
	 * 初始化
	 * 
	 * @throws JMSException
	 */
	public void init() throws JMSException {
		String topic = ProxyEventTopic.ALARM_EVENT;
		subscriber.subscribeTopic(topic).setListener(processor);
	}

}
