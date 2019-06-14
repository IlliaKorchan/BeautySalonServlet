package controller.command;

import javax.servlet.http.HttpServletRequest;

/**
 * Functional interface for redirecting to the pages
 * @author Illia Korchan
 * @version 0.6.5
 */
@FunctionalInterface
public interface Command {
    String execute(HttpServletRequest req);
}
