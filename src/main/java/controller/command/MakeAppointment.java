package controller.command;

import model.dao.AppointmentDao;
import model.dao.DaoFactory;
import model.dao.ProcedureDao;
import model.entities.Appointment;
import model.entities.Procedure;
import model.entities.User;
import model.dto.UserDto;
import model.services.impl.EmailInvitationService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Locale;
import java.util.ResourceBundle;

import static string.containers.QueryContainer.FIND_PROCEDURE_BY_NAME;
import static string.containers.StringContainer.*;

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
        User client = (User) req.getSession().getAttribute(USER_LOGGED);

        String language = (String) req.getSession().getAttribute(LANGUAGE);
        ResourceBundle queriesBundle = ResourceBundle.getBundle(QUERIES_BUNDLE, new Locale(language));

        ProcedureDao procedureDao = DaoFactory.getInstance().createProcedureDao();
        AppointmentDao appointmentDao = DaoFactory.getInstance().createAppointmentDao();

        String query = queriesBundle.getString(FIND_PROCEDURE_BY_NAME);

        Procedure procedure = procedureDao.findByName(procedureName, query);
        Appointment appointment = new Appointment(client.getId(), master.getUser().getId(),
                                                    date, time, procedure.getId());
        appointmentDao.create(appointment);

        new EmailInvitationService().sendInvitation(client.getEmail());
        return MAKE_APPOINTMENT_PAGE;
    }
}
