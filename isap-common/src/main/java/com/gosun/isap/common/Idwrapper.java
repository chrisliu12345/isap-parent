package com.gosun.isap.common;

/**
 * id包装器
 * 
 * @author liuzk
 *
 * @param <T>
 */
public class Idwrapper<T> {
	private T id;

	public T getId() {
		return id;
	}

	public void setId(T id) {
		this.id = id;
	}

}
