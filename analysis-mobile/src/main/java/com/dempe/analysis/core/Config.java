package com.dempe.analysis.core;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * 配置analystics启动参数
 * 读取配置顺序依次为：启动参数，配置文件参数，环境变量参数
 *
 * @author : Dempe
 * @version 1.0 date : 2014/10/13
 */
public class Config {

    private static final Logger LOGGER = Logger.getLogger(Config.class);

    private static PropertiesConfiguration conf;

    static {
        initProp();
    }

    public static void initProp() {
        try {
            conf = new PropertiesConfiguration(R.PROPS_NAME);
        } catch (ConfigurationException e) {
            LOGGER.error(e);
        }
    }


    public static String getString(String key) {
        String str = System.getenv(key);
        if (str == null) {
            str = conf.getString(key);
        }
        return str;
    }

    public static String getString(String key, String defaultValue) {
        String str = System.getenv(key);
        if (str == null) {
            str = conf.getString(key, defaultValue);
        }
        return str;
    }

    public static Integer getInteger(String key, Integer defaultNumber) {
        return conf.getInteger(key, defaultNumber);
    }


    public static List<Object> getList(String key) {
        return conf.getList(key);
    }
}
