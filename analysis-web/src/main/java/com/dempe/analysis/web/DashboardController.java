package com.dempe.analysis.web;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dempe.analysis.web.usage.dao.UsageHourlyDao;
import com.dempe.analysis.web.usage.dao.UsageOverrideDao;
import com.dempe.analysis.web.usage.model.UsageHourly;
import com.dempe.analysis.web.usage.model.UsageOverride;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author : Dempe
 * @version 1.0 date : 2014/12/1
 */
@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @Resource
    private UsageOverrideDao usageOverrideDao;

    @Resource
    private UsageHourlyDao usageHourlyDao;

    private final static String APPKEY="ca2bbd6a539ae3a33c5f2832f8baa4ac";
    private final static String PLATFORM="1";
    private final static String CREATE_DATE="1";





    @RequestMapping("/home")
    public String home(Model model){

        UsageOverride today_usageOverride = usageOverrideDao.findByAppkeyAndPlatformAndCreateDate(APPKEY,PLATFORM,CREATE_DATE);
        UsageOverride yest_usageOverride =  usageOverrideDao.findByAppkeyAndPlatformAndCreateDate(APPKEY,PLATFORM,CREATE_DATE);

        if(today_usageOverride==null){
            today_usageOverride = new UsageOverride();
        }
        if(yest_usageOverride ==null){
            yest_usageOverride =  new UsageOverride();
        }

        model.addAttribute("today_usageOverride",today_usageOverride);
        model.addAttribute("yest_usageOverride",yest_usageOverride);
        return "dashboard";
    }

    @ResponseBody
    @RequestMapping("/newNumHourly.json")
    public String getNewNumHourly(){
        JSONArray jsonArray = new JSONArray();

        JSONArray ja = new JSONArray();
        ja.add(1);
        ja.add(12);
        jsonArray.add(ja);



       ja = new JSONArray();
        ja.add(2);
        ja.add(13);
        jsonArray.add(ja);


        ja = new JSONArray();
        ja.add(3);
        ja.add(14);
        jsonArray.add(ja);


        ja = new JSONArray();
        ja.add(4);
        ja.add(15);
        jsonArray.add(ja);

        ja = new JSONArray();
        ja.add(5);
        ja.add(16);
        jsonArray.add(ja);


        return jsonArray.toJSONString();
    }

    @ResponseBody
    @RequestMapping("/runNumHourly.json")
    public String getRunNumHourly(){
        List<UsageHourly> usageHourlies = usageHourlyDao.findByAppkeyAndPlatformAndCreateDate(APPKEY, PLATFORM, CREATE_DATE);
        JSONArray jsonArray = new JSONArray();
        for(UsageHourly usageHourly : usageHourlies){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put(usageHourly.getCreate_date(),usageHourly.getRunNum());
            jsonArray.add(jsonObject);
        }
        return jsonArray.toJSONString();
    }
}
