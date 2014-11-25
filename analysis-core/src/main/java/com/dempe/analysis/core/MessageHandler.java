package com.dempe.analysis.core;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dempe.analysis.core.store.CountStoreMap;
import com.dempe.analysis.core.store.UniqueCountStoreMap;
import com.dempe.analysis.core.utils.DateUtil;

/**
 * @author : Dempe
 * @version 1.0 date : 2014/9/30
 */
public class MessageHandler {

    private final static CountStoreMap countStoreMap = CountStoreMap.getInstance();

    private final static UniqueCountStoreMap uniqueCountStoreMap = UniqueCountStoreMap.getInstance();

    public static void streaming(String message) {

        JSONObject data = JSONObject.parseObject(message);

        String appkey = data.getString(R.APPKEY);
        JSONObject m = data.getJSONObject("m");

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
        String country = deviceData.getString(R.DEVICE_COUNTRY);
        String province = deviceData.getString(R.PROVINCE);
        StringBuffer common_key = new StringBuffer(appkey).append(R.KEY_SPACE).append(platform)
                .append(R.KEY_SPACE).append(version).append(R.KEY_SPACE).append(channel)
                .append(R.KEY_SPACE);
        JSONObject launchData;
        String createDate;
        StringBuffer common_date_key;

        for (int i = 0, size = launchDatas.size(); i < size; i++) {
            launchData = launchDatas.getJSONObject(i);
            createDate = DateUtil.format(launchData.getString(R.CREATE_DATE));
            common_date_key = common_key.append(createDate);
            countStoreMap.incr(new StringBuffer(R.USAGE_OVERRIDE).append(R.DOLLAR_SPLIT).append(appkey).append(R.KEY_SPACE).append(createDate).toString(),R.COUNT);
            countStoreMap.incr(new StringBuffer(R.USAGE_DAILY).append(R.DOLLAR_SPLIT).append(common_date_key).toString(),R.COUNT);
            countStoreMap.incr(new StringBuffer(R.DEVICE_COUNTRY).append(R.DOLLAR_SPLIT).append(common_date_key).append(R.KEY_SPACE).append(country).toString(),R.COUNT);
            countStoreMap.incr(new StringBuffer(R.DEVICE_CARRIER).append(R.DOLLAR_SPLIT).append(common_date_key).append(R.KEY_SPACE).append(carrier).toString(),R.COUNT);
            countStoreMap.incr(new StringBuffer(R.DEVICE_MODEL).append(R.DOLLAR_SPLIT).append(common_date_key).append(R.KEY_SPACE).append(model).toString(),R.COUNT);
            countStoreMap.incr(new StringBuffer(R.DEVICE_PROVINCE).append(R.DOLLAR_SPLIT).append(common_date_key).append(R.KEY_SPACE).append(province).toString(),R.COUNT);

        }


    }
}
