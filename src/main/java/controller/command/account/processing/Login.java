package controller.command.account.processing;

import controller.command.Command;

import javax.servlet.http.HttpServletRequest;

public class Login implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        return "/WEB-INF/view/login.jsp";
    }
}
