package controller.command;

import model.entities.User;
import model.services.ReviewsService;

import javax.servlet.http.HttpServletRequest;

/**
 * Class for processing client request for displaying his/her reviews
 * and redirecting to the page with results
 * @author Illia Korchan
 * @version 0.6.5
 */
public class ClientReviews implements Command {
    /**
     * Method, that fetches id of logged user and sends it to the client reviews service.
     * Then, sends the results to the jsp
     * @param req
     * @return page with results
     */
    @Override
    public String execute(HttpServletRequest req) {
        User user = ((User) req.getSession().getAttribute("user"));
        String language = (String) req.getSession().getAttribute("language");

        req.setAttribute("clientReviews", new ReviewsService().getClientReviews(user.getId(), language));

        return "/WEB-INF/view/reviews/client-reviews.jsp";
    }
}
