package com.demep.analysis.common.message;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Dempe
 * Date: 2016/3/22
 * Time: 16:35
 * To change this template use File | Settings | File Templates.
 */
public class AccessMessage implements Serializable {

    private String uri;

    private String method;

    private String remoteHost;

    private String agent;// 浏览器类型

    private int status;// http请求返回状态码

    private long takeTime;// 请求耗费时间


    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getRemoteHost() {
        return remoteHost;
    }

    public void setRemoteHost(String remoteHost) {
        this.remoteHost = remoteHost;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public long getTakeTime() {
        return takeTime;
    }

    public void setTakeTime(long takeTime) {
        this.takeTime = takeTime;
    }
}
