package model.dao.mapper;

import model.entities.ClientAppointment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

/**
 * Class for processing data, received from table "appointments" joined with data
 * from tables "procedures" and "users" after executing queries.
 * @author Illia Korchan
 * @version 0.7.0
 */
public class ClientAppointmentMapper implements ObjectMapper<ClientAppointment> {
    /**
     * Method for fetching data from result set
     * @param rs, that contains data to fetch
     * @return POJO object with fetched data
     * @throws SQLException
     */
    @Override
    public ClientAppointment extractFromResultSet(ResultSet rs) throws SQLException {
            Integer appointmentId = rs.getInt("appointment_id");
            String masterName = rs.getString("surname");
            LocalDate date = rs.getDate("appointment_date").toLocalDate();
            LocalTime time = rs.getTime("appointment_time").toLocalTime();
            String procedureName = rs.getString("procedure_name");
            Long procedurePrice = rs.getLong("procedure_price") / 100;

            return new ClientAppointment(appointmentId, masterName, date, time, procedureName, procedurePrice);
    }

    /**
     * Method, that allows not to store repeating POJO objects. Method put {@param clientAppointment}
     * to {@param cache} if there is no object with the same id
     * @param cache storage of Appointment class instances
     * @param clientAppointment POJO object, that should be added to cache
     * @return value from {@param cache} with the key id from {@param clientAppointment}
     */
    @Override
    public ClientAppointment makeUnique(Map<Integer, ClientAppointment> cache,
                                        ClientAppointment clientAppointment) {
        cache.putIfAbsent(clientAppointment.getId(), clientAppointment);

        return cache.get(clientAppointment.getId());
    }
}
