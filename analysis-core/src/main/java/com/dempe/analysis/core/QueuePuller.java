package com.dempe.analysis.core;


import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: lamfire
 * Date: 14-10-20
 * Time: 下午7:39
 * To change this template use File | Settings | File Templates.
 */

public class QueuePuller implements Runnable {
    private final static Logger LOGGER = Logger.getLogger(QueuePuller.class);

    private BlockingQueue<String> blockingQueue;

    public QueuePuller(String host, int port, BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }


    public byte[] pull() {
        byte[] bytes = null;
        JSONObject json = new JSONObject();
        json.put("CMD", "POLL");

        if (bytes != null && bytes.length == 0) {
            bytes = null;
        }
        return bytes;
    }

    @Override
    public void run() {
        try {
            while (true) {
                byte[] data = pull();
                if (data == null) {
                    TimeUnit.SECONDS.sleep(1);
                    continue;
                }
                //LOGGER.debug("[data] = "+data);
                blockingQueue.put(new String(data));
                //localQueue.add(data);
            }

        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }

    }

    public static void main(String args[]) {

    }
}
