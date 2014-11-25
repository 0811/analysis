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
    public final static String PLAT = "plat";
    public final static String COUNT = "count";
    public final static String RANK_NAME = "rankName";

    public final static String VERSION = "version";


    //******************************************
    public final static String DEVICE_DATA = "device_data";
    public final static String LAUNCH_DATA = "launch_data";
    public final static String EXIT_DATA = "exit_data";
    public final static String PAGE_DATA = "page_data";
    public final static String EVENT_DATA = "event_data";
    public final static String EVENTKV_DATA = "eventkv_data";
    public final static String ERROR_DATA = "error_data";



    public final static String APPKEY = "appkey";

    public final static String CREATE_DATE = "create_date";

//    "device_id": "50589a7dc52a2d0",
//    "appver": "1.0",
//    "apppkg": " com.example.sharesdk_test",
//    "platform_id": 1,
//    "sdkver": "1.0.0",
//    "channel_name": "Channel_A",
//    "mac": "d8:50:e6:80:45:98",
//    "model": "Nexus 7",
//    "sysver": "4.4.2",
//    "carrier": "400001",
//    "screensize": "1200x1824",
//    "factory": " asus",
//    "networktype": "Wi-Fi",
//    "is_jailbroken": 0,
//    "longitude": "121.49656",
//    "latitude": "31.21842",
//    "language": "zh",
//    "timezone": 8,
//    "cpu": "ARMv7 Processor rev 0 (v7l)",
//    "manuid": "KOT49H",
//    "manutime": "1386201442000"
    public final static String DEVICE_ID = "device_id";
    public final static String APPVER = "device_id";
    public final static String CHANNEL = "chanel";
    public final static String MODEL = "model";
    public final static String SYSVER = "sysver";
    public final static String CARRIER = "carrier";
    public final static String PLATFORM = "platform";



    //***********************报表名称

    public final static String usage_daily ="usage_daily";
    public final static String usage_hourly ="usage_hourly";
    public final static String usage_duration ="usage_duration";
    public final static String usage_duration_daily ="usage_duration_daily";
    public final static String usage_frequency ="usage_frequency";
    public final static String usage_page_count ="usage_page_count";
    public final static String retention_daily ="retention_daily";
    public final static String retention_weekly ="retention_weekly";
    public final static String retention_monthly ="retention_monthly";
    public final static String device_model ="device_model";
    public final static String usage_interval ="usage_interval";
    public final static String device_resolution ="device_resolution";
    public final static String device_os ="device_os";
    public final static String device_network ="device_network";
    public final static String device_country ="device_country";
    public final static String device_province ="device_province";
    public final static String device_carrier ="device_carrier";
















}
