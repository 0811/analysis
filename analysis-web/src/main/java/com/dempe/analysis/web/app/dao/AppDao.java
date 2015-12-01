package com.dempe.analysis.web.app.dao;

import com.dempe.analysis.web.app.model.App;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Dempe
 * Date: 2015/12/1
 * Time: 18:00
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class AppDao extends BasicDAO<App, Serializable> {

    @Autowired
    protected AppDao(Datastore dataStore) {
        super(dataStore);
        ensureIndexes();// 自动创建索引
    }
}
