package com.foxminded.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAOFactory {

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost/universitydb";
            String username = "postgres";
            String password = "admin";

            Connection connection = DriverManager.getConnection(url, username, password);
            return connection;

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(2);
        }
        return null;
    }

}
