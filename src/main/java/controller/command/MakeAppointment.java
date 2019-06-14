package controller.command;

import javax.servlet.http.HttpServletRequest;

/**
 * Class for redirecting to the page for making an appointment by client
 * @author Illia Korchan
 * @version 0.6.5
 */
public class MakeAppointment implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        return "/WEB-INF/view/make-appointment.jsp";
    }
}
