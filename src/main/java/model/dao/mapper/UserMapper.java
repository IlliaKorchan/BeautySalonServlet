package model.dao.mapper;

import model.entities.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class UserMapper implements ObjectMapper<User> {
    @Override
    public User extractFromResultSet(ResultSet rs) throws SQLException {

        Integer id = rs.getInt("user_id");
        String nameUkr = rs.getString("user_name_ukr");
        String nameEn = rs.getString("user_name_en");
        String surnameUkr = rs.getString("user_surname_ukr");
        String surnameEn = rs.getString("user_surname_en");
        String login = rs.getString("user_login");
        String password = rs.getString("user_password");
        String gender = rs.getString("user_gender");
        String email = rs.getString("user_email");
        String role = rs.getString("user_role");
        Boolean active = rs.getBoolean("user_active");
        Long amountOfMoney = rs.getLong("user_amount_money");

        return new User(id, nameUkr, nameEn, surnameUkr, surnameEn,
                login, password, gender, email, role, active, amountOfMoney);

    }

    @Override
    public User makeUnique(Map<Integer, User> cache, User user) {
        cache.putIfAbsent(user.getId(), user);

        return cache.get(user.getId());
    }
}
