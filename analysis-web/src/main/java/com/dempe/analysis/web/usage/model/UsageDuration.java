package com.dempe.analysis.web.usage.model;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/**
 * Created by Administrator on 2014/11/30.
 */
@Entity(value = "usage_daily")
public class UsageDuration {

    @Id
    private String id;

    private String appkey;

    private String platform;

    private String create_date;

    private String version;

    private String channel;

    private String s1_3;

    private String s4_10;

    private String s11_30;

    private String s31_60;

    private String s61_180;

    private String s180_600;

    private String s600_1800;

    private String s1800_;

}
