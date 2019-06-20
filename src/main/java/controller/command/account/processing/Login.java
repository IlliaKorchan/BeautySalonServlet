package controller.command.account.processing;

import controller.command.Command;

import javax.servlet.http.HttpServletRequest;

import java.util.Objects;

import static string.containers.StringContainer.LOGIN_PAGE;

/**
 * Class for redirecting to the authorization page
 * @author Illia Korchan
 * @version 0.6.5
 */
public class Login implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        return LOGIN_PAGE;
    }

    @Override
    public boolean checkRole(String role) {
        return true;
    }
}
