package controller.command.account.processing;

import controller.command.Command;

import javax.servlet.http.HttpServletRequest;

import static string.containers.StringContainer.REGISTRATION_PAGE;

/**
 * Class for redirecting to the registration page
 * @author Illia Korchan
 * @version 0.6.5
 */
public class Registration implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        return REGISTRATION_PAGE;
    }
}
