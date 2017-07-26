package com.gosun.isap.warn.impl.guard.job;

import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Cron时间表达式生成器
 * 
 * @author liuzk
 *
 */
public class CronExpressionBuilder {

	// 分割符
	private static final String SEPRATOR = " ";
	// 未指定
	private static final String UNSPECIFIED = "?";
	// 全匹配
	private static final String ALL = "*";

	/**
	 * 根据时间生成cron表达式
	 * 
	 * @param dateTime
	 *            datetime
	 * @return cron表达式
	 */
	public static String build(LocalDateTime dateTime) {
		if (null == dateTime) {
			throw new IllegalArgumentException("date is null");
		}

		StringBuilder builder = new StringBuilder();

		// Seconds (秒) ：可以用数字0－59 表示，
		// Minutes(分) ：可以用数字0－59 表示，
		// Hours(时) ：可以用数字0-23表示,
		// Day-of-Month(天) ：可以用数字1-31 中的任一一个值，但要注意一些特别的月份
		// Month(月) ：可以用0-11表示
		// Day-of-Week(每周) ：可以用数字1-7表示（1 ＝ 星期日）表示
		// Year (可选字段)

		builder.append(dateTime.getSecond()).append(SEPRATOR);
		builder.append(dateTime.getMinute()).append(SEPRATOR);
		builder.append(dateTime.getHour()).append(SEPRATOR);
		builder.append(dateTime.getDayOfMonth()).append(SEPRATOR);
		builder.append(dateTime.getMonthValue()).append(SEPRATOR);
		builder.append(UNSPECIFIED).append(SEPRATOR);
		builder.append(dateTime.getYear());

		return builder.toString();
	}

	/**
	 * cron时间表达式生成
	 * 
	 * @param time
	 *            时间
	 * @param weekdays
	 *            周几，如1，2，3
	 * @return cron表达式
	 */
	public static String build(LocalTime time, String weekdays) {
		if (null == weekdays) {
			throw new IllegalArgumentException("weekdays is null");
		}

		if (null == time) {
			throw new IllegalArgumentException("time is null");
		}

		StringBuilder builder = new StringBuilder();

		// Seconds (秒) ：可以用数字0－59 表示，
		// Minutes(分) ：可以用数字0－59 表示，
		// Hours(时) ：可以用数字0-23表示,
		// Day-of-Month(天) ：可以用数字1-31 中的任一一个值，但要注意一些特别的月份
		// Month(月) ：可以用0-11表示
		// Day-of-Week(每周) ：可以用数字1-7表示（1 ＝ 星期日）表示
		// Year (可选字段)

		builder.append(time.getSecond()).append(SEPRATOR);
		builder.append(time.getMinute()).append(SEPRATOR);
		builder.append(time.getHour()).append(SEPRATOR);
		builder.append(UNSPECIFIED).append(SEPRATOR);
		builder.append(ALL).append(SEPRATOR);
		builder.append(weekdays).append(SEPRATOR);
		builder.append(ALL);

		return builder.toString();
	}
}
