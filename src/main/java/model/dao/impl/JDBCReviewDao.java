package model.dao.impl;

import model.dao.ReviewDao;
import model.dao.mapper.ReviewMapper;
import model.entities.Review;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static string.containers.QueryContainer.*;


/**
 * Class for processing queries for table "reviews" from beauty_salon db
 *
 * @author Illia Korchan
 * @version 0.7.0
 */
public class JDBCReviewDao implements ReviewDao {
    private Connection connection;
    private ReviewMapper reviewMapper;
    private Map<Integer, Review> reviews;

    JDBCReviewDao(Connection connection) {
        this.connection = connection;
    }

    /**
     * Method for inserting new review
     *
     * @param entity
     */
    @Override
    public void create(Review entity) {
        try (PreparedStatement statement = connection.prepareStatement(CREATE_REVIEW)) {
            statement.setDate(1, Date.valueOf(entity.getDate()));
            statement.setInt(2, entity.getClientId());
            statement.setInt(3, entity.getMasterId());
            statement.setString(4, entity.getText());

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method for searching for review by id
     *
     * @param id
     * @return review found
     */
    @Override
    public Review findById(int id) {
        try (PreparedStatement statement = connection.prepareStatement(FIND_REVIEW_BY_ID)) {
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

    /**
     * Method for fetching all reviews from the table
     *
     * @return list of all reviews in db
     */
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

    /**
     * Method for updating text of review
     *
     * @param entity
     */
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

    /**
     * Method for closing connection to db
     */
    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
