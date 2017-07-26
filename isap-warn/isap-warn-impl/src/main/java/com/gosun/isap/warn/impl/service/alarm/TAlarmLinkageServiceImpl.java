package com.gosun.isap.warn.impl.service.alarm;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.gosun.isap.dao.mapper.TActionLinkageMapper;
import com.gosun.isap.dao.mapper.TActionMapper;
import com.gosun.isap.dao.mapper.TActionParamMapper;
import com.gosun.isap.dao.po.TAction;
import com.gosun.isap.dao.po.TActionExample;
import com.gosun.isap.dao.po.TActionLinkage;
import com.gosun.isap.dao.po.TActionLinkageExample;
import com.gosun.isap.dao.po.TActionParam;
import com.gosun.isap.dao.po.TActionParamExample;
import com.gosun.isap.warn.api.service.alarm.TAlarmLinkageService;

/**
 * 告警联动数据服务实现
 * 
 * @author lucf
 *
 */
@Service
public class TAlarmLinkageServiceImpl implements TAlarmLinkageService {

	@Autowired
	private TActionLinkageMapper actionLinkageMapper;

	@Autowired
	private TActionMapper actionMapper;

	@Autowired
	private TActionParamMapper actionParamMapper;

	@Override
	public List<TActionLinkage> getAlarmLinkage(TActionLinkageExample example) {
		return actionLinkageMapper.selectByExample(example);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public int insertLinkageItem(TActionLinkage item) {
		if (1 == actionLinkageMapper.insert(item)) {
			return 0;
		} else {
			return -1;
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void deleteLinkageItemByKey(Long id) {
		actionLinkageMapper.deleteByPrimaryKey(id);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public int insertLinkageActionObj(TAction actionItem, List<TActionParam> actionParamItems) {

		int result;

		// step1:向t_action表插入一条数据
		result = actionMapper.insert(actionItem);
		if (1 != result) {
			return -1;
		}

		Long actionID = actionItem.getId();

		// step2:向t_action_param表插入N条数据（N为动作参数数目）
		for (TActionParam e : actionParamItems) {
			e.setActionId(actionID);

			result = actionParamMapper.insert(e);
			if (1 != result) {
				return -1;
			}
		}

		return 0;
	}

	@Override
	public List<TAction> getAction(TActionExample example) {
		return actionMapper.selectByExample(example);
	}

	@Override
	public List<TActionParam> getActionParam(TActionParamExample example) {
		return actionParamMapper.selectByExample(example);
	}

	@Override
	public int getActionCount(TActionExample example) {
		return actionMapper.countByExample(example);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void deleteActionItems(List<Long> items) {
		TActionExample example = new TActionExample();
		TActionExample.Criteria criteria = example.createCriteria();
		criteria.andIdIn(items);

		actionMapper.deleteByExample(example);
	}
}