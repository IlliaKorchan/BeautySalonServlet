package model.services;

import model.entities.ClientAppointment;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface MasterScheduleProcessor {
    List<LocalDate> findDates(Integer id);
    List<ClientAppointment> findAppointmentsForMasterByDate(Integer id, LocalDate date, String query);
    List<LocalTime> findFreeTimes(Integer id, LocalDate date);
}
