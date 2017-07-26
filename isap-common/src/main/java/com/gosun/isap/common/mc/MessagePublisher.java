package com.gosun.isap.common.mc;

import java.util.HashMap;
import java.util.Map;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * 消息发布器
 * 
 * @author liuzk
 *
 */
public class MessagePublisher {
	private ConnectionFactory factory = null;
	private Connection connection = null;
	private Session session = null;
	private MessageProducer producer = null;
	private Map<String, TopicChannel> topicChannel = new HashMap<String, TopicChannel>();

	/**
	 * 构造消息发布器
	 * 
	 * @param brokerURL
	 * @throws JMSException
	 */
	public MessagePublisher(String brokerURL) throws JMSException {
		factory = new ActiveMQConnectionFactory(brokerURL);
		connection = factory.createConnection();
		try {
			connection.start();
		} catch (JMSException jmse) {
			connection.close();
			throw jmse;
		}
		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		producer = session.createProducer(null);
	}

	/**
	 * 注册topic
	 * 
	 * @param topic
	 *            消息主题
	 * @throws JMSException
	 *             jms异常
	 */
	public void registerTopic(String topic) throws JMSException {
		synchronized (topicChannel) {
			if (null == topicChannel.get(topic)) {
				topicChannel.put(topic, new TopicChannel(topic));
			}
		}
	}

	/**
	 * 注册一个topic
	 * 
	 * @param topic
	 *            消息主题
	 */
	public void unregisterTopic(String topic) {
		synchronized (topicChannel) {
			topicChannel.remove(topic);
		}
	}

	/**
	 * 根据topic获取topic channel
	 * 
	 * @param topic
	 *            消息主題
	 * @return 主題通道
	 */
	public TopicChannel getTopicChannel(String topic) {
		synchronized (topicChannel) {
			return topicChannel.get(topic);
		}
	}

	/**
	 * 关闭发布器
	 * 
	 * @throws JMSException
	 *             jms异常
	 */
	public void destroy() throws JMSException {
		if (null != connection) {
			connection.close();
			connection = null;
		}
	}

	/**
	 * 析構
	 * 
	 * @throws Throwable
	 *             exception
	 */
	protected void finalize() throws Throwable {
		try {
			destroy();
		} catch (JMSException e) {
		}
		super.finalize();
	}

	/**
	 * topic channel
	 * 
	 * @author liuzk
	 *
	 */
	public class TopicChannel {
		private Destination dest = null;

		TopicChannel(String topic) throws JMSException {
			dest = session.createTopic(topic);
		}

		/**
		 * 发布消息
		 * 
		 * @param message
		 *            字符串消息
		 * @throws JMSException
		 *             jms异常
		 */
		public void publishMessage(String message) throws JMSException {
			TextMessage msg = session.createTextMessage();
			msg.setText(message);
			producer.send(dest, msg);
		}
	}
}
