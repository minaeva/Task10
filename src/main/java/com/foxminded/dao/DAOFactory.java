package com.foxminded.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAOFactory {

    public static Connection getConnection(){

        ConfigPropertiesReader properties = new ConfigPropertiesReader();
        String driver = properties.getDriver();
        String url = properties.getUrl();
        String login = properties.getLogin();
        String password = properties.getPassword();

        try {
            Class.forName(driver);
            return DriverManager.getConnection(url, login, password);
        }
        catch (ClassNotFoundException ex){
            throw new DAOException("JDBC driver cannot be initialized ", ex);
        }
        catch (SQLException ex) {
            throw new DAOException("Connection to DB cannot be established ", ex);
        }
    }
}
