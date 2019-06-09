package model.dao.impl;

import model.dao.UserDao;
import model.dao.mapper.UserMapper;
import model.entities.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.HashMap;
import java.util.Map;

import static string.containers.QueryContainer.*;


public class JDBCUserDao implements UserDao {
    private Connection connection;
    private UserMapper userMapper = new UserMapper();
    private Map<Integer, User> users = new HashMap<>();

    JDBCUserDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(User entity) {
        try (PreparedStatement statement = connection.prepareStatement(CREATE_USER)) {
            statement.setString(1, entity.getNameUkr());
            statement.setString(2, entity.getNameEn());
            statement.setString(3, entity.getSurnameUkr());
            statement.setString(4, entity.getSurnameEn());
            statement.setString(5, entity.getLogin());
            statement.setString(6, entity.getPassword());
            statement.setString(7, entity.getGender());
            statement.setString(8, entity.getEmail());
            statement.setString(9, entity.getRole());
            statement.setLong(10, 0L);

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User findById(int id) {
        try (PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_ID)) {
            statement.setInt(1, id);

            return findUser(statement);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public User findByLogin(String login) {
        try (PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_LOGIN)) {
            statement.setString(1, login);

            return findUser(statement);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public User findByLoginAndPassword(String login, String password) {
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


    public void delete(int id) {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_USER_BY_ID)) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void update(User entity) {
//        try (PreparedStatement statement = connection.prepareStatement(UPDATE_USER)) {
//            statement.setString(1, entity.getName());
//            statement.setString(2, entity.getSurname());
//            statement.setString(3, entity.getLogin());
//            statement.setString(4, entity.getPassword());
//            statement.setString(5, entity.getGender());
//            statement.setString(6, entity.getEmail());
//            statement.setString(7, entity.getRole());
//            statement.setBoolean(8, entity.getActive());
//            statement.setLong(9, entity.getAmountOfMoney());
//
//            statement.setInt(10, entity.getId());
//
//            statement.execute();
//        } catch (SQLException e) {
//            System.out.println("Unable to update user!");
//        }
    }

    public void updatePassword(Integer id, String newPassword) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_USER_PASSWORD)) {
            statement.setInt(1, id);
            statement.setString(2, newPassword);

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
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

    private User findUser(PreparedStatement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery();

        User user = userMapper.extractFromResultSet(resultSet);

        if (Objects.nonNull(user)) {
            userMapper.makeUnique(users, user);
        }
        resultSet.close();

        return user;
    }
}
