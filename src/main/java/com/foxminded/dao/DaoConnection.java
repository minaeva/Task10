package com.foxminded.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import static com.foxminded.dao.DaoProperties.getPropertyValue;

public class DaoConnection {

        private static DaoProperties properties = null;

    static Connection getConnection(){
        if (properties == null){
             properties.read();
            try {
                Class.forName(getPropertyValue("db.postgre.driver"));
            } catch (ClassNotFoundException ex){
                throw new DaoException("JDBC driver cannot be initialized ", ex);
            }
        }

        try {
            return DriverManager.getConnection(
                    getPropertyValue("db.postgre.url"),
                    getPropertyValue("db.postgre.login"),
                    getPropertyValue("db.postgre.password"));
        } catch (SQLException ex) {
            throw new DaoException("Connection to DB cannot be established ", ex);
        }
    }
}
