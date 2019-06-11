package controller.command;

import javax.servlet.http.HttpServletRequest;

public class ChangePassword implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        return "/WEB-INF/view/change-password.jsp";
    }
}
