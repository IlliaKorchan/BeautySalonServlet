package model.services;

import model.dao.DaoFactory;
import model.entities.Appointment;
import model.entities.ClientAppointmentDto;
import model.entities.User;

import java.time.LocalDate;
import java.util.List;

import static string.containers.QueryContainer.*;

public class MasterScheduleService {
    public List<Appointment> getMasterScheduleForClient(Integer id, String language) {
        return null;
    }

//    public List<ClientAppointmentDto> getMasterScheduleForMaster(User master, LocalDate date, String language) {
//        String query = language.equals("uk") ? FIND_APPOINTMENTS_BY_MASTER_ID_AND_DATE_UKR :
//                                                FIND_APPOINTMENTS_BY_MASTER_ID_AND_DATE_EN;
//
//        Integer id = DaoFactory.getInstance().createUserDao().findByLogin(master.getLogin()).getId();
//        return DaoFactory.getInstance().createAppointmentDao().findByMasterIdAndDate(id, date, query);
//    }

//    public List<ClientAppointmentDto> getMasterScheduleForAdmin(String masterSurname, LocalDate date, String language) {
//        String query = language.equals("uk") ? FIND_MASTER_BY_SURNAME_UKR :
//                                               FIND_MASTER_BY_SURNAME_EN;
//
//        User master = DaoFactory.getInstance().createUserDao().findBySurname(masterSurname, query);
//        return getMasterScheduleForMaster(master, date, language);
//    }
}
