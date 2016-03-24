package com.dempe.analysis.core.utils;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IpArea {

    public final static String IPCODE_UNKNOWN = "UNKNOWN";
    /**
     * This field corresponds to the database column SYS_IP_AREA.START_IP
     */
    private Long startIp = new Long(0);

    /**
     * This field corresponds to the database column SYS_IP_AREA.END_IP
     */
    private Long endIp = new Long(0);

    /**
     * This field corresponds to the database column SYS_IP_AREA.COUNTRY
     */
    private String country = "";

    /**
     * This field corresponds to the database column SYS_IP_AREA.PROVINCE
     */
    private String province = "";

    /**
     * This field corresponds to the database column SYS_IP_AREA.CITY
     */
    private String city = "";

    /**
     * This field corresponds to the database column SYS_IP_AREA.DISTRICT
     */
    private String district = "";

    /**
     * This field corresponds to the database column SYS_IP_AREA.AREA_CODE
     */
    private String areaCode = IPCODE_UNKNOWN;

    /**
     * This field corresponds to the database column SYS_IP_AREA.OWNER
     */
    private String owner = "";

    /**
     * This field corresponds to the database column SYS_IP_AREA.EDU
     */
    private String edu = "";

    /**
     * This field corresponds to the database column SYS_IP_AREA.COMPANY
     */
    private String company = "";

    /**
     * This field corresponds to the database column SYS_IP_AREA.CREATE_TIME
     */
    private Date createTime = new Date();


    /**
     * This method returns the value of the database column SYS_IP_AREA.START_IP
     *
     * @return the value of SYS_IP_AREA.START_IP
     */
    public Long getStartIp() {
        return startIp;
    }

    public String getStartIpv4() {
        return ipv4(startIp);
    }

    /**
     * This method sets the value of the database column SYS_IP_AREA.START_IP
     *
     * @param startIp the value for SYS_IP_AREA.START_IP
     */
    public void setStartIp(Long startIp) {
        this.startIp = startIp;
    }

    /**
     * This method returns the value of the database column SYS_IP_AREA.END_IP
     *
     * @return the value of SYS_IP_AREA.END_IP
     */
    public Long getEndIp() {
        return endIp;
    }

    public String getEndIpv4() {
        return ipv4(endIp);
    }

    /**
     * This method sets the value of the database column SYS_IP_AREA.END_IP
     *
     * @param endIp the value for SYS_IP_AREA.END_IP
     */
    public void setEndIp(Long endIp) {
        this.endIp = endIp;
    }

    /**
     * This method returns the value of the database column SYS_IP_AREA.COUNTRY
     *
     * @return the value of SYS_IP_AREA.COUNTRY
     */
    public String getCountry() {
        return country;
    }

    /**
     * This method sets the value of the database column SYS_IP_AREA.COUNTRY
     *
     * @param country the value for SYS_IP_AREA.COUNTRY
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * This method returns the value of the database column SYS_IP_AREA.PROVINCE
     *
     * @return the value of SYS_IP_AREA.PROVINCE
     */
    public String getProvince() {
        return province;
    }

    /**
     * This method sets the value of the database column SYS_IP_AREA.PROVINCE
     *
     * @param province the value for SYS_IP_AREA.PROVINCE
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * This method returns the value of the database column SYS_IP_AREA.CITY
     *
     * @return the value of SYS_IP_AREA.CITY
     */
    public String getCity() {
        return city;
    }

    /**
     * This method sets the value of the database column SYS_IP_AREA.CITY
     *
     * @param city the value for SYS_IP_AREA.CITY
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * This method returns the value of the database column SYS_IP_AREA.DISTRICT
     *
     * @return the value of SYS_IP_AREA.DISTRICT
     */
    public String getDistrict() {
        return district;
    }

    /**
     * This method sets the value of the database column SYS_IP_AREA.DISTRICT
     *
     * @param district the value for SYS_IP_AREA.DISTRICT
     */
    public void setDistrict(String district) {
        this.district = district;
    }

    /**
     * This method returns the value of the database column
     * SYS_IP_AREA.AREA_CODE
     *
     * @return the value of SYS_IP_AREA.AREA_CODE
     */
    public String getAreaCode() {
        return areaCode;
    }

    /**
     * This method sets the value of the database column SYS_IP_AREA.AREA_CODE
     *
     * @param areaCode the value for SYS_IP_AREA.AREA_CODE
     */
    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    /**
     * This method returns the value of the database column SYS_IP_AREA.OWNER
     *
     * @return the value of SYS_IP_AREA.OWNER
     */
    public String getOwner() {
        return owner;
    }

    /**
     * This method sets the value of the database column SYS_IP_AREA.OWNER
     *
     * @param owner the value for SYS_IP_AREA.OWNER
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     * This method returns the value of the database column SYS_IP_AREA.EDU
     *
     * @return the value of SYS_IP_AREA.EDU
     */
    public String getEdu() {
        return edu;
    }

    /**
     * This method sets the value of the database column SYS_IP_AREA.EDU
     *
     * @param edu the value for SYS_IP_AREA.EDU
     */
    public void setEdu(String edu) {
        this.edu = edu;
    }

    /**
     * This method returns the value of the database column SYS_IP_AREA.COMPANY
     *
     * @return the value of SYS_IP_AREA.COMPANY
     */
    public String getCompany() {
        return company;
    }

    /**
     * This method sets the value of the database column SYS_IP_AREA.COMPANY
     *
     * @param company the value for SYS_IP_AREA.COMPANY
     */
    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * This method returns the value of the database column
     * SYS_IP_AREA.CREATE_TIME
     *
     * @return the value of SYS_IP_AREA.CREATE_TIME
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method sets the value of the database column SYS_IP_AREA.CREATE_TIME
     *
     * @param createTime the value for SYS_IP_AREA.CREATE_TIME
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public IpArea() {

    }

    public IpArea(String country, String province, String city, String district,
                  String areaCode, String owner, String edu, String company) {
        this.country = country;
        this.province = province;
        this.city = city;
        this.district = district;
        this.areaCode = areaCode;
        this.owner = owner;
        this.edu = edu;
        this.company = company;
        this.createTime = new Date();
    }

    public static long valueOfIpv4(String ip) {
        long ret = 0;
        if (!isIp(ip)) {
            ip = "127.0.0.1";
        }
        String[] p = ip.split("\\.");
        /*if(logger.isInfoEnabled())
            logger.info("ip>>" + ip);*/
        ret += Long.parseLong(p[0]) << 24;
        ret += Long.parseLong(p[1]) << 16;
        ret += Long.parseLong(p[2]) << 8;
        ret += Long.parseLong(p[3]);
        return ret;
    }

    public static String ipv4(long ip) {
        StringBuffer ret = new StringBuffer();
        ret.insert(0, ip % 256).insert(0, ".");
        ip >>= 8;
        ret.insert(0, ip % 256).insert(0, ".");
        ip >>= 8;
        ret.insert(0, ip % 256).insert(0, ".");
        ip >>= 8;
        ret.insert(0, ip);
        return ret.toString();
    }

    public static boolean isIp(String ipAddress) {
        String ip = "([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";
        Pattern pattern = Pattern.compile(ip);
        Matcher matcher = pattern.matcher(ipAddress);
        return matcher.matches();
    }

	/*public static void main(String args[]){
        logger.info(valueOfIpv4("121.14.129.100"));
		logger.info(valueOfIpv4("59.36.102.247"));
		logger.info(valueOfIpv4("59.36.102.177"));
	}*/

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}