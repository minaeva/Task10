package com.foxminded.dao;

import java.sql.Connection;
import java.sql.SQLException;
import org.apache.log4j.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DaoConnection {

    private static final Logger log = Logger.getLogger(DaoConnection.class);

    private static Connection connection;
    private static DataSource dataSource = null;

    private static Context initContext() {
        Context context = null;
        try {
            log.trace("Initializing context");
            context = new InitialContext();
        } catch (NamingException e) {
            log.error("Cannot initialize context ", e);
            throw new DaoException("Cannot initialize context ", e);
        }
        return context;
    }

    private static void initDataSource(Context context) {
        try {
            log.trace("Initializing data source");
            dataSource = (DataSource) context.lookup("java:/comp/env/jdbc/postgres");
        } catch (NamingException e) {
            log.error("Cannot initialize data source ", e);
            throw new DaoException("Cannot initialize data source ", e);
        }
    }
    public static Connection getConnection() {
        if (dataSource == null) {
            initDataSource(initContext());
        }
        try {
            log.trace("Establishing postgres connection");
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            log.error("Cannot establish postgres connection ", e);
            throw new DaoException("Cannot establish postgres connection ", e);
        }
        return connection;
    }
}
