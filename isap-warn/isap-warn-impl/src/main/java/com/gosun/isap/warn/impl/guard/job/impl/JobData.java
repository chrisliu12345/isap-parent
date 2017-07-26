package com.gosun.isap.warn.impl.guard.job.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 任务dto
 * 
 * @author liuzk
 *
 */
public class JobData implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 任务名称
	 */
	private String name;
	/**
	 * 任务分组
	 */
	private String group;
	/**
	 * 任务描述
	 */
	private String description;
	/**
	 * 任务class名称
	 */
	private String beanClass;
	/**
	 * 任务cron表达式，一个任务支持多个cron表达式
	 */
	private Map<String, String> crons = new HashMap<String, String>();
	/**
	 * 任务数据
	 */
	private Map<String, Object> data = new HashMap<String, Object>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public final Map<String, String> getCrons() {
		return crons;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBeanClass() {
		return beanClass;
	}

	public void setBeanClass(String beanClass) {
		this.beanClass = beanClass;
	}

	public Map<String, Object> getData() {
		return data;
	}

	/**
	 * tostring
	 * 
	 * @return string
	 */
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("name:" + name + ", ");
		builder.append("group:" + group + ", ");
		builder.append("description:" + description + ", ");
		builder.append("beanClass:" + beanClass + ", ");
		builder.append("cronExpressions:[");
		for (String cronId : crons.keySet()) {
			builder.append(cronId + "(" + crons.get(cronId) + ") ");
		}
		builder.append("]");
		return builder.toString();
	}
}
