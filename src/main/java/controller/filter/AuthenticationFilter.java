package controller.filter;

import model.dao.UserDao;
import model.entities.User;
import model.exceptions.IncorrectDataInputException;
import org.apache.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

import static string.containers.RegexContainer.LOGIN_PASSWORD_REGEX;
import static string.containers.StringContainer.*;

/**
 * Class for authentication processing
 * @author Illia Korchan
 * @version 0.6.5
 */
public class AuthenticationFilter implements Filter {
    private static final Logger LOGGER = Logger.getLogger(AuthenticationFilter.class.getSimpleName());

    @Override
    public void init(FilterConfig filterConfig) {
    }

    /**
     * Method, that receives user request, response and chain of filters. If user is already logged in,
     * fethes role from session and redirects to the menu page according to role. If user isn`t logged in,
     * but login and password are already entered, finds user with entered data and log in, else if user
     * is not logged in and logging data aren`t entered, redirects to the login page.
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(final ServletRequest servletRequest,
                         final ServletResponse servletResponse,
                         final FilterChain filterChain) throws IOException, ServletException {

        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;
        final String login = request.getParameter("login");
        final String password = request.getParameter("password");

        if (Objects.nonNull(login) && Objects.nonNull(password)) {
            try {
                checkByRegex(login, LOGIN_INCORRECT);
                checkByRegex(password, PASSWORD_INCORRECT);
            } catch (IncorrectDataInputException e) {
                LOGGER.error("Incorrect input data on login page");
                request.setAttribute("warning", e.getMessage());
                request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
            }
        }

        @SuppressWarnings("unchecked")
        final AtomicReference<UserDao> userDao = (AtomicReference<UserDao>) request.getServletContext()
                .getAttribute("userDao");

        final HttpSession session = request.getSession();

        if (Objects.nonNull(session) &&
                Objects.nonNull(session.getAttribute(USER_LOGGED)) &&
                Objects.nonNull(session.getAttribute(USER_LOGGED_ROLE))) {

            filterChain.doFilter(request, response);

        } else if (Objects.nonNull(userDao.get().findByLogin(login))) {
            final User user = userDao.get().findByLogin(login);

            if (BCrypt.checkpw(password, user.getPassword())) {
                final String role = user.getRole();

                request.getSession().setAttribute(USER_LOGGED, user);
                request.getSession().setAttribute(USER_LOGGED_ROLE, role);

                LOGGER.info(role + " authorized");
                request.getRequestDispatcher("/WEB-INF/view/menu/" + role + "-menu.jsp").forward(request, response);
            }
        } else {
            request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
        }
    }

    @Override
    public void destroy() {
    }

    private void checkByRegex(String dataToCheck, String warning) throws IncorrectDataInputException {
        if (!dataToCheck.matches(LOGIN_PASSWORD_REGEX)) {
            throw new IncorrectDataInputException(warning);
        }
    }
}
