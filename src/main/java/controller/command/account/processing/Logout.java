package controller.command.account.processing;

import controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Logout implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        final HttpSession session = req.getSession();

        session.removeAttribute("user");
        session.removeAttribute("role");

        return "/WEB-INF/view/index.jsp";
    }
}
