package controller.listeners;

import model.dao.DaoFactory;
import model.dao.UserDao;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Class, that implements ServletContextListener interface and set UserDao implementation to the ServletContext
 * to use it in authorization and registration filters
 * @author Illia Korchan
 * @version 0.6.6
 */
@WebListener
public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        AtomicReference<UserDao> userDao = new AtomicReference<>(DaoFactory.getInstance().createUserDao());

        final ServletContext servletContext = servletContextEvent.getServletContext();

        servletContext.setAttribute("userDao", userDao);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {}
}
