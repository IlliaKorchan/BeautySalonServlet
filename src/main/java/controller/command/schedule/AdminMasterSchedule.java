package controller.command.schedule;

import controller.command.Command;
import model.dto.UserDto;
import model.services.impl.MasterFinderService;
import model.services.impl.MasterScheduleProcessorService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

import static string.containers.QueryContainer.FIND_APPOINTMENTS_BY_MASTER_ID_AND_DATE;
import static string.containers.StringContainer.*;

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
        String language = (String) req.getSession().getAttribute(LANGUAGE);
        ResourceBundle queriesBundle = ResourceBundle.getBundle("queries", new Locale(language));

        String masterSurname = req.getParameter("masterSurname");
        String date = req.getParameter("date");

        MasterScheduleProcessorService masterScheduleService = new MasterScheduleProcessorService();
        List<UserDto> masters = new MasterFinderService().findAll(language);

        req.setAttribute("masters", masters);

        if (Objects.nonNull(masterSurname)) {
            Integer masterId = Integer.valueOf(masterSurname);

            req.getSession().setAttribute("master", masters.stream()
                                                    .filter(m -> m.getUser().getId().equals(masterId))
                                                    .findFirst()
                                                    .get());

            req.setAttribute("workingDays", masterScheduleService.findDates(masterId));
        }


        if (Objects.nonNull(date)) {
            req.getSession().setAttribute("selectedDate", date);

            Integer masterId = ((UserDto) req.getSession().getAttribute("master")).getUser().getId();

            String query = queriesBundle.getString(FIND_APPOINTMENTS_BY_MASTER_ID_AND_DATE);

            req.setAttribute("appointments", masterScheduleService
                    .findAppointmentsForMasterByDate(masterId, LocalDate.parse(date), query));
        }

        return ADMIN_MASTER_SCHEDULE_PAGE;
    }
}

