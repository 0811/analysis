package com.dempe.analysis.core.utils;

public class IpEntry {

    private long startIpLong;

    private long endIpLong;

    private String country;

    private String area;


    public String getStartIp() {
        return IpArea.ipv4(startIpLong);
    }

    public long getStartIpLong() {
        return startIpLong;
    }

    public void setStartIpLong(long startIpLong) {
        this.startIpLong = startIpLong;
    }

    public String getEndIp() {
        return IpArea.ipv4(endIpLong);
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public long getEndIpLong() {
        return endIpLong;
    }

    public void setEndIpLong(long endIpLong) {
        this.endIpLong = endIpLong;
    }

    public String toString() {
        return this.startIpLong + "," + this.endIpLong + "," + this.country + this.area;
    }

}
