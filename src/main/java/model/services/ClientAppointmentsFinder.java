package model.services;

import model.entities.ClientAppointmentDto;

import java.util.List;

public interface ClientAppointmentsFinder {
    List<ClientAppointmentDto> getAllClientAppointments(String language, Integer id);
}
