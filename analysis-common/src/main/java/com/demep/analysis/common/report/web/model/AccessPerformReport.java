package com.demep.analysis.common.report.web.model;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/**
 * Created with IntelliJ IDEA.
 * User: Dempe
 * Date: 2016/3/23
 * Time: 13:37
 * To change this template use File | Settings | File Templates.
 */

@Entity(value = "hourly_access_perform_report")
public class AccessPerformReport {
    @Id
    private String id;
    private String hour;
    private String uri;
    private int totalTime;
    private int maxTime;
    private int minTime;
    private int num;
    private int errNum;

    public void addTotalTime(int takeTime) {
        totalTime = totalTime + takeTime;
    }

    public void incNum() {
        num = num + 1;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public int getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(int totalTime) {
        this.totalTime = totalTime;
    }

    public int getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(int maxTime) {
        this.maxTime = maxTime;
    }

    public int getMinTime() {
        return minTime;
    }

    public void setMinTime(int minTime) {
        this.minTime = minTime;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getErrNum() {
        return errNum;
    }

    public void setErrNum(int errNum) {
        this.errNum = errNum;
    }
}
