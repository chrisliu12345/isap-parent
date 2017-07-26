package com.gosun.isap.warn.impl.service.guard;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.gosun.isap.common.error.ErrorMessage;
import com.gosun.isap.dao.mapper.TGuardPlanMapper;
import com.gosun.isap.dao.mapper.TGuardPlanResMapper;
import com.gosun.isap.dao.mapper.customer.TGuardTimeTemplateMapperCustomer;
import com.gosun.isap.dao.po.TGuardPlan;
import com.gosun.isap.dao.po.TGuardPlanExample;
import com.gosun.isap.dao.po.TGuardPlanRes;
import com.gosun.isap.dao.po.TGuardPlanResExample;
import com.gosun.isap.warn.api.service.guard.TGuardPlanService;
import com.gosun.isap.warn.impl.guard.constants.GuardErrorCode;

@Service
public class TGuardPlanServiceImpl implements TGuardPlanService {

	@Autowired
	TGuardPlanMapper planMapper;

	@Autowired
	TGuardPlanResMapper resMapper;

	@Autowired
	TGuardTimeTemplateMapperCustomer templateMapperCust;

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public boolean saveGuardPlan(TGuardPlan plan, List<TGuardPlanRes> resList) {
		int row = planMapper.insertSelective(plan);

		// 增加时间模板引用
		templateMapperCust.increaseRefCount(plan.getTemplateId());

		for (TGuardPlanRes res : resList) {
			TGuardPlanResExample e = new TGuardPlanResExample();
			TGuardPlanResExample.Criteria c = e.createCriteria();
			c.andAlarmTypeEqualTo(res.getAlarmType());
			c.andDevIdEqualTo(res.getDevId());
			List<TGuardPlanRes> origRes = resMapper.selectByExample(e);
			if (null != origRes && origRes.size() > 0) {
				throw new IllegalArgumentException(
						ErrorMessage.get(GuardErrorCode.DEVICE_ALREADY_IN_GUARD, res.getDevId()));
			}

			// 更新计划id
			res.setPlanId(plan.getId());
			resMapper.insertSelective(res);
		}

		return 1 == row ? true : false;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public boolean deleteGuardPlanBatch(List<Long> planIds) {
		// 减少时间模板引用
		for (Long id : planIds) {
			TGuardPlan plan = planMapper.selectByPrimaryKey(id);
			if (null != plan) {
				templateMapperCust.decreaseRefCount(plan.getTemplateId());
			}
		}

		TGuardPlanExample example = new TGuardPlanExample();
		TGuardPlanExample.Criteria criteria = example.createCriteria();
		criteria.andIdIn(planIds);
		// 删除时只需要删除主表就可以了
		return planMapper.deleteByExample(example) == planIds.size() ? true : false;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public boolean modifyGuardPlan(TGuardPlan plan, List<TGuardPlanRes> resList) {
		// 解除原始的引用
		TGuardPlan origPlan = planMapper.selectByPrimaryKey(plan.getId());
		if (null != origPlan) {
			templateMapperCust.decreaseRefCount(origPlan.getTemplateId());
		}

		// 删除以前的布防资源
		TGuardPlanResExample e = new TGuardPlanResExample();
		TGuardPlanResExample.Criteria c = e.createCriteria();
		c.andPlanIdEqualTo(origPlan.getId());
		resMapper.deleteByExample(e);

		// 更新布防计划
		int row = planMapper.updateByPrimaryKeySelective(plan);

		// 增加新的引用
		templateMapperCust.increaseRefCount(plan.getTemplateId());

		// 增加布防资源
		for (TGuardPlanRes res : resList) {
			TGuardPlanResExample e1 = new TGuardPlanResExample();
			TGuardPlanResExample.Criteria c1 = e.createCriteria();
			c1.andAlarmTypeEqualTo(res.getAlarmType());
			c1.andDevIdEqualTo(res.getDevId());
			List<TGuardPlanRes> origRes = resMapper.selectByExample(e1);
			if (null != origRes && origRes.size() > 0) {
				throw new IllegalArgumentException(
						ErrorMessage.get(GuardErrorCode.DEVICE_ALREADY_IN_GUARD, res.getDevId()));
			}
			// 更新计划id
			res.setPlanId(plan.getId());
			resMapper.insertSelective(res);
		}

		return 1 == row ? true : false;
	}

	@Override
	public TGuardPlan getGuardPlan(Long planId) {
		return planMapper.selectByPrimaryKey(planId);
	}

	@Override
	public List<TGuardPlanRes> getGuardPlanRes(Long planId) {
		TGuardPlanResExample example = new TGuardPlanResExample();
		TGuardPlanResExample.Criteria criteria = example.createCriteria();
		criteria.andPlanIdEqualTo(planId);
		return resMapper.selectByExample(example);
	}

	@Override
	public List<TGuardPlan> listGuardPlanByExample(TGuardPlanExample example) {
		return planMapper.selectByExample(example);
	}

	@Override
	public int countGuardPlanByExample(TGuardPlanExample example) {
		return planMapper.countByExample(example);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public boolean modifyGuardPlanStatus(Long planId, int status) {
		TGuardPlan plan = new TGuardPlan();
		plan.setId(planId);
		plan.setStatus((byte) status);
		return 1 == planMapper.updateByPrimaryKeySelective(plan) ? true : false;
	}

	@Override
	public boolean deleteGuardPlan(Long planId) {
		return 1 == planMapper.deleteByPrimaryKey(planId) ? true : false;
	}
}
