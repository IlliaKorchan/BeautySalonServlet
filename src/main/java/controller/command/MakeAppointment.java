package controller.command;

import model.dao.AppointmentDao;
import model.dao.DaoFactory;
import model.dao.ProcedureDao;
import model.entities.Appointment;
import model.entities.Procedure;
import model.entities.User;
import model.entities.UserDto;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalTime;

import static string.containers.QueryContainer.FIND_PROCEDURE_BY_NAME_EN;
import static string.containers.QueryContainer.FIND_PROCEDURE_BY_NAME_UKR;

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
        Integer clientId = ((User) req.getSession().getAttribute("user")).getId();
        String language = (String) req.getSession().getAttribute("language");

        ProcedureDao procedureDao = DaoFactory.getInstance().createProcedureDao();
        AppointmentDao appointmentDao = DaoFactory.getInstance().createAppointmentDao();

        Procedure procedure = procedureDao.findByName(procedureName, language.equals("uk") ? FIND_PROCEDURE_BY_NAME_UKR
                                                                                            : FIND_PROCEDURE_BY_NAME_EN);
        Appointment appointment = new Appointment(clientId, master.getUser().getId(), date, time, procedure.getId());
        appointmentDao.create(appointment);
        return "/WEB-INF/view/make-appointment.jsp";
    }
}
