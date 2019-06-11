package model.dao;

import model.dao.impl.JDBCDaoFactory;
import model.entities.WorkingDay;

public abstract class DaoFactory {
    private static DaoFactory daoFactory;

    public abstract UserDao createUserDao();
    public abstract AppointmentDao createAppointmentDao();
    public abstract ProcedureDao createProcedureDao();
    public abstract ReviewDao createReviewDao();
    public abstract ClientAppointmentDao createClientAppointmentDao();
    public abstract ClientReviewDao createClientReviewDao();
    public abstract WorkingDayDao createWorkingDayDao();

    public static DaoFactory getInstance(){
        if (daoFactory == null) {
            synchronized (DaoFactory.class){
                if (daoFactory == null) {
                    daoFactory = new JDBCDaoFactory();
                }
            }
        }

        return daoFactory;
    }
}
