package model.dao.mapper;

import model.entities.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 * Class for processing data, received from table "users" after executing queries.
 * @author Illia Korchan
 * @version 0.7.0
 */
public class UserMapper implements ObjectMapper<User> {
    /**
     * Method for fetching data from result set
     * @param rs, that contains data to fetch
     * @return POJO object with fetched data
     * @throws SQLException
     */
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

        return new User(id, nameUkr, nameEn, surnameUkr, surnameEn,
                login, password, gender, email, role, active);

    }

    /**
     * Method, that allows not to store repeating POJO objects. Method put {@param user} to {@param cache}
     * if there is no object with the same id
     * @param cache storage of Appointment class instances
     * @param user POJO object, that should be added to cache
     * @return value from {@param cache} with the key id from {@param user}
     */
    @Override
    public User makeUnique(Map<Integer, User> cache, User user) {
        cache.putIfAbsent(user.getId(), user);

        return cache.get(user.getId());
    }
}
