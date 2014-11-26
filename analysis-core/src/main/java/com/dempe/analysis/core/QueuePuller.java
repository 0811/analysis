package com.dempe.analysis.core;


import com.dempe.analysis.core.simulator.RandomClientMsgProducer;
import org.apache.log4j.Logger;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;


public class QueuePuller implements Runnable {
    private final static Logger LOGGER = Logger.getLogger(QueuePuller.class);

    private BlockingQueue<String> blockingQueue;

    public QueuePuller(String host, int port, BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }


    public String pull() {
        return RandomClientMsgProducer.getmsg();
    }

    @Override
    public void run() {
        try {
            while (true) {
                String data = pull();
                if (data == null) {
                    TimeUnit.SECONDS.sleep(1);
                    continue;
                }
                TimeUnit.SECONDS.sleep(1);
                LOGGER.debug("[data] = " + data);
                blockingQueue.put(data);
                //localQueue.add(data);
            }

        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }

    }

    public static void main(String args[]) {

    }
}
