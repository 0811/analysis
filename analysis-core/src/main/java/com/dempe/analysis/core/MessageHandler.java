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
        System.out.println(data);

        JSONObject deviceData = data.getJSONObject(R.DEVICE_DATA);
        JSONArray launchDatas = data.getJSONArray(R.LAUNCH_DATA);
        JSONArray pageDatas = data.getJSONArray(R.PAGE_DATA);
        JSONArray exitDatas = data.getJSONArray(R.EXIT_DATA);
        JSONArray eventDatas = data.getJSONArray(R.EVENT_DATA);
        JSONArray eventKVDatas = data.getJSONArray(R.EVENTKV_DATA);


        // 算启动数
        String appkey = deviceData.getString(R.APPKEY);
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
            countStoreMap.incr(new StringBuffer(R.USAGE_OVERRIDE).append(appkey).append(R.KEY_SPACE).append(createDate).toString());
            countStoreMap.incr(new StringBuffer(R.USAGE_DAILY).append(common_date_key).toString());
            countStoreMap.incr(new StringBuffer(R.DEVICE_COUNTRY).append(common_date_key).append(R.KEY_SPACE).append(country).toString());
            countStoreMap.incr(new StringBuffer(R.DEVICE_CARRIER).append(common_date_key).append(R.KEY_SPACE).append(carrier).toString());
            countStoreMap.incr(new StringBuffer(R.DEVICE_MODEL).append(common_date_key).append(R.KEY_SPACE).append(model).toString());
            countStoreMap.incr(new StringBuffer(R.DEVICE_PROVINCE).append(common_date_key).append(R.KEY_SPACE).append(province).toString());

        }


    }
}
