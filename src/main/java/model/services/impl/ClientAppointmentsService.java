package model.services.impl;

import model.dao.ClientAppointmentDao;
import model.dao.DaoFactory;
import model.entities.ClientAppointment;
import model.services.ClientAppointmentsFinder;

import java.util.List;

import static string.containers.QueryContainer.FIND_ALL_CLIENT_APPOINTMENTS_EN;
import static string.containers.QueryContainer.FIND_ALL_CLIENT_APPOINTMENTS_UKR;
import static string.containers.StringContainer.LOCALE_UKR;

public class ClientAppointmentsService implements ClientAppointmentsFinder {
    public List<ClientAppointment> getAllClientAppointments(String language, Integer id) {
        ClientAppointmentDao clientAppointmentDao = DaoFactory.getInstance().createClientAppointmentDao();

        List<ClientAppointment> clientAppointments = clientAppointmentDao.findByClientId(id, language.equals(LOCALE_UKR)
                                                                                ? FIND_ALL_CLIENT_APPOINTMENTS_UKR
                                                                                : FIND_ALL_CLIENT_APPOINTMENTS_EN);
        clientAppointmentDao.close();
        return clientAppointments;
    }
}
