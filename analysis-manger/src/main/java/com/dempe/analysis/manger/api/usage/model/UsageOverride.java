package com.dempe.analysis.manger.api.usage.model;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/**
 * @author : Dempe
 * @version 1.0 date : 2014/11/27
 */
@Entity(value = "usage_override")
public class UsageOverride {

    @Id
    private String id;

    private String appkey;

    private String platform;

    private String create_date;

    private int runNum;

    private int newNum;

    private int activeNum;

    private long duration;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAppkey() {
        return appkey;
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public int getRunNum() {
        return runNum;
    }

    public void setRunNum(int runNum) {
        this.runNum = runNum;
    }

    public int getNewNum() {
        return newNum;
    }

    public void setNewNum(int newNum) {
        this.newNum = newNum;
    }

    public int getActiveNum() {
        return activeNum;
    }

    public void setActiveNum(int activeNum) {
        this.activeNum = activeNum;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }


    @Override
    public String toString() {
        return "UsageOverride{" +
                "id='" + id + '\'' +
                ", appkey='" + appkey + '\'' +
                ", platform='" + platform + '\'' +
                ", create_date='" + create_date + '\'' +
                ", runNum=" + runNum +
                ", newNum=" + newNum +
                ", activeNum=" + activeNum +
                ", duration=" + duration +
                '}';
    }
}
