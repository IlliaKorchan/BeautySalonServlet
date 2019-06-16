package controller.command.schedule;

import controller.command.Command;
import model.dao.DaoFactory;
import model.entities.UserDto;
import model.services.impl.MasterFinderService;
import model.services.impl.MasterScheduleProcessorService;
import model.services.impl.ProceduresService;

import javax.servlet.http.Cookie;
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
        String masterSurname = req.getParameter("masterSurname");
        String date = req.getParameter("date");

        MasterScheduleProcessorService masterScheduleService = new MasterScheduleProcessorService();
        List<UserDto> masters = new MasterFinderService().findAll(language);

        req.setAttribute("masters", masters);

        if (Objects.nonNull(masterSurname) || Objects.nonNull(req.getSession().getAttribute("master"))) {
            if (masterSurname != null) {
                UserDto master = masters.stream().filter(mstr -> mstr.getName().equals(masterSurname))
                        .findFirst()
                        .get();
                req.getSession().setAttribute("master", master);
            }

            req.setAttribute("workingDays", masterScheduleService.findDates(((UserDto) req.getSession()
                                                                    .getAttribute("master")).getUser().getId()));

            if (Objects.nonNull(date)) {
                req.getSession().setAttribute("selectedDate", date);
                req.setAttribute("procedures", new ProceduresService().getAllProcedures(language));
                req.setAttribute("freeTimes", masterScheduleService.findFreeTimes(((UserDto) req.getSession()
                                .getAttribute("master")).getUser().getId(),
                        LocalDate.parse(date)));
            }
        }

        return "/WEB-INF/view/schedule/client-master-schedule.jsp";
    }
}
