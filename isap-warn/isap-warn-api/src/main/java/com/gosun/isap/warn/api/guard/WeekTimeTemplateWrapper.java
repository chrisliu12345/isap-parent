package com.gosun.isap.warn.api.guard;

import java.io.Serializable;


public class WeekTimeTemplateWrapper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private GuardTimeTemplate template;
	private GuardWeekTime time;

	public GuardTimeTemplate getTemplate() {
		return template;
	}

	public void setTemplate(GuardTimeTemplate template) {
		this.template = template;
	}

	public GuardWeekTime getTime() {
		return time;
	}

	public void setTime(GuardWeekTime time) {
		this.time = time;
	}
}
