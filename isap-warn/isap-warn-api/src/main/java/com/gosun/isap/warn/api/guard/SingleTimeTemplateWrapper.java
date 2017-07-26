package com.gosun.isap.warn.api.guard;

import java.io.Serializable;


public class SingleTimeTemplateWrapper implements Serializable {

	private static final long serialVersionUID = 1L;

	private GuardTimeTemplate template;
	private GuardSingleTime time;

	public GuardTimeTemplate getTemplate() {
		return template;
	}

	public void setTemplate(GuardTimeTemplate template) {
		this.template = template;
	}

	public GuardSingleTime getTime() {
		return time;
	}

	public void setTime(GuardSingleTime time) {
		this.time = time;
	}
}
