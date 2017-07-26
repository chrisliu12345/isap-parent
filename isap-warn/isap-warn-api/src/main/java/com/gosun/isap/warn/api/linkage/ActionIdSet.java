package com.gosun.isap.warn.api.linkage;

import java.io.Serializable;
import java.util.List;

/**
 * 联动动作ID集合
 * 
 * @author lucf
 *
 */
public class ActionIdSet implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<ActionID> actionObj;

	public void setActionObj(List<ActionID> actionObj) {
		this.actionObj = actionObj;
	}

	public List<ActionID> getActionObj() {
		return this.actionObj;
	}

	/**
	 * 动作ID
	 * 
	 * @author lucf
	 *
	 */
	public static class ActionID implements Serializable {

		private static final long serialVersionUID = 1L;

		private Long id;

		public void setId(Long id) {
			this.id = id;
		}

		public Long getId() {
			return this.id;
		}
	}
}
