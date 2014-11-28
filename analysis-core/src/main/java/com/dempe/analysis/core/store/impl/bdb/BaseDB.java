package com.dempe.analysis.core.store.impl.bdb;


import com.dempe.analysis.core.Config;
import com.dempe.analysis.core.R;
import com.dempe.analysis.core.utils.MD5;
import com.sleepycat.bind.tuple.StringBinding;
import com.sleepycat.collections.StoredMap;
import com.sleepycat.je.*;

import java.io.File;
import java.util.Calendar;

/**
 * Created by liss on 14-7-16.
 */
public abstract class BaseDB {

    Environment env;

    Database storedb;

    StoredMap<String, String> storedMap;

    static final boolean defaultFileByDay = false;

    boolean fileByDay;

    int day;

    String dbname;

    String dbpath;

    public BaseDB(String dbname, String dbpath) {
        this(dbname, dbpath, defaultFileByDay);
    }

    public BaseDB(String dbname, String dbpath, boolean fileByDay) {
        this.dbname = dbname;
        this.dbpath = dbpath;
        this.fileByDay = fileByDay;
        String dayDbPath = dbpath;
        if (fileByDay) {
            Calendar now = Calendar.getInstance();
            day = now.get(Calendar.DATE);
            // String nowString = Utils.format(now);
            dayDbPath += File.separator + "123";
        }
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
        //envConfig.setConfigParam(EnvironmentConfig.LOG_BUFFER_SIZE,"1024");
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

    //change db path by day.
    public void changeDBFile() {
        if (!fileByDay)
            return;
        Calendar now = Calendar.getInstance();
        int nowDate = now.get(Calendar.DATE);
        if (nowDate != day) {
            syncClose();
            // String nowString = Utils.format(now);
            String dayDbPath = this.dbpath + File.separator + "123";
            setUpEnv(dayDbPath);
            openDb(dbname);
            storedMap = new StoredMap(storedb, new StringBinding(), new StringBinding(), true);
        }
    }

    public boolean sismember(String key, String member) {
        String holdKey = MD5.hash(key + R.KEY_SPACE + member);
        return storedMap.containsKey(holdKey);
    }


    public void sadd(String key, String member) {
        String holdKey = MD5.hash(key + R.KEY_SPACE + member);
        storedMap.put(holdKey, member);
    }

    public boolean hexists(String key, String field) {
        String holdKey = MD5.hash(key + R.KEY_SPACE + field);
        return storedMap.containsKey(holdKey);
    }

    public void hset(String key, String field, String value) {
        String holdKey = MD5.hash(key + R.KEY_SPACE + field);
        storedMap.put(holdKey, value);
    }

    public String hget(String key, String field) {
        String holdKey = MD5.hash(key + R.KEY_SPACE + field);
        return storedMap.get(holdKey);
    }


}
