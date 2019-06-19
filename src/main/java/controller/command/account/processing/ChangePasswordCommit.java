package controller.command.account.processing;

import controller.command.Command;
import model.entities.User;
import model.exceptions.IncorrectDataInputException;
import model.exceptions.IncorrectPasswordException;
import model.services.impl.UserUpdateService;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.http.HttpServletRequest;

import static string.containers.StringContainer.*;

/**
 * Class for processing password change
 * @author Illia Korchan
 * @version 0.6.5
 */
public class ChangePasswordCommit implements Command {

    /**
     * Method, that receives current password and new password and sends them
     * to the  user update service
     * @param req
     * @return main page
     */
    @Override
    public String execute(HttpServletRequest req) {
        User user = (User) req.getSession().getAttribute(USER_LOGGED);

        try {
            new UserUpdateService().updatePassword(user,
                    req.getParameter("current-password"),
                    req.getParameter("new-password"));

        } catch (IncorrectPasswordException | IncorrectDataInputException e) {
            req.setAttribute("warning", e.getMessage());
            return CHANGE_PASSWORD_PAGE;
        }


        req.getSession().removeAttribute(USER_LOGGED);
        req.getSession().removeAttribute(USER_LOGGED_ROLE);
        return INDEX_PAGE;
    }
}
