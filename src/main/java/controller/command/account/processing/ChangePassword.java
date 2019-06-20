package controller.command.account.processing;

import controller.command.Command;

import javax.servlet.http.HttpServletRequest;

import java.util.Arrays;

import static string.containers.StringContainer.CHANGE_PASSWORD_PAGE;
import static string.containers.StringContainer.CLIENT_ROLE;

/**
 * Class for redirecting to the change password page
 * @author Illia Korchan
 * @version 0.6.5
 */
public class ChangePassword implements Command {
    private String[] hadAccess = {CLIENT_ROLE};

    @Override
    public String execute(HttpServletRequest req) {
        return CHANGE_PASSWORD_PAGE;
    }

    @Override
    public boolean checkRole(String role) {
        return Arrays.asList(hadAccess).contains(role);
    }
}
