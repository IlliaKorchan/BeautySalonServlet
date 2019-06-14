package controller.command.schedule;

import controller.command.Command;
import model.entities.User;
import model.services.MasterScheduleService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.Objects;

import static string.containers.QueryContainer.FIND_APPOINTMENTS_BY_MASTER_ID_AND_DATE_EN;
import static string.containers.QueryContainer.FIND_APPOINTMENTS_BY_MASTER_ID_AND_DATE_UKR;

/**
 * Class for processing request by master to get data about his/her schedule
 * @author Illia Korchan
 * @version 0.6.5
 */
public class MasterSchedule implements Command {
    /**
     * Method, that fetches master account from session, date, chosen by master
     * and sends it to the master schedule service
     * @param req
     * @return master schedule page for master
     */
    @Override
    public String execute(HttpServletRequest req) {
        Integer id = ((User) req.getSession().getAttribute("user")).getId();
        String language = (String) req.getSession().getAttribute("language");
        MasterScheduleService masterScheduleService = new MasterScheduleService();

        req.setAttribute("workingDays", masterScheduleService.findDates(id));

        String date = req.getParameter("date");

        if (Objects.nonNull(date)) {
            String query = language.equals("uk") ? FIND_APPOINTMENTS_BY_MASTER_ID_AND_DATE_UKR
                                                 : FIND_APPOINTMENTS_BY_MASTER_ID_AND_DATE_EN;
            req.setAttribute("appointments", masterScheduleService
                                                    .findAppointmentsForMasterByDate(id, LocalDate.parse(date), query));
        }
        return "/WEB-INF/view/schedule/master-schedule.jsp";
    }
}