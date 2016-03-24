package com.dempe.analysis.core.utils;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class QQWry {
    private String qqwry_path = "classpath:QQWry.Dat";
    // 纯真IP数据库地址
    private String country, local;
    private long ipLong;
    private int recordCount, countryFlag;
    private long rangE, rangB, offSet, startIP, endIp, firstStartIP, lastStartIP, endIPOff;
    private static RandomAccessFile fis;
    private byte[] buff;

    File conf;

    //private static HashMap<String , IpEntry> cache = new HashMap<String,IpEntry>(30000);
    public QQWry() {
        try {
            conf = ResourceUtils.getFile(qqwry_path);
            fis = new RandomAccessFile(conf, "r");
        } catch (Exception e) {

        }

    }

    private long B2L(byte[] b) {
        long ret = 0;
        for (int i = 0; i < b.length; i++) {

            long t = 1L;
            for (int j = 0; j < i; j++)
                t = t * 256L;
            ret += ((b[i] < 0) ? 256 + b[i] : b[i]) * t;
        }
        return ret;
    }

    private void seek(String ip) {
        this.ipLong = IpArea.valueOfIpv4(ip);
        try {

            buff = new byte[4];
            fis.seek(0);

            fis.read(buff);
            firstStartIP = this.B2L(buff);

            fis.read(buff);
            lastStartIP = this.B2L(buff);
            recordCount = (int) ((lastStartIP - firstStartIP) / 7);

            if (recordCount <= 1) {
                local = "其他";
                country = "中国";
            }

            rangB = 0;
            rangE = recordCount;
            long RecNo;

            while (rangB < rangE - 1) {
                RecNo = (rangB + rangE) / 2;
                setStartIP(RecNo);
                if (ipLong == startIP) {
                    rangB = RecNo;
                    break;
                }
                if (ipLong > startIP)
                    rangB = RecNo;
                else
                    rangE = RecNo;
            }

            setStartIP(rangB);
            setEndIP();
            setCountry(ipLong);
            //fis.close();
        } catch (Exception e) {
            local = "其他";
            country = "中国";
        }

    }

    public IpEntry getIpEntry(String ip) {
        this.seek(ip);
        IpEntry entry = new IpEntry();
        entry.setStartIpLong(startIP);
        entry.setEndIpLong(endIp);
        entry.setCountry(country);
        entry.setArea(local);
        return entry;
    }

    private String getFlagStr(long offSet) throws IOException {
        int flag = 0;
        do {
            fis.seek(offSet);
            buff = new byte[1];
            fis.read(buff);
            flag = (buff[0] < 0) ? 256 + buff[0] : buff[0];
            if (flag == 1 || flag == 2) {
                buff = new byte[3];
                fis.read(buff);
                if (flag == 2) {
                    countryFlag = 2;
                    endIPOff = offSet - 4;
                }
                offSet = this.B2L(buff);
            } else
                break;
        } while (true);

        if (offSet < 12) {
            return "";
        } else {
            fis.seek(offSet);
            return getStr();
        }
    }

    private String getStr() throws IOException {
        long l = fis.length();
        ByteArrayOutputStream byteout = new ByteArrayOutputStream();
        byte c = fis.readByte();
        do {
            byteout.write(c);
            c = fis.readByte();
        } while (c != 0 && fis.getFilePointer() < l);
        return byteout.toString("gbk");
    }

    private void setCountry(long ip) throws IOException {
        if (countryFlag == 1 || countryFlag == 2) {
            country = getFlagStr(endIPOff + 4);
            if (countryFlag == 1) {
                local = getFlagStr(fis.getFilePointer());
                if (ipLong >= IpArea.valueOfIpv4("255.255.255.0")
                        && ipLong <= IpArea.valueOfIpv4("255.255.255.255")) {
                    local = getFlagStr(endIPOff + 21);
                    country = getFlagStr(endIPOff + 12);
                }
            } else {
                local = getFlagStr(endIPOff + 8);
            }
        } else {
            country = getFlagStr(endIPOff + 4);
            local = getFlagStr(fis.getFilePointer());
        }

    }

    private void setEndIP() throws IOException {
        fis.seek(endIPOff);
        buff = new byte[4];
        fis.read(buff);
        endIp = this.B2L(buff);
        buff = new byte[1];
        fis.read(buff);
        countryFlag = (buff[0] < 0) ? 256 + buff[0] : buff[0];
    }

    private void setStartIP(long RecNo) throws IOException {
        offSet = firstStartIP + RecNo * 7;
        fis.seek(offSet);
        buff = new byte[4];
        fis.read(buff);
        startIP = this.B2L(buff);
        buff = new byte[3];
        fis.read(buff);
        endIPOff = this.B2L(buff);

    }


    public static void main(String[] args) {
        String ip = "192.168.9.10";
        IpArea ipArea = new IpAreaLoader().handleLine(1, ip);
        String country = ipArea.getCountry();
        String province = ipArea.getProvince();
        System.out.println(country + "," + province);

    }
}