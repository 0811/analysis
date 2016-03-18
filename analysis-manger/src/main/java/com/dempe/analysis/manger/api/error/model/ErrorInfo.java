package com.dempe.analysis.manger.api.error.model;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/**
 * Created by Administrator on 2014/11/29.
 */
@Entity(value = "error_info")
public class ErrorInfo {

    @Id
    private String id;

    private String appkey;

    private String platform;

    private String version;

    private String model;

    private String network;

    private String sysver;

    private String create_date;

    private String error_log;

    private String stack_trace;

    private Integer num;


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

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public String getSysver() {
        return sysver;
    }

    public void setSysver(String sysver) {
        this.sysver = sysver;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public String getError_log() {
        return error_log;
    }

    public void setError_log(String error_log) {
        this.error_log = error_log;
    }

    public String getStack_trace() {
        return stack_trace;
    }

    public void setStack_trace(String stack_trace) {
        this.stack_trace = stack_trace;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
