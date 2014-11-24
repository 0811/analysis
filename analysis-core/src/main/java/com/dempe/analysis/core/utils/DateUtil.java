package com.dempe.analysis.core.utils;

/**
 * Created by dempe on 14-10-17.
 */
public class DateUtil {

    public static String format(String dateStr) {
        return format(dateStr, "YYYYMMDD");
    }

    public static String format(String DateStr, String regex) {
        String year = DateStr.substring(0, 4);
        String month = DateStr.substring(5, 7);
        String day = DateStr.substring(8, 10);
        String hour = DateStr.substring(11, 13);
        String min = DateStr.substring(14, 16);
        String sec = DateStr.substring(17, 19);
        regex = regex.toUpperCase();

        return regex.replaceAll("YYYY", year).replaceAll("MM", month).replaceAll("DD", day).replaceAll("HH", hour).replaceAll("MI", min).replaceAll("SS", sec);
    }
}
