package com.gosun.isap.warn.api.alert.util;

import com.gosun.isap.warn.api.alert.AlertConst;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * <p>创建时间：2017-5-20 11:37</p>
 *
 * @author 娄存银
 * @version 1.0
 */
public class DateUtils {

    /**
     * @param date 日期
     * @return 开始时间
     */
    public static Date getDayStart(Date date) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        dayStart(calendar);
        return calendar.getTime();
    }

    /**
     * @param date 日期
     * @return 开始时间
     */
    public static Date getBeforeDayStart(Date date) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        dayStart(calendar);
        return calendar.getTime();
    }

    /**
     * @param date 日期
     * @return 开始时间
     */
    public static Date getNextDayStart(Date date) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        dayStart(calendar);
        return calendar.getTime();
    }

    /**
     * @param date 日期
     * @return 开始时间
     */
    public static Date getWeekStart(Date date) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        weekStart(calendar);
        return calendar.getTime();
    }

    /**
     * @param date 日期
     * @return 开始时间
     */
    public static Date getNextWeekStart(Date date) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        weekStart(calendar);
        return calendar.getTime();
    }

    /**
     * @param date 日期
     * @return 开始时间
     */
    public static Date getMonthStart(Date date) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        monthStart(calendar);
        return calendar.getTime();
    }

    /**
     * @param date 日期
     * @return 开始时间
     */
    public static Date getNextMonthStart(Date date) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        monthStart(calendar);
        return calendar.getTime();
    }

    private static void dayStart(Calendar calendar) {
        if (calendar == null) {
            return;
        }
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
    }

    private static void weekStart(Calendar calendar) {
        if (calendar == null) {
            return;
        }
        dayStart(calendar);
        calendar.set(Calendar.DAY_OF_WEEK, 2);
    }

    private static void monthStart(Calendar calendar) {
        if (calendar == null) {
            return;
        }
        dayStart(calendar);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
    }

    private static void yearStart(Calendar calendar) {
        if (calendar == null) {
            return;
        }
        monthStart(calendar);
        calendar.set(Calendar.MONTH, 0);
    }


    /**
     * @param strDate yyyy-MM-dd 或者 yyyy-MM-dd HH:mm:ss
     * @return date
     */
    public static Date parseDate(String strDate) {
        if (strDate == null) {
            return null;
        }
        DateFormat format = new SimpleDateFormat(AlertConst.DATE_TIME_PATTERN);
        Date date = null;
        try {
            date = format.parse(strDate);
        } catch (ParseException ignored) {
        }
        if (date == null) {
            format = new SimpleDateFormat(AlertConst.DATE_PATTERN);
            try {
                date = format.parse(strDate);
            } catch (ParseException ignored) {
            }
        }
        return date;
    }

    /**
     * @param date 日期
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String formatDateTime(Date date) {
        if (date == null) {
            return null;
        }
        DateFormat format = new SimpleDateFormat(AlertConst.DATE_TIME_PATTERN);
        return format.format(date);
    }

    /**
     * @param date 日期
     * @return yyyy-MM-dd
     */
    public static String formatDate(Date date) {
        if (date == null) {
            return null;
        }
        DateFormat format = new SimpleDateFormat(AlertConst.DATE_PATTERN);
        return format.format(date);
    }

    /**
     * @param date 日期
     * @return HH:mm:ss
     */
    public static String formatTime(Date date) {
        if (date == null) {
            return null;
        }
        DateFormat format = new SimpleDateFormat(AlertConst.TIME_PATTERN);
        return format.format(date);
    }
}
