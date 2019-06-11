package model.dao;

import model.entities.ClientAppointmentDto;

import java.util.List;

public interface ClientAppointmentDao extends GenericDao<ClientAppointmentDto> {
    List<ClientAppointmentDto> findAll(Integer id, String query);
}
