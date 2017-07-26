package com.gosun.isap.common;

import java.util.List;

/**
 * 批量id包装器
 * 
 * @author liuzk
 *
 * @param <T>
 */
public class IdwrapperBat<T> {
	private List<T> id;

	public List<T> getId() {
		return id;
	}

	public void setId(List<T> id) {
		this.id = id;
	}
}
