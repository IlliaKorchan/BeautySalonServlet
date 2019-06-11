package model.dao.mapper;

import model.entities.ClientReviewDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Map;

public class ClientReviewMapper implements ObjectMapper<ClientReviewDto> {
    @Override
    public ClientReviewDto extractFromResultSet(ResultSet rs) throws SQLException {
        Integer id = rs.getInt("review_id");
        LocalDate date = rs.getDate("appointment_date").toLocalDate();
        String procedureName = rs.getString("procedure_name");
        String masterSurname = rs.getString("master_surname");
        String text = rs.getString("review_text");

        return new ClientReviewDto(id, date, procedureName, masterSurname, text);
    }

    @Override
    public ClientReviewDto makeUnique(Map<Integer, ClientReviewDto> cache, ClientReviewDto clientReviewDto) {
        cache.putIfAbsent(clientReviewDto.getId(), clientReviewDto);

        return cache.get(clientReviewDto.getId());
    }
}
