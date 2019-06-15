package controller.command.account.processing;

import controller.command.Command;
import model.entities.User;
import model.services.impl.AccountDeleteService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Class for processing delete account procedure
 * @author Illia Korchan
 * @version 0.6.5
 */
public class DeleteAccount implements Command {
    /**
     * Method, that fetches current user logged in, sends it to the account delete service
     * and removes logged account and it`s role from session
     * @param req
     * @return main page
     */
    @Override
    public String execute(HttpServletRequest req) {
        HttpSession session = req.getSession();

        new AccountDeleteService().delete((User) session.getAttribute("user"));

        session.removeAttribute("user");
        session.removeAttribute("role");
        return "/WEB-INF/view/index.jsp";
    }
}
