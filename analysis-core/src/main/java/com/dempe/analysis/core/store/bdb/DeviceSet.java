package com.dempe.analysis.core.store.bdb;

import com.dempe.analysis.core.Config;
import com.sleepycat.bind.tuple.StringBinding;
import com.sleepycat.collections.StoredKeySet;
import com.sleepycat.je.*;
import org.apache.log4j.Logger;

import java.io.File;

/**
 * @author : Dempe
 * @version 1.0 date : 2014/11/26
 */
public class DeviceSet {

    private static Logger LOGGER = Logger.getLogger(BdbKeySet.class);

    public static DeviceSet instance;

    private DeviceSet() {

    }

    public static DeviceSet getInstance() {
        if (instance == null) {
            instance = new DeviceSet("KEY_SET", "/data/analysis/deviceSet");
        }
        return instance;
    }

    Environment env;

    Database storedb;

    StoredKeySet<String> deviceSet;

    String dbname;

    String dbpath;


    public DeviceSet(String dbname, String dbpath) {
        this.dbname = dbname;
        this.dbpath = dbpath;
        String dayDbPath = dbpath;
        setUpEnv(dayDbPath);
        openDb(dbname);
        deviceSet = new StoredKeySet<String>(storedb, new StringBinding(), true);
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
            deviceSet = null;
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


    public boolean exists(String key) {
        return deviceSet.contains(key);
    }

    public void add(String key) {
        deviceSet.add(key);
    }

}
