package com.foxminded.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoConnection {

    private static DaoPropertiesReader properties = null;

    static Connection getConnection(){
        if (properties == null){
            DaoProperties config = new DaoProperties();
            properties = config.readProperties();
            try {
                Class.forName(properties.getDriver());
            } catch (ClassNotFoundException ex){
                throw new DaoException("JDBC driver cannot be initialized ", ex);
            }
        }

        try {
            return DriverManager.getConnection(properties.getUrl(), properties.getLogin(), properties.getPassword());
        } catch (SQLException ex) {
            throw new DaoException("Connection to DB cannot be established ", ex);
        }
    }
}
