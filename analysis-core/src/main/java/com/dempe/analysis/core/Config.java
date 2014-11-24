package com.dempe.analysis.core;

/**
 * Created by dempe on 14-6-23.
 */

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

/**
 * @author dempe
 * @version 1.0
 * @Description 读配置文件
 * @date 2014-5-27下午06:40:59
 */
public class Config {
    private static PropertiesConfiguration config = null;


    static {
        try {
            config = new PropertiesConfiguration("simulator.properties");
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
    }

    public static String get(String key) {
        return config.getString(key);
    }

    /**
     * 读取key，如果不存在，则返回defaultValue
     *
     * @param key
     * @param
     * @return
     */
    public static String get(String key, String defaultValue) {
        return config.getString(key, defaultValue);
    }
}