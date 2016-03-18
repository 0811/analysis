package com.dempe.analysis.manger.system.service;

import com.dempe.analysis.manger.system.dao.AppDao;
import com.dempe.analysis.manger.system.model.App;
import com.dempe.analysis.manger.utils.MD5;
import com.dempe.analysis.manger.utils.UUIDUtils;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Key;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Dempe
 * Date: 2015/12/1
 * Time: 18:07
 * To change this template use File | Settings | File Templates.
 */
@Service
public class AppService {

    @Resource
    private AppDao appDao;

    /**
     * 新建app
     *
     * @param appName
     * @param appInfo
     * @return
     */
    public Key<App> addAPP(String appName, String appInfo) {
        App app = new App();
        app.setName(appName);
        app.setId(appInfo);
        app.setCreateAt(System.currentTimeMillis());
        app.setId(MD5.hash(UUIDUtils.uuidString()));
        return appDao.save(app);
    }

    public Key<App> addAPP(App app) {
        app.setId(new ObjectId().toString());
        app.setCreateAt(System.currentTimeMillis());
        return appDao.save(app);
    }

    public void updateApp(App app) {
        appDao.updateApp(app);

    }

    public List<App> listAPP() {
        return appDao.createQuery().asList();
    }

    /**
     * 删除app
     *
     * @param appKey
     */
    public void deleteAPP(String appKey) {
        appDao.deleteById(appKey);
    }


    public App getAppById(String appKey) {
        return appDao.get(appKey);
    }


}
