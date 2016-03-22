package com.demep.analysis.common.store.mapdb;


import com.demep.analysis.common.store.Queue;
import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.HTreeMap;
import org.mapdb.Serializer;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Dempe
 * Date: 2016/3/22
 * Time: 18:16
 * To change this template use File | Settings | File Templates.
 */
public class MDBQueue<E extends Serializable> implements Queue<E> {

    private int readIndex;
    private int writeIndex;
    private int SIZE = Integer.MAX_VALUE;
    private DB db;
    private String queueName = "DEF_MAPDB-QUEUE";
    private HTreeMap<Integer, Serializable> store;

    public MDBQueue(DB db, String queueName) {
        this.db = db;
        this.queueName = queueName;
        this.store = db.hashMap(queueName).keySerializer(Serializer.INTEGER).valueSerializer(Serializer.JAVA).create();
    }

    public MDBQueue() {
        this.db = DBMaker.memoryDB().make();
    }


    @Override
    public boolean add(E e) {
        store.put(writeIndex, e);
        return true;
    }

    @Override
    public boolean offer(E e) {
        return false;
    }

    @Override
    public E remove() {
        return null;
    }

    @Override
    public E poll() {
        return null;
    }

    @Override
    public E element() {
        return null;
    }

    @Override
    public E peek() {
        return null;
    }
}
