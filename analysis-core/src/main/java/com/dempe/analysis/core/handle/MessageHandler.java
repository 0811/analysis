package com.dempe.analysis.core.handle;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dempe.analysis.core.R;
import com.dempe.analysis.core.utils.ZipUtil;

/**
 * @author : Dempe
 * @version 1.0 date : 2014/9/30
 */
public class MessageHandler {


    private LaunchDataHandler launchDataHandler;

    public MessageHandler() {
        launchDataHandler = new LaunchDataHandler();
    }

    public void streaming(String message) {

        JSONObject data = JSONObject.parseObject(message);

        String appKey = data.getString(R.APPKEY);
        String zipMessage = data.getString("m");

        //解压
        JSONObject m = JSONObject.parseObject(ZipUtil.gunzip(zipMessage));

        JSONObject deviceData = m.getJSONObject(R.DEVICE_DATA);
        JSONArray launchDatas = m.getJSONArray(R.LAUNCH_DATA);
        JSONArray pageDatas = m.getJSONArray(R.PAGE_DATA);
        JSONArray exitDatas = m.getJSONArray(R.EXIT_DATA);
        JSONArray eventDatas = m.getJSONArray(R.EVENT_DATA);
        JSONArray eventKVDatas = m.getJSONArray(R.EVENTKV_DATA);


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

        launchDataHandler.handle(appKey, version, platform, channel, deviceId, country, model, os, carrier, screensize, province, launchDatas);


    }
}
