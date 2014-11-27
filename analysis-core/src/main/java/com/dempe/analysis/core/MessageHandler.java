package com.dempe.analysis.core;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dempe.analysis.core.store.CountStoreMap;
import com.dempe.analysis.core.store.bdb.BdbKeySet;
import com.dempe.analysis.core.utils.DateUtil;
import com.dempe.analysis.core.utils.ZipUtil;

/**
 * @author : Dempe
 * @version 1.0 date : 2014/9/30
 */
public class MessageHandler {

    private final CountStoreMap countStoreMap = CountStoreMap.getInstance();

    private final BdbKeySet bdbKeySet = BdbKeySet.getInstance();

    public void streaming(String message) {

        JSONObject data = JSONObject.parseObject(message);

        String appkey = data.getString(R.APPKEY);
        String zipMessage = data.getString("m");

        //解压
        JSONObject m = JSONObject.parseObject(ZipUtil.gunzip(zipMessage));

        JSONObject deviceData = m.getJSONObject(R.DEVICE_DATA);
        JSONArray launchDatas = m.getJSONArray(R.LAUNCH_DATA);
        JSONArray pageDatas = m.getJSONArray(R.PAGE_DATA);
        JSONArray exitDatas = m.getJSONArray(R.EXIT_DATA);
        JSONArray eventDatas = m.getJSONArray(R.EVENT_DATA);
        JSONArray eventKVDatas = m.getJSONArray(R.EVENTKV_DATA);


        // 算启动数

        String deviceId = deviceData.getString(R.DEVICE_ID);
        String version = deviceData.getString(R.VERSION);
        String model = deviceData.getString(R.MODEL);
        String platform = deviceData.getString(R.PLATFORM);
        String channel = deviceData.getString(R.CHANNEL);

        String carrier = deviceData.getString(R.CARRIER);
        String country = deviceData.getString(R.COUNTRY);
        String province = deviceData.getString(R.PROVINCE);
        String os = deviceData.getString(R.SYSVER);
        String screensize = deviceData.getString(R.SCREENSIZE);
        StringBuffer common_key = new StringBuffer(appkey).append(R.KEY_SPACE).append(platform)
                .append(R.KEY_SPACE).append(version).append(R.KEY_SPACE).append(channel)
                .append(R.KEY_SPACE);
        JSONObject launchData;
        String createDate;
        String lastEndDate;
        String cDate = null;
        StringBuffer common_date_key;
        String launch_data_create_date;

        for (int i = 0, size = launchDatas.size(); i < size; i++) {
            launchData = launchDatas.getJSONObject(i);
            launch_data_create_date = launchData.getString(R.LAUNCH_DATA_CREATE_DATE);
            createDate = DateUtil.format(launch_data_create_date);
            lastEndDate = launchData.getString(R.LAUNCH_DATA_LAST_END_DATE);

            common_date_key = common_key.append(createDate);
            countStoreMap.incr(R.RUN_NUM, new StringBuffer(R.USAGE_OVERRIDE).append(R.DOLLAR_SPLIT).append(appkey).append(R.KEY_SPACE).append(createDate).toString());
            countStoreMap.incr(R.RUN_NUM, new StringBuffer(R.USAGE_DAILY).append(R.DOLLAR_SPLIT).append(common_date_key).toString());
            countStoreMap.incr(R.RUN_NUM, new StringBuffer(R.DEVICE_COUNTRY).append(R.DOLLAR_SPLIT).append(common_date_key).append(R.KEY_SPACE).append(country).toString());
            countStoreMap.incr(R.RUN_NUM, new StringBuffer(R.DEVICE_CARRIER).append(R.DOLLAR_SPLIT).append(common_date_key).append(R.KEY_SPACE).append(carrier).toString());
            countStoreMap.incr(R.RUN_NUM, new StringBuffer(R.DEVICE_MODEL).append(R.DOLLAR_SPLIT).append(common_date_key).append(R.KEY_SPACE).append(model).toString());
            countStoreMap.incr(R.RUN_NUM, new StringBuffer(R.DEVICE_PROVINCE).append(R.DOLLAR_SPLIT).append(common_date_key).append(R.KEY_SPACE).append(province).toString());
            countStoreMap.incr(R.RUN_NUM, new StringBuffer(R.DEVICE_OS).append(R.DOLLAR_SPLIT).append(common_date_key).append(R.KEY_SPACE).append(os).toString());
            countStoreMap.incr(R.RUN_NUM, new StringBuffer(R.DEVICE_RESOLUTION).append(R.DOLLAR_SPLIT).append(common_date_key).append(R.KEY_SPACE).append(screensize).toString());

            //analysis device create_date.
            if (cDate == null || DateUtil.dateIsBefore(cDate, createDate)) {
                cDate = createDate;
            }


            long dur = DateUtil.dateTimeDuration(launch_data_create_date, lastEndDate);
            dur = dur / 1000 / 60 / 60;
            String segment = DateUtil.getIntervalSegment(dur);
            countStoreMap.incr(R.RUN_NUM, new StringBuffer(R.USAGE_INTERVAL).append(R.DOLLAR_SPLIT).append(common_date_key).append(R.KEY_SPACE).append(segment).toString());

        }
        if (bdbKeySet.exists(String.format("%s:%s%s", appkey, cDate, deviceId))) {
            common_date_key = common_key.append(cDate);
            countStoreMap.incr(R.NEW_NUM, new StringBuffer(R.USAGE_OVERRIDE).append(R.DOLLAR_SPLIT).append(appkey).append(R.KEY_SPACE).append(cDate).toString());
            countStoreMap.incr(R.NEW_NUM, new StringBuffer(R.USAGE_DAILY).append(R.DOLLAR_SPLIT).append(common_date_key).toString());
        }


    }
}
