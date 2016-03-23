package com.demep.analysis.common.store.mapdb;


import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.HTreeMap;

import java.io.Serializable;
import java.util.AbstractQueue;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 基于MapDB实现的file queue
 * User: Dempe
 * Date: 2016/3/22
 * Time: 18:16
 * To change this template use File | Settings | File Templates.
 */
public class MQueue<E extends Serializable> extends AbstractQueue<E> {

    private final static String DED_QUEUE_NAME = "DEF_MAP_QUEUE";
    private final static String HEAD_INDEX_KEY = "headIndex";
    private final static String TAIL_INDEX_KEY = "tailIndex";
    private final static String QUEUE_META_STORE = "QUEUE_META_STORE";
    private DB db;

    private String queueName;
    private HTreeMap<Long, E> store;
    private HTreeMap<String, Long> metaStore;
    private AtomicLong headIndex;                   // 头部指针
    private AtomicLong tailIndex;


    public MQueue(DB db, String queueName) {
        this.db = db;
        this.queueName = queueName;
        store = db.getHashMap(queueName);
        metaStore = db.getHashMap(QUEUE_META_STORE);
        Long headIndexMeta = metaStore.get(HEAD_INDEX_KEY);
        Long tailIndexMeta = metaStore.get(TAIL_INDEX_KEY);
        if (headIndexMeta == null || tailIndexMeta == null) {
            tailIndexMeta = 0L;
            headIndexMeta = 0L;
        }
        this.headIndex = new AtomicLong(headIndexMeta);
        this.tailIndex = new AtomicLong(tailIndexMeta);

    }

    public MQueue() {
        this(DBMaker.newMemoryDB().make(), DED_QUEUE_NAME);
    }


    public String getQueueName() {
        return queueName;
    }

    @Override
    public Iterator<E> iterator() {
        return store.values().iterator();
    }

    @Override
    public int size() {
        synchronized (tailIndex) {
            synchronized (headIndex) {
                return (int) (tailIndex.get() - headIndex.get());
            }
        }
    }


    @Override
    public synchronized boolean offer(E e) {
        long key = tailIndex.get();
        store.put(key, e);
        tailIndex.incrementAndGet();
        return true;
    }


    @Override
    public E poll() {
        long andIncrement = headIndex.getAndIncrement();
        return store.remove(andIncrement);
    }

    @Override
    public E peek() {
        return store.get(headIndex.get());
    }

    /**
     * 清理
     */
    public void clear() {
        metaStore.clear();
        store.clear();
    }

    /**
     * 关闭
     */
    public void close() {
        metaStore.put(HEAD_INDEX_KEY, headIndex.get());
        metaStore.put(TAIL_INDEX_KEY, tailIndex.get());
        db.commit();
        db.close();
    }

    public void commit() {
        db.commit();
    }

}
