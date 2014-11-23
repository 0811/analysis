package com.dempe.analysis.web.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2014/11/23.
 */
@Controller
public class UserController {


    @RequestMapping("/login")
    public String login() {
        System.out.println("----------login controller-----------");
        return "dashboard";
    }


}
