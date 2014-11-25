package com.dempe.analysis.core.store.sync;

/**
 * @author : Dempe
 * @version 1.0 date : 2014/10/13
 */


import com.dempe.analysis.core.Config;
import com.dempe.analysis.core.FieldsMap;
import com.dempe.analysis.core.R;
import com.mongodb.*;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.lang.reflect.Field;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

/**
 * @author : Dempe
 * @version 1.0 date : 2014/10/13
 */
public class MongoSyncDao implements SyncDao {

    public static final Logger LOGGER = Logger.getLogger(MongoSyncDao.class);

    private final static String DEF_MONGO_URL = "127.0.0.1:27000";
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
    public void sync4Map(Map<String, Integer> storeMap) {
        LOGGER.info("sync map to mongodb now");
        Iterator<Map.Entry<String, Integer>> keys = storeMap.entrySet().iterator();
        String key = "";
        String colName;
        String fieldKey;
        String[] keyValues;
        Integer value = 0;
        Map.Entry<String, Integer> entry;
        DBObject query;
        DBObject update;
        while (keys.hasNext()) {
            try {
                entry = keys.next();
                key = entry.getKey();
                colName = StringUtils.substringBefore(key, R.DOLLAR_SPLIT);
                System.out.println(colName);
                String[] fields = FieldsMap.getStringArray(colName);
                fieldKey = StringUtils.substringAfter(key, R.DOLLAR_SPLIT);
                if(fields==null||fieldKey==null){
                    return;
                }

                keyValues = fieldKey.split(R.KEY_SPACE);
                int length =fields.length;
                if(length>=keyValues.length){
                    return;
                }
                query = new BasicDBObject("_id", fieldKey);
                System.out.println("====>"+query);
                for(int i= 0;i<length;i++){
                    query.put(fields[i],keyValues[i]);
                }
                value = entry.getValue();
                update = new BasicDBObject();
                update.put(keyValues[length], value);
                System.out.println(query);
                incrBy(colName, query, update);
            } catch (Exception e) {
                e.printStackTrace();
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
