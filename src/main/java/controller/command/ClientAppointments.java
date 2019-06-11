package controller.command;

import model.entities.User;
import model.services.ClientAppointmentsService;

import javax.servlet.http.HttpServletRequest;

public class ClientAppointments implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        String language = (String) req.getSession().getAttribute("language");
        Integer id = ((User) req.getSession().getAttribute("user")).getId();

        req.setAttribute("clientAppointments", new ClientAppointmentsService()
                                                            .getAllClientAppointments(language, id));

        return "/WEB-INF/view/client-appointments.jsp";
    }
}
