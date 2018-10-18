package com.github.guoyaohui.common;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author 郭垚辉
 * @date 2018/10/19
 */
public class DateUtils {


    public static LocalDateTime getDateTimeOfTimestamp(Timestamp timestamp) {
        return getDateTimeOfTime(timestamp.getTime());
    }

    public static LocalDateTime getDateTimeOfDate(Date date) {
        return getDateTimeOfTime(date.getTime());
    }

    public static LocalDateTime getDateTimeOfTime(long time) {
        Instant instant = Instant.ofEpochMilli(time);
        ZoneId zone = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zone);
    }

}
