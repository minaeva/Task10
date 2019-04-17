package com.foxminded.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import org.apache.log4j.Logger;

public class DaoConnection {

    private final static Logger log = Logger.getLogger(DaoConnection.class);
    private static Properties properties = null;

    static public Connection getConnection(){
        if (properties == null){
            properties = DaoProperties.read();
            try {
                log.trace("Initializing JDBC driver");
                Class.forName(properties.getProperty("db.postgre.driver"));
            } catch (ClassNotFoundException ex){
                log.error("Cannot initialize JDBC driver ", ex);
                throw new DaoException("Cannot initialize JDBC driver ", ex);
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
            throw new DaoException("Cannot establish postgres connection ", ex);
        }
    }
}
