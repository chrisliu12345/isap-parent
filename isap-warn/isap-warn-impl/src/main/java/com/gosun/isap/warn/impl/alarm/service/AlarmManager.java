package com.gosun.isap.warn.impl.alarm.service;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.gosun.isap.dao.po.TAction;
import com.gosun.isap.dao.po.TActionExample;
import com.gosun.isap.dao.po.TActionLinkage;
import com.gosun.isap.dao.po.TActionLinkageExample;
import com.gosun.isap.dao.po.TActionParam;
import com.gosun.isap.dao.po.TActionParamExample;
import com.gosun.isap.dao.po.TAlarmRecord;
import com.gosun.isap.dao.po.TAlarmType;
import com.gosun.isap.dao.po.TDevice;
import com.gosun.isap.resource.api.service.TDeviceService;
import com.gosun.isap.warn.api.linkage.Sequence;
import com.gosun.isap.warn.api.linkage.livelinkage.LiveLinkageActionItem;
import com.gosun.isap.warn.api.linkage.livelinkage.UnHandledLiveLinkage;
import com.gosun.isap.warn.api.report.AlarmConfirmType;
import com.gosun.isap.warn.api.report.AlarmData;
import com.gosun.isap.warn.api.report.AlarmInnerServiceApi;
import com.gosun.isap.warn.api.service.alarm.TAlarmLinkageService;
import com.gosun.isap.warn.api.service.alarm.TAlarmRecordService;
import com.gosun.isap.warn.api.alert.service.AlertService;

/**
 * 告警服务管理类
 * 
 * @author lucf
 *
 */
public class AlarmManager implements AlarmInnerServiceApi {
	private static Logger logger = LoggerFactory.getLogger(AlarmManager.class);

	@Autowired
	private TAlarmRecordService alarmRecordService;

	@Autowired
	private TDeviceService deviceService;

	@Autowired
	private AlertService alertService;

	@Autowired
	private TAlarmLinkageService alarmLinkageService;

	// 当前已配置的告警联动
	private List<TActionLinkage> alarmLinkageList = new ArrayList<TActionLinkage>();

	// 待处理的告警联动实况
	private List<UnHandledLiveLinkage> liveLinkageList = new ArrayList<UnHandledLiveLinkage>();

	/**
	 * 初始化
	 */
	public void init() {
		TActionLinkageExample example = new TActionLinkageExample();

		List<TActionLinkage> items = alarmLinkageService.getAlarmLinkage(example);

		alarmLinkageList.addAll(items);
	}

	@Override
	public void pushAlarmInfo(AlarmData data) {

		TAlarmRecord record = new TAlarmRecord();

		TDevice device = deviceService.queryPlatDeviceByCode(data.getPlatID(), data.getDeviceID());

		if (device == null) {
			logger.error("Fail to query device by plat id and device code.");
			return;
		}

		record.setDevId(device.getId());
		record.setAlarmType(new Long(data.getAlarmType()));
		record.setFlag(new Byte((byte) (data.getAlarmFlag())));
		record.setAlarmData(data.getAlarmData());

		// 根据告警类型获取告警等级
		TAlarmType alarmType = alarmRecordService.getAlarmLevel(new Long(data.getAlarmType()));
		record.setAlarmLevel(alarmType.getAlarmLevel());

		// 设置当前时间为告警时间
		record.setAlarmTime(new Date());

		// 设置为未确认状态
		record.setConfirm(new Byte(AlarmConfirmType.UnChecked.getValue()));

		// 写入告警记录表
		if (0 != alarmRecordService.addAlarmRecord(record)) {
			logger.error("Fail to insert alarm info to t_alarm_info.");
			return;
		}

		// 如果是移动侦测告警,拌线告警，区域入侵告警需要向警情表插入一条记录
		if ((data.getAlarmType() == 0 && data.getAlarmFlag() == 0)
				|| (data.getAlarmType() == 5 && data.getAlarmFlag() == 0)
				|| (data.getAlarmType() == 6 && data.getAlarmFlag() == 0)) {
			if (0 > alertService.createAlert(device.getId(), "移动侦测")) {
				logger.error("Fail to create alert.");
			}
		}

		List<TActionLinkage> matchedItems = new ArrayList<TActionLinkage>();

		// 查找是否存在匹配的告警联动
		synchronized (alarmLinkageList) {
			for (TActionLinkage e : alarmLinkageList) {
				if ((e.getDevId().equals(device.getId())) && (e.getAlarmType().equals(new Long(data.getAlarmType())))) {
					matchedItems.add(e);
				}
			}
		}

		// 将不同的联动类型存入对应集合中
		for (TActionLinkage e : matchedItems) {
			switch (e.getActionType().intValue()) {
			case 1: {
				List<UnHandledLiveLinkage> items = getLiveLinkageItems(e);
				synchronized (liveLinkageList) {
					for (UnHandledLiveLinkage l : items) {
						liveLinkageList.add(l);
					}
				}
				break;
			}
			default:
				break;
			}
		}
	}

