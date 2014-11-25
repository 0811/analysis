package com.dempe.analysis.core.store.sync;

import java.util.Map;

/**
 * @author : Dempe
 * @version 1.0 date : 2014/10/13
 */
public interface SyncDao {
    void sync4Map(Map<String, Integer> storeMap);
}
