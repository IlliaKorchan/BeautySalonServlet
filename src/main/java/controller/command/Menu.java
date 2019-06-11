package controller.command;

import javax.servlet.http.HttpServletRequest;

public class Menu implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        return "/WEB-INF/view/menu/" + req.getSession().getAttribute("role") + "-menu.jsp";
    }
}
