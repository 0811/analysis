package com.dempe.analysis.core.simulator;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by dempe on 14-5-22.
 */
public class RandDateUtil {
    public static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static Date randomDate(String beginDate, String endDate) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date start = format.parse(beginDate);// 开始日期
            Date end = format.parse(endDate);// 结束日期
            if (start.getTime() >= end.getTime()) {
                return null;
            }
            long date = random(start.getTime(), end.getTime());

            return new Date(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    private static long random(long begin, long end) {
        long rtnn = begin + (long) (Math.random() * (end - begin));
        if (rtnn == begin || rtnn == end) {
            return random(begin, end);
        }
        return rtnn;
    }

    public static String getRandomDateString() {
        return format.format(randomDate("2014-07-22", "2014-07-24"));
    }

    public static Date getRandomDate() {
        return randomDate("2014-07-10", "2014-07-24");
    }

    public static void main(String[] args) {
        Date randomDate = randomDate("2014-06-26", "2014-06-31");
        System.out.println(format.format(randomDate));
    }


}
