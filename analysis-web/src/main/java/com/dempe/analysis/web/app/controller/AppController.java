package com.dempe.analysis.web.app.controller;

import com.dempe.analysis.web.app.service.AppService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: Dempe
 * Date: 2015/12/1
 * Time: 18:20
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/app")
public class AppController {

    @Resource
    private AppService appService;

    public void saveApp(@RequestParam String appName, @RequestParam String appInfo) {
        appService.addAPP(appName, appInfo);
    }


}
