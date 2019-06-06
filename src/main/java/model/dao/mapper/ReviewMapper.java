package model.dao.mapper;

import model.entities.Review;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class ReviewMapper implements ObjectMapper<Review> {
    @Override
    public Review extractFromResultSet(ResultSet rs) throws SQLException {
        if (rs.next()) {
            Integer id = rs.getInt("review_id");
            Integer clientId = rs.getInt("review_client_id");
            Integer appointmentId = rs.getInt("review_appointment_id");
            String text = rs.getString("review_text");

            return new Review(id, clientId, appointmentId, text);
        }
        return null;
    }

    @Override
    public Review makeUnique(Map<Integer, Review> cache, Review review) {
        cache.putIfAbsent(review.getId(), review);

        return cache.get(review.getId());
    }
}

