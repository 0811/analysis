package com.dempe.analysis.web.device.dao;

import com.dempe.analysis.web.device.model.DeviceOs;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * Created by Administrator on 2014/11/29.
 */
@Repository
public class DeviceOsDao extends BasicDAO<DeviceOs, Serializable> {

    @Autowired
    protected DeviceOsDao(Datastore dataStore) {
        super(dataStore);
        ensureIndexes();// 自动创建索引
    }
}
