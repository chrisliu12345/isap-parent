package com.gosun.isap.common.jpush;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.gosun.isap.common.Idwrapper;
import com.gosun.isap.common.IdwrapperBat;
import com.gosun.isap.common.utils.JsonUtils;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.push.PushClient;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

/**
 * 测试推送
 * 
 * @author lyf
 *
 */
public class IsapJpushUtil {
	public static final String WARN_ALERT_KEY = "warnName";// ios
															// alert里面的值，andriod里extra字段包含的告警名称的key名字

	public static void main(String[] arg) {
		try {
			//warmInfoPushBroadCast("新的告警信息待处理");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param alarmName
	 *            告警信息类型名称
	 * @throws Exception
	 */
	public static void warmInfoPushBroadCast(String alarmName, String userId) throws Exception {
		Audience audience = Audience.tag(new String[] { userId });
		PushPayload pushPayload = PushPayload.newBuilder().setPlatform(Platform.all()).setAudience(Audience.all())
				.setNotification(Notification.newBuilder()
						.addPlatformNotification(AndroidNotification.newBuilder().setAlert("")
								.addExtra(WARN_ALERT_KEY, alarmName).build())
						.addPlatformNotification(IosNotification.newBuilder().setAlert(alarmName).build()).build())
				.setOptions(Options.newBuilder().setApnsProduction(false).setTimeToLive(60).build()).setAudience(audience).build();
		ClientConfig clientConfig = ClientConfig.getInstance();
		PushClient pushClient = new PushClient(JPAppInfo.masterSecret, JPAppInfo.appKey, null, clientConfig);
		pushClient.sendPush(pushPayload);
	}

}
