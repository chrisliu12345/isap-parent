package com.gosun.isap.warn.impl.alert.util;

import com.gosun.isap.warn.api.alert.util.DateUtils;

import java.util.Date;

/**
 * <p>创建时间：2017-6-5 10:46</p>
 *
 * @author 娄存银
 * @version 1.0
 */
public class DateParseUtil {
    /**
     * 根据开始时间和日期字符串，解析一个 Date 对象
     *
     * @param startTime 开始时间
     * @param strDate   日期
     * @return Date
     */
    public static Date getStartTime(String startTime, String strDate) {
        Date startDate = DateUtils.parseDate(startTime);
        if (startDate != null) {
            return startDate;
        }
        return getStartTime(strDate);
    }

    /**
     * 根据结束时间和日期字符串，解析一个 Date 对象
     *
     * @param endTime 结束时间
     * @param strDate 日期
     * @return Date
     */
    public static Date getEndTime(String endTime, String strDate) {
        Date endDate = DateUtils.parseDate(endTime);
        if (endDate != null) {
            return endDate;
        }
        return getEndTime(strDate);
    }

    private static Date getStartTime(String strDate) {
        Date date = DateUtils.parseDate(strDate);
        if (date == null) {
            date = new Date();
        }
        return DateUtils.getDayStart(date);
    }

    private static Date getEndTime(String strDate) {
        Date date = DateUtils.parseDate(strDate);
        if (date == null) {
            date = new Date();
        }
        return DateUtils.getNextDayStart(date);
    }
}
