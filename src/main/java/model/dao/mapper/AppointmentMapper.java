package model.dao.mapper;

import model.entities.Appointment;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

public class AppointmentMapper implements ObjectMapper<Appointment> {
    @Override
    public Appointment extractFromResultSet(ResultSet rs) throws SQLException {

        if (rs.next()) {
            Integer id = rs.getInt("appointment_id");
            Integer userId = rs.getInt("appointment_user_id");
            Integer masterId = rs.getInt("appointment_master_id");
            LocalDate date = rs.getDate("appointment_date").toLocalDate();
            LocalTime time = rs.getTime("appointment_time").toLocalTime();
            Integer procedureId = rs.getInt("appointment_procedure_id");

            return new Appointment(id, userId, masterId, date, time, procedureId);
        }
        return null;
    }

    @Override
    public Appointment makeUnique(Map<Integer, Appointment> cache, Appointment appointment) {
        cache.putIfAbsent(appointment.getId(), appointment);

        return cache.get(appointment.getId());
    }
}
