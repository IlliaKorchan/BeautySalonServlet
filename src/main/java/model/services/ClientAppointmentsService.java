package model.services;

import model.dao.ClientAppointmentDao;
import model.dao.DaoFactory;
import model.entities.ClientAppointmentDto;

import java.util.List;

import static string.containers.QueryContainer.FIND_ALL_CLIENT_APPOINTMENTS_EN;
import static string.containers.QueryContainer.FIND_ALL_CLIENT_APPOINTMENTS_UKR;

public class ClientAppointmentsService {
    public List<ClientAppointmentDto> getAllClientAppointments(String language,  Integer id) {
        ClientAppointmentDao clientAppointmentDao = DaoFactory.getInstance().createClientAppointmentDao();

        return clientAppointmentDao.findAll(id, language.equals("uk") ? FIND_ALL_CLIENT_APPOINTMENTS_UKR
                                                                : FIND_ALL_CLIENT_APPOINTMENTS_EN);
    }
}
