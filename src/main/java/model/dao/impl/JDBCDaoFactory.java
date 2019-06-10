package model.dao.impl;

import model.dao.*;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCDaoFactory extends DaoFactory {
    private DataSource dataSource = ConnectionPoolHolder.getDataSource();

    private Connection getConnection(){
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public UserDao createUserDao() {
        return new JDBCUserDao(getConnection());
    }

    @Override
    public AppointmentDao createAppointmentDao() {
        return new JDBCAppointmentDao(getConnection());
    }

    @Override
    public ProcedureDao createProcedureDao() {
        return new JDBCProcedureDao(getConnection());
    }

    @Override
    public ReviewDao createReviewDao() {
        return new JDBCReviewDao(getConnection());
    }

    @Override
    public ClientAppointmentDao createClientAppointmentDao() {
        return new JDBCClientAppointmentDao(getConnection());
    }

    @Override
    public ClientReviewDao createClientReviewDao() {
        return new JDBCClientReviewDao(getConnection());
    }
}
