package com.dempe.analysis.core.store;

import com.dempe.analysis.core.store.sync.MongoSyncDao;
import com.dempe.analysis.core.store.sync.SyncDao;
import org.apache.log4j.Logger;

import java.util.Map;

/**
 * Created by Administrator on 2014/11/25.
 */
public class CountStoreMap extends TimeSyncStoreMap {

    public static final Logger LOGGER = Logger.getLogger(CountStoreMap.class);

    public static CountStoreMap instance;

    private final static SyncDao syncDao = new MongoSyncDao();

    private CountStoreMap() {
        super();
    }

    @Override
    protected void sync(Map<String, Integer> storeMap) {
        LOGGER.debug("sync to mongodb start ...");
        syncDao.sync4Map(storeMap);
    }

    public static CountStoreMap getInstance() {
        if (instance == null) {
            instance = new CountStoreMap();
        }
        return instance;
    }

}

