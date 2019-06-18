package model.services;

import model.entities.ClientAppointment;

import java.util.List;

public interface ClientAppointmentsFinder {
    List<ClientAppointment> getAllClientAppointments(String language, Integer id);
}
