package com.gosun.isap.proxy.instance;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gosun.isap.common.utils.FilePathUtils;
import com.gosun.isap.proxy.api.instance.CallNativeSdkException;
import com.gosun.isap.proxy.api.sdk.AlarmConfigParam;
import com.gosun.isap.proxy.api.sdk.Device;
import com.gosun.isap.proxy.api.sdk.EventCallback;
import com.gosun.isap.proxy.api.sdk.LoginParam;
import com.gosun.isap.proxy.api.sdk.NativeSdk;
import com.gosun.isap.proxy.api.sdk.PicCaptureParam;
import com.gosun.isap.proxy.api.sdk.Preset;
import com.gosun.isap.proxy.api.sdk.PtzControlParam;
import com.gosun.isap.proxy.api.sdk.SdkInfo;

/**
 * 本地sdk调用实现
 * 
 * @author liuzk
 *
 */
public class NativeSdkCallerImpl implements ISdkCaller {
	private static Logger logger = LoggerFactory.getLogger(NativeSdkCallerImpl.class);
	private static final String NATIVE_DLL_NAME = "adapterjni";

	private final NativeSdk nativeSdk = new NativeSdk();

	private int handle = 0;
	private boolean inited = false;
	private boolean logined = false;
	private String pictureDirectory;

	public synchronized boolean isInited() {
		return inited;
	}

	public synchronized void setInited(boolean inited) {
		this.inited = inited;
	}

	@Override
	public synchronized boolean isLogined() {
		return logined;
	}

	@Override
	public synchronized void setLogined(boolean logined) {
		this.logined = logined;
	}

	@Override
	public void init(String platformType) throws CallNativeSdkException {
		logger.info("Begin to init " + platformType + " sdk...");

		if (isInited()) {
			logger.info("Sdk already inited, ignore");
			return;
		}

		// 加载dll
		System.loadLibrary(NATIVE_DLL_NAME);

		// 初始化sdk
		handle = nativeSdk.init(platformType);
		if (handle <= 0) {
			throw new CallNativeSdkException(handle, "init", handle);
		}

		setInited(true);

		logger.info("Succeed to init sdk, handle= " + handle + ", enjoy...");
	}

	@Override
	public String getPid() {
		String name = ManagementFactory.getRuntimeMXBean().getName();
		System.out.println(name);
		// get pid
		String pid = name.split("@")[0];
		return pid;
	}

	@Override
	public SdkInfo getSdkInfo() throws CallNativeSdkException {
		SdkInfo sdkInfo = nativeSdk.getSdkInfo();
		if (null == sdkInfo) {
			throw new CallNativeSdkException(handle, "getSdkInfo", 0, "sdk return null");
		}
		return sdkInfo;
	}

	@Override
	public void destroy() throws CallNativeSdkException {
		logger.info("Destroy sdk, handle = " + handle);
		int ret = nativeSdk.destroy(handle);
		if (0 != ret) {
			throw new CallNativeSdkException(handle, "destroy", ret);
		}
		inited = false;
	}

	@Override
	public void login(LoginParam param) throws CallNativeSdkException {
		logger.info("Login platform, user=" + param.user + ", password=" + param.password + ", ipaddr="
				+ param.ipAddress + ", port=" + param.port);

		// 防止重复登录
		if (isLogined()) {
			logger.info("Sdk already logged, ignore repeat login");
			return;
		}

		int ret = nativeSdk.login(handle, param);
		if (0 != ret) {
			throw new CallNativeSdkException(handle, "login", ret);
		}
		setLogined(true);

		logger.info("Succeed to login platform " + param.ipAddress + ":" + param.port);
	}

	@Override
	public void logout() throws CallNativeSdkException {
		int ret = nativeSdk.logout(handle);
		if (0 != ret) {
			throw new CallNativeSdkException(handle, "logout", ret);
		}
		logined = false;
	}

	@Override
	public void ptzControl(PtzControlParam param) throws CallNativeSdkException {
		int ret = nativeSdk.ptzControl(handle, param);
		if (0 != ret) {
			throw new CallNativeSdkException(handle, "ptzControl", ret);
		}
	}

	@Override
	public void enableAlaram(AlarmConfigParam param) throws CallNativeSdkException {
		int ret = nativeSdk.enableAlaram(handle, param);
		if (0 != ret) {
			throw new CallNativeSdkException(handle, "enableAlaram", ret);
		}
	}

	@Override
	public void disableAlarm(AlarmConfigParam param) throws CallNativeSdkException {
		int ret = nativeSdk.disableAlarm(handle, param);
		if (0 != ret) {
			throw new CallNativeSdkException(handle, "disableAlarm", ret);
		}
	}

	@Override
	public List<Device> queryAllDevices() throws CallNativeSdkException {
		ArrayList<Device> devices = new ArrayList<Device>();
		int ret = nativeSdk.queryAllDevices(handle, devices);
		if (0 != ret) {
			throw new CallNativeSdkException(handle, "queryAllDevices", ret);
		}
		return devices;
	}

	@Override
	public List<Preset> queryPtzPreset(String deviceId) throws CallNativeSdkException {
		ArrayList<Preset> presets = new ArrayList<Preset>();
		int ret = nativeSdk.queryPtzPreset(handle, deviceId, presets);
		if (0 != ret) {
			throw new CallNativeSdkException(handle, "queryPtzPreset", ret);
		}
		return presets;
	}

	@Override
	public void capturePicture(PicCaptureParam param) throws CallNativeSdkException {
		String fileFullPath = param.filePath;

		// 如果给的是相对路径，则组装绝对路径
		if (!FilePathUtils.isAbsolutePath(param.filePath)) {
			StringBuilder sb = new StringBuilder();
			sb.append(getPictureDirectory()).append(File.separator).append(param.filePath);
			fileFullPath = FilePathUtils.getFilePath(sb.toString());
		} else {
			fileFullPath = FilePathUtils.getFilePath(param.filePath);
		}

		logger.info("Capture picture, dev " + param.deviceCode + ", picType " + param.picFormatType + ", filePath "
				+ fileFullPath);

		// 递归创建目录
		File f = new File(fileFullPath);
		mkdirRecursive(f.getParentFile());

		param.filePath = fileFullPath;
		int ret = nativeSdk.capturePicture(handle, param);
		if (0 != ret) {
			throw new CallNativeSdkException(handle, "capturePicture", ret);
		}
	}

	@Override
	public void setEventCallback(EventCallback callback) throws CallNativeSdkException {
		int ret = nativeSdk.setEventCallback(handle, callback);
		if (0 != ret) {
			throw new CallNativeSdkException(handle, "setEventCallback", ret);
		}
	}

	public String getPictureDirectory() {
		return pictureDirectory;
	}

	public void setPictureDirectory(String pictureDirectory) {
		this.pictureDirectory = pictureDirectory;
	}

	private void mkdirRecursive(File file) {
		if (null == file || null == file.getParentFile()) {
			return;
		}
		if (file.getParentFile().exists()) {
			file.mkdir();
		} else {
			mkdirRecursive(file.getParentFile());
			file.mkdir();
		}
	}
}
