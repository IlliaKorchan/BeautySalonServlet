package controller.command.schedule;

import controller.command.Command;
import model.entities.User;
import model.services.MasterScheduleProcessor;
import model.services.impl.MasterScheduleProcessorService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

import static string.containers.QueryContainer.FIND_APPOINTMENTS_BY_MASTER_ID_AND_DATE;
import static string.containers.StringContainer.*;

/**
 * Class for processing request by master to get data about his/her schedule
 * @author Illia Korchan
 * @version 0.6.5
 */
public class MasterSchedule implements Command {
    private String[] hadAccess = {MASTER_ROLE};
    /**
     * Method, that fetches master account from session, date, chosen by master
     * and sends it to the master schedule service
     * @param req
     * @return master schedule page for master
     */
    @Override
    public String execute(HttpServletRequest req) {
        Integer id = ((User) req.getSession().getAttribute(USER_LOGGED)).getId();
        String language = (String) req.getSession().getAttribute(LANGUAGE);
        ResourceBundle queriesBundle = ResourceBundle.getBundle("queries", new Locale(language));

        MasterScheduleProcessor masterScheduleService = new MasterScheduleProcessorService();

        req.setAttribute("workingDays", masterScheduleService.findDates(id));

        String date = req.getParameter("date");

        if (Objects.nonNull(date)) {

            String query = queriesBundle.getString(FIND_APPOINTMENTS_BY_MASTER_ID_AND_DATE);
            req.setAttribute("appointments", masterScheduleService
                                                    .findAppointmentsForMasterByDate(id, LocalDate.parse(date), query));
        }
        return MASTER_SCHEDULE_PAGE;
    }

    @Override
    public boolean checkRole(String role) {
        return Arrays.asList(hadAccess).contains(role);
    }
}
