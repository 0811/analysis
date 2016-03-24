package com.dempe.analysis.queue.cli;

import com.google.code.yanf4j.core.impl.StandardSocketOption;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.exception.MemcachedException;
import net.rubyeye.xmemcached.utils.AddrUtil;

import java.io.IOException;
import java.io.Serializable;
import java.util.AbstractQueue;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created with IntelliJ IDEA.
 * User: Dempe
 * Date: 2016/3/23
 * Time: 15:17
 * To change this template use File | Settings | File Templates.
 */
public class QueueCli<E extends Serializable> extends AbstractQueue<E> {


    public QueueCli(String host, int port) {

    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean offer(E e) {
        return false;
    }

    @Override
    public E poll() {
        return null;
    }

    @Override
    public E peek() {
        return null;
    }

    private static MemcachedClientBuilder builder = null;

    private static MemcachedClient client;

    public static void main(String[] args) throws InterruptedException, MemcachedException, TimeoutException {
        builder = new XMemcachedClientBuilder(AddrUtil.getAddresses("127.0.0.1:9999"));
        builder.setConnectionPoolSize(50); // set connection pool size to five

        try {
            client = builder.build();
            client.setOptimizeGet(false);
            builder.setSocketOption(StandardSocketOption.SO_KEEPALIVE, true);
            builder.setSocketOption(StandardSocketOption.SO_RCVBUF, 64 * 1024);
            builder.setSocketOption(StandardSocketOption.SO_SNDBUF, 64 * 1024);
            builder.setSocketOption(StandardSocketOption.SO_REUSEADDR, true);
            builder.setSocketOption(StandardSocketOption.TCP_NODELAY, false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        client.set("hello", 0, "123333");
        while (true){
            System.out.println("getvalue>>>>>>>>>>>>"+client.get("hello"));
            TimeUnit.SECONDS.sleep(1);
        }
    }


}
