package com.dempe.analysis.manger.api.usage.dao;

import com.dempe.analysis.manger.api.usage.model.UsageHourly;
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
public class UsageHourlyDao extends BasicDAO<UsageHourly, Serializable> {

    @Autowired
    protected UsageHourlyDao(Datastore dataStore) {
        super(dataStore);
        ensureIndexes();// 自动创建索引
    }

    public List<UsageHourly> findByAppkeyAndPlatformAndCreateDate(String appkey,String platform,String ceate_date){
        return  this.find(this.createQuery().field("appkey").equal(appkey).field("platform").
                equal(platform).field("create_date").equal(ceate_date).order("create_hour")).asList();
    }
}
