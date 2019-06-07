package string.containers;

public interface QueryContainer {
    String CREATE_USER = "INSERT INTO users(user_name, user_surname, user_login, user_password, user_gender," +
                         " user_email, user_role, user_amount_money)" +
                         " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    String FIND_USER_BY_ID = "SELECT * FROM users WHERE user_id = ?";
    String FIND_USER_BY_LOGIN = "SELECT * FROM users WHERE user_login = ?";
    String FIND_USER_BY_LOGIN_AND_PASSWORD = "SELECT * FROM users WHERE user_login = ? AND user_password = ?";
    String FIND_ALL_USERS = "SELECT * FROM users";
    String DELETE_USER_BY_ID = "DELETE FROM users WHERE user_id = ?";

    String CREATE_APPOINTMENT = "INSERT INTO appointments(appointment_user_id, appointment_master_id, " +
                                "appointment_date, appointment_time, appointment_procedure_id) " +
                                "VALUES (?, ?, ?, ?, ?)";
    String FIND_APPOINTMENT_BY_ID = "SELECT * FROM appointments WHERE appointment_id = ?";
    String FIND_ALL_APPOINTMENTS = "SELECT * FROM appointments";
    String DELETE_APPOINTMENT_BY_ID = "DELETE FROM appointments WHERE appointment_id = ?";

    String CREATE_PROCEDURE = "INSERT INTO procedures(procedure_price, procedure_time) " +
                              "VALUES (?, ?)";
    String FIND_PROCEDURE_BY_ID = "SELECT * FROM procedures WHERE procedure_id = ?";
    String FIND_ALL_PROCEDURES = "SELECT * FROM procedures";
    String UPDATE_PROCEDURE = "UPDATE procedures SET procedure_price = ?, procedure_time = ? WHERE procedure_id = ?";
    String DELETE_PROCEDURE_BY_ID = "DELETE FROM procedures WHERE procedure_id = ?";

    String CREATE_REVIEW = "INSERT INTO reviews(review_client_id, review_appointment_id, review_text TEXT) " +
                           "VALUES (?, ?, ?)";
    String FIND_REVIEW_BY_ID = "SELECT * FROM reviews WHERE review_id = ?";
    String FIND_REVIEW_BY_CLIENT_ID = "SELECT * FROM reviews WHERE review_client_id = ?";
    String FIND_REVIEW_BY_MASTER_ID = "SELECT * FROM reviews JOIN appointments" +
                                      " ON reviews.review_appointment_id = appointments.appointment_id" +
                                      " WHERE appointments.appointment_master_id = ?";
    String FIND_ALL_REVIEWS = "SELECT * FROM reviews";
    String UPDATE_REVIEW = "UPDATE reviews SET review_text = ? WHERE review_id = ?";

}
