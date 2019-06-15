package model.dao.impl;

import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;

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
                    ds.setDriverClassName("com.mysql.jdbc.Driver");
                    ds.setUrl("jdbc:mysql://localhost:3306/beauty_salon?useSSL=false&serverTimezone=UTC");
                    ds.setUsername("root");
                    ds.setPassword("root");
                    ds.setMaxOpenPreparedStatements(100);
                    dataSource = ds;
                }
            }
        }
        return dataSource;
    }
}
