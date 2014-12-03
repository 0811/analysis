package com.dempe.analysis.web.usage.service;

import com.alibaba.fastjson.JSONArray;
import com.dempe.analysis.web.usage.dao.UsageHourlyDao;
import com.dempe.analysis.web.usage.model.UsageHourly;

import java.util.*;

/**
 * Created by Administrator on 2014/12/3.
 */
public class UsageHourlyService {


    private UsageHourlyDao usageHourlyDao;

    private final static String APPKEY="ca2bbd6a539ae3a33c5f2832f8baa4ac";
    private final static String PLATFORM="1";


    public JSONArray getRunNumHourly(String create_date){
        List<UsageHourly> usageHourlies = usageHourlyDao.findByAppkeyAndPlatformAndCreateDate(APPKEY, PLATFORM, create_date);
        Map<String,UsageHourly> usageHourlyMap = new HashMap<String, UsageHourly>();


        JSONArray jsonArray = new JSONArray();
        for(UsageHourly usageHourly : usageHourlies){
            usageHourlyMap.put(usageHourly.getCreate_hour(), usageHourly);
        }


        return jsonArray;
    }

    public JSONArray getNewNumHourly(String create_date){
        List<UsageHourly> usageHourlies = usageHourlyDao.findByAppkeyAndPlatformAndCreateDate(APPKEY, PLATFORM, create_date);
        JSONArray jsonArray = new JSONArray();
        for(UsageHourly usageHourly : usageHourlies){
            jsonArray.add(usageHourly.getNewNum());
        }
        return jsonArray;
    }


}
