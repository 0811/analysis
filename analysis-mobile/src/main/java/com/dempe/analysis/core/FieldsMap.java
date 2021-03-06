package com.dempe.analysis.core;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.Logger;

/**
 * Created by Administrator on 2014/11/25.
 */
public class FieldsMap {


    private static final Logger LOGGER = Logger.getLogger(FieldsMap.class);

    private static PropertiesConfiguration conf;

    static {
        try {
            conf = new PropertiesConfiguration("report.properties");
        } catch (ConfigurationException e) {
            LOGGER.error(e);
        }
    }

    public static String getString(String key) {
        return conf.getString(key);
    }

    public static String[] getStringArray(String key) {
        return conf.getStringArray(key);
    }

}
