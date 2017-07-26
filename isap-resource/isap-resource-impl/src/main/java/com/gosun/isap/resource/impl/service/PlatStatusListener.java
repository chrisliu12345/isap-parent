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
import com.gosun.isap.dao.mapper.TPlatMapper;
import com.gosun.isap.dao.po.TPlat;
import com.gosun.isap.proxy.api.instance.ProxyEventTopic;
import com.gosun.isap.proxy.api.sdk.PlatStatusEvent;
import com.gosun.isap.resource.api.constants.PlatStatus;

public class PlatStatusListener {
	private static final Logger logger = LoggerFactory.getLogger(PlatStatusListener.class);

	private MessageSubscriber subscriber;

	@Autowired
	private TPlatMapper platMapper;

	public MessageSubscriber getSubscriber() {
		return subscriber;
	}

	public void setSubscriber(MessageSubscriber subscriber) {
		this.subscriber = subscriber;
	}

	public void init() throws JMSException {
		subscriber.subscribeTopic(ProxyEventTopic.PLAT_STATUS).setListener(new MessageListener() {

			@Override
			public void onMessage(Message message) {
				TextMessage msg = (TextMessage) message;

				try {
					PlatStatusEvent platStatusEvent = JsonUtils.json2pojo(msg.getText(), PlatStatusEvent.class);
					if (null != platStatusEvent) {
						Long platId = platStatusEvent.getPlatId();
						TPlat plat = new TPlat();
						plat.setId(platId);
						plat.setStatus(platStatusEvent.isOnline() ? PlatStatus.ONLINE : PlatStatus.OFFLINE);
						platMapper.updateByPrimaryKeySelective(plat);
					}
				} catch (Exception e) {
					logger.error(e.getMessage());
				}
			}
		});
	}
}
