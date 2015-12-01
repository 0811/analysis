package com.dempe.analysis.utils;

import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: Dempe
 * Date: 2015/12/1
 * Time: 18:10
 * To change this template use File | Settings | File Templates.
 */
public class UUIDUtils {

    public static String uuidString() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
