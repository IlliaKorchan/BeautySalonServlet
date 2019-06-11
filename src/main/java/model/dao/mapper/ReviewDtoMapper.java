package model.dao.mapper;

import model.entities.ReviewDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Map;

public class ReviewDtoMapper implements ObjectMapper<ReviewDto> {
    @Override
    public ReviewDto extractFromResultSet(ResultSet rs) throws SQLException {
        Integer id = rs.getInt("review_id");
        LocalDate date = rs.getDate("appointment_date").toLocalDate();
        String procedureName = rs.getString("procedure_name");
        String masterSurname = rs.getString("surname");
        String text = rs.getString("review_text");

        return new ReviewDto(id, date, procedureName, masterSurname, text);
    }

    @Override
    public ReviewDto makeUnique(Map<Integer, ReviewDto> cache, ReviewDto reviewDto) {
        cache.putIfAbsent(reviewDto.getId(), reviewDto);

        return cache.get(reviewDto.getId());
    }
}
