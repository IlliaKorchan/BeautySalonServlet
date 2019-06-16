package controller.command.account.processing;

import controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static string.containers.StringContainer.INDEX_PAGE;
import static string.containers.StringContainer.USER_LOGGED;
import static string.containers.StringContainer.USER_LOGGED_ROLE;

/**
 * Class for removing logged account and it`s role from the session
 * @author Illia Korchan
 * @version 0.6.5
 */
public class Logout implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        final HttpSession session = req.getSession();

        session.removeAttribute(USER_LOGGED);
        session.removeAttribute(USER_LOGGED_ROLE);

        return INDEX_PAGE;
    }
}
