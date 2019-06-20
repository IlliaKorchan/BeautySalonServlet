package controller.command;

import model.entities.User;
import model.services.impl.ReviewsService;

import javax.servlet.http.HttpServletRequest;

import java.util.Arrays;

import static string.containers.StringContainer.*;

/**
 * Class for processing client request for displaying his/her reviews
 * and redirecting to the page with results
 * @author Illia Korchan
 * @version 0.6.5
 */
public class ClientReviews implements Command {
    private String[] hadAccess = {CLIENT_ROLE};

    /**
     * Method, that fetches id of logged user and sends it to the client reviews service.
     * Then, sends the results to the jsp
     * @param req
     * @return page with results
     */
    @Override
    public String execute(HttpServletRequest req) {
        User user = ((User) req.getSession().getAttribute(USER_LOGGED));
        String language = (String) req.getSession().getAttribute(LANGUAGE);

        req.setAttribute("clientReviews", new ReviewsService().getClientReviews(user.getId(), language));

        return CLIENT_REVIEWS_PAGE;
    }

    @Override
    public boolean checkRole(String role) {
        return Arrays.asList(hadAccess).contains(role);
    }
}
