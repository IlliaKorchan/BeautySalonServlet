package controller.command.account.processing;

import controller.command.Command;

import javax.servlet.http.HttpServletRequest;

public class Registration implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        return "/WEB-INF/view/registration.jsp";
    }
}
