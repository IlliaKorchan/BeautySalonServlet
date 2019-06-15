package model.dao;

import model.entities.ClientAppointmentDto;

import java.time.LocalDate;
import java.util.List;

public interface ClientAppointmentDtoDao extends GenericDao<ClientAppointmentDto> {
    List<ClientAppointmentDto> findByClientId(Integer id, String query);
    List<ClientAppointmentDto> findByMasterIdAndDate(Integer id, LocalDate date, String query);
}
