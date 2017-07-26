package com.gosun.isap.proxy.api.sdk;

import java.io.Serializable;

/**
 * 事件回调接口
 * 
 * @author liuzk
 *
 */
public interface EventCallback extends Serializable {
	/**
	 * 事件接口
	 * 
	 * @param event 事件类型
	 * @param data 数据 
	 */
	void onEvent(int event, Object data);
}
