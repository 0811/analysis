package com.dempe.analysis.core.store;

import com.alibaba.fastjson.JSONObject;
import com.dempe.analysis.core.Config;
import com.dempe.analysis.core.R;
import org.apache.log4j.Logger;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 每个storeMap享有一个后台同步线程，主要负责数据定时同步
 *
 * @author : Dempe
 * @version 1.0 date : 2014/9/30
 */
public abstract class TimeSyncStoreMap implements Command {

    public static final Logger LOGGER = Logger.getLogger(TimeSyncStoreMap.class);

    private final static String DEF_SLEEP_TIME = "5000";

    protected static Map<String, JSONObject> storeMap = new ConcurrentHashMap<String, JSONObject>();

    private Thread _syncer;

    private Map<String, JSONObject> syncMap;

    public TimeSyncStoreMap() {
        final int sleepTime = Integer.parseInt(Config.getString(R.SLEEP_TIME, DEF_SLEEP_TIME));
        _syncer = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        syncMap = storeMap;
                        clearStoreDB();
                        sync(syncMap);
                        Thread.sleep(sleepTime);
                    }
                } catch (Exception e) {
                    LOGGER.error("data sync error", e);
                }
            }
        });
        _syncer.setDaemon(true);
        LOGGER.info(this.getClass().getName() + " syncThread start");
        _syncer.start();
    }

    @Override
    public void incr(String field, String key, int step) {
        JSONObject jsonObject = storeMap.get(key);
        if (jsonObject == null) {
            jsonObject = new JSONObject();
            jsonObject.put(field, step);
        } else {
            Integer value = jsonObject.getInteger(field);
            jsonObject.put(field, value == null ? step : step + value);
        }
        storeMap.put(key, jsonObject);


    }

    @Override
    public void incr(String field, String key) {
        incr(field, key, 1);
    }

    public void clearStoreDB() {
        storeMap = new ConcurrentHashMap<String, JSONObject>();
    }

    protected void sync(Map<String, JSONObject> storeMap) {
    }

    public void saftyClose() {
        //同步数据完成关闭
        if (storeMap != null) {
            sync(storeMap);
        }


    }

}
