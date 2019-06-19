package controller.filter;

import model.entities.User;
import model.exceptions.IncorrectDataInputException;
import model.exceptions.LoginAlreadyExistsException;
import model.services.impl.UserRegistrationService;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static string.containers.StringContainer.*;
import static string.containers.RegexContainer.*;

/**
 * Filter for registrating users
 * @author Illia Korchan
 * @version 0.6.6
 */
public class RegistrationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {

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

        try {
            checkByRegex(nameUkr, UKR_NAME_SURNAME_REGEX, USER_NAME_UKR_INCORRECT);
            checkByRegex(nameEn, LAT_NAME_SURNAME_REGEX, USER_NAME_LAT_INCORRECT);
            checkByRegex(surnameUkr, UKR_NAME_SURNAME_REGEX, USER_SURNAME_UKR_INCORRECT);
            checkByRegex(surnameEn, LAT_NAME_SURNAME_REGEX, USER_SURNAME_LAT_INCORRECT);
            checkByRegex(login, LOGIN_PASSWORD_REGEX, LOGIN_INCORRECT);
            checkByRegex(password, LOGIN_PASSWORD_REGEX, PASSWORD_INCORRECT);
            checkByRegex(email, EMAIL_REGEX, EMAIL_INCORRECT);

        } catch (IncorrectDataInputException e) {
            request.setAttribute("warning", e.getMessage());
            request.getRequestDispatcher(REGISTRATION_PAGE).forward(request, response);
        }

        final String hashPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        User user = new User(nameUkr, nameEn, surnameUkr, surnameEn,
                login, hashPassword, gender, email, CLIENT_ROLE);

        try {
            new UserRegistrationService().register(user);
        } catch (LoginAlreadyExistsException e) {
            request.setAttribute("warning", LOGIN_EXISTS);
            request.getRequestDispatcher(REGISTRATION_PAGE).forward(request, response);
        }

        request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
    }

    @Override
    public void destroy() {

    }

    private void checkByRegex(String dataToCheck, String regex, String warning) throws IncorrectDataInputException {
        if (!dataToCheck.matches(regex)) {
            throw new IncorrectDataInputException(warning);
        }
    }

}
