package com.dempe.analysis.core;

/**
 * @author : Dempe
 * @version 1.0 date : 2014/10/17
 */
public class Analysis {


    public static void main(String args[]) {


        AnalysisBuilder builder = new AnalysisBuilder()
                .setTaskNum(4)
                .setCloseOnJvmShutdown(true)
                .setSyncThreadSleepTime(5000);

        final AnalysisContext context = new AnalysisContext(builder);
        context.start();


    }
}
