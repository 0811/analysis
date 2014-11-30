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

    @RequestMapping("/getTodayUsageOverride")
    private String getTodayUsageOverride(@PathVariable String appkey, @PathVariable String platform,Model model) {
        List<UsageOverride> usageOverrides = usageOverrideDao.find(usageOverrideDao.createQuery().field("appkey").equal(appkey).field("platform").
                equal(platform).field("create_date").equal(DateUtils.today())).asList();
        model.addAttribute("usageOverrides",usageOverrides);
        return "usageOverride";
    }
}
