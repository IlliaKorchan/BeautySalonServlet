package model.dao;

import model.entities.Appointment;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface AppointmentDao extends GenericDao<Appointment> {
    List<Appointment> findByMasterIdAndDate(Integer id, LocalDate date, String query);
    Appointment findByMasterIdAndDateAndTime(Integer id, LocalDate date, LocalTime time);
    List<Appointment> findByMasterId(Integer id);
}
