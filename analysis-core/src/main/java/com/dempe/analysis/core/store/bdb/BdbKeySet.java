package com.dempe.analysis.core.store.bdb;

import com.dempe.analysis.core.Config;
import com.sleepycat.bind.tuple.StringBinding;
import com.sleepycat.collections.StoredKeySet;
import com.sleepycat.je.*;
import org.apache.log4j.Logger;

import java.io.File;

/**
 * TODO
 * Created by dempe on 14-10-17.
 */
public class BdbKeySet {

    private static Logger LOGGER = Logger.getLogger(BdbKeySet.class);

    public static BdbKeySet instance;

    private BdbKeySet() {

    }

    public static BdbKeySet getInstance() {
        if (instance == null) {
            instance = new BdbKeySet("KEY_SET", "/data/analysis/keySet2");
        }
        return instance;
    }

    Environment env;

    Database storedb;

    StoredKeySet<String> storedKeySet;

    String dbname;

    String dbpath;


    public BdbKeySet(String dbname, String dbpath) {
        this.dbname = dbname;
        this.dbpath = dbpath;
        String dayDbPath = dbpath;
        setUpEnv(dayDbPath);
        openDb(dbname);
        storedKeySet = new StoredKeySet<String>(storedb, new StringBinding(), true);
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
            storedKeySet = null;
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
        return storedKeySet.contains(key);
    }

    public void add(String key) {
        storedKeySet.add(key);
    }


}
