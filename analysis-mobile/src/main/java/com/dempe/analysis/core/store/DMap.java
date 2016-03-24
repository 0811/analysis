package com.dempe.analysis.core.store;

/**
 * Created by Administrator on 2014/11/28.
 */
public interface DMap<K, V> {
    public void set(K key, V value);

    public V get(String key);
}
