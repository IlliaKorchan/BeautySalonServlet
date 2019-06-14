package controller.command.account.processing;

import controller.command.Command;

import javax.servlet.http.HttpServletRequest;

/**
 * Class for redirecting to the authorization page
 * @author Illia Korchan
 * @version 0.6.5
 */
public class Login implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        return "/WEB-INF/view/login.jsp";
    }
}
