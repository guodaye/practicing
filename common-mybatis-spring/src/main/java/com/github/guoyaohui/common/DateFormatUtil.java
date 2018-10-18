package com.github.guoyaohui.common;

import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author 郭垚辉
 * @date 2018/10/19
 */
public class DateFormatUtil {

    /**
     * 适用于mysql中的Timestamp和DateTime类型
     */
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * 使用于mysql中的Date类型
     */
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");


    public static String buildDateTime(Date date) {
        return DATE_TIME_FORMATTER.format(DateUtils.getDateTimeOfDate(date));
    }

    public static String buildDateTime(Timestamp timestamp) {
        return DATE_TIME_FORMATTER.format(DateUtils.getDateTimeOfDate(timestamp));
    }

    public static String buildDate(Date date) {
        return DATE_FORMATTER.format(DateUtils.getDateTimeOfDate(date));
    }

}
