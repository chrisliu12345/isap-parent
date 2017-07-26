package com.gosun.isap.warn.api.linkage;

import java.util.List;

import com.gosun.isap.common.IdwrapperBat;
import com.gosun.isap.common.ResponseResult;
import com.gosun.isap.warn.api.linkage.livelinkage.LiveLinkageActionItem;
import com.gosun.isap.warn.api.linkage.livelinkage.LiveLinkageInfo;
import com.gosun.isap.warn.api.linkage.livelinkage.TriggeredLiveLinkageItem;

/**
 * 告警联动API
 * 
 * @author lucf
 *
 */
public interface AlarmLinkageApi {

	/**
	 * 添加告警联动实况
	 * 
	 * @param info
	 *            实况联动信息
	 * @return 添加成功或失败的结果
	 */
	ResponseResult addLiveLinkage(LiveLinkageInfo info);

	/**
	 * 查询告警联动实况
	 * 
	 * @param start
	 *            起始偏移量
	 * @param limit
	 *            分页数目
	 * @param alarmDeviceID
	 *            所查询的设备编码
	 * @param alarmType
	 *            告警类型
	 * @return 查询返回的结果
	 */
	ResponseResult<List<LiveLinkageActionItem>> getLiveLinkage(Integer start, Integer limit, String alarmDeviceID,
			Integer alarmType);

	/**
	 * 删除告警联动实况
	 * 
	 * @param idSet
	 *            需要删除的告警联动ID集合
	 * @return 删除成功或失败的结果
	 */
	ResponseResult deleteLiveLinkage(IdwrapperBat<Long> idSet);

	/**
	 * 查询触发的联动实况
	 * 
	 * @return 查询到的已触发的实况联动的结果
	 */
	ResponseResult<List<TriggeredLiveLinkageItem>> getTriggeredLiveLinkage();
}
