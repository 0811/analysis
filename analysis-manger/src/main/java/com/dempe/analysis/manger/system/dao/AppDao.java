package com.dempe.analysis.manger.system.dao;

import com.dempe.analysis.manger.system.model.App;
import com.dempe.analysis.manger.system.model.User;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.mapping.Mapper;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
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

    public void updateApp(App app) {
        UpdateOperations<App> updateOperations = createUpdateOperations();

        if (app.getName() != null) {
            updateOperations=  updateOperations.set("name", app.getName());
        }
        if (app.getInfo() != null) {
            updateOperations= updateOperations.set("info", app.getInfo());
        }
        Query<App> query = createQuery().field(Mapper.ID_KEY).equal(app.getId());
        update(query, updateOperations);

    }
}
