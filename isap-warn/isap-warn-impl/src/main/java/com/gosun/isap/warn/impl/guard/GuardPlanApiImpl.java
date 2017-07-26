package com.gosun.isap.warn.impl.guard;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.QueryParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.gosun.isap.common.BeanValidate;
import com.gosun.isap.common.IdwrapperBat;
import com.gosun.isap.common.OrderSqlUtil;
import com.gosun.isap.common.ResponseResult;
import com.gosun.isap.common.error.ErrorCode;
import com.gosun.isap.dao.po.TGuardPlan;
import com.gosun.isap.dao.po.TGuardPlanExample;
import com.gosun.isap.dao.po.TGuardPlanRes;
import com.gosun.isap.dao.po.TGuardTimeTemplate;
import com.gosun.isap.operlog.api.OperateType;
import com.gosun.isap.operlog.api.ServiceType;
import com.gosun.isap.operlog.api.annotation.SysOperateLog;
import com.gosun.isap.warn.api.guard.GuardPlan;
import com.gosun.isap.warn.api.guard.GuardPlanRes;
import com.gosun.isap.warn.api.guard.GuardPlanWrapper;
import com.gosun.isap.warn.api.guard.IGuardPlanApi;
import com.gosun.isap.warn.api.service.guard.TGuardPlanService;
import com.gosun.isap.warn.api.service.guard.TGuardTimeTemplateService;
import com.gosun.isap.warn.impl.guard.constants.GuardErrorCode;
import com.gosun.isap.warn.impl.guard.constants.GuardPlanStatus;
import com.gosun.isap.warn.impl.guard.job.AutoGuardJobHelper;

/**
 * 布防计划rest API实现
 * 
 * @author liuzk
 *
 */
@SuppressWarnings("rawtypes")
public class GuardPlanApiImpl implements IGuardPlanApi {
	private static Logger logger = LoggerFactory.getLogger(GuardPlanApiImpl.class);

	@Autowired
	private TGuardPlanService guardPlanService;

	@Autowired
	private TGuardTimeTemplateService templateService;

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	@SysOperateLog(serviceType = ServiceType.CONFIG_RES, operateType = OperateType.CONFIG_ADD, description = "添加布防计划")
	public ResponseResult addGuardPlan(GuardPlanWrapper wrapper) {
		ResponseResult response = ResponseResult.ok();

		if (null == wrapper || null == wrapper.getPlan() || null == wrapper.getResources()) {
			throw new IllegalArgumentException();
		}
		String msg = BeanValidate.validateModel(wrapper.getPlan());
		if (StringUtils.isNotEmpty(msg)) {
			logger.error(msg);
			throw new IllegalArgumentException();
		}

		// 默认布防状态设置为停止
		wrapper.getPlan().setStatus(GuardPlanStatus.STOPPED);
		TGuardPlan daoPlan = typeConvert(wrapper.getPlan());
		List<TGuardPlanRes> daoResList = new ArrayList<TGuardPlanRes>();
		for (GuardPlanRes r : wrapper.getResources()) {
			daoResList.add(typeConvert(r));
		}

		// 保存布防计划
		guardPlanService.saveGuardPlan(daoPlan, daoResList);

		// 创建布防任务
		AutoGuardJobHelper.createGuardJob(daoPlan);

		// 启动布防计划
		startGuardPlan(daoPlan);

		return response;
	}

