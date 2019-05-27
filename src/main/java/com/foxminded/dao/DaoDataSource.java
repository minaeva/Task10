package com.foxminded.dao;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DaoDataSource {

    private static DataSource dataSource = null;

    private static void initDataSource() {
        try {
            Context context = new InitialContext();
            dataSource = (DataSource) context.lookup("java:/comp/env/jdbc/postgres");
        } catch (NamingException e) {
            throw new DaoException("Cannot initialize context or data source ", e);
        }
    }

    public DataSource receiveDataSource() {
        if (dataSource == null) {
            initDataSource();
        }
        return dataSource;
    }
}
