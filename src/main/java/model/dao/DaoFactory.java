package model.dao;

import model.dao.impl.JDBCDaoFactory;
import model.entities.Appointment;
import model.entities.Procedure;

public abstract class DaoFactory {
    private static DaoFactory daoFactory;

    public abstract UserDao createUserDao();
    public abstract AppointmentDao createAppointmentDao();
    public abstract ProcedureDao createProcedureDao();
    public abstract ReviewDao createReviewDao();

    public static DaoFactory getInstance(){
        if( daoFactory == null ){
            synchronized (DaoFactory.class){
                if(daoFactory==null){
                    DaoFactory temp = new JDBCDaoFactory();
                    daoFactory = temp;
                }
            }
        }
        return daoFactory;
    }
}
