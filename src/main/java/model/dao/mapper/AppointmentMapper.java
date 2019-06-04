package model.dao.mapper;

import model.entities.Appointment;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class AppointmentMapper implements ObjectMapper<Appointment> {
    @Override
    public Appointment extractFromResultSet(ResultSet rs) throws SQLException {
        Appointment appointment = new Appointment();

        appointment.setId(rs.getInt("appointment_id"));
        appointment.setUserId(rs.getInt("appointment_user_id"));
        appointment.setMasterId(rs.getInt("appointment_master_id"));
        appointment.setDate(rs.getDate("appointment_date").toLocalDate());
        appointment.setTime(rs.getTime("appointment_time").toLocalTime());
        appointment.setProcedureId(rs.getInt("appointment_procedure_id"));

        return appointment;
    }

    @Override
    public Appointment makeUnique(Map<Integer, Appointment> cache, Appointment appointment) {
        cache.putIfAbsent(appointment.getId(), appointment);
        return cache.get(appointment.getId());
    }
}
