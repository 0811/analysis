package com.dempe.analysis.core.store;


import com.alibaba.fastjson.JSONObject;
import com.dempe.analysis.core.store.sync.MongoSyncDao;
import com.dempe.analysis.core.store.sync.SyncDao;
import org.apache.log4j.Logger;

import java.util.Map;

/**
 * 去重累加map
 * 定时同步数据到mongodb
 *
 * @author : Dempe
 * @version 1.0 date : 2014/10/17
 */
public class UniqueCountStoreMap extends TimeSyncStoreMap {

    public static final Logger LOGGER = Logger.getLogger(UniqueCountStoreMap.class);

    public static UniqueCountStoreMap instance;

    private final static SyncDao syncDao = new MongoSyncDao();

    private UniqueCountStoreMap() {
        super();
    }

    @Override
    protected void sync(Map<String, JSONObject> storeMap) {
        LOGGER.debug("sync to mongodb start ...");
        syncDao.sync4Map(storeMap);
    }

    public static UniqueCountStoreMap getInstance() {
        if (instance == null) {
            instance = new UniqueCountStoreMap();
        }
        return instance;
    }

}
