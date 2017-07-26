package com.gosun.isap.warn.impl.guard;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.gosun.isap.dao.po.TGuardSingleTime;
import com.gosun.isap.dao.po.TGuardTimeTemplate;
import com.gosun.isap.dao.po.TGuardTimeTemplateExample;
import com.gosun.isap.dao.po.TGuardWeekTime;
import com.gosun.isap.operlog.api.OperateType;
import com.gosun.isap.operlog.api.ServiceType;
import com.gosun.isap.operlog.api.annotation.SysOperateLog;
import com.gosun.isap.warn.api.guard.GuardSingleTime;
import com.gosun.isap.warn.api.guard.GuardTimeTemplate;
import com.gosun.isap.warn.api.guard.GuardWeekTime;
import com.gosun.isap.warn.api.guard.ITimeTemplateApi;
import com.gosun.isap.warn.api.guard.SingleTimeTemplateWrapper;
import com.gosun.isap.warn.api.guard.WeekTimeTemplateWrapper;
import com.gosun.isap.warn.api.service.guard.TGuardTimeTemplateService;
import com.gosun.isap.warn.impl.guard.constants.GuardErrorCode;
import com.gosun.isap.warn.impl.guard.constants.TimeTemplateType;

/**
 * 事件模板rest接口实现
 * 
 * @author liuzk
 *
 */
@SuppressWarnings("rawtypes")
public class TimeTemplateApiImpl implements ITimeTemplateApi {
	private static Logger logger = LoggerFactory.getLogger(TimeTemplateApiImpl.class);

	private static final String TIME_BOUND_SYMBOL = " ~ ";

	@Autowired
	private TGuardTimeTemplateService daoService;

	@Override
	@SysOperateLog(serviceType = ServiceType.CONFIG_RES, operateType = OperateType.CONFIG_ADD, description = "添加单时间模板")
	public ResponseResult addSingleTimeTemplate(SingleTimeTemplateWrapper wrapper) {
		ResponseResult response = ResponseResult.ok();

		if (null == wrapper || null == wrapper.getTemplate() || null == wrapper.getTime()) {
			throw new IllegalArgumentException();
		}
		String msg = BeanValidate.validateModel(wrapper.getTemplate());
		if (StringUtils.isNotEmpty(msg)) {
			logger.error(msg);
			throw new IllegalArgumentException();
		}

		// 校验时间有效性
		if (!validateTime(wrapper.getTime())) {
			throw new IllegalArgumentException("Invalid time");
		}

		wrapper.getTemplate().setTemplateType(TimeTemplateType.SINGLE);
		TGuardTimeTemplate daoTemplate = typeConvert(wrapper.getTemplate());
		TGuardSingleTime daoTime = typeConvert(wrapper.getTime());

		// 查询是否已经存在同名的时间模板
		if (daoService.isTimeTemplateExist(daoTemplate.getName())) {
			logger.error("The time template already exists, id " + daoTemplate.getName());
			response.setErrorEx(GuardErrorCode.TIME_TEMPLATE_ALREADY_EXISTS, null);
			return response;
		}

		// 如果没有携带描述信息，则自动生成描述信息
		String description = daoTemplate.getDescription();
		if (null == description || "".equals(description)) {
			description = generateDescription(daoTemplate, daoTime);
			daoTemplate.setDescription(description);
		}
		daoService.saveSingleTemplate(daoTemplate, daoTime);

		return response;
	}

	@Override
	@SysOperateLog(serviceType = ServiceType.CONFIG_RES, operateType = OperateType.CONFIG_ADD, description = "修改单时间模板")
	public ResponseResult modifySingleTimeTemplate(SingleTimeTemplateWrapper wrapper) {
		ResponseResult response = ResponseResult.ok();

		if (null == wrapper || null == wrapper.getTemplate() || null == wrapper.getTime()) {
			throw new IllegalArgumentException();
		}
		String msg = BeanValidate.validateModel(wrapper.getTemplate());
		if (StringUtils.isNotEmpty(msg)) {
			logger.error(msg);
			throw new IllegalArgumentException();
		}

		// 校验时间有效性
		if (!validateTime(wrapper.getTime())) {
			throw new IllegalArgumentException("Invalid time");
		}

		wrapper.getTemplate().setTemplateType(TimeTemplateType.SINGLE);
		TGuardTimeTemplate daoTemplate = typeConvert(wrapper.getTemplate());
		TGuardSingleTime daoTime = typeConvert(wrapper.getTime());

		// 如果没有携带描述信息，则自动生成描述信息
		String description = daoTemplate.getDescription();
		if (null == description || "".equals(description)) {
			description = generateDescription(daoTemplate, daoTime);
			daoTemplate.setDescription(description);
		}

		daoService.modifySingleTemplate(daoTemplate, daoTime);

		// 模板变更处理
		TimeTemplateHelper.onTemplateChanged(daoTemplate.getId());

		return response;
	}

