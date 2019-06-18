package model.dao;

import model.entities.ClientAppointment;

import java.time.LocalDate;
import java.util.List;

public interface ClientAppointmentDao extends GenericDao<ClientAppointment> {
    List<ClientAppointment> findByClientId(Integer id, String query);
    List<ClientAppointment> findByMasterIdAndDate(Integer id, LocalDate date, String query);
}
