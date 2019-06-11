package controller.command;


import model.entities.User;
import model.services.AccountDeleteService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class DeleteAccount implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        HttpSession session = req.getSession();
        new AccountDeleteService().delete((User) session.getAttribute("user"));

        session.removeAttribute("user");
        session.removeAttribute("role");
        return "/WEB-INF/view/index.jsp";
    }
}
