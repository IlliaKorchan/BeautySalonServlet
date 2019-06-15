package controller.filter;

import model.entities.User;
import model.exceptions.LoginAlreadyExistsException;
import model.services.UserRegistrationService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static string.containers.MessageContainer.*;
import static string.containers.RegexContainer.*;

/**
 * Filter for registrating users
 * @author Illia Korchan
 * @version 0.6.6
 */
public class RegistrationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(final ServletRequest servletRequest,
                         final ServletResponse servletResponse,
                         final FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;

        final String nameUkr = request.getParameter("userNameUkr");
        final String nameEn = request.getParameter("userNameEn");
        final String surnameUkr = request.getParameter("userSurnameUkr");
        final String surnameEn = request.getParameter("userSurnameEn");
        final String login = request.getParameter("userLogin");
        final String password = request.getParameter("userPassword");
        final String gender = request.getParameter("userGender");
        final String email = request.getParameter("userEmail");

        checkByRegex(request, response, nameUkr.matches(UKR_NAME_SURNAME_REGEX), USER_NAME_UKR_INCORRECT);
        checkByRegex(request, response, nameEn.matches(LAT_NAME_SURNAME_REGEX), USER_NAME_LAT_INCORRECT);
        checkByRegex(request, response, surnameUkr.matches(UKR_NAME_SURNAME_REGEX), USER_SURNAME_UKR_INCORRECT);
        checkByRegex(request, response, surnameEn.matches(LAT_NAME_SURNAME_REGEX), USER_SURNAME_LAT_INCORRECT);
        checkByRegex(request, response, login.matches(LOGIN_PASSWORD_REGEX), LOGIN_INCORRECT);
        checkByRegex(request, response, password.matches(LOGIN_PASSWORD_REGEX), PASSWORD_INCORRECT);
        checkByRegex(request, response, email.matches(EMAIL_REGEX), EMAIL_INCORRECT);


        User user = new User(nameUkr, nameEn, surnameUkr, surnameEn,
                login, password, gender, email, "client");

        try {
            new UserRegistrationService().register(user);
        } catch (LoginAlreadyExistsException e) {
            request.setAttribute("warning", LOGIN_EXISTS);
            request.getRequestDispatcher("/WEB-INF/view/registration.jsp").forward(request, response);
        }

        request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
    }

    @Override
    public void destroy() {

    }

    private void checkByRegex(HttpServletRequest request, HttpServletResponse response,
                              boolean matches, String warning) throws ServletException, IOException {
        if (!matches) {
            request.setAttribute("warning", warning);
            request.getRequestDispatcher("/WEB-INF/view/registration.jsp").forward(request, response);
        }
    }

}
