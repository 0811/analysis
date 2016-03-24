package com.dempe.analysis.core.utils;

import org.apache.log4j.Logger;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : Dempe
 * @version 1.0 date : 2014/11/24
 */
public class StatusMonitor extends TimerTask implements Runnable {

    private static final Logger LOGGER = Logger.getLogger(StatusMonitor.class);

    private String name;

    private AtomicInteger ps = new AtomicInteger(0);

    public StatusMonitor(String name) {
        this.name = name;
        new Timer().scheduleAtFixedRate(this, 1000l, 1000l);
    }

    public void increment() {
        ps.incrementAndGet();
    }

    @Override
    public void run() {
        LOGGER.debug("[name=" + name + "], " + "[ps/s=" + ps.get() + "]");
        ps = new AtomicInteger(0);
    }
}