	@Override
	@SysOperateLog(serviceType = ServiceType.CONFIG_RES, operateType = OperateType.CONFIG_ADD, description = "添加循环间模板")
	public ResponseResult addWeekTimeTemplate(WeekTimeTemplateWrapper wrapper) {
		ResponseResult response = ResponseResult.ok();

		if (null == wrapper || null == wrapper.getTemplate() || null == wrapper.getTime()) {
			throw new IllegalArgumentException();
		}

		String msg = BeanValidate.validateModel(wrapper.getTemplate());
		if (StringUtils.isNotEmpty(msg)) {
			logger.error(msg);
			throw new IllegalArgumentException();
		}

		// 校验时间有效性
		if (!validateTime(wrapper.getTime())) {
			throw new IllegalArgumentException("Invalid time");
		}

		wrapper.getTemplate().setTemplateType(TimeTemplateType.WEEK);
		TGuardTimeTemplate daoTemplate = typeConvert(wrapper.getTemplate());
		TGuardWeekTime daoTime = typeConvert(wrapper.getTime());

		// 查询是否已经存在同名的时间模板
		if (daoService.isTimeTemplateExist(daoTemplate.getName())) {
			logger.error("The time template already exists, id " + daoTemplate.getName());
			response.setErrorEx(GuardErrorCode.TIME_TEMPLATE_ALREADY_EXISTS, null);
			return response;
		}

		// 如果没有携带描述信息，则自动生成描述信息
		String description = daoTemplate.getDescription();
		if (null == description || description.equals("")) {
			description = generateDescription(daoTemplate, daoTime);
			daoTemplate.setDescription(description);
		}

		daoService.saveWeekTemplate(daoTemplate, daoTime);

		return response;
	}

