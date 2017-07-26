package com.gosun.isap.warn.api.service.alarm;

import java.util.List;

import com.gosun.isap.dao.po.TActionLinkage;
import com.gosun.isap.dao.po.TActionLinkageExample;
import com.gosun.isap.dao.po.TAction;
import com.gosun.isap.dao.po.TActionExample;
import com.gosun.isap.dao.po.TActionParam;
import com.gosun.isap.dao.po.TActionParamExample;

/**
 * 告警联动数据服务接口
 * 
 * @author lucf
 *
 */
public interface TAlarmLinkageService {

	/**
	 * 获取告警联动业务(只涉及表t_action_linkage)
	 * 
	 * @param example
	 *            查询条件
	 * @return 告警联动查询结果
	 */
	List<TActionLinkage> getAlarmLinkage(TActionLinkageExample example);

	/**
	 * 添加告警联动业务(只涉及表t_action_linkage)
	 * 
	 * @param item
	 *            联动业务数据
	 * @return 执行结果
	 */
	int insertLinkageItem(TActionLinkage item);

	/**
	 * 根据主键删除告警联动业务（只涉及表t_action_linkage）
	 * 
	 * @param id
	 *            联动编号
	 */
	void deleteLinkageItemByKey(Long id);

	/**
	 * 添加告警联动动作对象(涉及表t_action和t_action_param)
	 * 
	 * @param actionItem
	 *            联动单元数据
	 * @param actionParamItems
	 *            联动参数数据
	 * @return 执行结果
	 */
	int insertLinkageActionObj(TAction actionItem, List<TActionParam> actionParamItems);

	/**
	 * 获取告警联动动作(涉及表t_action)
	 * 
	 * @param example
	 *            查询条件
	 * @return 查询结果
	 */
	List<TAction> getAction(TActionExample example);

	/**
	 * 获取告警联动动作参数(涉及表t_action_param)
	 * 
	 * @param example
	 *            查询条件
	 * @return 查询结果
	 */
	List<TActionParam> getActionParam(TActionParamExample example);

	/**
	 * 获取告警联动动作数目(涉及表t_action)
	 * 
	 * @param example
	 *            查询条件
	 * @return 执行结果
	 */
	int getActionCount(TActionExample example);

	/**
	 * 删除告警联动动作(涉及表t_action, 由于外键关联t_action_param中的记录自动删除)
	 * 
	 * @param items
	 *            需要删除的联动集合
	 */
	void deleteActionItems(List<Long> items);
}