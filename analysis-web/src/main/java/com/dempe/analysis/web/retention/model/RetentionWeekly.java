package com.dempe.analysis.web.retention.model;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/**
 * Created by Administrator on 2014/11/29.
 */
@Entity(value = "retention_daily")
public class RetentionWeekly {

    @Id
    private String id;

    private String appkey;

    private String platform;

    private String version;

    private String channel;

    private String create_date;

    private String w1;

    private String w2;

    private String w3;

    private String w4;

    private String w5;

    private String w6;

    private String w7;

    private String w8;

    private String w9;

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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public String getW1() {
        return w1;
    }

    public void setW1(String w1) {
        this.w1 = w1;
    }

    public String getW2() {
        return w2;
    }

    public void setW2(String w2) {
        this.w2 = w2;
    }

    public String getW3() {
        return w3;
    }

    public void setW3(String w3) {
        this.w3 = w3;
    }

    public String getW4() {
        return w4;
    }

    public void setW4(String w4) {
        this.w4 = w4;
    }

    public String getW5() {
        return w5;
    }

    public void setW5(String w5) {
        this.w5 = w5;
    }

    public String getW6() {
        return w6;
    }

    public void setW6(String w6) {
        this.w6 = w6;
    }

    public String getW7() {
        return w7;
    }

    public void setW7(String w7) {
        this.w7 = w7;
    }

    public String getW8() {
        return w8;
    }

    public void setW8(String w8) {
        this.w8 = w8;
    }

    public String getW9() {
        return w9;
    }

    public void setW9(String w9) {
        this.w9 = w9;
    }
}
