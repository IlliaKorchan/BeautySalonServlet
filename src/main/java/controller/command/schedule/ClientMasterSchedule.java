package controller.command.schedule;

import controller.command.Command;
import model.entities.UserDto;
import model.services.MasterFinder;
import model.services.MasterScheduleService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * Class for processing client request to get data about master schedule
 * @author Illia Korchan
 * @version 0.6.5
 */
public class ClientMasterSchedule implements Command {
    /**
     * Method, that sends list of masters to the jsp and fetches master surname, chosen by client, date
     * and sends it to the master schedule service
     * @param req
     * @return master schedule page for client
     */
    @Override
    public String execute(HttpServletRequest req) {
        String language = (String) req.getSession().getAttribute("language");

        MasterScheduleService masterScheduleService = new MasterScheduleService();
        List<UserDto> masters = new MasterFinder().findAll(language);

        req.setAttribute("masters", masters);

        String masterSurname = req.getParameter("masterSurname");
        String date = req.getParameter("date");

        if (Objects.nonNull(masterSurname)) {
            UserDto master = masters.stream().filter(mstr -> mstr.getName().equals(masterSurname))
                    .findFirst()
                    .get();

            req.setAttribute("workingDays", masterScheduleService.findDates(master.getUser().getId()));
            req.setAttribute("masterSurname", masterSurname);

            if (Objects.nonNull(date)) {
                req.setAttribute("freeTimes", masterScheduleService.findFreeTimes(master.getUser().getId(),
                        LocalDate.parse(date)));
            }
        }

        return "/WEB-INF/view/schedule/client-master-schedule.jsp";
    }
}