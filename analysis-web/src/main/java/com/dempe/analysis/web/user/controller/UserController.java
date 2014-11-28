package com.dempe.analysis.web.user.controller;

import com.dempe.analysis.web.user.dao.UserDao;
import com.dempe.analysis.web.user.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2014/11/23.
 */
@Controller
public class UserController {

    @Resource
    private UserDao userDao;

    @RequestMapping("/login/{name}/{password}")
    public String login(@PathVariable String name, @PathVariable String password) {
        User user = userDao.findOne(userDao.createQuery().field("name").equal(name).field("password").equal(password));
       if(user==null){
           return "error";
       }
        return "dashboard";
    }

    @RequestMapping("/register/{name}/{password}")
    public String register(@PathVariable String name, @PathVariable String password){
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        user.setCreateAt(System.currentTimeMillis());
        userDao.save(user);
        return "success";
    }


}
