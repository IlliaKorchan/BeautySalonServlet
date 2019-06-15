package controller.command;

import javax.servlet.http.HttpServletRequest;

public class Menu implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        String role = (String) req.getSession().getAttribute("role");
        return "/WEB-INF/view/menu/" + role + "-menu.jsp";
    }
}
