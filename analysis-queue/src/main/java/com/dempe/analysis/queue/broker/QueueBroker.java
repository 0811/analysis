package com.dempe.analysis.queue.broker;

import com.dempe.analysis.queue.MapDBStorage;
import com.thimbleware.jmemcached.CacheImpl;
import com.thimbleware.jmemcached.Key;
import com.thimbleware.jmemcached.LocalCacheElement;
import com.thimbleware.jmemcached.MemCacheDaemon;
import com.thimbleware.jmemcached.storage.CacheStorage;

import java.net.InetSocketAddress;

/**
 * Created with IntelliJ IDEA.
 * User: Dempe
 * Date: 2016/3/23
 * Time: 15:43
 * To change this template use File | Settings | File Templates.
 */
public class QueueBroker {

    public static void main(String[] args) {
        InetSocketAddress addr = new InetSocketAddress("0.0.0.0", 9999);
        int idle = -1;
        boolean verbose = false;
        MemCacheDaemon.memcachedVersion = "0.1";
        final MemCacheDaemon<LocalCacheElement> daemon = new MemCacheDaemon<LocalCacheElement>();
        CacheStorage<Key, LocalCacheElement> storage = new MapDBStorage();
        CacheImpl cacheImpl = new CacheImpl(storage);
        daemon.setCache(cacheImpl);
        daemon.setAddr(addr);
        daemon.setBinary(false);
        daemon.setIdleTime(idle);
        daemon.setVerbose(verbose);
        daemon.start();


    }
}
