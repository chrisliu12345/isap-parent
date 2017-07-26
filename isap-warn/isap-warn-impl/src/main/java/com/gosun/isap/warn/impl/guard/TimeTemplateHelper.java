package com.gosun.isap.warn.impl.guard;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.dubbo.container.spring.SpringContainer;
import com.gosun.isap.dao.po.TGuardPlan;
import com.gosun.isap.dao.po.TGuardPlanExample;
import com.gosun.isap.dao.po.TGuardSingleTime;
import com.gosun.isap.dao.po.TGuardTimeTemplate;
import com.gosun.isap.dao.po.TGuardWeekTime;
import com.gosun.isap.warn.api.service.guard.TGuardPlanService;
import com.gosun.isap.warn.api.service.guard.TGuardTimeTemplateService;
import com.gosun.isap.warn.impl.guard.constants.TimeTemplateType;
import com.gosun.isap.warn.impl.guard.job.AutoGuardJobHelper;

/**
 * 事件模板辅助类
 * 
 * @author liuzk
 *
 */
public class TimeTemplateHelper {
	private static Logger logger = LoggerFactory.getLogger(TimeTemplateHelper.class);

	/**
	 * 判断是否在时间周期内
	 * 
	 * @param templateId
	 *            模板id
	 * @param d
	 *            时间
	 * @return true/false
	 */
	public static boolean isBetweenGuardPeriod(Long templateId, Date date) {
		boolean flag = false;

		TGuardTimeTemplateService daoSrv = SpringContainer.getContext().getBean(TGuardTimeTemplateService.class);
		TGuardTimeTemplate template = daoSrv.getTemplate(templateId);
		if (null != template) {
			if (TimeTemplateType.SINGLE == template.getTemplateType()) {
				TGuardSingleTime time = daoSrv.getSingleTime(templateId);
				if (null != time) {
					flag = isBetweenGuardPeriod(time, date);
				}
			} else {
				TGuardWeekTime time = daoSrv.getWeekTime(templateId);
				if (null != time) {
					flag = isBetweenGuardPeriod(time, date);
				}
			}
		}
		return flag;
	}

	public static void onTemplateChanged(Long templateId) {
		// 查询出时间模板对于的所有布防计划
		TGuardPlanService guardPlanService = SpringContainer.getContext().getBean(TGuardPlanService.class);
		TGuardPlanExample example = new TGuardPlanExample();
		TGuardPlanExample.Criteria cri = example.createCriteria();
		cri.andTemplateIdEqualTo(templateId);
		List<TGuardPlan> plans = guardPlanService.listGuardPlanByExample(example);

		// 创建布防计划任务（已经存在，会自动修改任务）
		for (TGuardPlan plan : plans) {
			logger.info("Time template " + templateId + " has changed, the guard plan " + plan.getId()
					+ " job will be reschedule");
			AutoGuardJobHelper.createGuardJob(plan);
		}
	}

	private static boolean isBetweenGuardPeriod(TGuardSingleTime time, Date now) {
		boolean flag = false;
		do {
			if (null != time.getBeginTime1() && null != time.getEndTime1()) {
				if (now.after(time.getBeginTime1()) && now.before(time.getEndTime1())) {
					flag = true;
					break;
				}
			}

			if (null != time.getBeginTime2() && null != time.getEndTime2()) {
				if (now.after(time.getBeginTime2()) && now.before(time.getEndTime2())) {
					flag = true;
					break;
				}
			}

			if (null != time.getBeginTime3() && null != time.getEndTime3()) {
				if (now.after(time.getBeginTime3()) && now.before(time.getEndTime3())) {
					flag = true;
					break;
				}
			}

			if (null != time.getBeginTime4() && null != time.getEndTime4()) {
				if (now.after(time.getBeginTime4()) && now.before(time.getEndTime4())) {
					flag = true;
					break;
				}
			}
		} while (false);

		return flag;
	}

	private static boolean isBetweenGuardPeriod(TGuardWeekTime time, Date now) {
		boolean flag = false;
		do {
			// 先判断星期
			if (null != time.getWeekdays()) {
				// 没有找到，说明不是属于重复周期内，时间部分就不需要再比较了
				if (time.getWeekdays().indexOf(getWeekOfDate(now)) < 0) {
					flag = false;
					break;
				}
			}

			Date t = null;
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			try {
				t = sdf.parse(sdf.format(now));
			} catch (ParseException e) {
				flag = false;
				break;
			}

			if (null != time.getBeginTime1() && null != time.getEndTime1()) {
				if (t.after(time.getBeginTime1()) && t.before(time.getEndTime1())) {
					flag = true;
					break;
				}
			}

			if (null != time.getBeginTime2() && null != time.getEndTime2()) {
				if (t.after(time.getBeginTime2()) && t.before(time.getEndTime2())) {
					flag = true;
					break;
				}
			}

			if (null != time.getBeginTime3() && null != time.getEndTime3()) {
				if (t.after(time.getBeginTime3()) && t.before(time.getEndTime3())) {
					flag = true;
					break;
				}
			}

			if (null != time.getBeginTime4() && null != time.getEndTime4()) {
				if (t.after(time.getBeginTime4()) && t.before(time.getEndTime4())) {
					flag = true;
					break;
				}
			}
		} while (false);

		return flag;
	}

	/**
	 * 获取当前日期是星期几<br>
	 * 
	 * @param dt
	 * @return 当前日期是星期几
	 */
	private static String getWeekOfDate(Date dt) {
		String[] weekDays = { "7", "1", "2", "3", "4", "5", "6" };
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);

		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0) {
			w = 0;
		}
		return weekDays[w];
	}
}
