package com.dempe.analysis.core.store;


import com.dempe.analysis.core.store.sync.MongoSyncDao;
import com.dempe.analysis.core.store.sync.SyncDao;
import org.apache.log4j.Logger;

import java.util.Map;

/**
 * @author : Dempe
 * @version 1.0 date : 2014/10/17
 */
public class UniqueStoreMap extends TimeSyncStoreMap {

    public static final Logger LOGGER = Logger.getLogger(UniqueStoreMap.class);

    public static UniqueStoreMap instance;

    private final static SyncDao syncDao = new MongoSyncDao();

    private UniqueStoreMap() {
        super();
    }

    @Override
    protected void sync(Map<String, Integer> storeMap) {
        LOGGER.debug("sync to mongodb start ...");
        syncDao.sycn4Map(storeMap);
    }

    public static UniqueStoreMap getInstance() {
        if (instance == null) {
            instance = new UniqueStoreMap();
        }
        return instance;
    }

}
