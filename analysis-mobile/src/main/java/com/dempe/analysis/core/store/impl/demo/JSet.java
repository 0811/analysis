package com.dempe.analysis.core.store.impl.demo;

import com.dempe.analysis.core.store.DSet;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2014/11/28.
 */
public class JSet<T> implements DSet<T> {

    public Set<T> set = new HashSet<T>();

    public JSet() {
        set = new HashSet<T>();
    }

    @Override
    public boolean exist(T value) {
        return set.contains(value);
    }

    @Override
    public void add(T value) {
        set.add(value);

    }

}
