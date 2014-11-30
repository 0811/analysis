package com.dempe.analysis.web.usage.dao;

import com.dempe.analysis.web.usage.model.UsageOverride;
import com.dempe.analysis.web.utils.DateUtils;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * Created by Administrator on 2014/11/27.
 */
@Repository
public class UsageOverrideDao extends BasicDAO<UsageOverride, Serializable> {

    @Autowired
    protected UsageOverrideDao(Datastore dataStore) {
        super(dataStore);
        ensureIndexes();// 自动创建索引
    }

    public UsageOverride findByAppkeyAndPlatformAndCreateDate(String appkey,String platform,String ceate_date){
        return  this.findOne(this.createQuery().field("appkey").equal(appkey).field("platform").
                equal(platform).field("create_date").equal(ceate_date));
    }
}
