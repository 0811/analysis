package com.demep.analysis.common.message;

/**
 * Created with IntelliJ IDEA.
 * User: Dempe
 * Date: 2016/3/22
 * Time: 16:36
 * To change this template use File | Settings | File Templates.
 */
public class Message {
    private short type;
    private byte[] content;


    public static enum Type {
        WEB_ACCESS((short) 1),
        JDBC_MSG((short) 2);
        short value;

        private Type(short value) {
            this.value = value;
        }

    }


}
