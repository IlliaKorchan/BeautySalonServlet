package string.containers;

public interface QueryContainer {
    String CREATE_USER = "INSERT INTO users(user_name_ukr, user_name_en, user_surname_ukr,user_surname_en, user_login," +
                         " user_password, user_gender, user_email, user_role)" +
                         " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    String FIND_USER_BY_ROLE = "SELECT * FROM users WHERE user_role = ?";
    String FIND_USER_BY_ID = "SELECT * FROM users WHERE user_id = ?";
    String FIND_USER_BY_LOGIN = "SELECT * FROM users WHERE user_login = ?";
    String FIND_USER_BY_LOGIN_AND_PASSWORD = "SELECT * FROM users WHERE user_login = ? AND user_password = ?";
    String FIND_ALL_USERS = "SELECT * FROM users";
    String UPDATE_USER_PASSWORD = "UPDATE users SET user_password = ? WHERE user_id = ?";
    String DELETE_USER_BY_ID = "DELETE FROM users WHERE user_id = ?";

    String CREATE_APPOINTMENT = "INSERT INTO appointments(appointment_user_id, appointment_master_id, " +
                                "appointment_date, appointment_time, appointment_procedure_id) " +
                                "VALUES (?, ?, ?, ?, ?)";
    String FIND_APPOINTMENT_BY_ID = "SELECT * FROM appointments WHERE appointment_id = ?";
    String FIND_ALL_APPOINTMENTS = "SELECT * FROM appointments";
    String FIND_ALL_CLIENT_APPOINTMENTS_UKR = "SELECT appointments.appointment_id, users.user_surname_ukr AS surname, appointment_date, appointment_time, " +
            "procedures.procedure_name_ukr AS procedure_name, procedures.procedure_price FROM appointments JOIN users " +
            "ON appointments.appointment_master_id = users.user_id JOIN procedures " +
            "ON appointments.appointment_procedure_id = procedures.procedure_id " +
            "WHERE appointments.appointment_user_id = ?";
    String FIND_ALL_CLIENT_APPOINTMENTS_EN = "SELECT appointments.appointment_id, users.user_surname_en AS surname, appointment_date, appointment_time, " +
            "procedures.procedure_name_en AS procedure_name, procedures.procedure_price FROM appointments JOIN users " +
            "ON appointments.appointment_master_id = users.user_id JOIN procedures " +
            "ON appointments.appointment_procedure_id = procedures.procedure_id " +
            "WHERE appointments.appointment_user_id = ?";

    String FIND_APPOINTMENTS_BY_MASTER_ID = "SELECT * FROM appointments WHERE appointment_master_id = ?";

    String CREATE_PROCEDURE = "INSERT INTO procedures(procedure_name, procedure_price) " +
                              "VALUES (?, ?)";
    String FIND_PROCEDURE_BY_ID = "SELECT * FROM procedures WHERE procedure_id = ?";
    String FIND_PROCEDURE_BY_NAME_UKR = "SELECT * FROM procedures WHERE procedure_name_ukr = ?";
    String FIND_PROCEDURE_BY_NAME_EN = "SELECT * FROM procedures WHERE procedure_name_en = ?";
    String FIND_ALL_PROCEDURES = "SELECT * FROM procedures";
    String UPDATE_PROCEDURE = "UPDATE procedures SET procedure_price = ? WHERE procedure_id = ?";

    String CREATE_REVIEW = "INSERT INTO reviews(review_date, review_client_id, review_master_id, review_text)" +
            " VALUES (?, ?, ?, ?)";
    String FIND_REVIEW_BY_ID = "SELECT * FROM reviews WHERE review_id = ?";
    String FIND_REVIEWS_BY_CLIENT_ID_UKR = "SELECT reviews.review_id, reviews.review_date, users.user_surname_ukr AS surname," +
            " reviews.review_text FROM reviews JOIN users ON reviews.review_master_id = users.user_id " +
            "WHERE reviews.review_client_id = ?";

    String FIND_REVIEWS_BY_CLIENT_ID_EN = "SELECT reviews.review_id, reviews.review_date, users.user_surname_en AS surname," +
            " reviews.review_text FROM reviews JOIN users ON reviews.review_master_id = users.user_id " +
            "WHERE reviews.review_client_id = ?";

    String FIND_ALL_REVIEWS = "SELECT * FROM reviews";
    String UPDATE_REVIEW = "UPDATE reviews SET review_text = ? WHERE review_id = ?";

    String CREATE_WORKING_DAY = "INSERT INTO working_days(working_day_master_id, working_day_date) VALUES (?, ?)";
    String FIND_ALL_WORKING_DAYS = "SELECT * FROM working_days";

    String FIND_WORKING_DAYS_BY_MASTER_ID = "SELECT * FROM working_days WHERE working_day_master_id = ? " +
            "AND working_day_date > NOW()";
    String FIND_APPOINTMENTS_BY_MASTER_ID_AND_DATE_EN = "SELECT appointments.appointment_id, users.user_surname_en" +
            " AS surname, appointment_date, appointment_time, procedures.procedure_name_en" +
            " AS procedure_name, procedures.procedure_price FROM appointments JOIN users" +
            " ON appointments.appointment_user_id = users.user_id JOIN procedures" +
            " ON appointments.appointment_procedure_id = procedures.procedure_id" +
            " WHERE appointments.appointment_master_id = ? AND appointments.appointment_date = ?";

    String FIND_APPOINTMENTS_BY_MASTER_ID_AND_DATE_UKR = "SELECT appointments.appointment_id, users.user_surname_ukr" +
            " AS surname, appointment_date, appointment_time, procedures.procedure_name_ukr" +
            " AS procedure_name, procedures.procedure_price FROM appointments JOIN users" +
            " ON appointments.appointment_user_id = users.user_id JOIN procedures" +
            " ON appointments.appointment_procedure_id = procedures.procedure_id" +
            " WHERE appointments.appointment_master_id = ? AND appointments.appointment_date = ?";

    String FIND_REVIEWS_BY_MASTER_ID_UKR = "SELECT reviews.review_id, reviews.review_date, users.user_surname_ukr AS surname," +
            " reviews.review_text FROM reviews JOIN users ON reviews.review_client_id = users.user_id " +
            "WHERE reviews.review_master_id = ?";
    String FIND_REVIEWS_BY_MASTER_ID_EN = "SELECT reviews.review_id, reviews.review_date, users.user_surname_en AS surname," +
            " reviews.review_text FROM reviews JOIN users ON reviews.review_client_id = users.user_id " +
            "WHERE reviews.review_master_id = ?";

    String FIND_APPOINTMENT_BY_MASTER_ID_DATE_TIME = "SELECT * FROM appointments WHERE appointment_master_id = ? " +
            "AND appointment_date = ? AND appointment_time = ? ";
}
