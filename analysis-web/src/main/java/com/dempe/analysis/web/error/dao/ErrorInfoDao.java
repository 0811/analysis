package com.dempe.analysis.web.error.dao;

import com.dempe.analysis.web.error.model.ErrorInfo;
import com.dempe.analysis.web.retention.model.RetentionDaily;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * Created by Administrator on 2014/11/30.
 */
@Repository
public class ErrorInfoDao  extends BasicDAO<ErrorInfo, Serializable> {

    @Autowired
    protected ErrorInfoDao(Datastore dataStore) {
        super(dataStore);
        ensureIndexes();// 自动创建索引
    }
}