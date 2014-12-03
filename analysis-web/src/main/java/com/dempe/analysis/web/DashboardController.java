package com.dempe.analysis.web;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dempe.analysis.web.chart.HighChart;
import com.dempe.analysis.web.chart.SeriesBean;
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
    private final static String CREATE_DATE="20140722";

    private final static String[] hours = {"00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15",
            "16","17","18","19","20","21","22","23","24"};





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
        List<UsageHourly> usageHourlies = usageHourlyDao.findByAppkeyAndPlatformAndCreateDate(APPKEY, PLATFORM, CREATE_DATE);
        List<UsageHourly> usageHourlies2 = usageHourlyDao.findByAppkeyAndPlatformAndCreateDate(APPKEY, PLATFORM, "20140721");

        JSONArray jsonArray = new JSONArray();
        for(UsageHourly usageHourly : usageHourlies){
            jsonArray.add(usageHourly.getRunNum());
        }

        JSONArray jsonArray2 = new JSONArray();
        for(UsageHourly usageHourly : usageHourlies2){
            jsonArray2.add(usageHourly.getRunNum());
        }

        SeriesBean seriesBean = new SeriesBean("today",jsonArray);
        SeriesBean seriesBean2 = new SeriesBean("yesterday",jsonArray2);

        //seriesBean.setShowInLegend(false);
        JSONArray result = new JSONArray();
        result.add(seriesBean);
        result.add(seriesBean2);

        JSONObject xAxis = new JSONObject();
        xAxis.put("categories",hours);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("series",result);
        jsonObject.put("xAxis",xAxis);

        HighChart highChart = new HighChart();

        highChart.setSeries(result);
        highChart.setxAxis(xAxis);
        return jsonObject.toJSONString();
    }

    @ResponseBody
    @RequestMapping("/runNumHourly.json")
    public String getRunNumHourly(){
        List<UsageHourly> usageHourlies = usageHourlyDao.findByAppkeyAndPlatformAndCreateDate(APPKEY, PLATFORM, CREATE_DATE);
        JSONArray jsonArray = new JSONArray();
        for(UsageHourly usageHourly : usageHourlies){
            jsonArray.add(usageHourly.getRunNum());
        }

        return jsonArray.toJSONString();
    }
}
