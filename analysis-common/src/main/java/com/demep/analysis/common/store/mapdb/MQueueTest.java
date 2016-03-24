package com.demep.analysis.common.store.mapdb;

import com.demep.analysis.common.message.AccessMessage;
import org.mapdb.DBMaker;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: Dempe
 * Date: 2016/3/23
 * Time: 11:11
 * To change this template use File | Settings | File Templates.
 */
public class MQueueTest {
    public static void main(String[] args) {
        MQueue<AccessMessage> queue = new MQueue(DBMaker.newFileDB(new File("queue_test4")).closeOnJvmShutdown().make(), "test");

        AccessMessage message = new AccessMessage();
        message.setMethod("post");
        queue.offer(message);
        System.out.println(queue.poll().getMethod());
        queue.close();


    }
}
