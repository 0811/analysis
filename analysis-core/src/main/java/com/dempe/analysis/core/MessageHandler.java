package com.dempe.analysis.core;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dempe.analysis.core.store.TimeSyncStoreMap;
import com.dempe.analysis.core.utils.DateUtil;

/**
 * @author : Dempe
 * @version 1.0 date : 2014/9/30
 */
public class MessageHandler {

    private final static TimeSyncStoreMap storeMap  = new  TimeSyncStoreMap();


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
        JSONObject launchData;
        for(int i=0,size=launchDatas.size();i<size;i++){
            launchData = launchDatas.getJSONObject(i);
            String createDate = DateUtil.format(launchData.getString(R.CREATE_DATE));

            storeMap.incr( new StringBuffer("daily:").append(appkey).append(R.KEY_SPACE).append(createDate).toString());
            storeMap.incr( new StringBuffer("sum_daily_version_channel:").append(appkey).append(R.KEY_SPACE).append(createDate).toString());

        }



    }
}
