package model.dao.mapper;

import model.entities.Review;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class ReviewMapper implements ObjectMapper<Review> {
    @Override
    public Review extractFromResultSet(ResultSet rs) throws SQLException {
        Review review = new Review();

        review.setId(rs.getInt("review_id"));
        review.setClientId(rs.getInt("review_client_id"));
        review.setAppointmentId(rs.getInt("review_appointment_id"));
        review.setText(rs.getString("review_text"));

        return review;
    }

    @Override
    public Review makeUnique(Map<Integer, Review> cache, Review review) {
        cache.putIfAbsent(review.getId(), review);
        return cache.get(review.getId());
    }
}

