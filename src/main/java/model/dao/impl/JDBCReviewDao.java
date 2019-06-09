package model.dao.impl;

import model.dao.ReviewDao;
import model.dao.mapper.ReviewMapper;
import model.entities.Review;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static string.containers.QueryContainer.FIND_ALL_REVIEWS;
import static string.containers.QueryContainer.FIND_REVIEW_BY_ID;
import static string.containers.QueryContainer.UPDATE_REVIEW;
import static string.containers.QueryContainer.CREATE_REVIEW;
import static string.containers.QueryContainer.FIND_REVIEW_BY_CLIENT_ID;
import static string.containers.QueryContainer.FIND_REVIEW_BY_MASTER_ID;


public class JDBCReviewDao implements ReviewDao {
    private Connection connection;
    private ReviewMapper reviewMapper;
    private Map<Integer, Review> reviews;

    JDBCReviewDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Review entity) {
        try (PreparedStatement statement = connection.prepareStatement(CREATE_REVIEW)) {
            statement.setInt(1, entity.getClientId());
            statement.setInt(2, entity.getAppointmentId());
            statement.setString(3, entity.getText());

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Review findById(int id) {
        return findReview(FIND_REVIEW_BY_ID, id);
    }

    @Override
    public Review findByClientId(int clientId) {
        return findReview(FIND_REVIEW_BY_CLIENT_ID, clientId);
    }

    @Override
    public Review findByMasterId(int masterId) {
        return findReview(FIND_REVIEW_BY_MASTER_ID, masterId);
    }

    @Override
    public List<Review> findAll() {
        try (PreparedStatement statement = connection.prepareStatement(FIND_ALL_REVIEWS)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Review review = reviewMapper.extractFromResultSet(resultSet);
                reviewMapper.makeUnique(reviews, review);
            }
            resultSet.close();

            return new ArrayList<>(reviews.values());
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void update(Review entity) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_REVIEW)) {
            statement.setString(1, entity.getText());
            statement.setInt(2, entity.getId());

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Review findReview(String query, int id) {
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            Review review = null;

            if (resultSet.next()) {
                review = reviewMapper.extractFromResultSet(resultSet);
            }
            if (Objects.nonNull(review)) {
                reviewMapper.makeUnique(reviews, review);
            }
            resultSet.close();

            return review;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }
}
