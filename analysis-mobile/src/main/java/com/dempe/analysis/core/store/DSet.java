package com.dempe.analysis.core.store;

/**
 * Created by Administrator on 2014/11/28.
 */
public interface DSet<T> {
    boolean exist(T key);

    void add(T key);
}
