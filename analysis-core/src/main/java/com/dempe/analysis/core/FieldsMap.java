package com.dempe.analysis.core;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.Logger;

import java.util.Arrays;

/**
 * Created by Administrator on 2014/11/25.
 */
public class FieldsMap {


    private static final Logger LOGGER = Logger.getLogger(FieldsMap.class);

    private static PropertiesConfiguration conf;

    static {
        initProp();
    }

    public static void initProp() {
        try {
            conf = new PropertiesConfiguration("report.properties");
        } catch (ConfigurationException e) {

            LOGGER.error(e);
        }
    }


    public static String getString(String key) {

        return  conf.getString(key);
    }

    public static String[] getStringArray(String key) {

        return  conf.getStringArray(key);
    }

    public static String getString(String key, String defaultValue) {
        return conf.getString(key);
    }

    public static void main(String args[]){
        String[] value = FieldsMap.getStringArray("usage_override");
        System.out.println("====>"+ Arrays.toString(value));
    }

}
