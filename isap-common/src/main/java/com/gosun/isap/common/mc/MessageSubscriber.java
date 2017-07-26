package com.gosun.isap.common.mc;

import java.util.HashMap;
import java.util.Map;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * 消息订阅器
 * 
 * @author liuzk
 *
 */
public class MessageSubscriber {
	private ConnectionFactory factory = null;
	private Connection connection = null;
	private Session session = null;
	private Map<String, TopicChannel> topicChannel = new HashMap<String, TopicChannel>();

	/**
	 * 创建消息订阅器
	 * 
	 * @param brokerURL
	 * @throws JMSException
	 */
	public MessageSubscriber(String brokerURL) throws JMSException {
		factory = new ActiveMQConnectionFactory(brokerURL);
		connection = factory.createConnection();
		connection.start();
		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
	}

	/**
	 * 注册topic
	 * 
	 * @param topic
	 *            消息主题
	 * @return topic channel
	 * @throws JMSException
	 *             jms异常
	 */
	public TopicChannel subscribeTopic(String topic) throws JMSException {
		synchronized (topicChannel) {
			TopicChannel chl = topicChannel.get(topic);
			if (null == chl) {
				chl = new TopicChannel(topic);
				topicChannel.put(topic, chl);
			}
			return chl;
		}
	}

	/**
	 * 注册一个topic
	 * 
	 * @param topic
	 *            消息主题
	 */
	public void unsubscribeTopic(String topic) {
		synchronized (topicChannel) {
			topicChannel.remove(topic);
		}
	}

	/**
	 * 根据topic获取topic channel
	 * 
	 * @param topic
	 *            消息主题
	 * @return 消息通道
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
	 * 析构
	 * 
	 * @throws Throwable
	 *             exception
	 * 
	 */
	protected void finalize() throws Throwable {
		try {
			destroy();
		} catch (JMSException e) {
		}
		super.finalize();
	}

	/**
	 * 消息通道
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
		 * 设置消息监听器
		 * 
		 * @param listener
		 *            监听器
		 * @throws JMSException
		 *             jms异常
		 */
		public void setListener(MessageListener listener) throws JMSException {
			MessageConsumer consumer = session.createConsumer(dest);
			consumer.setMessageListener(listener);
		}
	}
}
