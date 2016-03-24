package com.dempe.analysis.core.utils;

import org.apache.log4j.Logger;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : Dempe
 * @version 1.0 date : 2014/11/24
 */
public class ThreadSafeStatsMonitor extends TimerTask implements Runnable {


    private static final Logger LOGGER = Logger.getLogger(ThreadSafeStatsMonitor.class);

    private AtomicInteger ps = new AtomicInteger(0);


    public static ThreadSafeStatsMonitor instance;

    private ThreadSafeStatsMonitor() {
        new Timer().scheduleAtFixedRate(this, 1000l, 1000l);
    }

    public static ThreadSafeStatsMonitor getInstance() {
        if (instance == null) {
            synchronized (ThreadSafeStatsMonitor.class) {
                if (instance == null) {
                    instance = new ThreadSafeStatsMonitor();
                }
            }
        }
        return instance;
    }


    public void increment() {
        ps.incrementAndGet();
    }

    @Override
    public void run() {
        LOGGER.debug("[ps/s=" + ps.get());
        ps = new AtomicInteger(0);
    }
}
