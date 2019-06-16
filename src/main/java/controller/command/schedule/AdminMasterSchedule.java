package controller.command.schedule;

import controller.command.Command;
import model.dao.AppointmentDao;
import model.dao.DaoFactory;
import model.entities.Appointment;
import model.entities.UserDto;
import model.services.impl.MasterFinderService;
import model.services.impl.MasterScheduleProcessorService;
import model.services.impl.ProceduresService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import static string.containers.QueryContainer.FIND_APPOINTMENTS_BY_MASTER_ID_AND_DATE_EN;
import static string.containers.QueryContainer.FIND_APPOINTMENTS_BY_MASTER_ID_AND_DATE_UKR;
import static string.containers.StringContainer.ADMIN_MASTER_SCHEDULE_PAGE;
import static string.containers.StringContainer.LOCALE_UKR;

/**
 * Class for processing admin request to get data about master schedule
 * @author Illia Korchan
 * @version 0.6.5
 */
public class AdminMasterSchedule implements Command {
    /**
     * Method, that sends list of masters to the jsp, fetches master surname, chosen by admin, date
     * and sends it to the master schedule service
     * @param req
     * @return master schedule page for admin
     */
    @Override
    public String execute(HttpServletRequest req) {
        String language = (String) req.getSession().getAttribute("language");
        String masterSurname = req.getParameter("masterSurname");
        String date = req.getParameter("date");

        MasterScheduleProcessorService masterScheduleService = new MasterScheduleProcessorService();
        List<UserDto> masters = new MasterFinderService().findAll(language);

        req.setAttribute("masters", masters);

        if (Objects.nonNull(masterSurname)) {
            UserDto master = masters.stream().filter(mstr -> mstr.getName().equals(masterSurname))
                    .findFirst()
                    .get();
            req.getSession().setAttribute("master", master);

            req.setAttribute("workingDays", masterScheduleService.findDates(((UserDto) req.getSession()
                    .getAttribute("master")).getUser().getId()));
        }


        if (Objects.nonNull(date)) {
            req.getSession().setAttribute("selectedDate", date);

            Integer masterId = ((UserDto) req.getSession().getAttribute("master")).getUser().getId();

            String query = language.equals(LOCALE_UKR) ? FIND_APPOINTMENTS_BY_MASTER_ID_AND_DATE_UKR
                                                 : FIND_APPOINTMENTS_BY_MASTER_ID_AND_DATE_EN;
            req.setAttribute("appointments", masterScheduleService
                    .findAppointmentsForMasterByDate(masterId, LocalDate.parse(date), query));
        }

        return ADMIN_MASTER_SCHEDULE_PAGE;
    }
}

