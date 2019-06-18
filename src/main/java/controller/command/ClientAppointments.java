package controller.command;

import model.entities.User;
import model.services.impl.ClientAppointmentsService;

import javax.servlet.http.HttpServletRequest;

import static string.containers.StringContainer.CLIENT_APPOINTMENTS_PAGE;
import static string.containers.StringContainer.LANGUAGE;
import static string.containers.StringContainer.USER_LOGGED;

/**
 * Class for processing client request for displaying his/her appointments
 * and redirecting to the page with results
 * @author Illia Korchan
 * @version 0.6.5
 */
public class ClientAppointments implements Command {
    /**
     * Method, that fetches id of logged user and sends it to the client appointment service.
     * Then, sends the results to the jsp
     * @param req
     * @return page with results
     */
    @Override
    public String execute(HttpServletRequest req) {
        String language = (String) req.getSession().getAttribute(LANGUAGE);
        Integer id = ((User) req.getSession().getAttribute(USER_LOGGED)).getId();

        req.setAttribute("clientAppointments", new ClientAppointmentsService()
                                                            .getAllClientAppointments(language, id));

        return CLIENT_APPOINTMENTS_PAGE;
    }
}
