package model.services;


import model.dao.AppointmentDao;
import model.dao.ClientAppointmentDtoDao;
import model.dao.DaoFactory;
import model.dao.WorkingDayDao;
import model.entities.ClientAppointmentDto;
import model.entities.WorkingDay;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MasterScheduleService {
    public List<LocalDate> findDates(Integer id) {
        WorkingDayDao workingDayDao = DaoFactory.getInstance().createWorkingDayDao();

        List<WorkingDay> masterWorkingDays = workingDayDao.findByMasterId(id);
        List<LocalDate> dates = new ArrayList<>();

        masterWorkingDays.forEach(day -> dates.add(day.getDate()));

        workingDayDao.close();
        return dates;
    }

    public List<ClientAppointmentDto> findAppointmentsForMasterByDate(Integer id, LocalDate date, String query) {
        ClientAppointmentDtoDao appointmentDtoDao = DaoFactory.getInstance().createClientAppointmentDao();

        List<ClientAppointmentDto> appointments = appointmentDtoDao.findByMasterIdAndDate(id, date, query);

        appointmentDtoDao.close();
        return appointments;
    }

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
