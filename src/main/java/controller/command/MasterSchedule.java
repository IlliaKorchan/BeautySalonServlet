package controller.command;

import model.entities.User;
import model.services.MasterScheduleService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

public class MasterSchedule implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        //TODO rewrite method
        MasterScheduleService masterScheduleService = new MasterScheduleService();
        LocalDate date = (LocalDate) req.getAttribute("date");
        String language = (String) req.getSession().getAttribute("language");

        if (req.getSession().getAttribute("role").equals("admin")) {
           String masterSurname = req.getParameter("masterSurname");
//           req.setAttribute("masterSchedule", masterScheduleService
//                                                    .getMasterScheduleForAdmin(masterSurname, date, language));
           return "/WEB-INF/view/schedule/admin-master-schedule.jsp";
        } else {
            User master = (User) req.getSession().getAttribute("user");
//            req.setAttribute("masterSchedule", masterScheduleService
//                                                    .getMasterScheduleForMaster(master, date, language));
            return "/WEB-INF/view/schedule/master-schedule.jsp";
        }
    }
}
