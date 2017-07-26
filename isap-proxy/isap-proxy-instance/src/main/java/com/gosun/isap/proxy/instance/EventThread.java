package com.gosun.isap.proxy.instance;

import javax.jms.JMSException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gosun.isap.common.mc.MessagePublisher;
import com.gosun.isap.common.utils.JsonUtils;
import com.gosun.isap.proxy.api.instance.CallNativeSdkException;
import com.gosun.isap.proxy.api.instance.ProxyEventTopic;
import com.gosun.isap.proxy.api.sdk.AlarmConfigParam;
import com.gosun.isap.proxy.api.sdk.AlarmEvent;
import com.gosun.isap.proxy.api.sdk.DeviceStatusEvent;
import com.gosun.isap.proxy.api.sdk.EventCallback;
import com.gosun.isap.proxy.api.sdk.LoginParam;
import com.gosun.isap.proxy.api.sdk.PlatStatusEvent;
import com.gosun.isap.proxy.api.sdk.constants.AlarmType;
import com.gosun.isap.proxy.api.sdk.constants.EventType;

/**
 * 事件处理线程
 * 
 * @author liuzk
 *
 */
public class EventThread extends Thread {
	private static Logger logger = LoggerFactory.getLogger(EventThread.class);

	private static final int CHECK_INTERVAL = 5 * 1000;

	private String proxyId;
	private String ipAddress;
	private Integer port;
	private String user;
	private String password;

	private MessagePublisher publisher;
	private ISdkCaller sdk;

	/**
	 * 初始化
	 * 
	 * @param proxyId
	 *            proxy ID
	 * @param ipAddress
	 *            IP地址
	 * @param port
	 *            端口
	 * @param user
	 *            登录用户
	 * @param password
	 *            登录密码
	 */
	public void init(String proxyId, String ipAddress, Integer port, String user, String password) {
		this.ipAddress = ipAddress;
		this.port = port;
		this.user = user;
		this.password = password;
		this.proxyId = proxyId;

		try {
			publisher.registerTopic(ProxyEventTopic.PLAT_STATUS);
			publisher.registerTopic(ProxyEventTopic.ALARM_EVENT);
			publisher.registerTopic(ProxyEventTopic.DEVICE_STATUS);
		} catch (JMSException e) {
			logger.error(e.getMessage());
		}
	}

	/**
	 * property数据变更后调用
	 * 
	 * @param ipAddress
	 *            平台IP
	 * @param port
	 *            平台端口
	 * @param user
	 *            用户
	 * @param password
	 *            密码
	 */
	public void onPropertyChanged(String ipAddress, Integer port, String user, String password) {
		// 更新数据
		this.ipAddress = ipAddress;
		this.port = port;
		this.user = user;
		this.password = password;

		// 退出登录
		if (sdk.isLogined()) {
			try {
				sdk.logout();
			} catch (CallNativeSdkException e) {
				logger.error(e.getMessage());
			}
		}

		// 上报退出事件
		try {
			PlatStatusEvent e = new PlatStatusEvent();
			e.setPlatId(Long.parseLong(proxyId));
			e.setOnline(false);
			logger.info("Notify plat offline, proxyId " + proxyId);
			publisher.getTopicChannel(ProxyEventTopic.PLAT_STATUS).publishMessage(JsonUtils.obj2json(e));
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	@Override
	public void run() {
		while (true) {
			try {
				if (!sdk.isLogined()) {

					LoginParam loginParam = new LoginParam();
					loginParam.ipAddress = ipAddress;
					loginParam.port = (short) (int) port;
					loginParam.loginType = 1;
					loginParam.user = user;
					loginParam.password = password;

					// 登录平台
					sdk.login(loginParam);

					// 上报登录成功事件
					try {
						PlatStatusEvent e = new PlatStatusEvent();
						e.setPlatId(Long.parseLong(proxyId));
						e.setOnline(true);
						logger.info("Notify plat online, proxyId " + proxyId);
						publisher.getTopicChannel(ProxyEventTopic.PLAT_STATUS).publishMessage(JsonUtils.obj2json(e));
					} catch (Exception e) {
						logger.error(e.getMessage());
					}

					// 订阅基础平台告警
					subscribeAlarm(AlarmType.ALARM_TYPE_MOVEDET);
					subscribeAlarm(AlarmType.ALARM_TYPE_PININPUT);
					subscribeAlarm(AlarmType.ALARM_TYPE_VIDLOST);
					subscribeAlarm(AlarmType.ALARM_TYPE_VIDEOSHELTER);
					subscribeAlarm(AlarmType.ALARM_TYPE_DISKFULL);
					subscribeAlarm(AlarmType.ALARM_TYPE_CROSSLINE);
					subscribeAlarm(AlarmType.ALARM_TYPE_CROSSREGION);

					// 设置事件回调
					sdk.setEventCallback(new EventCallback() {
						private static final long serialVersionUID = 1L;

						@Override
						public void onEvent(int event, Object data) {
							String topic = null;
							Object msgObj = null;

							/**
							 * 处理事件
							 */
							switch (event) {
							case EventType.DISCONNECT_EVENT: {
								sdk.setLogined(false);
								topic = ProxyEventTopic.PLAT_STATUS;
								PlatStatusEvent e = new PlatStatusEvent();
								e.setPlatId(Long.parseLong(proxyId));
								e.setOnline(false);
								msgObj = e;
								logger.info("Notify plat offline, proxyId " + proxyId);
								break;
							}
							case EventType.DEV_STATUS_EVENT: {
								topic = ProxyEventTopic.DEVICE_STATUS;
								if (data instanceof DeviceStatusEvent) {
									DeviceStatusEvent e = (DeviceStatusEvent) data;
									e.setPlatId(Long.parseLong(proxyId));
									msgObj = e;
									logger.info("Notify device status changed, proxyId=" + proxyId + ", dev="
											+ e.deviceCode + ", online=" + e.online);
								}
								break;
							}
							case EventType.DEV_ARARM_EVENT: {
								topic = ProxyEventTopic.ALARM_EVENT;
								if (data instanceof AlarmEvent) {
									AlarmEvent e = (AlarmEvent) data;
									e.setPlatId(Long.parseLong(proxyId));
									msgObj = e;
									logger.info("Notify device alarm event, proxyId=" + proxyId + ", dev="
											+ e.deviceCode + ", eventType=" + e.getAlarmType());
								}
								break;
							}
							default: {
								break;
							}
							}

							try {
								// 向通道发布消息
								publisher.getTopicChannel(topic).publishMessage(JsonUtils.obj2json(msgObj));
							} catch (Exception e) {
								logger.error(e.getMessage());
							}
						}
					});
				}

			} catch (CallNativeSdkException e) {
				logger.error(e.getMessage());
			}

			try {
				Thread.sleep(CHECK_INTERVAL);
			} catch (InterruptedException e) {
			}
		}
	}

	public ISdkCaller getSdk() {
		return sdk;
	}

	public void setSdk(ISdkCaller sdk) {
		this.sdk = sdk;
	}

	public MessagePublisher getPublisher() {
		return publisher;
	}

	public void setPublisher(MessagePublisher publisher) {
		this.publisher = publisher;
	}

	private void subscribeAlarm(int alarmType) throws CallNativeSdkException {
		AlarmConfigParam p = new AlarmConfigParam();
		p.alarmDeviceId = "*"; // 通配符，订阅所有设备
		p.alarmInputNo = -1;
		p.videoNo = -1;
		p.alarmType = alarmType;
		sdk.enableAlaram(p);
	}
}
