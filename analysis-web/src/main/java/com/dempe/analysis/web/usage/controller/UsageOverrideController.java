package com.dempe.analysis.web.usage.controller;

import com.dempe.analysis.web.usage.dao.UsageOverrideDao;
import com.dempe.analysis.web.usage.model.UsageOverride;
import com.dempe.analysis.web.utils.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2014/11/30.
 */
@Controller
@RequestMapping("/usageOverride")
public class UsageOverrideController {


    @Resource
    private UsageOverrideDao usageOverrideDao;

    @RequestMapping("/getTodayUsageOverride/{appkey}/{platform}")
    private String getTodayUsageOverride(@PathVariable String appkey, @PathVariable String platform,Model model) {
        UsageOverride today_usageOverride = usageOverrideDao.findByAppkeyAndPlatformAndCreateDate(appkey,platform,"20140723");
        UsageOverride yest_usageOverride =  usageOverrideDao.findByAppkeyAndPlatformAndCreateDate(appkey, platform, DateUtils.today());

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
}
