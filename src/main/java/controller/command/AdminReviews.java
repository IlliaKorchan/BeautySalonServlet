package controller.command;

import model.entities.UserDto;
import model.services.MasterFinder;
import model.services.ReviewsService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

public class AdminReviews implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        String language = (String) req.getSession().getAttribute("language");

        List<UserDto> masters = new MasterFinder().findAll(language);

        req.setAttribute("masters", masters);

        String masterSurname = req.getParameter("masterSurname");

        if (Objects.nonNull(masterSurname)) {
            UserDto master = masters.stream().filter(mstr -> mstr.getName().equals(masterSurname))
                                             .findFirst()
                                             .get();
            req.setAttribute("adminReviews", new ReviewsService().getAdminReviews(master.getUser(), language));
        }

        return "/WEB-INF/view/reviews/admin-reviews.jsp";
    }
}
