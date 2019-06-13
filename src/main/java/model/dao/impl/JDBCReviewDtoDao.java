package model.dao.impl;

import model.dao.ReviewDtoDao;
import model.dao.mapper.ReviewDtoMapper;
import model.entities.ReviewDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBCReviewDtoDao implements ReviewDtoDao {
    private Connection connection;
    private ReviewDtoMapper reviewDtoMapper = new ReviewDtoMapper();
    private Map<Integer, ReviewDto> reviewDtos = new HashMap<>();

    public JDBCReviewDtoDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(ReviewDto entity) {

    }

    @Override
    public ReviewDto findById(int id) {
        return null;
    }

    @Override
    public List<ReviewDto> findAllById(Integer id, String query) {
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            return findListReviewDtos(statement);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<ReviewDto> findAll() {
        return null;
    }

    @Override
    public void update(ReviewDto entity) {

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

    private List<ReviewDto> findListReviewDtos(PreparedStatement statement) {
        try  {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                ReviewDto review = reviewDtoMapper.extractFromResultSet(resultSet);
                reviewDtoMapper.makeUnique(reviewDtos, review);
            }
            resultSet.close();

            return new ArrayList<>(reviewDtos.values());
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
