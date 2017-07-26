package com.gosun.isap.proxy.api.sdk;

import java.util.ArrayList;

/**
 * native sdk接口
 * 
 * @author liuzk
 *
 */
public class NativeSdk {

	/**
	 * 获取sdk信息
	 * 
	 * @return sdk信息
	 */
	public native SdkInfo getSdkInfo();

	/**
	 * 初始化sdk
	 * 
	 * @param platformType
	 *            平台类型
	 * @return 成功返回0，失败返回其他
	 */
	public native int init(String platformType);

	/**
	 * 销毁sdk
	 * 
	 * @param handle
	 *            sdk句柄
	 * @return 成功返回0，失败返回其他
	 */
	public native int destroy(int handle);

	/**
	 * 登录平台
	 * 
	 * @param handle
	 *            sdk句柄
	 * @param param
	 *            登录参数
	 * @return 成功返回0，失败返回其他
	 */
	public native int login(int handle, LoginParam param);

	/**
	 * 退出登录
	 * 
	 * @param handle
	 *            句柄
	 * @return 成功返回0，失败返回其他
	 */
	public native int logout(int handle);

	/**
	 * 云台控制
	 * 
	 * @param handle
	 *            sdk句柄
	 * @param param
	 *            云台控制参数
	 * @return 成功返回0，失败返回其他
	 */
	public native int ptzControl(int handle, PtzControlParam param);

	/**
	 * 告警使能
	 * 
	 * @param handle
	 *            sdk句柄
	 * @param param
	 *            告警参数
	 * @return 成功返回0，失败返回其他
	 */
	public native int enableAlaram(int handle, AlarmConfigParam param);

	/**
	 * 告警关闭
	 * 
	 * @param handle
	 *            sdk句柄
	 * @param param
	 *            告警参数
	 * @return 成功返回0，失败返回其他
	 */
	public native int disableAlarm(int handle, AlarmConfigParam param);

	/**
	 * 设置事件回调
	 * 
	 * @param handle
	 *            sdk句柄
	 * @param callback
	 *            回调对象
	 * @return 成功返回0，失败返回其他
	 */
	public native int setEventCallback(int handle, EventCallback callback);

	/**
	 * 查询所有设备
	 * 
	 * @param handle
	 *            sdk句柄
	 * @param devices
	 *            返回的设备列表
	 * @return 成功返回0，失败返回其他
	 */
	public native int queryAllDevices(int handle, ArrayList<Device> devices);

	/**
	 * 查询云台预知位
	 * 
	 * @param handle
	 *            sdk句柄
	 * @param deviceId
	 *            设备id
	 * @param presets
	 *            返回的预置位列表
	 * @return 成功返回0，失败返回其他
	 */
	public native int queryPtzPreset(int handle, String deviceId, ArrayList<Preset> presets);

	/**
	 * 开启平台录像
	 * 
	 * @param handle
	 *            sdk句柄
	 * @param deviceId
	 *            设备id
	 * @param devstream
	 *            录像类型 0-全部;1-主码流;2-辅码流;3-三码流;5-M60本地信号.
	 * @return 成功返回0，失败返回其他
	 */
	public native int startPlatformRec(int handle, String deviceId, int devstream);

	/**
	 * 停止平台录像
	 * 
	 * @param handle
	 *            sdk句柄
	 * @param deviceId
	 *            设备id
	 * @return 成功返回0，失败返回其他
	 */
	public native int stopPlatformRec(int handle, String deviceId);

	/**
	 * 抓图
	 * 
	 * @param handle
	 *            sdk句柄
	 * @param param
	 *            抓图参数
	 * @return 成功返回0，失败返回其他
	 */
	public native int capturePicture(int handle, PicCaptureParam param);

}
