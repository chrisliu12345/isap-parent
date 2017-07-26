package com.gosun.isap.resource.impl.service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.gosun.isap.common.mc.MessageSubscriber;
import com.gosun.isap.common.utils.JsonUtils;
import com.gosun.isap.dao.mapper.TDeviceMapper;
import com.gosun.isap.dao.po.TDevice;
import com.gosun.isap.dao.po.TDeviceExample;
import com.gosun.isap.proxy.api.instance.ProxyEventTopic;
import com.gosun.isap.proxy.api.sdk.DeviceStatusEvent;
import com.gosun.isap.resource.api.constants.DeviceStatus;

public class DeviceStatusListener {
	private static final Logger logger = LoggerFactory.getLogger(DeviceStatusListener.class);

	private MessageSubscriber subscriber;

	@Autowired
	TDeviceMapper deviceMapper;

	public MessageSubscriber getSubscriber() {
		return subscriber;
	}

	public void setSubscriber(MessageSubscriber subscriber) {
		this.subscriber = subscriber;
	}

	public void init() throws JMSException {
		subscriber.subscribeTopic(ProxyEventTopic.DEVICE_STATUS).setListener(new MessageListener() {

			@Override
			public void onMessage(Message message) {
				TextMessage msg = (TextMessage) message;

				try {
					DeviceStatusEvent devStatusEvent = JsonUtils.json2pojo(msg.getText(), DeviceStatusEvent.class);
					if (null != devStatusEvent) {
						Long platId = devStatusEvent.getPlatId();
						String deviceCode = devStatusEvent.deviceCode;

						TDeviceExample e = new TDeviceExample();
						TDeviceExample.Criteria c = e.createCriteria();
						c.andPlatIdEqualTo(platId);
						c.andCodeEqualTo(deviceCode);

						TDevice dev = new TDevice();
						dev.setStatus(devStatusEvent.online ? DeviceStatus.ONLINE : DeviceStatus.OFFLINE);
						deviceMapper.updateByExampleSelective(dev, e);
					}
				} catch (Exception e) {
					logger.error(e.getMessage());
				}
			}
		});
	}
}
