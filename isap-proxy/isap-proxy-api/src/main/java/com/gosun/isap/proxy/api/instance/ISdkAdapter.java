package com.gosun.isap.proxy.api.instance;

import java.util.List;

import com.gosun.isap.proxy.api.sdk.AlarmConfigParam;
import com.gosun.isap.proxy.api.sdk.Device;
import com.gosun.isap.proxy.api.sdk.LoginParam;
import com.gosun.isap.proxy.api.sdk.PicCaptureParam;
import com.gosun.isap.proxy.api.sdk.Preset;
import com.gosun.isap.proxy.api.sdk.PtzControlParam;
import com.gosun.isap.proxy.api.sdk.SdkInfo;

/**
 * SDK适配器
 * 
 * @author Administrator
 *
 */
public interface ISdkAdapter {
	/**
	 * 获取sdk进程pid
	 * 
	 * @return pid
	 */
	String getPid();

	/**
	 * 获取sdk信息
	 * 
	 * @return sdk信息
	 * @throws CallNativeSdkException
	 *             调用本地sdk接口失败
	 */
	SdkInfo getSdkInfo() throws CallNativeSdkException;

	/**
	 * 登录平台
	 * 
	 * @param param
	 *            登录参数
	 * @throws CallNativeSdkException
	 *             调用本地sdk接口失败
	 */
	void login(LoginParam param) throws CallNativeSdkException;

	/**
	 * 退出登录
	 * 
	 * 句柄
	 * 
	 * @throws CallNativeSdkException
	 *             调用本地sdk接口失败
	 */
	void logout() throws CallNativeSdkException;

	/**
	 * 云台控制
	 * 
	 * sdk句柄
	 * 
	 * @param param
	 *            云台控制参数
	 * @throws CallNativeSdkException
	 *             调用本地sdk接口失败
	 */
	void ptzControl(PtzControlParam param) throws CallNativeSdkException;

	/**
	 * 告警使能
	 * 
	 * 
	 * sdk句柄
	 * 
	 * @param param
	 *            告警参数
	 * @throws CallNativeSdkException
	 *             调用本地sdk接口失败
	 */
	void enableAlaram(AlarmConfigParam param) throws CallNativeSdkException;

	/**
	 * 告警关闭
	 * 
	 * sdk句柄
	 * 
	 * @param param
	 *            告警参数
	 * @throws CallNativeSdkException
	 *             调用本地sdk接口失败
	 */
	void disableAlarm(AlarmConfigParam param) throws CallNativeSdkException;

	/**
	 * 获取所有设备
	 * 
	 * @return 设备列表
	 * @throws CallNativeSdkException
	 *             调用本地sdk接口失败
	 */
	List<Device> queryAllDevices() throws CallNativeSdkException;

	/**
	 * 查询云台预知位
	 * 
	 * @param deviceId
	 *            设备id
	 * @return 预置位列表
	 * @throws CallNativeSdkException
	 *             调用本地sdk接口失败
	 */
	List<Preset> queryPtzPreset(String deviceId) throws CallNativeSdkException;

	/**
	 * 抓图
	 * 
	 * @param param
	 *            抓图参数
	 * @throws CallNativeSdkException
	 *             调用本地sdk接口失败
	 */
	void capturePicture(PicCaptureParam param) throws CallNativeSdkException;
}
