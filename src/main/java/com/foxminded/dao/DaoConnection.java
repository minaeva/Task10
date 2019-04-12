package com.foxminded.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DaoConnection {

    private static Properties properties = null;

    static public Connection getConnection(){
        if (properties == null){
            properties = DaoProperties.read();
            try {
                Class.forName(properties.getProperty("db.postgre.driver"));
            } catch (ClassNotFoundException ex){
                throw new DaoException("JDBC driver cannot be initialized ", ex);
            }
        }
        try {
            return DriverManager.getConnection(
                    properties.getProperty("db.postgre.url"),
                    properties.getProperty("db.postgre.login"),
                    properties.getProperty("db.postgre.password"));
        } catch (SQLException ex) {
            throw new DaoException("Connection to DB cannot be established ", ex);
        }
    }
}
