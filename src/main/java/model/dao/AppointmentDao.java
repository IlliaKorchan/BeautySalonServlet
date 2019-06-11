package model.dao;

import model.entities.Appointment;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentDao extends GenericDao<Appointment> {
    List<Appointment> findByMasterIdAndDate(Integer id, LocalDate date, String query);
}
