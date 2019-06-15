package model.dao.mapper;

import model.entities.Appointment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

/**
 * Class for processing data, received from table "appointments" after executing queries.
 * @author Illia Korchan
 * @version 0.7.0
 */
public class AppointmentMapper implements ObjectMapper<Appointment> {
    /**
     * Method for fetching data from result set
     * @param rs, that contains data to fetch
     * @return POJO object with fetched data
     * @throws SQLException
     */
    @Override
    public Appointment extractFromResultSet(ResultSet rs) throws SQLException {

        Integer id = rs.getInt("appointment_id");
        Integer userId = rs.getInt("appointment_user_id");
        Integer masterId = rs.getInt("appointment_master_id");
        LocalDate date = rs.getDate("appointment_date").toLocalDate();
        LocalTime time = rs.getTime("appointment_time").toLocalTime();
        Integer procedureId = rs.getInt("appointment_procedure_id");

        return new Appointment(id, userId, masterId, date, time, procedureId);
    }

    /**
     * Method, that allows not to store repeating POJO objects. Method put {@param appointment} to {@param cache}
     * if there is no object with the same id
     * @param cache storage of Appointment class instances
     * @param appointment POJO object, that should be added to cache
     * @return value from {@param cache} with the key id from {@param appointment}
     */
    @Override
    public Appointment makeUnique(Map<Integer, Appointment> cache, Appointment appointment) {
        cache.putIfAbsent(appointment.getId(), appointment);

        return cache.get(appointment.getId());
    }
}
