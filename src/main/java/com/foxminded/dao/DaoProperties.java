package com.foxminded.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.log4j.Logger;

public class DaoProperties {

    private static Logger log = Logger.getLogger(DaoProperties.class.getName());

    public static Properties read() {
        Properties properties = null;
        try (InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties")) {
            log.trace("Opening config.properties file");
            properties = new Properties();
            properties.load(stream);
        } catch (IOException e) {
            log.error("Cannot open config.properties file ", e);
            throw new DaoException("Config.properties file cannot be opened ", e);
        }
        return properties;
    }
}
