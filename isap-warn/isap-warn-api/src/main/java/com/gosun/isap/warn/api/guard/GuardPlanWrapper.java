package com.gosun.isap.warn.api.guard;

import java.io.Serializable;
import java.util.List;

public class GuardPlanWrapper implements Serializable {
	private static final long serialVersionUID = 1L;

	// 布防计划
	private GuardPlan plan;

	// 计划关联的资源
	private List<GuardPlanRes> resources;

	public GuardPlan getPlan() {
		return plan;
	}

	public void setPlan(GuardPlan plan) {
		this.plan = plan;
	}

	public List<GuardPlanRes> getResources() {
		return resources;
	}

	public void setResources(List<GuardPlanRes> resources) {
		this.resources = resources;
	}
}
