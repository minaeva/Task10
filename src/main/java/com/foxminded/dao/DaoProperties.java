package com.foxminded.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.log4j.Logger;

public class DaoProperties {

    private final static Logger log = Logger.getLogger(DaoProperties.class);
    private final static String CONFIG_PROPERTIES = "config.properties";

    public static Properties read() {
        Properties properties = null;
        try (InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(CONFIG_PROPERTIES)) {
            log.trace("Loading file" + CONFIG_PROPERTIES);
            properties = new Properties();
            properties.load(stream);
            log.info("File " + CONFIG_PROPERTIES + " loaded");
        } catch (IOException e) {
            log.error("Cannot open file " + CONFIG_PROPERTIES, e);
            throw new DaoException("Cannot open file " + CONFIG_PROPERTIES, e);
        }
        return properties;
    }
}
