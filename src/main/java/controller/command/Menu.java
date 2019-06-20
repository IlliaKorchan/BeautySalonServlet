package controller.command;

import javax.servlet.http.HttpServletRequest;

import java.util.Arrays;

import static string.containers.StringContainer.*;

public class Menu implements Command {
    private String[] hadAccess = {CLIENT_ROLE, MASTER_ROLE, ADMIN_ROLE};
    @Override
    public String execute(HttpServletRequest req) {
        String role = (String) req.getSession().getAttribute(USER_LOGGED_ROLE);
        return "/WEB-INF/view/menu/" + role + "-menu.jsp";
    }

    @Override
    public boolean checkRole(String role) {
        return Arrays.asList(hadAccess).contains(role);
    }
}
