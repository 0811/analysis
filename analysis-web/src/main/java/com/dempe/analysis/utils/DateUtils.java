package com.dempe.analysis.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2014/11/30.
 */
public class DateUtils {


    private static final DateFormat df = new SimpleDateFormat("yyyyMMdd");

    public static String today() {
        return df.format(new Date());
    }


}
