package org.chrisli.activiti.utils;

import org.apache.commons.lang3.time.FastDateFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * []
 *
 * @author Chris Li[黎超]
 * @create [2020-06-22]
 */
public class DateUtils {
    static FastDateFormat formatTimestamp = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");
    static FastDateFormat formatTimestampSerial = FastDateFormat.getInstance("yyyyMMddHHmmssSSS");

    public DateUtils() {
    }

    public static String getCurTimestampSerial(){
        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
        c.setTime(new Date());
        return formatTimestampSerial.format(c);
    }

    public static String getCurTimestamp() {
        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
        c.setTime(new Date());
        return formatTimestamp.format(c);
    }

}