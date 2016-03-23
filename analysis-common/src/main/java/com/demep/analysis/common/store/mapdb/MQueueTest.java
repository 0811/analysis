package com.demep.analysis.common.store.mapdb;

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
        MQueue<Integer> queue = new MQueue(DBMaker.newFileDB(new File("queue_test")).closeOnJvmShutdown().make(), "test");
        for (int i = 0; i < 1000; i++) {
            queue.offer(i);
        }
        for (int i = 0; i < 100; i++) {
            Integer value = queue.poll();
            System.out.println(value);
        }
        queue.close();


    }
}
