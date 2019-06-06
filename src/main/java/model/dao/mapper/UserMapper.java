package model.dao.mapper;

import model.entities.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class UserMapper implements ObjectMapper<User> {
    @Override
    public User extractFromResultSet(ResultSet rs) {
        try {
            if (rs.next()) {
                int id = rs.getInt("user_id");
                String name = rs.getString("user_name");
                String surname = rs.getString("user_surname");
                String login = rs.getString("user_login");
                String password = rs.getString("user_password");
                String gender = rs.getString("user_gender");
                String email = rs.getString("user_email");
                String role = rs.getString("user_role");
                Boolean active = rs.getBoolean("user_active");
                Long amountOfMoney = rs.getLong("user_amount_money");
                return new User(id, name, surname, login, password, gender, email, role, active, amountOfMoney);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User makeUnique(Map<Integer, User> cache, User user) {
        cache.putIfAbsent(user.getId(), user);
        return cache.get(user.getId());
    }
}