	@Override
	@SysOperateLog(serviceType = ServiceType.CONFIG_RES, operateType = OperateType.CONFIG_ADD, description = "修改循环间模板")
	public ResponseResult modifyWeekTimeTemplate(WeekTimeTemplateWrapper wrapper) {
		ResponseResult response = ResponseResult.ok();

		if (null == wrapper || null == wrapper.getTemplate() || null == wrapper.getTime()) {
			throw new IllegalArgumentException();
		}

		String msg = BeanValidate.validateModel(wrapper.getTemplate());
		if (StringUtils.isNotEmpty(msg)) {
			logger.error(msg);
			throw new IllegalArgumentException();
		}

		// 校验时间有效性
		if (!validateTime(wrapper.getTime())) {
			throw new IllegalArgumentException("Invalid time");
		}

		wrapper.getTemplate().setTemplateType(TimeTemplateType.WEEK);
		TGuardTimeTemplate daoTemplate = typeConvert(wrapper.getTemplate());
		TGuardWeekTime daoTime = typeConvert(wrapper.getTime());

		// 如果没有携带描述信息，则自动生成描述信息
		String description = daoTemplate.getDescription();
		if (null == description || description.equals("")) {
			description = generateDescription(daoTemplate, daoTime);
			daoTemplate.setDescription(description);
		}

		daoService.modifyWeekTemplate(daoTemplate, daoTime);

		// 模板变更处理
		TimeTemplateHelper.onTemplateChanged(daoTemplate.getId());

		return response;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	@SysOperateLog(serviceType = ServiceType.CONFIG_RES, operateType = OperateType.CONFIG_DEL, description = "批量删除时间模板")
	public ResponseResult deleteTimeTemplate(IdwrapperBat<Long> templateIds) {
		ResponseResult response = ResponseResult.ok();

		// 校验是否可以删除
		for (Long id : templateIds.getId()) {
			if (null == daoService.getTemplate(id)) {
				continue;
			}
			// 获取引用数量
			int refCount = daoService.getRefCount(id);
			if (refCount > 0) {
				response.setErrorEx(GuardErrorCode.TIME_TEMPLATE_BIND_GUARD_PLAN, null);
				return response;
			}
		}

		// 删除时间模板
		daoService.deleteTemplateBatch(templateIds.getId());

		return response;
	}

	@Override
	public ResponseResult<SingleTimeTemplateWrapper> getSingleTimeTemplate(Long templateId) {
		ResponseResult<SingleTimeTemplateWrapper> response = ResponseResult.ok();

		TGuardTimeTemplate daoTemplate = daoService.getTemplate(templateId);
		if (null == daoTemplate) {
			response.setErrorEx(ErrorCode.ERR_RECORD_NOT_EXIST, null);
			return response;
		}

		TGuardSingleTime daoTime = daoService.getSingleTime(templateId);
		if (null == daoTime) {
			response.setErrorEx(ErrorCode.ERR_RECORD_NOT_EXIST, null);
			return response;
		}

		SingleTimeTemplateWrapper wrapper = new SingleTimeTemplateWrapper();
		wrapper.setTemplate(typeConvert(daoTemplate));
		wrapper.setTime(typeConvert(daoTime));

		response.setBody(wrapper);

		return response;
	}

	@Override
	public ResponseResult<WeekTimeTemplateWrapper> getWeekTimeTemplate(Long templateId) {
		ResponseResult<WeekTimeTemplateWrapper> response = ResponseResult.ok();

		TGuardTimeTemplate daoTemplate = daoService.getTemplate(templateId);
		if (null == daoTemplate) {
			response.setErrorEx(ErrorCode.ERR_RECORD_NOT_EXIST, null);
			return response;
		}

		TGuardWeekTime daoTime = daoService.getWeekTime(templateId);
		if (null == daoTime) {
			response.setErrorEx(ErrorCode.ERR_RECORD_NOT_EXIST, null);
			return response;
		}

		WeekTimeTemplateWrapper wrapper = new WeekTimeTemplateWrapper();
		wrapper.setTemplate(typeConvert(daoTemplate));
		wrapper.setTime(typeConvert(daoTime));

		response.setBody(wrapper);

		return response;
	}

	@Override
	public ResponseResult<List<GuardTimeTemplate>> listTimeTemplate(Integer start, Integer limit, String sort,
			String fuzzyField, String fuzzyValue) {
		ResponseResult<List<GuardTimeTemplate>> response = ResponseResult.ok();

		TGuardTimeTemplateExample example = new TGuardTimeTemplateExample();
		example.setLimit(limit);
		example.setOffset(start);
		if (!StringUtils.isEmpty(sort)) {
			String sqlOrder = OrderSqlUtil.getOrderSqlStringBySort(sort);
			example.setOrderByClause(sqlOrder);
		}
		if (!StringUtils.isEmpty(fuzzyField) && !StringUtils.isEmpty(fuzzyValue)) {
			TGuardTimeTemplateExample.Criteria c = example.createCriteria();
			c.andGeneralLike(fuzzyField, "%" + fuzzyValue + "%");
		}

		int total = daoService.countByExample(example);
		if (total > 0) {
			List<TGuardTimeTemplate> daoTemplateList = daoService.listTimeTemplateByExample(example);
			List<GuardTimeTemplate> templateList = new ArrayList<GuardTimeTemplate>();
			for (TGuardTimeTemplate item : daoTemplateList) {
				templateList.add(typeConvert(item));
			}
			response.setBody(templateList);
		}
		response.setTotal(total);

		return response;
	}

	private String generateDescription(TGuardTimeTemplate template, TGuardSingleTime time) {
		StringBuilder builder = new StringBuilder();

		builder.append("单次时间模板");

		if (null != time.getBeginTime1() && null != time.getEndTime1()) {
			builder.append(", ");
			builder.append(dateTimeToString(time.getBeginTime1())).append(TIME_BOUND_SYMBOL)
					.append(dateTimeToString(time.getEndTime1()));
		}

		if (null != time.getBeginTime2() && null != time.getEndTime2()) {
			builder.append(", ");
			builder.append(dateTimeToString(time.getBeginTime2())).append(TIME_BOUND_SYMBOL)
					.append(dateTimeToString(time.getEndTime2()));
		}

		if (null != time.getBeginTime3() && null != time.getEndTime3()) {
			builder.append(", ");
			builder.append(dateTimeToString(time.getBeginTime3())).append(TIME_BOUND_SYMBOL)
					.append(dateTimeToString(time.getEndTime3()));
		}

		if (null != time.getBeginTime4() && null != time.getEndTime4()) {
			builder.append(", ");
			builder.append(dateTimeToString(time.getBeginTime4())).append(TIME_BOUND_SYMBOL)
					.append(dateTimeToString(time.getEndTime4()));
		}

		return builder.toString();
	}

	private String generateDescription(TGuardTimeTemplate template, TGuardWeekTime time) {
		StringBuilder builder = new StringBuilder();

		builder.append("循环时间模板");

		if (null != time.getWeekdays() && !time.getWeekdays().isEmpty()) {
			builder.append(", ");
			builder.append("重复星期 " + time.getWeekdays());
		}

		if (null != time.getBeginTime1() && null != time.getEndTime1()) {
			builder.append(", ");
			builder.append(timeToString(time.getBeginTime1())).append(TIME_BOUND_SYMBOL)
					.append(timeToString(time.getEndTime1()));
		}

		if (null != time.getBeginTime2() && null != time.getEndTime2()) {
			builder.append(", ");
			builder.append(timeToString(time.getBeginTime2())).append(TIME_BOUND_SYMBOL)
					.append(timeToString(time.getEndTime2()));
		}

		if (null != time.getBeginTime3() && null != time.getEndTime3()) {
			builder.append(", ");
			builder.append(timeToString(time.getBeginTime3())).append(TIME_BOUND_SYMBOL)
					.append(timeToString(time.getEndTime3()));
		}

		if (null != time.getBeginTime4() && null != time.getEndTime4()) {
			builder.append(", ");
			builder.append(timeToString(time.getBeginTime4())).append(TIME_BOUND_SYMBOL)
					.append(timeToString(time.getEndTime4()));
		}

		return builder.toString();
	}

	private String timeToString(Date time) {
		SimpleDateFormat formatter;
		formatter = new SimpleDateFormat("HH:mm:ss");
		String ctime = formatter.format(time);
		return ctime;
	}

	private String dateTimeToString(Date time) {
		SimpleDateFormat formatter;
		formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String ctime = formatter.format(time);
		return ctime;
	}

	private TGuardTimeTemplate typeConvert(GuardTimeTemplate obj) {
		TGuardTimeTemplate daoObj = new TGuardTimeTemplate();
		daoObj.setDescription(obj.getDescription());
		daoObj.setId(obj.getId());
		daoObj.setName(obj.getName());
		daoObj.setRefCount(obj.getRefCount());
		daoObj.setTemplateType(obj.getTemplateType());
		return daoObj;
	}

	private GuardTimeTemplate typeConvert(TGuardTimeTemplate daoObj) {
		GuardTimeTemplate obj = new GuardTimeTemplate();
		obj.setDescription(daoObj.getDescription());
		obj.setId(daoObj.getId());
		obj.setName(daoObj.getName());
		obj.setRefCount(daoObj.getRefCount());
		obj.setTemplateType(daoObj.getTemplateType());
		return obj;
	}

	private TGuardSingleTime typeConvert(GuardSingleTime obj) {
		TGuardSingleTime daoObj = new TGuardSingleTime();
		daoObj.setBeginTime1(obj.getBeginTime1());
		daoObj.setBeginTime2(obj.getBeginTime2());
		daoObj.setBeginTime3(obj.getBeginTime3());
		daoObj.setBeginTime4(obj.getBeginTime4());
		daoObj.setEndTime1(obj.getEndTime1());
		daoObj.setEndTime2(obj.getEndTime2());
		daoObj.setEndTime3(obj.getEndTime3());
		daoObj.setEndTime4(obj.getEndTime4());
		daoObj.setTemplateId(obj.getTemplateId());
		return daoObj;
	}

	private GuardSingleTime typeConvert(TGuardSingleTime daoObj) {
		GuardSingleTime obj = new GuardSingleTime();
		obj.setBeginTime1(daoObj.getBeginTime1());
		obj.setBeginTime2(daoObj.getBeginTime2());
		obj.setBeginTime3(daoObj.getBeginTime3());
		obj.setBeginTime4(daoObj.getBeginTime4());
		obj.setEndTime1(daoObj.getEndTime1());
		obj.setEndTime2(daoObj.getEndTime2());
		obj.setEndTime3(daoObj.getEndTime3());
		obj.setEndTime4(daoObj.getEndTime4());
		obj.setTemplateId(daoObj.getTemplateId());
		return obj;
	}

	private TGuardWeekTime typeConvert(GuardWeekTime obj) {
		TGuardWeekTime daoObj = new TGuardWeekTime();
		daoObj.setBeginTime1(obj.getBeginTime1());
		daoObj.setBeginTime2(obj.getBeginTime2());
		daoObj.setBeginTime3(obj.getBeginTime3());
		daoObj.setBeginTime4(obj.getBeginTime4());
		daoObj.setEndTime1(obj.getEndTime1());
		daoObj.setEndTime2(obj.getEndTime2());
		daoObj.setEndTime3(obj.getEndTime3());
		daoObj.setEndTime4(obj.getEndTime4());
		daoObj.setTemplateId(obj.getTemplateId());
		daoObj.setWeekdays(obj.getWeekdays());
		return daoObj;
	}

	private GuardWeekTime typeConvert(TGuardWeekTime daoObj) {
		GuardWeekTime obj = new GuardWeekTime();
		obj.setBeginTime1(daoObj.getBeginTime1());
		obj.setBeginTime2(daoObj.getBeginTime2());
		obj.setBeginTime3(daoObj.getBeginTime3());
		obj.setBeginTime4(daoObj.getBeginTime4());
		obj.setEndTime1(daoObj.getEndTime1());
		obj.setEndTime2(daoObj.getEndTime2());
		obj.setEndTime3(daoObj.getEndTime3());
		obj.setEndTime4(daoObj.getEndTime4());
		obj.setTemplateId(daoObj.getTemplateId());
		obj.setWeekdays(daoObj.getWeekdays());
		return obj;
	}
	private boolean validateTime(GuardSingleTime time) {
		boolean flag = true;
		do {
			if (null != time.getBeginTime1() && null != time.getEndTime1()) {
				if (!time.getBeginTime1().before(time.getEndTime1())) {
					flag = false;
					break;
				}
			}
			if (null != time.getBeginTime2() && null != time.getEndTime2()) {
				if (!time.getBeginTime2().before(time.getEndTime2())) {
					flag = false;
					break;
				}
			}
			if (null != time.getBeginTime3() && null != time.getEndTime3()) {
				if (!time.getBeginTime3().before(time.getEndTime3())) {
					flag = false;
					break;
				}
			}
			if (null != time.getBeginTime4() && null != time.getEndTime4()) {
				if (!time.getBeginTime1().before(time.getEndTime1())) {
					flag = false;
					break;
				}
			}
		} while (false);
		return flag;
	}

	private boolean validateTime(GuardWeekTime time) {
		boolean flag = true;
		do {
			if (null != time.getBeginTime1() && null != time.getEndTime1()) {
				if (!time.getBeginTime1().before(time.getEndTime1())) {
					flag = false;
					break;
				}
			}
			if (null != time.getBeginTime2() && null != time.getEndTime2()) {
				if (!time.getBeginTime2().before(time.getEndTime2())) {
					flag = false;
					break;
				}
			}
			if (null != time.getBeginTime3() && null != time.getEndTime3()) {
				if (!time.getBeginTime3().before(time.getEndTime3())) {
					flag = false;
					break;
				}
			}
			if (null != time.getBeginTime4() && null != time.getEndTime4()) {
				if (!time.getBeginTime1().before(time.getEndTime1())) {
					flag = false;
					break;
				}
			}
		} while (false);
		return flag;
	}
}
