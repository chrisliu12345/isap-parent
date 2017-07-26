package com.gosun.isap.proxy.instance;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.gosun.isap.common.utils.JsonUtils;
import com.gosun.isap.proxy.api.instance.CallNativeSdkException;
import com.gosun.isap.proxy.api.sdk.AlarmConfigParam;
import com.gosun.isap.proxy.api.sdk.AlarmEvent;
import com.gosun.isap.proxy.api.sdk.Device;
import com.gosun.isap.proxy.api.sdk.EventCallback;
import com.gosun.isap.proxy.api.sdk.LoginParam;
import com.gosun.isap.proxy.api.sdk.PicCaptureParam;
import com.gosun.isap.proxy.api.sdk.Preset;
import com.gosun.isap.proxy.api.sdk.PtzControlParam;
import com.gosun.isap.proxy.api.sdk.SdkInfo;
import com.gosun.isap.proxy.api.sdk.constants.PicFormatType;

public class SdkApiImplTest {
	static NativeSdkCallerImpl api = new NativeSdkCallerImpl();
	String DEVICE_ID = "1000012$1$0$0";

	@BeforeClass
	public static void init() throws CallNativeSdkException, InterruptedException {
		System.setProperty("java.library.path", "F:\\jni");

		System.out.println("----init------");
		api.init("dahua");

		LoginParam param = new LoginParam();
		param.user = "system";
		param.password = "1234567a";
		param.ipAddress = "191.191.16.113";
		param.port = 9000;

		System.out.println("----login-----");
		api.login(param);

		api.logout();
		try {
			param.password = "aaa";
			api.login(param);
		} catch (Throwable e) {

		}
		param.password = "1234567a";
		api.login(param);

		Thread.sleep(3000);
	}

	@AfterClass
	public static void destroy() throws CallNativeSdkException, InterruptedException {
		Thread.sleep(3000);
		System.out.println("-------logout-----");
		api.logout();
		System.out.println("------destroy-------");
		api.destroy();
	}

	@Ignore
	public void testInit() throws CallNativeSdkException {
		api.init("dahua");
	}

	@Ignore
	public void testDestroy() throws CallNativeSdkException {
		api.destroy();
	}

	@Test
	public void testGetPid() {
		String pid = api.getPid();
		System.out.println("pid=" + pid);
	}

	@Test
	public void testGetSdkInfo() throws CallNativeSdkException {
		SdkInfo info = api.getSdkInfo();
		System.out.println(info.vendor);
		System.out.println(info.version);
	}

	@Ignore
	@Test
	public void testLogin() throws CallNativeSdkException {
		LoginParam param = new LoginParam();
		param.user = "ncys";
		param.password = "g12345678";
		param.ipAddress = "218.64.84.79";
		param.port = 9000;

		api.login(param);
	}

	@Ignore
	@Test
	public void testLogout() {

	}

	@Test
	public void testPtzControl() throws CallNativeSdkException {
		System.out.println("---------ptz-------------");
		PtzControlParam param = new PtzControlParam();
		param.deviceId = DEVICE_ID;
		param.szName = "";
		param.cmdType = PtzControlParam.PtzCmdType.PTZ_UP;
		param.param1 = 10;
		param.param2 = 1;
		api.ptzControl(param);
		System.out.println("------ptz operate ok--------");
	}

	@Test
	public void testEnableAlaram() throws CallNativeSdkException {
		AlarmConfigParam param = new AlarmConfigParam();
		param.alarmDeviceId = DEVICE_ID;
		param.alarmInputNo = -1;
		param.alarmType = -1;
		param.videoNo = -1;

		api.enableAlaram(param);

		Assert.assertTrue(true);
	}

	@Ignore
	@Test
	public void testDisableAlarm() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetEventCallback() throws CallNativeSdkException {
		api.setEventCallback(new EventCallback() {

			@Override
			public void onEvent(int event, Object data) {
				System.out.println("---get event, type " + event);
				if (data instanceof AlarmEvent) {
					AlarmEvent e = (AlarmEvent) data;
					System.out.println("devcode : " + e.deviceCode);
					System.out.println("alarmType : " + e.alarmType);

				}
			}

		});

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void testQueryAllDevices() throws Exception {
		List<Device> devices = api.queryAllDevices();
		System.out.println("size = " + devices.size());
		for (Device d : devices) {
			System.out
					.println("type=" + d.deviceType + ", chlType=" + d.channelType + ", chlSubType=" + d.chnanelSubType
							+ ", id=" + d.deviceCode + ", parentId=" + d.parentDeviceCode + ", name=" + d.alias);
			// String json = JsonUtils.obj2json(d);
			// System.out.println(json);
		}
	}

	@Test
	public void testQueryPtzPreset() throws Exception {

		List<Preset> presets = api.queryPtzPreset(DEVICE_ID);

		for (Preset p : presets) {
			String json = JsonUtils.obj2json(p);
			System.out.println(json);
		}
	}

	@Test
	public void testPicCapture() throws Exception {
		PicCaptureParam param = new PicCaptureParam();
		param.deviceCode = DEVICE_ID;
		param.picFormatType = PicFormatType.JPEG;
		param.filePath = "F:\\a.jpg";
		api.capturePicture(param);

		Thread.sleep(10000);
	}
}
