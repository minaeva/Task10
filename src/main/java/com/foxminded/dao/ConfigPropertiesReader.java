package com.foxminded.dao;

import lombok.Data;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Data
class ConfigPropertiesReader {

    private String driver;
    private String url;
    private String login;
    private String password;

    ConfigPropertiesReader() {
        try (FileInputStream inputStream = new FileInputStream("src/main/resources/config.properties")) {
            Properties property = new Properties();
            property.load(inputStream);

            driver = property.getProperty("db.postgre.driver");
            url = property.getProperty("db.postgre.url");
            login = property.getProperty("db.postgre.login");
            password = property.getProperty("db.postgre.password");
        } catch (IOException ex) {
            throw new DAOException("Property file cannot be reached ", ex);
        }
    }
}
