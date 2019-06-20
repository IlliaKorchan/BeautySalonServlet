package controller.command.account.processing;

import controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.Arrays;

import static string.containers.StringContainer.*;

/**
 * Class for removing logged account and it`s role from the session
 * @author Illia Korchan
 * @version 0.6.5
 */
public class Logout implements Command {
    private String[] hadAccess = {CLIENT_ROLE, MASTER_ROLE, ADMIN_ROLE};
    @Override
    public String execute(HttpServletRequest req) {
        final HttpSession session = req.getSession();

        session.removeAttribute(USER_LOGGED);
        session.removeAttribute(USER_LOGGED_ROLE);

        return INDEX_PAGE;
    }

    @Override
    public boolean checkRole(String role) {
        return Arrays.asList(hadAccess).contains(role);
    }
}
