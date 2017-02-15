package com.utils.time;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * 时间格式化输出
 * @author zwh
 *
 */
public class TimeFormatter {
    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 以默认格式化yyyy-MM-dd HH:mm:ss输出
     * @param time
     * @return
     */
    public static String getTime(Timestamp time) {
	return time == null ? "" : FORMAT.format(time);
    }

    /**
     * 根据给定的格式输出时间，如果给定的格式不合法则以默认格式输出
     * @param time
     * @param pattern
     * @return
     */
    public static String getTime(Timestamp time, String pattern) {
	try {
	    SimpleDateFormat format = new SimpleDateFormat(pattern);
	    return format.format(time);
	} catch (Exception e) {
	    return getTime(time);
	}
    }
}
