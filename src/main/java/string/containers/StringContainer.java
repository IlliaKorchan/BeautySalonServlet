package string.containers;

public interface StringContainer {
    String USER_NAME_UKR_INCORRECT = "warn.incorrect.user.name.ukr";
    String USER_NAME_LAT_INCORRECT = "warn.incorrect.user.name.lat";
    String USER_SURNAME_UKR_INCORRECT = "warn.incorrect.user.surname.ukr";
    String USER_SURNAME_LAT_INCORRECT = "warn.incorrect.user.surname.lat";
    String LOGIN_INCORRECT = "warn.incorrect.user.login";
    String PASSWORD_INCORRECT = "warn.incorrect.user.password";
    String EMAIL_INCORRECT = "warn.incorrect.user.email";
    String LOGIN_EXISTS = "warn.login.exists";
    String INCORRECT_PASSWORD_WARNING = "warn.incorrect.password";

    String USER_LOGGED = "user";
    String USER_LOGGED_ROLE = "role";
    String LOCALE_UKR = "ukr";
    String LOCALE_EN = "en";

    String CHANGE_PASSWORD_PAGE = "/WEB-INF/view/change-password.jsp";
    String INDEX_PAGE = "/WEB-INF/view/index.jsp";
    String LOGIN_PAGE = "/WEB-INF/view/login.jsp";
    String REGISTRATION_PAGE = "/WEB-INF/view/registration.jsp";

    String ADMIN_MASTER_SCHEDULE_PAGE = "/WEB-INF/view/schedule/admin-master-schedule.jsp";
    String CLIENT_MASTER_SCHEDULE_PAGE = "/WEB-INF/view/schedule/client-master-schedule.jsp";
    String MASTER_SCHEDULE_PAGE = "/WEB-INF/view/schedule/master-schedule.jsp";

    String ADMIN_REVIEWS_PAGE = "/WEB-INF/view/reviews/admin-reviews.jsp";
    String CLIENT_REVIEWS_PAGE = "/WEB-INF/view/reviews/client-reviews.jsp";

    String CLIENT_APPOINTMENTS_PAGE = "/WEB-INF/view/client-appointments.jsp";

    String MAKE_APPOINTMENT_PAGE = "/WEB-INF/view/make-appointment.jsp";
    String MAKE_REVIEW_PAGE = "/WEB-INF/view/make-review.jsp";

    String CLIENT_MENU_PAGE = "/WEB-INF/view/menu/client-menu.jsp";

}
