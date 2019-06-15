package model.services;

import model.dao.ClientAppointmentDtoDao;
import model.dao.DaoFactory;
import model.entities.ClientAppointmentDto;

import java.util.List;

import static string.containers.QueryContainer.FIND_ALL_CLIENT_APPOINTMENTS_EN;
import static string.containers.QueryContainer.FIND_ALL_CLIENT_APPOINTMENTS_UKR;

public class ClientAppointmentsService {
    public List<ClientAppointmentDto> getAllClientAppointments(String language,  Integer id) {
        ClientAppointmentDtoDao clientAppointmentDtoDao = DaoFactory.getInstance().createClientAppointmentDao();

        List<ClientAppointmentDto> clientAppointments = clientAppointmentDtoDao.findByClientId(id, language.equals("uk")
                                                                                ? FIND_ALL_CLIENT_APPOINTMENTS_UKR
                                                                                : FIND_ALL_CLIENT_APPOINTMENTS_EN);
        clientAppointmentDtoDao.close();
        return clientAppointments;
    }
}
