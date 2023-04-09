package com.hniesep.base.util;

import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author 吉铭炼
 */
@Service
public class DateTimeUtil {
    static final Date formatDate = new Date();
    /**
     *
     * @return 日期类
     */
    public static Date getDateTime(){
        formatDate.setTime(System.currentTimeMillis());
        return formatDate;
    }
}