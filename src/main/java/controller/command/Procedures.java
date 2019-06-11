package controller.command;

import model.services.ProceduresService;

import javax.servlet.http.HttpServletRequest;

public class Procedures implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        String language = (String) req.getSession().getAttribute("language");
        String role = (String) req.getSession().getAttribute("role");
        req.setAttribute("procedures", new ProceduresService().getAllProcedures(language));
        return "/WEB-INF/view/procedures/" + role + "-procedures.jsp";
    }
}
