package com.dempe.analysis.core.simulator;

import com.dempe.analysis.core.Config;

/**
 * @author : Dempe
 * @version 1.0 date : 2014/11/24
 */
public class MonitorProp {

    public static final String[] MONITOR_APPKEYS = Config.getString("client.monitor.appkeys").split(",");

    public static final String QUEUE_URL = Config.getString("client.monitor.url");

    public static final int THREAD_NUM = Integer.parseInt(Config.getString("client.monitor.nthread"));

    public static final int THREAD_SLEEP = Integer.parseInt(Config.getString("client.monitor.sleep"));

    public static final int MSG_NUM = Integer.parseInt(Config.getString("client.monnitor.msg.num"));


}
