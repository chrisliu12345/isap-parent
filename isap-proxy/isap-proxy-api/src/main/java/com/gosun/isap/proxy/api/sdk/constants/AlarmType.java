package com.gosun.isap.proxy.api.sdk.constants;

/**
 * 告警类型常量
 * 
 * @author liuzk
 *
 */
public interface AlarmType {
	/**
	 * 移动侦测
	 */
	int ALARM_TYPE_MOVEDET = 0;
	/**
	 * 并口输入告警
	 */
	int ALARM_TYPE_PININPUT = 1;
	/**
	 * 视频源丢失
	 */
	int ALARM_TYPE_VIDLOST = 2;
	/**
	 * 视频遮挡
	 */
	int ALARM_TYPE_VIDEOSHELTER = 3;
	/**
	 * 磁盘满告警
	 */
	int ALARM_TYPE_DISKFULL = 4;
	/**
	 * 拌线告警
	 */
	int ALARM_TYPE_CROSSLINE = 5;
	/**
	 * 区域入侵告警
	 */
	int ALARM_TYPE_CROSSREGION = 6;
}
