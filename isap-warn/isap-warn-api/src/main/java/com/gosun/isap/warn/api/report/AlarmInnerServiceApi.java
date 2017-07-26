package com.gosun.isap.warn.api.report;

import java.util.List;

import com.gosun.isap.warn.api.linkage.livelinkage.LiveLinkageActionItem;
import com.gosun.isap.dao.po.TActionLinkage;

/**
 * 告警模块内部服务接口
 * 
 * @author lucf
 *
 */
public interface AlarmInnerServiceApi {
	/**
	 * 向告警管理服务上报告警
	 * 
	 * @param data
	 *            告警数据
	 */
	void pushAlarmInfo(AlarmData data);

	/**
	 * 向告警管理服务上报新增的告警联动配置
	 * 
	 * @param newItem
	 *            告警联动配置
	 */
	void pushNewLinkage(TActionLinkage newItem);

	/**
	 * 向告警管理服务上报删除的告警联动配置
	 * 
	 * @param id
	 *            联动编码
	 */
	void pushDeletedLinkage(Long id);

	/**
	 * 根据用户编码向告警管理服务获取告警联动实况
	 * 
	 * @param userCode
	 *            用户编码
	 * @return 告警联动实况集合
	 */
	List<LiveLinkageActionItem> getLiveLinkage(String userCode);
}
