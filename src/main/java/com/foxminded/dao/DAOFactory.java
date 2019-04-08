package com.foxminded.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAOFactory {

    static Config config = null;

    static Connection getConnection(){
        if (config == null){
            config = new Config();
            config.takeDataFromReader();
        }
        String driver = config.getDriver();
        String url = config.getUrl();
        String login = config.getLogin();
        String password = config.getPassword();

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
