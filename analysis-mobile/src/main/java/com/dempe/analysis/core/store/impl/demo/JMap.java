package com.dempe.analysis.core.store.impl.demo;

import com.dempe.analysis.core.store.DMap;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Administrator on 2014/11/28.
 */
public class JMap<K, V> implements DMap<K, V> {

    public Map<K, V> map;

    public void JMap() {
        map = new ConcurrentHashMap<K, V>();
    }

    @Override
    public void set(K key, V value) {
        map.put(key, value);
    }

    @Override
    public V get(String key) {
        return map.get(key);
    }
}
