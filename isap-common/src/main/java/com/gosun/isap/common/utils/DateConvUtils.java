package com.gosun.isap.common.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

public class DateConvUtils {
	// 01. java.util.Date --> java.time.LocalDateTime
	public static LocalDateTime convUDateToLocalDateTime(java.util.Date dateTime) {
		java.util.Date date = dateTime;
		Instant instant = date.toInstant();
		ZoneId zone = ZoneId.systemDefault();
		LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
		return localDateTime;
	}

	// 02. java.util.Date --> java.time.LocalDate
	public static LocalDate convUDateToLocalDate(java.util.Date date) {
		Instant instant = date.toInstant();
		ZoneId zone = ZoneId.systemDefault();
		LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
		LocalDate localDate = localDateTime.toLocalDate();
		return localDate;
	}

	// 03. java.util.Date --> java.time.LocalTime
	public static LocalTime convUDateToLocalTime(java.util.Date time) {
		java.util.Date date = time;
		Instant instant = date.toInstant();
		ZoneId zone = ZoneId.systemDefault();
		LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
		LocalTime localTime = localDateTime.toLocalTime();
		return localTime;
	}

	// 04. java.time.LocalDateTime --> java.util.Date
	public static java.util.Date convLocalDateTimeToUdate(java.time.LocalDateTime dateTime) {
		LocalDateTime localDateTime = dateTime;
		ZoneId zone = ZoneId.systemDefault();
		Instant instant = localDateTime.atZone(zone).toInstant();
		java.util.Date date = Date.from(instant);
		return date;
	}

	// 05. java.time.LocalDate --> java.util.Date
	public static java.util.Date convLocalDateToUdate(java.time.LocalDate date) {
		LocalDate localDate = date;
		ZoneId zone = ZoneId.systemDefault();
		Instant instant = localDate.atStartOfDay().atZone(zone).toInstant();
		java.util.Date d = Date.from(instant);
		return d;
	}

	// 06. java.time.LocalTime --> java.util.Date
	public static java.util.Date convLocalTimeToUdate(java.time.LocalTime time) {
		LocalTime localTime = time;
		LocalDate localDate = LocalDate.now();
		LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
		ZoneId zone = ZoneId.systemDefault();
		Instant instant = localDateTime.atZone(zone).toInstant();
		java.util.Date date = Date.from(instant);
		return date;
	}
}