	@Override
	public void pushNewLinkage(TActionLinkage newItem) {
		synchronized (alarmLinkageList) {
			alarmLinkageList.add(newItem);
		}
	}

	@Override
	public void pushDeletedLinkage(Long id) {
		synchronized (alarmLinkageList) {
			for (TActionLinkage e : alarmLinkageList) {
				if (e.getId() == id) {
					alarmLinkageList.remove(e);
					return;
				}
			}
		}
	}

	@Override
	public List<LiveLinkageActionItem> getLiveLinkage(String userCode) {

		List<LiveLinkageActionItem> result = new ArrayList<LiveLinkageActionItem>();

		synchronized (liveLinkageList) {
			Iterator<UnHandledLiveLinkage> it = liveLinkageList.iterator();
			while (it.hasNext()) {
				UnHandledLiveLinkage item = it.next();

				if (item.getUserCode().equals(userCode)) {
					LiveLinkageActionItem element = new LiveLinkageActionItem(null, item.getCameraCode(),
							item.getUserCode(), item.getPaneIndex());
					result.add(element);

					it.remove();
				}
			}
		}

		return result;
	}

	private List<UnHandledLiveLinkage> getLiveLinkageItems(TActionLinkage info) {
		TActionExample exampleAction = new TActionExample();
		TActionExample.Criteria criteriaAction = exampleAction.createCriteria();
		criteriaAction.andLinkageIdEqualTo(info.getId());

		// 获取联动动作
		List<TAction> actionItems = alarmLinkageService.getAction(exampleAction);

		List<UnHandledLiveLinkage> data = new ArrayList<UnHandledLiveLinkage>();

		for (TAction e : actionItems) {
			TActionParamExample exampleParam = new TActionParamExample();
			TActionParamExample.Criteria criteriaParam = exampleParam.createCriteria();
			criteriaParam.andActionIdEqualTo(e.getId());

			List<TActionParam> actionParamItems = alarmLinkageService.getActionParam(exampleParam);

			String cameraCode = null;
			String userCode = null;
			Integer paneIndex = null;

			for (TActionParam ep : actionParamItems) {
				if ((Sequence.FIRST == ep.getParamSequence().intValue()) && (ep.getName().equals("cameraCode"))) {
					cameraCode = ep.getParamValue();
				} else if ((Sequence.SECOND == ep.getParamSequence().intValue()) && (ep.getName().equals("userCode"))) {
					userCode = ep.getParamValue();
				} else if ((Sequence.THIRD == ep.getParamSequence().intValue()) && (ep.getName().equals("paneIndex"))) {
					paneIndex = Integer.valueOf(ep.getParamValue());
				}
			}

			UnHandledLiveLinkage element = new UnHandledLiveLinkage();

			element.setCameraCode(cameraCode);
			element.setUserCode(userCode);
			element.setPaneIndex(paneIndex);
			element.setOccuredTime(new Date());
			// 设置有效时间为30秒
			element.setValidTime(30);

			data.add(element);
		}

		return data;
	}
}
