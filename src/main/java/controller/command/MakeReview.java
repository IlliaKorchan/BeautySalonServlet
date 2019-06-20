package controller.command;

import model.entities.User;
import model.dto.UserDto;
import model.services.impl.CreateReviewService;
import model.services.impl.MasterFinderService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static string.containers.StringContainer.*;

/**
 * Class for processing reviews, sent by client
 * @author Illia Korchan
 * @version 0.6.6
 */
public class MakeReview implements Command {
    private String[] hadAccess = {CLIENT_ROLE};
    private static final Logger LOGGER = Logger.getLogger(MakeAppointment.class.getSimpleName());
    /**
     * Method, that sends list of masters to the jsp and receives master surname and review text from client
     * and sends it to the service for creating review
     * @param req
     * @return page with review sending form or menu page
     */
    @Override
    public String execute(HttpServletRequest req) {
        String language = (String) req.getSession().getAttribute(LANGUAGE);
        User user = (User) req.getSession().getAttribute(USER_LOGGED);

        List<UserDto> masters = new MasterFinderService().findAll(language);

        req.setAttribute("masters", masters);

        String masterSurname = req.getParameter("masterSurname");
        String reviewText = req.getParameter("reviewText");

        if (Objects.nonNull(masterSurname) && Objects.nonNull(reviewText)) {
            UserDto master = masters.stream().filter(mstr -> mstr.getName().equals(masterSurname))
                    .findFirst()
                    .get();

            new CreateReviewService().sendReview(user.getId(), master.getUser().getId(), reviewText);
            LOGGER.info(user.getLogin() + " made a review about master " + master.getUser().getLogin());
            return CLIENT_MENU_PAGE;
        }
        return MAKE_REVIEW_PAGE;
    }

    @Override
    public boolean checkRole(String role) {
        return Arrays.asList(hadAccess).contains(role);
    }
}
