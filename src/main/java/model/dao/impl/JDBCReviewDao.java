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
import java.util.Optional;

import static controller.QueryContainer.*;

public class JDBCReviewDao implements ReviewDao {
    private Connection connection;
    private ReviewMapper reviewMapper;
    private Map<Integer, Review> reviews;

    public JDBCReviewDao(Connection connection, ReviewMapper reviewMapper, Map<Integer, Review> reviews) {
        this.connection = connection;
        this.reviewMapper = reviewMapper;
        this.reviews = reviews;
    }

    public JDBCReviewDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Review entity) {
        try (PreparedStatement statement = connection.prepareStatement(CREATE_REVIEW)) {
            statement.setInt(1, entity.getClientId());
            statement.setInt(2, entity.getAppointmentId());
            statement.setString(3, entity.getText());

            statement.execute();
            close();
        } catch (SQLException e) {
            System.out.println("Unable to create review!");
        }
    }

    @Override
    public Optional<Review> findById(int id) {
        return findReview(FIND_REVIEW_BY_ID, id);
    }

    @Override
    public Optional<Review> findByClientId(int clientId) {
        return findReview(FIND_REVIEW_BY_CLIENT_ID, clientId);
    }

    @Override
    public Optional<Review> findByMasterId(int masterId) {
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
            close();
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
            close();
        } catch (SQLException e) {
            System.out.println("Unable to update review!");
        }
    }

    @Override
    public void close() {

    }

    public Optional<Review> findReview(String query, int id) {
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            Review review = reviewMapper.extractFromResultSet(resultSet);
            reviewMapper.makeUnique(reviews, review);

            resultSet.close();
            close();

            return Optional.ofNullable(review);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }
}
