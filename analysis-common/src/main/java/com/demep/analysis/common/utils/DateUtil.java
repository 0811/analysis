package com.demep.analysis.common.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

    public static String getHourStr() {
        return new SimpleDateFormat("HH").format(new Date());
    }

    /**
     * TODO
     *
     * @param end
     * @param start
     * @return
     */
    public static long dateTimeDuration(String end, String start) {

        return 0;
    }

    public static String getIntervalSegment(long dur) {
        if (dur >= 0 && dur <= 0) {
            return "first";
        } else if (dur > 0 && dur <= 24) {
            return "0-24H";
        } else if (dur > 24 && dur <= 48) {
            return "1";
        } else if (dur > 48 && dur <= 72) {
            return "2";
        } else if (dur > 72 && dur <= 96) {
            return "3";
        } else if (dur > 96 && dur <= 120) {
            return "4";
        } else if (dur > 120 && dur <= 144) {
            return "5";
        } else if (dur > 144 && dur <= 168) {
            return "6";
        } else if (dur > 168 && dur <= 192) {
            return "7";
        } else if (dur > 192 && dur <= 360) {
            return "8-14";
        } else if (dur > 360 && dur <= 720) {
            return "15-30";
        }
        return null;
    }

    /**
     * return is other before first
     *
     * @param first
     * @param other
     * @return
     */
    public static boolean dateIsBefore(String first, String other) {
        try {
            DateFormat df = new SimpleDateFormat("yyyyMMdd");
            Date firstDate = df.parse(first);
            Date otherDate = df.parse(other);
            return otherDate.before(firstDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    public final static int[] browsingFreq = {1, 3, 6, 10, 20, 50, 100, 200, 300};

    public final static int[] retentionDaily = {0, 1, 2, 3, 4, 5, 6, 7, 14, 30};

    public final static int[] retentionWeekly = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

    public final static int[] retentionMonthly = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

    public static String getBrowsingFreq(Long num) {
        if (num == null) {
            num = 0l;
        }
        for (int i = 0; i < browsingFreq.length; i++) {
            if (browsingFreq[i] > num) {
                return browsingFreq[i - 1] + "-" + (browsingFreq[i] - 1);
            }
        }
        return browsingFreq[browsingFreq.length - 1] + "+";

    }

    /**
     * TODO
     *
     * @param day
     * @return
     */
    public static List<String> getLastestDays(int day) {
        List list = new ArrayList();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        for (int i = 0; i < day; i++) {
            calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) - i);
            //list.add(sdf.format(calendar.getTime()));
        }

        return list;

    }

    public static String dayOfweek(String date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(sdf.parse(date));
            calendar.setFirstDayOfWeek(Calendar.MONDAY);
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            String monday = sdf.format(calendar.getTime());
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
            String sunday = sdf.format(calendar.getTime());
            return monday + "-" + sunday;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String dayOfMonth(String date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(sdf.parse(date));
            calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
            String firstDay = sdf.format(calendar.getTime());
            calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
            String lastDay = sdf.format(calendar.getTime());
            return firstDay + "-" + lastDay;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 计算天数.
     *
     * @param smdate
     * @param bdate
     * @return
     */
    public static int dayBetween(String smdate, String bdate) {
        try {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            Calendar cal = Calendar.getInstance();
            Calendar cal2 = Calendar.getInstance();
            cal.setTime(sdf.parse(smdate));
            cal2.setTime(sdf.parse(bdate));
            if (cal2.before(cal)) {
                return 0;
            }
            long time1 = cal.getTimeInMillis();
            cal.setTime(sdf.parse(bdate));
            long time2 = cal.getTimeInMillis();

            long between = (time2 - time1) / (1000 * 3600 * 24);
            return Long.valueOf(between).intValue();
        } catch (Exception e) {
            return 0;
        }
    }

    public static int weekBetween(String sdate, String edate) {
        try {
            if (dayBetween(sdate, edate) < 7) {
                return 0;
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            Date d1 = sdf.parse(sdate);
            Date d2 = sdf.parse(edate);
            Calendar cal = Calendar.getInstance();
            cal.setTime(d1);
            int count = 0;
            cal.setFirstDayOfWeek(Calendar.MONDAY);
            cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            while (cal.getTime().before(d2)) {
                cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
                cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
                cal.add(Calendar.DAY_OF_WEEK, 1);
                count++;
            }
            return count;
        } catch (Exception e) {
            return dayBetween(sdate, edate) / 7;
        }

    }


    /**
     * get the duration.
     *
     * @param days
     * @return
     */
    public static int getDuration(int days, int[] durations) {
        int duration = durations[0];
        for (int d : durations) {
            if (days < d && days >= duration) {
                return duration;
            } else {
                duration = d;
            }
        }
        return duration;
    }

    public static boolean isAfter(String oDate, String nDate) {

//        try {
//            Date oldDate = DateUtils.parse(oDate, "yyyy-MM-dd HH:mm:ss");
//            Date newDate = DateUtils.parse(nDate, "yyyy-MM-dd HH:mm:ss");
//            if (newDate.after(oldDate)) {
//                return true;
//            }
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
        return false;
    }


    /**
     * TODO
     *
     * @param createAt
     * @param updateAt
     * @return
     */
    public static String getDayRetention(String createAt, String updateAt) {
        return "0-1";
    }

    /**
     * TODO
     *
     * @param createAt
     * @param updateAt
     * @return
     */
    public static String getWeekRetention(String createAt, String updateAt) {
        return "0-1";
    }

    /**
     * TODO
     *
     * @param createAt
     * @param updateAt
     * @return
     */
    public static String getMonthRetention(String createAt, String updateAt) {
        return "0-1";
    }


}
