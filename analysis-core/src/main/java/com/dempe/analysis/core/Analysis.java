package com.dempe.analysis.core;

/**
 * @author : Dempe
 * @version 1.0 date : 2014/10/17
 */
public class Analysis {


    public static void main(String args[]) {


        AnalysisBuilder builder = new AnalysisBuilder()
                .setTaskNum(8)
                .setCloseOnJvmShutdown(true)
                .setSyncThreadSleepTime(5000)
                .setQueueDataDir("/data/analystics");

        final AnalysisContext context = new AnalysisContext(builder);
        context.start();


    }
}
