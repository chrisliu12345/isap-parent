package com.gosun.isap.warn.impl.guard.job;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.gosun.isap.warn.impl.guard.job.impl.JobData;
import com.gosun.isap.warn.impl.guard.job.impl.IJobScheduler;

public class JobSchedulerServiceTest {

	static ApplicationContext context;

	@BeforeClass
	public static void before() {
		context = new ClassPathXmlApplicationContext("quartz-bean.xml");

	}

	@AfterClass
	public static void after() {

	}

	@Test
	public void testAddJob() {
		IJobScheduler service = context.getBean(IJobScheduler.class);
		JobData property = new JobData();
		property.setName("test");
		property.setGroup("DEFAULT_GROUP");
		property.setBeanClass("com.gosun.isap.warn.impl.guard.service.AutoAlarmEnableJob");
		property.getCrons().put("a", "30 31 11 12 5 ? 2017");

		try {
			service.addJob(property);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block

		}

		try {
			Thread.sleep(120000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testPauseJob() {
		fail("Not yet implemented");
	}

	@Test
	public void testResumeJob() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteJob() {
		fail("Not yet implemented");
	}

	@Test
	public void testRunAJobNow() {
		fail("Not yet implemented");
	}

	@Test
	public void testStart() {
		fail("Not yet implemented");
	}

	public static class HelloJob implements Job {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void execute(JobExecutionContext context) throws JobExecutionException {
			// TODO Auto-generated method stub
			System.out.println("----------hello-----");
		}

	}

	public static class TestJob implements Job {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void execute(JobExecutionContext context) throws JobExecutionException {
			// TODO Auto-generated method stub
			System.out.println("----------test-----");
		}

	}
}
