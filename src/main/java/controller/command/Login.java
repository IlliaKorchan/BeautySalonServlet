package controller.command;

import javax.servlet.http.HttpServletRequest;

public class Login implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        return "/WEB-INF/view/login.jsp";
    }
}
