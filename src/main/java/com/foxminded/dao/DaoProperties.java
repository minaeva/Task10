package com.foxminded.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.log4j.Logger;

public class DaoProperties {

    private static final Logger log = Logger.getLogger(DaoProperties.class);

    private static final String CONFIG_FILE_NAME = "config.properties";

    public static Properties read() {
        Properties properties = null;
        try (InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(CONFIG_FILE_NAME)) {
            log.trace("Loading file" + CONFIG_FILE_NAME);
            properties = new Properties();
            properties.load(stream);
            log.info("File " + CONFIG_FILE_NAME + " loaded");
        } catch (IOException e) {
            log.error("Cannot open file " + CONFIG_FILE_NAME, e);
            throw new DaoException("Cannot open file " + CONFIG_FILE_NAME, e);
        }
        return properties;
    }
}
