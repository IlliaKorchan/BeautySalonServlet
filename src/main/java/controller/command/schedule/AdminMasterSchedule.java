package controller.command.schedule;

import controller.command.Command;
import model.services.MasterScheduleService;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

import static string.containers.QueryContainer.FIND_APPOINTMENTS_BY_MASTER_ID_AND_DATE_EN;
import static string.containers.QueryContainer.FIND_APPOINTMENTS_BY_MASTER_ID_AND_DATE_UKR;

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
        MasterScheduleService masterScheduleService = new MasterScheduleService();

        String masterSurname = req.getParameter("masterSurname");

//        Integer id =
//
//        req.setAttribute("workingDays", masterScheduleService.findDates(id,));

        String date = req.getParameter("date");

        if (Objects.nonNull(date)) {
            String query = language.equals("uk") ? FIND_APPOINTMENTS_BY_MASTER_ID_AND_DATE_UKR
                    : FIND_APPOINTMENTS_BY_MASTER_ID_AND_DATE_EN;
//            req.setAttribute("appointments", masterScheduleService
//                    .findAppointmentsForMasterByDate(id, LocalDate.parse(date), query));
        }
        return "/WEB-INF/view/schedule/admin-master-schedule.jsp";
    }
}

