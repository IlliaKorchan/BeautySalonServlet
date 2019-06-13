package controller.command;

import javax.servlet.http.HttpServletRequest;

public class MakeAppointment implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        return "/WEB-INF/view/make-appointment.jsp";
    }
}
