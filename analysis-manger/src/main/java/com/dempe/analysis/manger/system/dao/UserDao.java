package com.dempe.analysis.manger.system.dao;

import com.dempe.analysis.manger.system.model.User;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;
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
 * Date: 2016/3/16
 * Time: 14:00
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class UserDao extends BasicDAO<User, Serializable> {

    @Autowired
    protected UserDao(Datastore dataStore) {
        super(dataStore);
        ensureIndexes();// 自动创建索引
    }

    public Key<User> saveUser(User user) {
        user.setCreateAt(System.currentTimeMillis());
        return save(user);
    }

    public User findByName(String name) {
        return findOne(createQuery().field("name").equal(name));
    }

    public void updateUser(User user) {
        UpdateOperations<User> updateOperations = createUpdateOperations();
        int roleId = user.getRoleId();
        updateOperations= updateOperations.set("roleId", roleId);
        if (user.getName() != null) {
            updateOperations=  updateOperations.set("name", user.getName());
        }
        if (user.getProfile() != null) {
            updateOperations= updateOperations.set("profile", user.getProfile());
        }
        Query<User> query = createQuery().field(Mapper.ID_KEY).equal(new ObjectId(user.getUid()));
        update(query, updateOperations);

    }
}