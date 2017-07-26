package com.gosun.isap.common.mc;

import static org.junit.Assert.*;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.junit.Test;

public class SubscriberTest {

	@Test
	public void test() throws JMSException, InterruptedException {
		String brokerURL = "tcp://191.191.16.120:61616";
		MessageSubscriber c = new MessageSubscriber(brokerURL);

		Thread.sleep(20000);
	}

	public static class MessageCallback1 implements MessageListener {

		@Override
		public void onMessage(Message message) {
			System.out.println("callback1 : " + message);
		}

	}

	public static class MessageCallback2 implements MessageListener {

		@Override
		public void onMessage(Message message) {
			System.out.println("callback2 : " + message);
		}

	}
}
