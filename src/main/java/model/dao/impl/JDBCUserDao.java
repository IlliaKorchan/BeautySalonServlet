package model.dao.impl;

import model.dao.UserDao;
import model.dao.mapper.UserMapper;
import model.entities.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Optional;
import static controller.QueryContainer.*;

public class JDBCUserDao implements UserDao {
    private Connection connection;
    private UserMapper userMapper = new UserMapper();
    private Map<Integer, User> users = new HashMap<>();

    public JDBCUserDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(User entity) {
        try (PreparedStatement statement = connection.prepareStatement(CREATE_USER)) {

            statement.setString(1, entity.getName());
            statement.setString(2, entity.getSurname());
            statement.setString(3, entity.getLogin());
            statement.setString(4, entity.getPassword());
            statement.setString(5, entity.getGender());
            statement.setString(6, entity.getEmail());
            statement.setString(7, entity.getRole());
            statement.setBoolean(8, entity.getActive());
            statement.setLong(9, entity.getAmountOfMoney());

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<User> findById(int id) {
        try (PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_ID)) {
            statement.setInt(1, id);
            return findUser(statement);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Optional<User> findByLogin(String login) {
        try (PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_LOGIN)) {
            statement.setString(1, login);
            return findUser(statement);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Optional<User> findByLoginAndPassword(String login, String password) {
        try (PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_LOGIN_AND_PASSWORD)) {
            statement.setString(1, login);
            statement.setString(2, password);
            return findUser(statement);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<User> findAll() {
        try (PreparedStatement statement = connection.prepareStatement(FIND_ALL_USERS)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                User user = userMapper.extractFromResultSet(resultSet);
                userMapper.makeUnique(users, user);
            }
            resultSet.close();
            return new ArrayList<>(users.values());
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void update(User entity) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_USER)) {
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getSurname());
            statement.setString(3, entity.getLogin());
            statement.setString(4, entity.getPassword());
            statement.setString(5, entity.getGender());
            statement.setString(6, entity.getEmail());
            statement.setString(7, entity.getRole());
            statement.setBoolean(8, entity.getActive());
            statement.setLong(9, entity.getAmountOfMoney());

            statement.setInt(10, entity.getId());

            statement.execute();
        } catch (SQLException e) {
            System.out.println("Unable to update user!");
        }
    }

    @Override
    public void close()  {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<User> findUser(PreparedStatement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery();
        User user = userMapper.extractFromResultSet(resultSet);
        userMapper.makeUnique(users, user);
        resultSet.close();
        return Optional.ofNullable(user);
    }
}
