package com.gosun.isap.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gosun.isap.common.error.ErrorMessage;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Restful接口统一响应消息格式
 * 
 * @author liuzk
 *
 * @param <T>
 */
@SuppressWarnings("rawtypes")
public class ResponseResult<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final String TOTAL = "totalNum";
	private static final String SIZE = "rowNum";
	private static final String DEFAULT_MESSAGE = "OK";

	@JsonProperty("resthead")
	private Head head;
	@JsonProperty("restbody")
	private T body;
	@JsonProperty("extend")
	private Map<String, Object> extend;

	public ResponseResult() {
		head = new Head();
	}

	/**
	 * 生成默认成功的rest响应消息
	 * 
	 * @param <T>
	 *            body类型
	 * @return T
	 */
	public static <T> ResponseResult<T> ok() {
		return new ResponseResult<T>();
	}

	public Head getHead() {
		return head;
	}

	public T getBody() {
		return body;
	}

	/**
	 * 设置body
	 * 
	 * @param body
	 *            rest body
	 */
	public void setBody(T body) {
		this.body = body;

		if (body != null && body instanceof List) {
			int size = ((List) body).size();
			setListSize(size);
			Integer total = (Integer) extend.get(TOTAL);
			if (total == null || total < size) {
				setTotal(size);
			}
		}
	}

	/**
	 * 设置错误信息(根据模板自动生成错误)
	 * 
	 * @param errorCode
	 *            错误码
	 * @param errorParam
	 *            模板中占位的参数列表
	 */
	public void setErrorEx(int errorCode, String[] errorParam) {
		String message = ErrorMessage.get(errorCode, errorParam);
		setError(errorCode, message);
	}

	/**
	 * 设置错误信息
	 * 
	 * @param errorCode
	 *            错误码
	 * @param message
	 *            错误消息
	 */
	public void setError(int errorCode, String message) {
		head.setErrorCode(errorCode);
		head.setMessage(message);
	}

	/**
	 * 设置总数(分页时使用)
	 * 
	 * @param total
	 *            总数
	 */
	public void setTotal(int total) {
		if (extend == null) {
			extend = new HashMap<>();
		}
		extend.put(TOTAL, total);
	}

	public Map<String, Object> getExtend() {
		return extend;
	}

	public void setExtend(Map<String, Object> extend) {
		this.extend = extend;
	}

	/**
	 * 增加扩展项
	 * 
	 * @param extend
	 *            扩展项
	 */
	public void addExtend(Map<String, Object> extend) {
		if (this.extend == null) {
			this.extend = extend;
		} else if (extend != null) {
			this.extend.putAll(extend);
		}
	}

	/**
	 * 增加一个扩展项
	 * 
	 * @param key
	 *            扩展项key
	 * @param value
	 *            扩展项value
	 */
	public void addExtend(String key, Object value) {
		if (extend == null) {
			extend = new HashMap<>();
		}

		extend.put(key, value);
	}

	private void setListSize(int size) {
		if (extend == null) {
			extend = new HashMap<>();
		}
		extend.put(SIZE, size);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		ResponseResult<?> that = (ResponseResult<?>) o;

		return new EqualsBuilder().append(head, that.head).append(body, that.body).append(extend, that.extend)
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37).append(head).append(body).append(extend).toHashCode();
	}

	/**
	 * rest头部
	 * 
	 * @author liuzk
	 *
	 */
	public static class Head {
		private int errorCode = 0;
		private String message = DEFAULT_MESSAGE;

		public Head() {
		}

		public Head(int errorCode, String message) {
			this.errorCode = errorCode;
			this.message = message;
		}

		public int getErrorCode() {
			return errorCode;
		}

		public void setErrorCode(int errorCode) {
			this.errorCode = errorCode;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) {
				return true;
			}
			if (o == null || getClass() != o.getClass()) {
				return false;
			}

			Head head = (Head) o;

			return new EqualsBuilder().append(errorCode, head.errorCode).append(message, head.message).isEquals();
		}

		@Override
		public int hashCode() {
			return new HashCodeBuilder(17, 37).append(errorCode).append(message).toHashCode();
		}
	}

}