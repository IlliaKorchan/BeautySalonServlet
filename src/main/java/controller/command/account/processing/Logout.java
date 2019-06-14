package controller.command.account.processing;

import controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Class for removing logged account and it`s role from the session
 * @author Illia Korchan
 * @version 0.6.5
 */
public class Logout implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        final HttpSession session = req.getSession();

        session.removeAttribute("user");
        session.removeAttribute("role");

        return "/WEB-INF/view/index.jsp";
    }
}
