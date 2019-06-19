package model.dao.impl;

import model.dao.UserReviewDao;
import model.dao.mapper.UserReviewMapper;
import model.entities.UserReview;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class for processing queries to table "reviews" joined with data from table "users"
 * @author Illia Korchan
 * @version 0.7.0
 */
public class JDBCUserReviewDao implements UserReviewDao {
    private Connection connection;
    private UserReviewMapper userReviewMapper = new UserReviewMapper();
    private Map<Integer, UserReview> reviewDtos = new HashMap<>();

    public JDBCUserReviewDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(UserReview entity) {

    }

    @Override
    public UserReview findById(int id) {
        return null;
    }

    /**
     * Method for searching for reviews by client/master id
     * @param id
     * @param query
     * @return list of reviews found
     */
    @Override
    public List<UserReview> findAllById(Integer id, String query) {
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                UserReview review = userReviewMapper.extractFromResultSet(resultSet);
                userReviewMapper.makeUnique(reviewDtos, review);
            }
            resultSet.close();

            return new ArrayList<>(reviewDtos.values());
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<UserReview> findAll() {
        return null;
    }

    @Override
    public void update(UserReview entity) {

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
