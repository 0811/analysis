package com.dempe.analysis.web.retention.dao;

import com.dempe.analysis.web.device.model.DeviceCarrier;
import com.dempe.analysis.web.retention.model.RetentionDaily;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * Created by Administrator on 2014/11/29.
 */
@Repository
public class RetentionDailyDao  extends BasicDAO<RetentionDaily, Serializable> {

    @Autowired
    protected RetentionDailyDao(Datastore dataStore) {
        super(dataStore);
        ensureIndexes();// 自动创建索引
    }
}
