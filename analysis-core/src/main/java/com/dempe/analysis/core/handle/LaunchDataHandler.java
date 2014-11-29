package com.dempe.analysis.core.handle;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dempe.analysis.core.R;
import com.dempe.analysis.core.store.CountStoreMap;
import com.dempe.analysis.core.store.impl.bdb.DeviceDB;
import com.dempe.analysis.core.store.impl.demo.JSet;
import com.dempe.analysis.core.utils.DateUtil;
import org.apache.commons.lang.StringUtils;

/**
 * @author : Dempe
 * @version 1.0 date : 2014/11/26
 */
public class LaunchDataHandler {


    private final CountStoreMap countStoreMap = CountStoreMap.getInstance();

    private final DeviceDB deviceDB = DeviceDB.getInstance();

    // 判断是否活跃设备[appkey:deviceId:date]
    private final JSet<String> activeSet = new JSet<String>();

    public void handle(String appKey, String version, String platform, String channel, String deviceId,
                       String country, String model, String os, String carrier, String screensize, String province, JSONArray launchDatas) {

        if (launchDatas == null || launchDatas.size() < 1) {
            return;
        }
        JSONObject launchData;
        String createDate;
        String lastEndDate;
        StringBuffer common_date_key;
        String launch_data_create_date;

        StringBuffer common_key = new StringBuffer(appKey).append(R.KEY_SPACE).append(platform)
                .append(R.KEY_SPACE).append(version).append(R.KEY_SPACE).append(channel)
                .append(R.KEY_SPACE);

        for (int i = 0, size = launchDatas.size(); i < size; i++) {
            launchData = launchDatas.getJSONObject(i);
            launch_data_create_date = launchData.getString(R.LAUNCH_DATA_CREATE_DATE);
            createDate = DateUtil.format(launch_data_create_date);
            lastEndDate = launchData.getString(R.LAUNCH_DATA_LAST_END_DATE);

            common_date_key = common_key.append(createDate);
            //启动次数
            countStoreMap.incr(R.RUN_NUM, new StringBuffer(R.USAGE_OVERRIDE).append(R.DOLLAR_SPLIT).append(appKey).append(R.KEY_SPACE).append(createDate).toString());
            countStoreMap.incr(R.RUN_NUM, new StringBuffer(R.USAGE_DAILY).append(R.DOLLAR_SPLIT).append(common_date_key).toString());
            countStoreMap.incr(R.RUN_NUM, new StringBuffer(R.DEVICE_COUNTRY).append(R.DOLLAR_SPLIT).append(common_date_key).append(R.KEY_SPACE).append(country).toString());
            countStoreMap.incr(R.RUN_NUM, new StringBuffer(R.DEVICE_CARRIER).append(R.DOLLAR_SPLIT).append(common_date_key).append(R.KEY_SPACE).append(carrier).toString());
            countStoreMap.incr(R.RUN_NUM, new StringBuffer(R.DEVICE_MODEL).append(R.DOLLAR_SPLIT).append(common_date_key).append(R.KEY_SPACE).append(model).toString());
            countStoreMap.incr(R.RUN_NUM, new StringBuffer(R.DEVICE_PROVINCE).append(R.DOLLAR_SPLIT).append(common_date_key).append(R.KEY_SPACE).append(province).toString());
            countStoreMap.incr(R.RUN_NUM, new StringBuffer(R.DEVICE_OS).append(R.DOLLAR_SPLIT).append(common_date_key).append(R.KEY_SPACE).append(os).toString());
            countStoreMap.incr(R.RUN_NUM, new StringBuffer(R.DEVICE_RESOLUTION).append(R.DOLLAR_SPLIT).append(common_date_key).append(R.KEY_SPACE).append(screensize).toString());

            //活跃用户
            String activeKey = new StringBuffer(createDate).append(appKey).append(deviceId).toString();
            if (!activeSet.exist(activeKey)) {
                countStoreMap.incr(R.ACTIVE_NUM, new StringBuffer(R.USAGE_OVERRIDE).append(R.DOLLAR_SPLIT).append(appKey).append(R.KEY_SPACE).append(createDate).toString());
                countStoreMap.incr(R.ACTIVE_NUM, new StringBuffer(R.USAGE_DAILY).append(R.DOLLAR_SPLIT).append(common_date_key).toString());
                activeSet.add(activeKey);


                //新增用户
                String createAt = deviceDB.get(appKey + R.KEY_SPACE + deviceId);
                if (createAt == null) {
                    common_date_key = common_key.append(createDate);
                    countStoreMap.incr(R.NEW_NUM, new StringBuffer(R.USAGE_OVERRIDE).append(R.DOLLAR_SPLIT).append(appKey).append(R.KEY_SPACE).append(createDate).toString());
                    countStoreMap.incr(R.NEW_NUM, new StringBuffer(R.USAGE_DAILY).append(R.DOLLAR_SPLIT).append(common_date_key).toString());
                    countStoreMap.incr(R.NEW_NUM, new StringBuffer(R.DEVICE_COUNTRY).append(R.DOLLAR_SPLIT).append(common_date_key).append(R.KEY_SPACE).append(country).toString());
                    countStoreMap.incr(R.NEW_NUM, new StringBuffer(R.DEVICE_CARRIER).append(R.DOLLAR_SPLIT).append(common_date_key).append(R.KEY_SPACE).append(carrier).toString());
                    countStoreMap.incr(R.NEW_NUM, new StringBuffer(R.DEVICE_MODEL).append(R.DOLLAR_SPLIT).append(common_date_key).append(R.KEY_SPACE).append(model).toString());
                    countStoreMap.incr(R.NEW_NUM, new StringBuffer(R.DEVICE_PROVINCE).append(R.DOLLAR_SPLIT).append(common_date_key).append(R.KEY_SPACE).append(province).toString());
                    countStoreMap.incr(R.NEW_NUM, new StringBuffer(R.DEVICE_OS).append(R.DOLLAR_SPLIT).append(common_date_key).append(R.KEY_SPACE).append(os).toString());
                    countStoreMap.incr(R.NEW_NUM, new StringBuffer(R.DEVICE_RESOLUTION).append(R.DOLLAR_SPLIT).append(common_date_key).append(R.KEY_SPACE).append(screensize).toString());
                    deviceDB.set(appKey + R.KEY_SPACE + deviceId, createDate);
                } else {

                    //计算留存用户
                    if (!StringUtils.equals(createAt, createDate)) {
                        countStoreMap.incr(DateUtil.getDayRetention(createAt, createDate), new StringBuffer(R.RETENTION_DAILY).append(R.DOLLAR_SPLIT).append(common_date_key).toString());
                        countStoreMap.incr(DateUtil.getWeekRetention(createAt, createDate), new StringBuffer(R.RETENTION_DAILY).append(R.DOLLAR_SPLIT).append(common_date_key).toString());
                        countStoreMap.incr(DateUtil.getMonthRetention(createAt, createDate), new StringBuffer(R.RETENTION_DAILY).append(R.DOLLAR_SPLIT).append(common_date_key).toString());
                    }

                }
            }


            // 单次使用时长
            long dur = DateUtil.dateTimeDuration(launch_data_create_date, lastEndDate);
            dur = dur / 1000 / 60 / 60;
            String segment = DateUtil.getIntervalSegment(dur);
            countStoreMap.incr(R.RUN_NUM, new StringBuffer(R.USAGE_INTERVAL).append(R.DOLLAR_SPLIT).append(common_date_key).append(R.KEY_SPACE).append(segment).toString());

        }


    }


}
