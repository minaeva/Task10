package com.foxminded.dao;

import lombok.Data;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Data
class ConfigPropertiesReader {

    private String driver;
    private String url;
    private String login;
    private String password;

    public void read() {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.properties")){
            Properties property = new Properties();
            property.load(inputStream);
            driver = property.getProperty("db.postgre.driver");
            url = property.getProperty("db.postgre.url");
            login = property.getProperty("db.postgre.login");
            password = property.getProperty("db.postgre.password");
        } catch (IOException e) {
            throw new DaoException("Property file cannot be reached ", e);
        }
    }
}
