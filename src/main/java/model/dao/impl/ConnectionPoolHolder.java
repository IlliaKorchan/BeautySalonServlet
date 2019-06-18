package model.dao.impl;

import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;

import static string.containers.StringContainer.*;

/**
 * Connection pool class
 * @author Illia Korchan
 * @version 0.6.6
 */
class ConnectionPoolHolder {
    private static volatile DataSource dataSource;

    /**
     * Method, that creates DataSource object of database beauty_salon
     * @return DataSource object
     */
    static DataSource getDataSource() {
        if (dataSource == null) {
            synchronized (ConnectionPoolHolder.class) {
                if (dataSource == null) {
                    BasicDataSource ds = new BasicDataSource();
                    ds.setDriverClassName(MYSQL_DRIVER);
                    ds.setUrl(DATABASE);
                    ds.setUsername(DATABASE_LOGIN);
                    ds.setPassword(DATABASE_PASSWORD);
                    ds.setMaxOpenPreparedStatements(100);
                    dataSource = ds;
                }
            }
        }
        return dataSource;
    }
}
