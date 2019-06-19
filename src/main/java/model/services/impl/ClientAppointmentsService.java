package model.services.impl;

import model.dao.ClientAppointmentDao;
import model.dao.DaoFactory;
import model.entities.ClientAppointment;
import model.services.ClientAppointmentsFinder;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import static string.containers.QueryContainer.FIND_ALL_CLIENT_APPOINTMENTS_EN;
import static string.containers.QueryContainer.FIND_ALL_CLIENT_APPOINTMENTS_UKR;
import static string.containers.StringContainer.FIND_ALL_CLIENT_APPOINTMENTS;
import static string.containers.StringContainer.LOCALE_UKR;
import static string.containers.StringContainer.QUERIES_BUNDLE;

public class ClientAppointmentsService implements ClientAppointmentsFinder {
    public List<ClientAppointment> getAllClientAppointments(String language, Integer id) {
        ResourceBundle queriesBundle = ResourceBundle.getBundle(QUERIES_BUNDLE, new Locale(language));
        ClientAppointmentDao clientAppointmentDao = DaoFactory.getInstance().createClientAppointmentDao();

        String query = queriesBundle.getString(FIND_ALL_CLIENT_APPOINTMENTS);

        List<ClientAppointment> clientAppointments = clientAppointmentDao.findByClientId(id, query);
        clientAppointmentDao.close();
        return clientAppointments;
    }
}
