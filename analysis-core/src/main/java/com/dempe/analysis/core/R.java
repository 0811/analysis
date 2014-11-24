package com.dempe.analysis.core;

/**
 * @author : Dempe
 * @version 1.0 date : 2014/9/28
 */
public class R {


    public final static String KEY_SPACE = ":";
    public final static String KEY_SPLIT = ",";
    public final static String DOLLAR_SPLIT = "$";


    public final static String EVENT_TYPE = "eventType";
    //public final static String EVENT_MESSAGE = "eventMessage";
    public final static String CONTENT = "content";
    public final static String UNIQUE_ID = "uniqueId";

    //***********************configuration properties*****************
    public final static String PROPS_NAME = "analystics.properties";
    public final static String MONGO_URL = "analystics.mongodb.url";
    public final static String SLEEP_TIME = "analystics.sync.interval";

    public final static String QUEUE_DATA_DIR = "analystics.file.queue.datadir";
    public final static String TASKNUM = "analystics.task.number";
    public final static String QUEUE_HOST = "analystics.file.queue.host";
    public final static String QUEUE_PORT = "analystics.file.queue.port";
    public final static String QUEUE_SERVER = "analystics.queue.server";


    public final static String DAILY = "daily";
    public final static String MONTHLY = "monthly";
    public final static String YEARLY = "yearly";
    public final static String ALL = "all";


    //*************************protocol**************
    public final static String KEY = "key";
    public final static String TYPE = "type";
    public final static String DATE_TIME = "date";
    public final static String ID = "id";
    public final static String RANK_FIELD = "rank";
    public final static int COUNTER_TYPE_CODE = 1;
    public final static int UNIQUE_TYPE_CODE = 2;
    public final static int RANKER_TYPE_CODE = 3;
    public final static int UNIQUE_RANKER_TYPE_CODE = 4;

    //*******************fields******************

    public final static String COUNTER_COLNAME = "counter";
    public final static String UNIQUER_COLNAME = "uniquer";
    public final static String EVENT_NAME = "field";
    public final static String APPKEY = "appkey";
    public final static String PLAT = "plat";
    public final static String COUNT = "count";
    public final static String MODEL = "model";
    public final static String RANK_NAME = "rankName";

    public final static String VERSION = "version";


}