	@Override
	@SysOperateLog(serviceType = ServiceType.CONFIG_RES, operateType = OperateType.CONFIG_DEL, description = "批量删除布防计划")
	public ResponseResult deleteGaurdPlan(IdwrapperBat<Long> planIds) {
		ResponseResult response = ResponseResult.ok();

		if (null == planIds) {
			throw new IllegalArgumentException();
		}

		// 校验是否满足删除条件
		for (Long id : planIds.getId()) {
			TGuardPlan plan = guardPlanService.getGuardPlan(id);
			if (null == plan) {
				logger.warn("The guard plan " + id + " not exist, ignore delete");
				continue;
			}

			if (!validateModifyOrUpdate(plan)) {
				logger.error("The guard plan " + id + " is running, you must stop it before delete");
				response.setErrorEx(GuardErrorCode.GUARD_PLAN_IS_RUNNING, null);
				return response;
			}

			deleteGuardPlan(plan);
		}

		return response;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	@SysOperateLog(serviceType = ServiceType.CONFIG_RES, operateType = OperateType.CONFIG_MOD, description = "修改布防计划")
	public ResponseResult modifyGuardPlan(GuardPlanWrapper wrapper) {
		ResponseResult response = ResponseResult.ok();

		String msg = BeanValidate.validateModel(wrapper);
		if (StringUtils.isNotEmpty(msg)) {
			logger.error(msg);
			throw new IllegalArgumentException();
		}

		// 运行状态不允许修改
		TGuardPlan origPlan = guardPlanService.getGuardPlan(wrapper.getPlan().getId());
		if (!validateModifyOrUpdate(origPlan)) {
			logger.error("Plan is running, you must stop it before modify");
			response.setErrorEx(GuardErrorCode.GUARD_PLAN_IS_RUNNING, null);
			return response;
		}

		// 删除原始的布防计划任务
		AutoGuardJobHelper.deleteGuardJob(origPlan);

		TGuardPlan daoPlan = typeConvert(wrapper.getPlan());
		List<TGuardPlanRes> daoResList = new ArrayList<TGuardPlanRes>();
		for (GuardPlanRes item : wrapper.getResources()) {
			daoResList.add(typeConvert(item));
		}

		// 修改布防计划
		guardPlanService.modifyGuardPlan(daoPlan, daoResList);

		// 生成新的布防任务
		AutoGuardJobHelper.createGuardJob(daoPlan);

		// 启动布防计划
		startGuardPlan(daoPlan);

		return response;
	}

	@Override
	@SysOperateLog(serviceType = ServiceType.CONFIG_RES, operateType = OperateType.USER_START, description = "批量启动布防计划")
	public ResponseResult startGuardPlan(IdwrapperBat<Long> planIds) {
		ResponseResult response = ResponseResult.ok();

		for (Long id : planIds.getId()) {
			TGuardPlan daoPlan = guardPlanService.getGuardPlan(id);
			if (null == daoPlan) {
				logger.warn("The guard plan " + id + " not exist, ignore to start it");
				continue;
			}
			startGuardPlan(daoPlan);
		}

		return response;
	}

	@Override
	@SysOperateLog(serviceType = ServiceType.CONFIG_RES, operateType = OperateType.USER_STOP, description = "批量停止布防计划")
	public ResponseResult stopGuardPlan(IdwrapperBat<Long> planIds) {
		ResponseResult response = ResponseResult.ok();

		for (Long id : planIds.getId()) {
			TGuardPlan daoPlan = guardPlanService.getGuardPlan(id);
			if (null == daoPlan) {
				logger.warn("The guard plan " + id + " not exist, ignore to stop it");
				continue;
			}
			stopGuardPlan(daoPlan);
		}

		return response;
	}

	@Override
	public ResponseResult<GuardPlanWrapper> getGuardPlan(Long planId) {
		ResponseResult<GuardPlanWrapper> response = ResponseResult.ok();

		TGuardPlan daoPlan = guardPlanService.getGuardPlan(planId);
		if (null == daoPlan) {
			logger.error("Guard plan not exist, id " + planId);
			response.setErrorEx(ErrorCode.ERR_RECORD_NOT_EXIST, null);
			return response;
		}

		List<TGuardPlanRes> daoResList = guardPlanService.getGuardPlanRes(planId);

		GuardPlanWrapper wrapper = new GuardPlanWrapper();
		wrapper.setPlan(typeConvert(daoPlan));
		if (null != daoResList) {
			List<GuardPlanRes> resList = new ArrayList<GuardPlanRes>();
			for (TGuardPlanRes item : daoResList) {
				resList.add(typeConvert(item));
			}
			wrapper.setResources(resList);
		}

		response.setBody(wrapper);

		return response;
	}

	@Override
	public ResponseResult<List<GuardPlan>> listGuardPlans(Integer start, Integer limit, String sort, String fuzzyField,
			String fuzzyValue) {
		ResponseResult<List<GuardPlan>> response = ResponseResult.ok();

		TGuardPlanExample example = new TGuardPlanExample();
		example.setLimit(limit);
		example.setOffset(start);

		if (!StringUtils.isEmpty(sort)) {
			String sqlOrder = OrderSqlUtil.getOrderSqlStringBySort(sort);
			example.setOrderByClause(sqlOrder);
		}
		if (!StringUtils.isEmpty(fuzzyField) && !StringUtils.isEmpty(fuzzyValue)) {
			TGuardPlanExample.Criteria c = example.createCriteria();
			c.andGeneralLike(fuzzyField, "%" + fuzzyValue + "%");
		}

		int total = guardPlanService.countGuardPlanByExample(example);
		if (total > 0) {
			List<TGuardPlan> daoPlanList = guardPlanService.listGuardPlanByExample(example);
			List<GuardPlan> planList = new ArrayList<GuardPlan>();
			for (TGuardPlan item : daoPlanList) {
				planList.add(typeConvert(item));
			}
			response.setBody(planList);
		}
		response.setTotal(total);

		return response;
	}

	private TGuardPlan typeConvert(GuardPlan obj) {
		TGuardPlan daoObj = new TGuardPlan();
		daoObj.setName(obj.getName());
		daoObj.setStatus(obj.getStatus());
		daoObj.setId(obj.getId());
		daoObj.setDescription(obj.getDescription());
		daoObj.setTemplateId(obj.getTemplateId());
		return daoObj;
	}

	private GuardPlan typeConvert(TGuardPlan daoObj) {
		GuardPlan guardPlan = new GuardPlan();
		guardPlan.setName(daoObj.getName());
		guardPlan.setStatus(daoObj.getStatus());
		guardPlan.setCreateTime(daoObj.getAddTime());
		guardPlan.setId(daoObj.getId());
		guardPlan.setDescription(daoObj.getDescription());
		guardPlan.setTemplateId(daoObj.getTemplateId());
		TGuardTimeTemplate template = templateService.getTemplate(daoObj.getTemplateId());
		guardPlan.setTemplateName(template.getName());
		return guardPlan;
	}

	private TGuardPlanRes typeConvert(GuardPlanRes obj) {
		TGuardPlanRes daoObj = new TGuardPlanRes();
		daoObj.setAlarmType(obj.getAlarmType());
		daoObj.setDevId(obj.getDevId());
		daoObj.setGuardStatus(obj.getGuardStatus());
		daoObj.setPlanId(obj.getPlanId());
		return daoObj;
	}

	private GuardPlanRes typeConvert(TGuardPlanRes daoObj) {
		GuardPlanRes obj = new GuardPlanRes();
		obj.setAlarmType(daoObj.getAlarmType());
		obj.setDevId(daoObj.getDevId());
		obj.setGuardStatus(daoObj.getGuardStatus());
		obj.setPlanId(daoObj.getPlanId());
		return obj;
	}

	@Transactional
	private void startGuardPlan(TGuardPlan plan) {
		guardPlanService.modifyGuardPlanStatus(plan.getId(), GuardPlanStatus.RUNNING);
		// 手动布防
		// 原因：因为布防计划是通过quartz任务来模拟的，只有临界点的时候才会变化，判断当前是否处于计划时间内，如果是，则需要手动触发
		if (TimeTemplateHelper.isBetweenGuardPeriod(plan.getTemplateId(), new Date())) {
			AutoGuardJobHelper.manualEnableGuard(plan);
		}
	}

	@Transactional
	private void stopGuardPlan(TGuardPlan plan) {
		AutoGuardJobHelper.manualDisableGuard(plan);
		guardPlanService.modifyGuardPlanStatus(plan.getId(), GuardPlanStatus.STOPPED);
	}

	private boolean validateModifyOrUpdate(TGuardPlan plan) {
		return (plan.getStatus() == GuardPlanStatus.RUNNING) ? false : true;
	}

	@Transactional
	private void deleteGuardPlan(TGuardPlan plan) {
		AutoGuardJobHelper.deleteGuardJob(plan);
		guardPlanService.deleteGuardPlan(plan.getId());
	}
}
