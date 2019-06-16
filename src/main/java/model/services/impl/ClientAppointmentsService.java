package model.services.impl;

import model.dao.ClientAppointmentDtoDao;
import model.dao.DaoFactory;
import model.entities.ClientAppointmentDto;
import model.services.ClientAppointmentsFinder;

import java.util.List;

import static string.containers.QueryContainer.FIND_ALL_CLIENT_APPOINTMENTS_EN;
import static string.containers.QueryContainer.FIND_ALL_CLIENT_APPOINTMENTS_UKR;
import static string.containers.StringContainer.LOCALE_UKR;

public class ClientAppointmentsService implements ClientAppointmentsFinder {
    public List<ClientAppointmentDto> getAllClientAppointments(String language,  Integer id) {
        ClientAppointmentDtoDao clientAppointmentDtoDao = DaoFactory.getInstance().createClientAppointmentDao();

        List<ClientAppointmentDto> clientAppointments = clientAppointmentDtoDao.findByClientId(id, language.equals(LOCALE_UKR)
                                                                                ? FIND_ALL_CLIENT_APPOINTMENTS_UKR
                                                                                : FIND_ALL_CLIENT_APPOINTMENTS_EN);
        clientAppointmentDtoDao.close();
        return clientAppointments;
    }
}
