package com.dempe.analysis.web.device.dao;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * Created by Administrator on 2014/11/29.
 */
@Repository
public class DeviceResolution extends BasicDAO<DeviceResolution, Serializable> {

    @Autowired
    protected DeviceResolution(Datastore dataStore) {
        super(dataStore);
        ensureIndexes();// 自动创建索引
    }
}
