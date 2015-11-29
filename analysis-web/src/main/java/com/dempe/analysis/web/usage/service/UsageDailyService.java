package com.dempe.analysis.web.usage.service;

import com.dempe.analysis.web.usage.dao.UsageDailyDao;
import com.dempe.analysis.web.usage.dao.UsageHourlyDao;
import com.dempe.analysis.web.usage.model.UsageDaily;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: zhengdaxia
 * Date: 15/11/29
 * Time: 下午2:15
 * To change this template use File | Settings | File Templates.
 */
@Service
public class UsageDailyService {

    private UsageHourlyDao usageHourlyDao;

    private final static String APPKEY = "ca2bbd6a539ae3a33c5f2832f8baa4ac";
    private final static String PLATFORM = "1";

    @Resource
    private UsageDailyDao usageDailyDao;

    /**
     * 日活跃用户
     *
     * @return
     */
    public Object[] getRunNumDaily() {
        List<UsageDaily> todayList = usageDailyDao.findByAppkeyAndPlatformAndCreateDate(APPKEY, PLATFORM, "20140711", "20140717");
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (UsageDaily usageDaily : todayList) {
            String time = usageDaily.getCreate_date();
            Integer runNum = map.get(time);

            if (runNum == null) {
                map.put(time, usageDaily.getRunNum());
            } else {
                map.put(time, usageDaily.getRunNum() + runNum);
            }
        }
        return map.values().toArray();
    }

    /**
     * 日新增用户
     *
     * @return
     */
    public Object[] getNewDaily() {
        List<UsageDaily> todayList = usageDailyDao.findByAppkeyAndPlatformAndCreateDate(APPKEY, PLATFORM, "20140710", "20140717");
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (UsageDaily usageDaily : todayList) {
            String time = usageDaily.getCreate_date();
            Integer runNum = map.get(time);
            if (runNum == null) {
                map.put(time, usageDaily.getNewNum());
            } else {
                map.put(time, usageDaily.getNewNum() + runNum);
            }
        }
        return map.values().toArray();
    }

    /**
     * 日新增用户
     *
     * @return
     */
    public Object[] getActiveDaily() {
        List<UsageDaily> todayList = usageDailyDao.findByAppkeyAndPlatformAndCreateDate(APPKEY, PLATFORM, "20140710", "20140717");
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (UsageDaily usageDaily : todayList) {
            String time = usageDaily.getCreate_date();
            Integer runNum = map.get(time);
            if (runNum == null) {
                map.put(time, usageDaily.getActiveNum());
            } else {
                map.put(time, usageDaily.getActiveNum() + runNum);
            }
        }
        return map.values().toArray();
    }

}
