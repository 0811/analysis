package com.dempe.analysis.manger.api.device.dao;

import com.dempe.analysis.manger.api.device.model.DeviceCountry;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * Created by Administrator on 2014/11/27.
 */
@Repository
public class DeviceCountryDao extends BasicDAO<DeviceCountry, Serializable> {

    @Autowired
    protected DeviceCountryDao(Datastore dataStore) {
        super(dataStore);
        ensureIndexes();// 自动创建索引
    }
}
