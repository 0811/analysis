package com.dempe.analysis.manger.system.service;

import com.dempe.analysis.manger.system.dao.UserDao;
import com.dempe.analysis.manger.system.model.User;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Key;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Dempe
 * Date: 2016/3/16
 * Time: 14:01
 * To change this template use File | Settings | File Templates.
 */
@Service
public class UserService {


    @Resource
    private UserDao userDao;

    public String saveUser(User user) {
        Key<User> userKey = userDao.saveUser(user);
        return userKey.getId().toString();
    }

    public User findByName(String name) {
        return userDao.findByName(name);
    }

    public User findByUid(String uid) {
        return userDao.get(new ObjectId(uid));
    }

    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    public void delUser(String uid) {
        userDao.deleteById(new ObjectId(uid));
    }

    public List<User> listUser() {
        return userDao.createQuery().asList();
    }
}
