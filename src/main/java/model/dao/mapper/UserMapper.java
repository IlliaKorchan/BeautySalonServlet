package model.dao.mapper;

import model.entities.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class UserMapper implements ObjectMapper<User> {
    @Override
    public User extractFromResultSet(ResultSet rs) throws SQLException {
        User user = new User();

        user.setId(rs.getInt("user_id"));
        user.setName(rs.getString("user_name"));
        user.setSurname(rs.getString("user_surname"));
        user.setLogin(rs.getString("user_login"));
        user.setPassword(rs.getString("user_password"));
        user.setGender(rs.getString("user_gender"));
        user.setEmail(rs.getString("user_email"));
        user.setRole(rs.getString("user_role"));
        user.setActive(rs.getBoolean("user_active"));
        user.setAmountOfMoney(rs.getLong("user_amount_money"));

        return user;
    }

    @Override
    public User makeUnique(Map<Integer, User> cache, User user) {
        cache.putIfAbsent(user.getId(), user);
        return cache.get(user.getId());
    }
}
