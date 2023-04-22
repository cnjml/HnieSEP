package com.hniesep.framework.util;

import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author 吉铭炼
 */
@Component
public class DateTimeUtil {
    static final Date FORMAT_DATE = new Date();
    /**
     *
     * @return 日期类
     */
    public static Date getDateTime() {
        FORMAT_DATE.setTime(System.currentTimeMillis());
        return FORMAT_DATE;
    }
}