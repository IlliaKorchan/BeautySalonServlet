package controller.command;

import javax.servlet.http.HttpServletRequest;

import static string.containers.StringContainer.USER_LOGGED_ROLE;

public class Menu implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        String role = (String) req.getSession().getAttribute(USER_LOGGED_ROLE);
        return "/WEB-INF/view/menu/" + role + "-menu.jsp";
    }
}
