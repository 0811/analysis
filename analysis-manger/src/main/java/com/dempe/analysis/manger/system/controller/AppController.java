package com.dempe.analysis.manger.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.dempe.analysis.manger.system.model.App;
import com.dempe.analysis.manger.system.service.AppService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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

    @RequestMapping("/save")
    public String saveApp(@ModelAttribute("app") App app) {
        appService.addAPP(app);
        return "redirect:/app/index";
    }

    @RequestMapping("/saveOrUpdate")
    public String saveOrUpdate(@ModelAttribute("app") App app) {
        if (StringUtils.isBlank(app.getId())) {
            appService.addAPP(app);
        } else {
            appService.updateApp(app);
        }
        return "redirect:/app/index";
    }


    @RequestMapping("/new")
    public String newApp() {
        return "/app/newApp";
    }

    @RequestMapping("/del")
    @ResponseBody
    public JSONObject delApp(@RequestParam String id) {
        appService.deleteAPP(id);
        JSONObject result = new JSONObject();
        result.put("code", 0);
        return result;
    }

    @RequestMapping("/getAppById")
    @ResponseBody
    public App getAppById(@RequestParam String id) {
        return appService.getAppById(id);
    }


    @RequestMapping("/index")
    public String home(Model model) {
        List<App> listAPP = appService.listAPP();
        if (listAPP == null) {
            listAPP = new ArrayList<App>();
        }
        model.addAttribute("appList", listAPP);
        return "/app/index";
    }

}
