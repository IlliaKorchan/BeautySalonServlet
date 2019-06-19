package controller.command.account.processing;

import controller.command.Command;
import model.entities.User;
import model.services.impl.AccountDeleteService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static string.containers.StringContainer.INDEX_PAGE;
import static string.containers.StringContainer.USER_LOGGED;
import static string.containers.StringContainer.USER_LOGGED_ROLE;

/**
 * Class for processing delete account procedure
 * @author Illia Korchan
 * @version 0.6.5
 */
public class DeleteAccount implements Command {
    private static final Logger LOGGER = Logger.getLogger(DeleteAccount.class.getSimpleName());
    /**
     * Method, that fetches current user logged in, sends it to the account delete service
     * and removes logged account and it`s role from session
     * @param req
     * @return main page
     */
    @Override
    public String execute(HttpServletRequest req) {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute(USER_LOGGED);

        new AccountDeleteService().delete(user);

        LOGGER.info("Account with login " + user.getLogin() + " deleted");
        session.removeAttribute(USER_LOGGED);
        session.removeAttribute(USER_LOGGED_ROLE);
        return INDEX_PAGE;
    }
}
