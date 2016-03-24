package com.dempe.analysis.queue;

import com.demep.analysis.common.store.mapdb.MQueue;
import com.thimbleware.jmemcached.Key;
import com.thimbleware.jmemcached.LocalCacheElement;
import com.thimbleware.jmemcached.storage.CacheStorage;
import org.jboss.netty.buffer.ChannelBuffers;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Dempe
 * Date: 2016/3/24
 * Time: 10:23
 * To change this template use File | Settings | File Templates.
 */
public class MapDBStorage implements CacheStorage<Key, LocalCacheElement> {

    private MQueue<byte[]> mQueue = new MQueue();

    @Override
    public long getMemoryCapacity() {
        return 0;
    }

    @Override
    public long getMemoryUsed() {
        return 0;
    }

    @Override
    public int capacity() {
        return 0;
    }

    @Override
    public void close() throws IOException {

    }

    @Override
    public LocalCacheElement putIfAbsent(Key key, LocalCacheElement value) {
        return null;
    }

    @Override
    public boolean remove(Object key, Object value) {
        return false;
    }

    @Override
    public boolean replace(Key key, LocalCacheElement oldValue, LocalCacheElement newValue) {
        return false;
    }

    @Override
    public LocalCacheElement replace(Key key, LocalCacheElement value) {

        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean containsKey(Object key) {
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public LocalCacheElement get(Object key) {
        if (!(key instanceof Key)) return null;
        byte[] data = mQueue.poll();
        if (data == null) {
            return null;
        }
        LocalCacheElement element = new LocalCacheElement((Key) key, 0, 0, 0);
        element.setData(ChannelBuffers.wrappedBuffer(data));
        return element;
    }

    @Override
    public LocalCacheElement put(Key key, LocalCacheElement value) {
        mQueue.add(value.getData().copy().array());
        return value;
    }

    @Override
    public LocalCacheElement remove(Object key) {
        return null;
    }

    @Override
    public void putAll(Map<? extends Key, ? extends LocalCacheElement> m) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Set<Key> keySet() {
        return null;
    }

    @Override
    public Collection<LocalCacheElement> values() {
        return null;
    }

    @Override
    public Set<Entry<Key, LocalCacheElement>> entrySet() {
        return null;
    }
}