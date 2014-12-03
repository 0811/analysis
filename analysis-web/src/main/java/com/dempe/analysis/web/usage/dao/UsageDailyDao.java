package com.dempe.analysis.web.usage.dao;

import com.dempe.analysis.web.device.model.DeviceCountry;
import com.dempe.analysis.web.usage.model.UsageDaily;
import com.dempe.analysis.web.usage.model.UsageHourly;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2014/11/27.
 */
@Repository
public class UsageDailyDao extends BasicDAO<UsageDaily, Serializable> {

    @Autowired
    protected UsageDailyDao(Datastore dataStore) {
        super(dataStore);
        ensureIndexes();// 自动创建索引
    }

    public List<UsageDaily> findByAppkeyAndPlatformAndCreateDate(String appkey,String platform,String startDate,String endDate){
        return  this.find(this.createQuery().field("appkey").equal(appkey).field("platform").
                equal(platform).field("create_date").greaterThan(startDate).field("create-date").lessThan(endDate)
                .order("create_date")).asList();
    }
}
