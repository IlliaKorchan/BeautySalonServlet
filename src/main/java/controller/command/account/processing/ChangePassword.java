package controller.command.account.processing;

import controller.command.Command;

import javax.servlet.http.HttpServletRequest;

public class ChangePassword implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        return "/WEB-INF/view/change-password.jsp";
    }
}
