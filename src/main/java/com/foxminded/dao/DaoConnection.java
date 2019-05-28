package com.foxminded.dao;

import java.sql.Connection;
import java.sql.SQLException;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import javax.sql.DataSource;

public class DaoConnection {

    private static final Logger log = Logger.getLogger(DaoConnection.class);

    private static DataSource dataSource = null;

    private static void initDataSource() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/spring-context.xml");
        DaoDataSource daoDataSource = context.getBean("daoDataSource", DaoDataSource.class);
        dataSource = daoDataSource.receiveDataSource();
    }

    public static Connection getConnection() {
        Connection connection = null;
        if (dataSource == null) {
            initDataSource();
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
