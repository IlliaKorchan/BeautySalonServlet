package controller.filter;

import model.dao.UserDao;
import model.entities.User;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicReference;
import java.util.Objects;

/**
 * Class for authentication processing
 * @author Illia Korchan
 * @version 0.6.5
 */
public class AuthenticationFilter implements Filter {

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

        @SuppressWarnings("unchecked")
        final AtomicReference<UserDao> userDao = (AtomicReference<UserDao>) request.getServletContext()
                .getAttribute("userDao");

        final HttpSession session = request.getSession();

        if (Objects.nonNull(session) &&
                Objects.nonNull(session.getAttribute("user")) &&
                Objects.nonNull(session.getAttribute("role"))) {

            filterChain.doFilter(request, response);

        } else if (Objects.nonNull(userDao.get().findByLogin(login))) {
            final User user = userDao.get().findByLogin(login);

            if (BCrypt.checkpw(password, user.getPassword())) {
                final String role = user.getRole();

                request.getSession().setAttribute("user", user);
                request.getSession().setAttribute("role", role);

                request.getRequestDispatcher("/WEB-INF/view/menu/" + role + "-menu.jsp").forward(request, response);
            }
        } else {
            request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
        }
    }

    @Override
    public void destroy() {
    }
}
