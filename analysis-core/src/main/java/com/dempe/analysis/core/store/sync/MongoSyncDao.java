package com.dempe.analysis.core.store.sync;

/**
 * @author : Dempe
 * @version 1.0 date : 2014/10/13
 */


import com.dempe.analysis.core.Config;
import com.dempe.analysis.core.R;
import com.mongodb.*;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.Map;

/**
 * @author : Dempe
 * @version 1.0 date : 2014/10/13
 */
public class MongoSyncDao implements SyncDao {

    public static final Logger LOGGER = Logger.getLogger(MongoSyncDao.class);

    private final static String DEF_MONGO_URL = "127.0.0.1:27017";
    private final static String DEF_MONGOD_DB = "ANALYSIS";

    private DB db;

    private Mongo mongo;


    public MongoSyncDao() {
        this(Config.getString(R.MONGO_URL, DEF_MONGO_URL));
    }

    public MongoSyncDao(String url) {
        try {
            mongo = new Mongo(url);
            db = mongo.getDB(DEF_MONGOD_DB);
        } catch (UnknownHostException e) {
            LOGGER.error(url + " unknown host error", e);
        }
    }

    public DBCollection getCollection(String name) {
        return db.getCollection(name);
    }

    @Override
    public void sycn4Map(Map<String, Integer> storeMap) {
        LOGGER.info("sync map to mongodb now");
        Iterator<Map.Entry<String, Integer>> keys = storeMap.entrySet().iterator();
        String key = "";
        String colName;
        String fieldKey;
        String[] keyValues;
        Integer value = 0;
        while (keys.hasNext()) {
            try {
                Map.Entry<String, Integer> entry = keys.next();
                key = entry.getKey();
                colName = StringUtils.substringBefore(key, R.DOLLAR_SPLIT);
                fieldKey = StringUtils.substringAfter(key, R.DOLLAR_SPLIT);
                keyValues = fieldKey.split(R.KEY_SPLIT);
                value = entry.getValue();
                DBObject query = new BasicDBObject("_id", fieldKey);
                for (String kv : keyValues) {
                    query.put(StringUtils.substringBefore(kv, R.KEY_SPACE), StringUtils.substringAfter(kv, R.KEY_SPACE));
                }
                DBObject update = new BasicDBObject();
                update.put(R.COUNT, value);
                incrBy(colName, query, update);
            } catch (Exception e) {
                LOGGER.error("sync error key-value:" + key + ":" + value);
            }
        }
    }

    public void incrBy(String name, DBObject query, DBObject update) {
        DBCollection collection = getCollection(name);
        LOGGER.info("[colname] = " + name + " [query] = " + query + "[update]=" + update);
        collection.update(query, new BasicDBObject("$inc", update), true, false);
    }
}
