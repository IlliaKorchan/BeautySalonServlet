package controller.command;

import model.entities.UserDto;
import model.services.impl.MasterFinderService;
import model.services.impl.ReviewsService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

import static string.containers.StringContainer.ADMIN_REVIEWS_PAGE;

/**
 * Class for processing admin request to find reviews about selected master
 * @author Illia Korchan
 * @version 0.6.5
 */
public class AdminReviews implements Command {
    /**
     * Method, that sends list of masters to the jsp, fetches surname of master, selected by admin,
     * and send it to the reviews service for admin
     * @param req
     * @return page with the reviews of selected master
     */
    @Override
    public String execute(HttpServletRequest req) {
        String language = (String) req.getSession().getAttribute("language");

        List<UserDto> masters = new MasterFinderService().findAll(language);
        req.setAttribute("masters", masters);

        String masterSurname = req.getParameter("masterSurname");

        if (Objects.nonNull(masterSurname)) {
            UserDto master = masters.stream().filter(mstr -> mstr.getName().equals(masterSurname))
                                             .findFirst()
                                             .get();
            req.setAttribute("adminReviews", new ReviewsService().getAdminReviews(master.getUser(), language));
        }

        return ADMIN_REVIEWS_PAGE;
    }
}
