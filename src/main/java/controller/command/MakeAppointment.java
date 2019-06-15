package controller.command;

import model.dao.AppointmentDao;
import model.dao.DaoFactory;
import model.dao.ProcedureDao;
import model.entities.Appointment;
import model.entities.UserDto;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Class for redirecting to the page for making an appointment by client
 * @author Illia Korchan
 * @version 0.6.5
 */
public class MakeAppointment implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        UserDto master = (UserDto) req.getSession().getAttribute("master");
        LocalDate date = LocalDate.parse((String) req.getSession().getAttribute("selectedDate"));
        LocalTime time = LocalTime.parse(req.getParameter("time"));
        String procedureName = req.getParameter("selectedProcedure");

        String language = req.getParameter("language");

        ProcedureDao procedureDao = DaoFactory.getInstance().createProcedureDao();
        AppointmentDao appointmentDao = DaoFactory.getInstance().createAppointmentDao();

        procedureDao.findByName(procedureName, language.equals("uk") ? )
//        Appointment appointment = new Appointment()
        return "/WEB-INF/view/make-appointment.jsp";
    }
}
