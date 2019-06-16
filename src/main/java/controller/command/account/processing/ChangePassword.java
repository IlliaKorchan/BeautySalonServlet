package controller.command.account.processing;

import controller.command.Command;

import javax.servlet.http.HttpServletRequest;

import static string.containers.StringContainer.CHANGE_PASSWORD_PAGE;

/**
 * Class for redirecting to the change password page
 * @author Illia Korchan
 * @version 0.6.5
 */
public class ChangePassword implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        return CHANGE_PASSWORD_PAGE;
    }
}
