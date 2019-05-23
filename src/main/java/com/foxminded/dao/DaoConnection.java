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

    public static Connection getConnection() {
        Context context;
        try {
            log.trace("Initializing context");
            context = new InitialContext();
            if (context == null) {
                log.error("Initial context is null");
                throw new DaoException("Initial context is null");
            }
            log.trace("Initializing data source");
            DataSource dataSource = (DataSource) context.lookup("java:/comp/env/jdbc/postgres");
            if (dataSource == null) {
                log.error("Data source is null");
                throw new DaoException("Data source is null");
            }
            try {
                log.trace("Establishing postgres connection");
                connection = dataSource.getConnection();
            } catch (SQLException e) {
                log.error("Cannot establish postgres connection ", e);
                throw new DaoException("Cannot establish postgres connection ", e);
            }
        } catch (NamingException e) {
            log.error("Cannot initialize context or data source ", e);
            throw new DaoException("Cannot initialize context or data source ", e);
        }
        return connection;
    }
}
