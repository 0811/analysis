package com.dempe.analysis.core;

/**
 * @author : Dempe
 * @version 1.0 date : 2014/10/17
 */
public class AnalysisBuilder {

    private int taskNum;
    private boolean closeOnJvmShutdown = true;
    private long syncThreadSleepTime = 5000;

    private String queueDataDir;
    private String queueHost;
    private int queuePort;

    public AnalysisBuilder() {
        queueDataDir = Config.getString(R.QUEUE_DATA_DIR, "/data/analystics");
        taskNum = Config.getInteger(R.TASKNUM, 8);
        queueHost = Config.getString(R.QUEUE_HOST, "0.0.0.0");
        queuePort = Config.getInteger(R.QUEUE_PORT, 7070);
    }

    public int getTaskNum() {
        return taskNum;
    }

    public AnalysisBuilder setTaskNum(int taskNum) {
        this.taskNum = taskNum;
        return this;
    }

    public boolean isCloseOnJvmShutdown() {
        return closeOnJvmShutdown;
    }

    public AnalysisBuilder setCloseOnJvmShutdown(boolean closeOnJvmShutdown) {
        this.closeOnJvmShutdown = closeOnJvmShutdown;
        return this;
    }

    public long getSyncThreadSleepTime() {
        return syncThreadSleepTime;
    }

    public AnalysisBuilder setSyncThreadSleepTime(long syncThreadSleepTime) {
        this.syncThreadSleepTime = syncThreadSleepTime;
        return this;
    }

    public String getQueueDataDir() {
        return queueDataDir;
    }

    public AnalysisBuilder setQueueDataDir(String queueDataDir) {
        this.queueDataDir = queueDataDir;
        return this;
    }

    public int getQueuePort() {
        return queuePort;
    }

    public AnalysisBuilder setQueuePort(int queuePort) {
        this.queuePort = queuePort;
        return this;
    }

    public String getQueueHost() {
        return queueHost;
    }

    public AnalysisBuilder setQueueHost(String queueHost) {
        this.queueHost = queueHost;
        return this;
    }
}
