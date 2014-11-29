package com.dempe.analysis.core;

import com.dempe.analysis.core.handle.MessageHandler;
import org.apache.log4j.Logger;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author : Dempe
 * @version 1.0 date : 2014/10/17
 */
public class MessageSpout implements Runnable {

    private final static Logger LOGGER = Logger.getLogger(MessageSpout.class);

    private BlockingQueue<String> blockingQueue;

    public MessageSpout(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    public String getMessage() throws InterruptedException {
        return blockingQueue.take();
    }

    private MessageHandler messageHandler = new MessageHandler();

    @Override
    public void run() {
        while (true) {
            try {
                String message = getMessage();
                while (message == null) {
                    LOGGER.debug("local queue is empty, thread sleep 1 s.");
                    TimeUnit.SECONDS.sleep(1);
                    continue;
                }
                LOGGER.debug("[message]=" + message);
                messageHandler.streaming(message);
            } catch (Exception e) {
                e.printStackTrace();
                LOGGER.error("handle message error:" + e);
            }

        }
    }

}
