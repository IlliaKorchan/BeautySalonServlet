package model.dao.impl;

import model.dao.ClientReviewDao;
import model.dao.mapper.ClientReviewMapper;
import model.entities.ClientReviewDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBCClientReviewDao implements ClientReviewDao {
    private Connection connection;
    private ClientReviewMapper clientReviewMapper = new ClientReviewMapper();
    private Map<Integer, ClientReviewDto> clientReviews = new HashMap<>();

    public JDBCClientReviewDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<ClientReviewDto> findReviewsByClientId(Integer id, String query) {
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                ClientReviewDto clientReview = clientReviewMapper.extractFromResultSet(resultSet);
                clientReviewMapper.makeUnique(clientReviews, clientReview);
            }
            resultSet.close();

            return new ArrayList<>(clientReviews.values());
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void create(ClientReviewDto entity) {

    }

    @Override
    public ClientReviewDto findById(int id) {
        return null;
    }

    @Override
    public List<ClientReviewDto> findAll() {
        return null;
    }

    @Override
    public void update(ClientReviewDto entity) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void close() {

    }
}
