package controller.command;

import model.entities.User;
import model.exceptions.IncorrectPasswordException;
import model.services.UserUpdateService;

import javax.servlet.http.HttpServletRequest;

import static string.containers.MessageContainer.INCORRECT_PASSWORD_WARNING;

public class ChangePasswordCommit implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        User user = (User) req.getSession().getAttribute("user");

        try {
            new UserUpdateService().updatePassword(user, req.getParameter("current-password"),
                                                         req.getParameter("new-password"));
        } catch (IncorrectPasswordException e) {
            req.setAttribute("incorrect-password-warning", INCORRECT_PASSWORD_WARNING);
            return "/WEB-INF/view/change-password.jsp";
        }
        return "/WEB-INF/view/index.jsp";
    }
}
