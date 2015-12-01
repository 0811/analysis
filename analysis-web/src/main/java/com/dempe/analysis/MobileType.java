package com.dempe.analysis;

/**
 * Created with IntelliJ IDEA.
 * User: Dempe
 * Date: 2015/12/1
 * Time: 18:14
 * To change this template use File | Settings | File Templates.
 */
public enum MobileType {

    IOS(1), ANDROID(2);

    private int type;

    private MobileType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
