package model.dao.mapper;

import model.entities.ReviewDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Map;

/**
 * Class for processing data, received from table "reviews" joined with data from table "users"
 * after executing queries.
 * @author Illia Korchan
 * @version 0.7.0
 */
public class ReviewDtoMapper implements ObjectMapper<ReviewDto> {
    /**
     * Method for fetching data from result set
     * @param rs, that contains data to fetch
     * @return POJO object with fetched data
     * @throws SQLException
     */
    @Override
    public ReviewDto extractFromResultSet(ResultSet rs) throws SQLException {
        Integer id = rs.getInt("review_id");
        LocalDate date = rs.getDate("review_date").toLocalDate();
        String masterSurname = rs.getString("surname");
        String text = rs.getString("review_text");

        return new ReviewDto(id, date, masterSurname, text);
    }

    /**
     * Method, that allows not to store repeating POJO objects. Method put {@param reviewDto} to {@param cache}
     * if there is no object with the same id
     * @param cache storage of Appointment class instances
     * @param reviewDto POJO object, that should be added to cache
     * @return value from {@param cache} with the key id from {@param reviewDto}
     */
    @Override
    public ReviewDto makeUnique(Map<Integer, ReviewDto> cache, ReviewDto reviewDto) {
        cache.putIfAbsent(reviewDto.getId(), reviewDto);

        return cache.get(reviewDto.getId());
    }
}
