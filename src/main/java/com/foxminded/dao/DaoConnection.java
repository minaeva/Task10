package com.foxminded.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import org.apache.log4j.Logger;

public class DaoConnection {

    private static Properties properties = null;
    private static Logger log = Logger.getLogger(DaoConnection.class.getName());

    static public Connection getConnection(){
        if (properties == null){
            properties = DaoProperties.read();
            try {
                log.trace("Initializing JDBC driver");
                Class.forName(properties.getProperty("db.postgre.driver"));
            } catch (ClassNotFoundException ex){
                log.error("Cannot initialize JDBC driver ", ex);
                throw new DaoException("JDBC driver cannot be initialized ", ex);
            }
        }
        try {
            log.trace("Establishing postgres connection");
            return DriverManager.getConnection(
                    properties.getProperty("db.postgre.url"),
                    properties.getProperty("db.postgre.login"),
                    properties.getProperty("db.postgre.password"));
        } catch (SQLException ex) {
            log.error("Cannot establish postgres connection ", ex);
            throw new DaoException("Connection to postgres cannot be established ", ex);
        }
    }
}
