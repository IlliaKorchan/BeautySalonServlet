package controller.command;

import model.entities.User;
import model.services.ReviewsService;

import javax.servlet.http.HttpServletRequest;

public class ClientReviews implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        User user = ((User) req.getSession().getAttribute("user"));
        String language = (String) req.getSession().getAttribute("language");
        req.setAttribute("clientReviews", new ReviewsService().getClientReviews(user, language));
        return "/WEB-INF/view/reviews/client-reviews.jsp";
    }
}
