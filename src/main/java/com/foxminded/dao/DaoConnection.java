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
    private static Context context = null;
    private static DataSource dataSource = null;

    private static void initContextAndDs() {
        try {
            log.trace("Initializing context");
            context = new InitialContext();
            log.trace("Initializing data source");
            dataSource = (DataSource) context.lookup("java:/comp/env/jdbc/postgres");
        } catch (NamingException e) {
            log.error("Cannot initialize context or data source ", e);
            throw new DaoException("Cannot initialize context or data source ", e);
        }
    }

    public static Connection getConnection() {
        if ((context == null) || (dataSource == null)) {
            initContextAndDs();
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
