package model.services.impl;

import model.dao.*;
import model.entities.ClientAppointment;
import model.entities.WorkingDay;
import model.services.MasterScheduleProcessor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MasterScheduleProcessorService implements MasterScheduleProcessor {
    @Override
    public List<LocalDate> findDates(Integer id) {
        WorkingDayDao workingDayDao = DaoFactory.getInstance().createWorkingDayDao();

        List<WorkingDay> masterWorkingDays = workingDayDao.findByMasterId(id);
        List<LocalDate> dates = new ArrayList<>();

        masterWorkingDays.forEach(day -> dates.add(day.getDate()));

        workingDayDao.close();
        return dates;
    }

    @Override
    public List<ClientAppointment> findAppointmentsForMasterByDate(Integer id, LocalDate date, String query) {
        ClientAppointmentDao appointmentDtoDao = DaoFactory.getInstance().createClientAppointmentDao();

        List<ClientAppointment> appointments = appointmentDtoDao.findByMasterIdAndDate(id, date, query);

        appointmentDtoDao.close();
        return appointments;
    }

    @Override
    public List<LocalTime> findFreeTimes(Integer id, LocalDate date) {
        List<LocalTime> workingHours = new DayTimeService().getWorkingHours();
        AppointmentDao appointmentDao = DaoFactory.getInstance().createAppointmentDao();

        workingHours = workingHours.stream()
                .filter(hour -> !Objects.nonNull(appointmentDao.findByMasterIdAndDateAndTime(id, date, hour)))
                .collect(Collectors.toList());

        appointmentDao.close();
        return workingHours;
    }
}
