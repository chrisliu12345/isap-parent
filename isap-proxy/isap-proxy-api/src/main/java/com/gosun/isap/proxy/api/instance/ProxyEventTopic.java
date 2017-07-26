package com.gosun.isap.proxy.api.instance;

/**
 * proxy事件主题
 * 
 * @author liuzk
 *
 */
public interface ProxyEventTopic {
	/**
	 * 平台状态事件
	 * 
	 * @see 数据类型：com.gosun.isap.proxy.api.sdk.PlatStatusEvent
	 */
	String PLAT_STATUS = "TOPIC.PLAT_STATUS";

	/**
	 * 告警事件
	 * 
	 * @see 数据类型: com.gosun.isap.proxy.api.sdk.AlarmEvent
	 */
	String ALARM_EVENT = "TOPIC.ALARM_EVENT";

	/**
	 * 设备状态事件
	 * 
	 * @see 数据类型：com.gosun.isap.proxy.api.sdk.DeviceStatusEvent
	 */
	String DEVICE_STATUS = "TOPIC.DEVICE_STATUS";
}
