package com.gosun.isap.common.mc;

import static org.junit.Assert.*;

import javax.jms.JMSException;

import org.junit.Test;

public class PublisherTest {

	@Test
	public void test() throws JMSException, InterruptedException {
		String brokerURL = "tcp://191.191.16.120:61616";
		MessagePublisher p = new MessagePublisher(brokerURL);

	}

}
