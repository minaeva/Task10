package com.foxminded.dao;

import lombok.Data;

@Data
public class DaoProperties {

    private DaoPropertiesReader properties;

    DaoPropertiesReader readProperties() {
        properties = new DaoPropertiesReader();
        properties.read();
        return properties;
    }
}
