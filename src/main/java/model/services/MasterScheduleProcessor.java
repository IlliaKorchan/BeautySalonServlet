package model.services;

import model.entities.ClientAppointmentDto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface MasterScheduleProcessor {
    List<LocalDate> findDates(Integer id);
    List<ClientAppointmentDto> findAppointmentsForMasterByDate(Integer id, LocalDate date, String query);
    List<LocalTime> findFreeTimes(Integer id, LocalDate date);
}
