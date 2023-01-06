package org.spider.common.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtil {
    public static String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static String DATE_FORMAT = "yyyy-MM-dd";
    /**
     *
     * @return
     */
    public static String getCurrentDate(){
        DateFormat format = new SimpleDateFormat(DATE_FORMAT);
        return format.format(new Date());
    }
}
