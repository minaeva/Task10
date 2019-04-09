package com.foxminded.dao;

import lombok.Data;

@Data
public class Config {

    private static ConfigPropertiesReader properties;

    ConfigPropertiesReader readProperties() {
        properties = new ConfigPropertiesReader();
        properties.read();
        return properties;
    }
}
