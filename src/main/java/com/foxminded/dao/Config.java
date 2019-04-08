package com.foxminded.dao;

import lombok.Data;

@Data
public class Config {

    private String driver;
    private String url;
    private String login;
    private String password;

    void takeDataFromReader() {
        ConfigPropertiesReader properties = new ConfigPropertiesReader();
        properties.read();
        driver = properties.getDriver();
        url = properties.getUrl();
        login = properties.getLogin();
        password = properties.getPassword();
    }
}
