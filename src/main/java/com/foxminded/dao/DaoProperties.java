package com.foxminded.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DaoProperties {

    public static Properties read() {
        Properties properties = null;
        try (InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties")) {
            properties = new Properties();
            properties.load(stream);
        } catch (IOException e) {
            throw new DaoException("Property file cannot be reached ", e);
        }
        return properties;
    }
}
