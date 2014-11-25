package com.dempe.analysis.core;


import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author : Dempe
 * @version 1.0 date : 2014/10/17
 */
public class AnalysisContext {

    private final static Logger LOGGER = Logger.getLogger(AnalysisContext.class);

    private int analysisPoolSize = Runtime.getRuntime().availableProcessors();

    private int taskNum;
    private boolean closeOnJvmShutdown = true;
    private String queueDataDir;


    private BlockingQueue<String> blockingQueue;


    public AnalysisContext(AnalysisBuilder builder) {
        this.taskNum = builder.getTaskNum();
        this.closeOnJvmShutdown = builder.isCloseOnJvmShutdown();
        this.queueDataDir = builder.getQueueDataDir();
        blockingQueue = new ArrayBlockingQueue<String>(50000);

    }

    public void start() {
        startQueuePuller();
        startAnalysis();
        safetyClose();
    }

    public void startAnalysis() {
        LOGGER.info("analysis is start...");
        LOGGER.info("analysis.task.number : " + taskNum);
        LOGGER.info("analysis.closeOnJvmShutdown : " + closeOnJvmShutdown);
        ExecutorService analysisPool = Executors.newFixedThreadPool(analysisPoolSize);
        for (int i = 0; i < taskNum; i++) {
            MessageSpout messageSpout = new MessageSpout(blockingQueue);
            analysisPool.submit(messageSpout);
        }


    }

    public void startQueuePuller() {
        List<Object> queues = Config.getList(R.QUEUE_SERVER);
        if (queues == null || queues.size() == 0) {
            return;
        }
        ExecutorService queuePullerPool = Executors.newFixedThreadPool(queues.size());
        for (Object queue : queues) {
            String qStr = (String) queue;
            String host = StringUtils.substringBefore(qStr, R.KEY_SPACE);
            String port = StringUtils.substringAfter(qStr, R.KEY_SPACE);
            QueuePuller puller = new QueuePuller(host, Integer.parseInt(port), blockingQueue);
            queuePullerPool.submit(puller);
        }

    }

    public void safetyClose() {
        if (closeOnJvmShutdown) {
            Runtime.getRuntime().addShutdownHook(new Thread() {
                public void run() {
                    //StoreMapManager.safetyClose();
                }
            });
        }
    }


}
