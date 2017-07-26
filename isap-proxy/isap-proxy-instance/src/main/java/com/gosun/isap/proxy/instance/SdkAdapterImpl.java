package com.gosun.isap.proxy.instance;

import java.util.List;
import com.gosun.isap.proxy.api.instance.CallNativeSdkException;
import com.gosun.isap.proxy.api.instance.ISdkAdapter;
import com.gosun.isap.proxy.api.sdk.AlarmConfigParam;
import com.gosun.isap.proxy.api.sdk.Device;
import com.gosun.isap.proxy.api.sdk.LoginParam;
import com.gosun.isap.proxy.api.sdk.PicCaptureParam;
import com.gosun.isap.proxy.api.sdk.Preset;
import com.gosun.isap.proxy.api.sdk.PtzControlParam;
import com.gosun.isap.proxy.api.sdk.SdkInfo;

/**
 * sdk适配器实现
 * 
 * @author liuzk
 *
 */
public class SdkAdapterImpl implements ISdkAdapter {
	private ISdkCaller sdk;

	public ISdkCaller getSdk() {
		return sdk;
	}

	public void setSdk(ISdkCaller sdk) {
		this.sdk = sdk;
	}

	@Override
	public String getPid() {
		return sdk.getPid();
	}

	@Override
	public SdkInfo getSdkInfo() throws CallNativeSdkException {
		return sdk.getSdkInfo();
	}

	@Override
	public void login(LoginParam param) throws CallNativeSdkException {
		sdk.login(param);
	}

	@Override
	public void logout() throws CallNativeSdkException {
		sdk.logout();
	}

	@Override
	public void ptzControl(PtzControlParam param) throws CallNativeSdkException {
		sdk.ptzControl(param);
	}

	@Override
	public void enableAlaram(AlarmConfigParam param) throws CallNativeSdkException {
		sdk.enableAlaram(param);
	}

	@Override
	public void disableAlarm(AlarmConfigParam param) throws CallNativeSdkException {
		sdk.disableAlarm(param);
	}

	@Override
	public List<Device> queryAllDevices() throws CallNativeSdkException {
		return sdk.queryAllDevices();
	}

	@Override
	public List<Preset> queryPtzPreset(String deviceId) throws CallNativeSdkException {
		return sdk.queryPtzPreset(deviceId);
	}

	@Override
	public void capturePicture(PicCaptureParam param) throws CallNativeSdkException {
		sdk.capturePicture(param);
	}
}
