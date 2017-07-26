package com.gosun.isap.warn.api.service.guard;

import java.util.List;

import com.gosun.isap.dao.po.TGuardPlan;
import com.gosun.isap.dao.po.TGuardPlanExample;
import com.gosun.isap.dao.po.TGuardPlanRes;

public interface TGuardPlanService {
	boolean saveGuardPlan(TGuardPlan plan, List<TGuardPlanRes> resList);
	
	boolean deleteGuardPlan(Long planId);

	boolean deleteGuardPlanBatch(List<Long> planIds);

	boolean modifyGuardPlan(TGuardPlan plan, List<TGuardPlanRes> resList);

	boolean modifyGuardPlanStatus(Long planId, int status);

	TGuardPlan getGuardPlan(Long planId);

	List<TGuardPlanRes> getGuardPlanRes(Long planId);

	int countGuardPlanByExample(TGuardPlanExample example);

	List<TGuardPlan> listGuardPlanByExample(TGuardPlanExample example);
}
