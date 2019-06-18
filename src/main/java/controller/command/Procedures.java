package controller.command;

import model.services.impl.ProceduresService;

import javax.servlet.http.HttpServletRequest;

import static string.containers.StringContainer.LANGUAGE;
import static string.containers.StringContainer.USER_LOGGED_ROLE;

/**
 * Class for redirecting to the page with procedure catalogue
 * @author Illia Korchan
 * @version 0.6.5
 */
public class Procedures implements Command {
    /**
     * Method, that receives list of procedures from procedures service and redirects
     * to the procedure page, according to the role of logged user
     * @param req
     * @return page with the list of procedures
     */
    @Override
    public String execute(HttpServletRequest req) {
        String language = (String) req.getSession().getAttribute(LANGUAGE);
        String role = (String) req.getSession().getAttribute(USER_LOGGED_ROLE);

        req.setAttribute("procedures", new ProceduresService().getAllProcedures(language));

        return "/WEB-INF/view/procedures/" + role + "-procedures.jsp";
    }
}
