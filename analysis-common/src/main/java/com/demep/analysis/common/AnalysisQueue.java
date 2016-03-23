package com.demep.analysis.common;

import com.demep.analysis.common.message.AccessMessage;
import com.demep.analysis.common.store.mapdb.MQueue;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: Dempe
 * Date: 2016/3/23
 * Time: 12:04
 * To change this template use File | Settings | File Templates.
 */
public class AnalysisQueue {
    private MQueue<AccessMessage> queue = new MQueue<AccessMessage>();
    private ScheduledExecutorService executorService;

    public AnalysisQueue() {
        this.executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                consume();
            }
        }, 1L, 1L, TimeUnit.SECONDS);
    }

    public boolean produce(AccessMessage message) {
        return queue.offer(message);
    }

    public void consume() {
        AccessMessage message = queue.poll();
        System.out.println(message);
    }
}
