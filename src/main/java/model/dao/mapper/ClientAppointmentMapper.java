package model.dao.mapper;

import model.entities.ClientAppointmentDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

public class ClientAppointmentMapper implements ObjectMapper<ClientAppointmentDto> {
    @Override
    public ClientAppointmentDto extractFromResultSet(ResultSet rs) throws SQLException {
            Integer appointmentId = rs.getInt("appointment_id");
            String masterName = rs.getString("master_surname");
            LocalDate date = rs.getDate("appointment_date").toLocalDate();
            LocalTime time = rs.getTime("appointment_time").toLocalTime();
            String procedureName = rs.getString("procedure_name");
            Long procedurePrice = rs.getLong("procedure_price") / 100;

            return new ClientAppointmentDto(appointmentId, masterName, date, time, procedureName, procedurePrice);
    }

    @Override
    public ClientAppointmentDto makeUnique(Map<Integer, ClientAppointmentDto> cache, ClientAppointmentDto clientAppointmentDto) {
        cache.putIfAbsent(clientAppointmentDto.getId(), clientAppointmentDto);

        return cache.get(clientAppointmentDto.getId());
    }
}
