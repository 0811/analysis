package com.dempe.analysis.web;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created with IntelliJ IDEA.
 * User: Dempe
 * Date: 2016/3/23
 * Time: 14:31
 * To change this template use File | Settings | File Templates.
 */
public class WebAnalysis {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"application-context.xml"});

    }
}
