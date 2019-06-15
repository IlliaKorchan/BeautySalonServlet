package model.dao.mapper;

import model.entities.Review;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Map;

/**
 * Class for processing data, received from table "reviews" after executing queries.
 * @author Illia Korchan
 * @version 0.7.0
 */
public class ReviewMapper implements ObjectMapper<Review> {
    /**
     * Method for fetching data from result set
     * @param rs, that contains data to fetch
     * @return POJO object with fetched data
     * @throws SQLException
     */
    @Override
    public Review extractFromResultSet(ResultSet rs) throws SQLException {

        Integer id = rs.getInt("review_id");
        LocalDate date = rs.getDate("review_date").toLocalDate();
        Integer clientId = rs.getInt("review_client_id");
        Integer masterId = rs.getInt("review_master_id");
        String text = rs.getString("review_text");

        return new Review(id, date, clientId, masterId, text);
    }

    /**
     * Method, that allows not to store repeating POJO objects. Method put {@param review} to {@param cache}
     * if there is no object with the same id
     * @param cache storage of Appointment class instances
     * @param review POJO object, that should be added to cache
     * @return value from {@param cache} with the key id from {@param review}
     */
    @Override
    public Review makeUnique(Map<Integer, Review> cache, Review review) {
        cache.putIfAbsent(review.getId(), review);

        return cache.get(review.getId());
    }
}

