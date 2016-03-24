package com.dempe.analysis.core.simulator;

import com.dempe.analysis.core.utils.StatusMonitor;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Administrator
 */
public class ClientMonitorServer {

    private final static StatusMonitor statusMonitor = new StatusMonitor("Client-Simulator");

    public static void main(String[] args) throws InterruptedException {

        ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(MonitorProp.THREAD_NUM);

        int count = 0;

        while (true) {
            executorService.submit(new ClientWriter());
            count++;
            if (count % MonitorProp.MSG_NUM == 0) {
                TimeUnit.MICROSECONDS.sleep(MonitorProp.THREAD_SLEEP);
            }
            statusMonitor.increment();
        }

    }

}
