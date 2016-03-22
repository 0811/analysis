package com.dempe.analysis.monitor.tomcat;

import com.demep.analysis.common.message.AccessMessage;
import org.iq80.leveldb.DB;
import org.iq80.leveldb.Options;
import org.objectweb.asm.Type;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

import static org.iq80.leveldb.impl.Iq80DBFactory.factory;

/**
 * Created with IntelliJ IDEA.
 * User: Dempe
 * Date: 2016/3/21
 * Time: 14:15
 * To change this template use File | Settings | File Templates.
 */
public class TomcatLogMonitor {


    public void test() throws IOException {
        Options options = new Options();
        options.createIfMissing(true);
        DB db = factory.open(new File("example"), options);
        try {
            // Use the db in here....
        } finally {
            // Make sure you close the db to shutdown the
            // database and avoid resource leaks.
            db.close();
        }

    }

    public static void handle(HttpServletRequest request, HttpServletResponse response, long time) {
        AccessMessage message = new AccessMessage();
        String remoteHost = request.getRemoteHost();
        String agent = request.getHeader("User-Agent");
        message.setRemoteHost(remoteHost);
        message.setAgent(agent);
        message.setUri(request.getRequestURI());
        message.setTakeTime(time);


        System.out.println("++++++++++++++>>>>>>>>>>>>>>uri:" + request.getRequestURI()
                + "response status>>>>>>>>>>" + response.getStatus() + ">>>take time:" + time);
    }

    public static void main(String[] args) throws NoSuchMethodException {
        String end = Type.getMethodDescriptor(TomcatLogMonitor.class.getMethod("handle", HttpServletRequest.class, HttpServletResponse.class, long.class));
        System.out.println(end);
    }
}
