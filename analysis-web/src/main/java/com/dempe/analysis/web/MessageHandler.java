package com.dempe.analysis.web;

import com.demep.analysis.common.message.AccessMessage;
import com.demep.analysis.common.report.web.model.AccessPerformReport;
import com.demep.analysis.common.utils.DateUtil;
import com.demep.analysis.common.utils.MD5;
import org.mapdb.DBMaker;
import org.mapdb.HTreeMap;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: Dempe
 * Date: 2016/3/23
 * Time: 14:04
 * To change this template use File | Settings | File Templates.
 */
@Component
public class MessageHandler {

    private final HTreeMap<String, AccessPerformReport> map = DBMaker.newMemoryDB().make().getHashMap("access_map");

    public void handle(AccessMessage message) {
        String uri = message.getUri();
        int takeTime = message.getTakeTime();
        String hourStr = DateUtil.getHourStr();
        String id = MD5.hash(new String(uri + "|" + hourStr));
        AccessPerformReport accessPerformReport = map.get(id);
        if (accessPerformReport == null) {
            accessPerformReport = new AccessPerformReport();
            accessPerformReport.setHour(hourStr);
            accessPerformReport.setUri(uri);
            accessPerformReport.setId(id);
        }
        accessPerformReport.incNum();
        accessPerformReport.addTotalTime(takeTime);
        map.put(id, accessPerformReport);
    }

    public MessageHandler() {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {

            }
        }, 0L, 1L, TimeUnit.HOURS);
    }
}
