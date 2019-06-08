package controller.filter;

import model.dao.UserDao;
import model.entities.User;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicReference;
import java.util.Objects;

public class AuthenticationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig){}

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

            final String role = (String) session.getAttribute("role");
            moveToMenu(request, response, role);

        } else if (Objects.nonNull(userDao.get().findByLoginAndPassword(login, password))) {

            final User user = userDao.get().findByLoginAndPassword(login, password);
            final String role = user.getRole();

            request.getSession().setAttribute("user", user);
            request.getSession().setAttribute("role", role);

            moveToMenu(request, response, role);

        } else {
//            request.setAttribute("message", (Objects.nonNull(login)) && Objects.nonNull(password) ?
//                                                            "Користувач з таким іменем/паролем не знайдений!" :
//                                                            "Для перегляду цієї сторінки необхідно авторизуватися!");
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
        setUserName(request,(User) request.getSession().getAttribute("user"));
        request.getRequestDispatcher("/WEB-INF/view/menu/" + role + "-menu.jsp").forward(request, response);
    }

    @Override
    public void destroy() {}

    private void setUserName(HttpServletRequest request, User user) {
        Locale locale = request.getLocale();
        System.out.println(request.getSession().getAttribute("language"));
        request.getSession().setAttribute("name", (locale.getLanguage().equals("uk")) ? user.getNameUkr()
                                                                                         : user.getNameEn());
        request.getSession().setAttribute("surname", (locale.getLanguage().equals("uk")) ? user.getSurnameUkr()
                                                                                            : user.getSurnameEn());
    }
}
