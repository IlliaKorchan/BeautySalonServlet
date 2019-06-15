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
            System.out.println(BCrypt.checkpw(password, user.getPassword()));
            if (BCrypt.checkpw(password, user.getPassword())) {
                System.out.println("Зашло");
                final String role = user.getRole();

                request.getSession().setAttribute("user", user);
                request.getSession().setAttribute("role", role);

                setUserName(request, (User) request.getSession().getAttribute("user"));
                moveToMenu(request, response, role);
            }
        } else {
            request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
        }
    }

    /**
     * Move user to menu.
     * If access 'admin' move to admin menu.
     * If access 'master' move to user menu.
     * If access 'user' move to user menu.
     */
    private void moveToMenu(final HttpServletRequest request, final HttpServletResponse response,
                            final String role) throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/view/menu/" + role + "-menu.jsp").forward(request, response);
    }

    @Override
    public void destroy() {
    }

    /**
     * Method for setting logged user and his/her role to the session
     * @param request
     * @param user logged in
     */
    private void setUserName(HttpServletRequest request, User user) {
        String language = (String) request.getSession().getAttribute("language");

        request.getSession().setAttribute("name", (language.equals("uk")) ? user.getNameUkr()
                                                                             : user.getNameEn());
        request.getSession().setAttribute("surname", (language.equals("uk")) ? user.getSurnameUkr()
                : user.getSurnameEn());
    }
}
