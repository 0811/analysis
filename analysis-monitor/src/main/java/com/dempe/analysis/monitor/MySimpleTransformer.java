package com.dempe.analysis.monitor;

/**
 * Created with IntelliJ IDEA.
 * User: Dempe
 * Date: 2016/3/18
 * Time: 16:32
 * To change this template use File | Settings | File Templates.
 */

import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

public class MySimpleTransformer implements ClassFileTransformer {
    public byte[] transform(ClassLoader classloader,
                            String classname,
                            Class redefinedclass,
                            ProtectionDomain protectiondomain,
                            byte b[]) throws IllegalClassFormatException {

        System.out.println(">>>>>>>>>>>>>><<<<<<<<<<<<<<<<" + classname);
        if (!classname.endsWith("CoyoteAdapter"))
            return (null);
        System.out.println(">>>>>>>>classname: " + classname);
        try {
            SpringMonitor.asm(b);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return (null);
    }
}