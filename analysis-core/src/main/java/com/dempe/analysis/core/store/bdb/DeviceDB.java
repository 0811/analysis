package com.dempe.analysis.core.store.bdb;

import com.dempe.analysis.core.Config;
import com.dempe.analysis.core.utils.MD5;
import com.sleepycat.bind.tuple.StringBinding;
import com.sleepycat.collections.StoredKeySet;
import com.sleepycat.collections.StoredMap;
import com.sleepycat.je.*;
import org.apache.log4j.Logger;

import java.io.File;

/**
 * @author : Dempe
 * @version 1.0 date : 2014/11/26
 */
public class DeviceDB {

    private static Logger LOGGER = Logger.getLogger(BdbKeySet.class);

    public static DeviceDB instance;

    private DeviceDB() {

    }

    public static DeviceDB getInstance() {
        if (instance == null) {
            instance = new DeviceDB("KEY_SET", "/data/analysis/deviceSet");
        }
        return instance;
    }

    Environment env;

    Database storedb;

    StoredMap<String, String> storedMap;

    String dbname;

    String dbpath;


    public DeviceDB(String dbname, String dbpath) {
        this.dbname = dbname;
        this.dbpath = dbpath;
        String dayDbPath = dbpath;
        setUpEnv(dayDbPath);
        openDb(dbname);
        storedMap = new StoredMap(storedb, new StringBinding(), new StringBinding(), true);
    }


    public void setUpEnv(String path) {
        long cacheSize = Long.valueOf(Config.getString("db.cache.size"));
        String logFileSize = Config.getString("db.log.size");
        String checkPointSize = Config.getString("db.checkpoint.size");
        EnvironmentConfig envConfig = new EnvironmentConfig();
        envConfig.setAllowCreateVoid(true);
        envConfig.setReadOnlyVoid(false);
        envConfig.setTransactionalVoid(false);
        envConfig.setDurability(Durability.COMMIT_NO_SYNC);
        envConfig.setCacheSizeVoid(cacheSize);
        envConfig.setConfigParam(EnvironmentConfig.LOG_FILE_MAX, logFileSize);
        envConfig.setConfigParam(EnvironmentConfig.CHECKPOINTER_BYTES_INTERVAL, checkPointSize);
        File envHome = new File(path);
        if (!envHome.exists()) {
            envHome.mkdirs();
        }
        env = new Environment(new File(path), envConfig);
    }

    public void syncClose() {
        try {
            if (storedb != null) {
                storedb.close();
            }
            if (env != null) {
                env.sync();
                env.close();
            }
        } catch (DatabaseException e) {
            if (env != null) {
                env.close();
            }
        } catch (IllegalStateException e) {
            if (env != null) {
                env.close();
            }
        } finally {
            storedMap = null;
            storedb = null;
            env = null;

        }
    }


    public void openDb(String dbname) {
        DatabaseConfig dbconfig = new DatabaseConfig();
        dbconfig.setAllowCreateVoid(true);
        dbconfig.setSortedDuplicatesVoid(false);

        try {
            storedb = env.openDatabase(null, dbname, dbconfig);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
    }


   public void set(String key,String value){
       storedMap.put(MD5.hash(key),value);
   }

    public String get(String key){
        return storedMap.get(MD5.hash(key));
    }

}
