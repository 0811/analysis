package com.dempe.analysis.monitor.tomcat;

import org.objectweb.asm.Type;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: Dempe
 * Date: 2016/3/21
 * Time: 14:15
 * To change this template use File | Settings | File Templates.
 */
public class TomcatLogMonitor {
    public static void handle(HttpServletRequest request, HttpServletResponse response, long time) {
        System.out.println("++++++++++++++>>>>>>>>>>>>>>uri:" + request.getRequestURI()
                + "response status>>>>>>>>>>" + response.getStatus() + ">>>take time:" + time);
    }

    public static void main(String[] args) throws NoSuchMethodException {
        String end = Type.getMethodDescriptor(TomcatLogMonitor.class.getMethod("handle", HttpServletRequest.class, HttpServletResponse.class, long.class));
        System.out.println(end);
    }
}
