package com.dempe.analysis.web;

import com.demep.analysis.common.message.AccessMessage;
import com.demep.analysis.common.store.mapdb.MQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: Dempe
 * Date: 2016/3/23
 * Time: 12:04
 * To change this template use File | Settings | File Templates.
 */
@Component
public class AnalysisQueue {
    private final static Logger LOGGER = LoggerFactory.getLogger(AnalysisQueue.class);
    private MQueue<AccessMessage> queue = new MQueue<AccessMessage>();
    @Resource
    private MessageHandler handler;
    private ScheduledExecutorService executorService;
    Thread thread;

    public AnalysisQueue() {
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    AccessMessage poll = queue.poll();
                    if (poll == null) {
                        try {
                            TimeUnit.SECONDS.sleep(1);
                        } catch (InterruptedException e) {
                            LOGGER.error(e.getMessage(), e);
                        }
                    }
                    // 分析数据
                    handler.handle(poll);
                }
            }
        });
        thread.setDaemon(true);
        thread.setName("analysis-consumer-thread");

    }

    public void startAnalysis() {
        thread.start();
    }

    public boolean produce(AccessMessage message) {
        return queue.offer(message);
    }


}
