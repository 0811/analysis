package com.dempe.analysis.web.user.dao;

import com.dempe.analysis.web.user.model.User;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * Created by Administrator on 2014/11/29.
 */
@Repository
public class UserDao extends BasicDAO<User, Serializable> {

    @Autowired
    protected UserDao(Datastore dataStore) {
        super(dataStore);
        ensureIndexes();// 自动创建索引
    }
}

