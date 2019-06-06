package controller.command;

import javax.servlet.http.HttpServletRequest;

public class Registration implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        return "/WEB-INF/view/registration.jsp";
    }
}
