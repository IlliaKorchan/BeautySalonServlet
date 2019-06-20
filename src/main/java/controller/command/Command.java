package controller.command;

import javax.servlet.http.HttpServletRequest;

/**
 * Interface for redirecting to the pages
 * @author Illia Korchan
 * @version 0.6.5
 */
public interface Command {
    String execute(HttpServletRequest req);

    boolean checkRole(String role);
}
