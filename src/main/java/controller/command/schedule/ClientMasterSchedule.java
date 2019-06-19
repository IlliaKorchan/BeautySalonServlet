package controller.command.schedule;

import controller.command.Command;
import model.dto.UserDto;
import model.services.impl.MasterFinderService;
import model.services.impl.MasterScheduleProcessorService;
import model.services.impl.ProceduresService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import static string.containers.StringContainer.CLIENT_MASTER_SCHEDULE_PAGE;
import static string.containers.StringContainer.LANGUAGE;

/**
 * Class for processing client request to get data about master schedule
 *
 * @author Illia Korchan
 * @version 0.6.5
 */
public class ClientMasterSchedule implements Command {
    /**
     * Method, that sends list of masters to the jsp and fetches master surname, chosen by client, date
     * and sends it to the master schedule service
     *
     * @param req
     * @return master schedule page for client
     */
    @Override
    public String execute(HttpServletRequest req) {
        String language = (String) req.getSession().getAttribute(LANGUAGE);

        String selectedMaster = req.getParameter("masterId");
        String date = req.getParameter("date");

        MasterScheduleProcessorService masterScheduleService = new MasterScheduleProcessorService();
        List<UserDto> masters = new MasterFinderService().findAll(language);

        req.setAttribute("masters", masters);

        if (Objects.nonNull(selectedMaster)) {
            Integer masterId = Integer.valueOf(selectedMaster);
            UserDto master = masters.stream().filter(mstr -> mstr.getUser().getId().equals(masterId))
                                            .findFirst()
                                            .get();
            req.getSession().setAttribute("master", master);

            req.setAttribute("workingDays", masterScheduleService.findDates(masterId));
        }


        if (Objects.nonNull(date)) {
            req.getSession().setAttribute("selectedDate", date);

            req.setAttribute("procedures", new ProceduresService().getAllProcedures(language));
            Integer masterId = ((UserDto) req.getSession().getAttribute("master")).getUser().getId();

            req.setAttribute("freeTimes", masterScheduleService.findFreeTimes(masterId, LocalDate.parse(date)));
        }

        return CLIENT_MASTER_SCHEDULE_PAGE;
    }
}
