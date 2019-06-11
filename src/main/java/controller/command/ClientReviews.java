package controller.command;

import model.entities.User;
import model.services.ReviewsService;

import javax.servlet.http.HttpServletRequest;

public class ClientReviews implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        Integer id = ((User) req.getSession().getAttribute("user")).getId();
        String language = (String) req.getSession().getAttribute("language");
        req.setAttribute("clientReviews", new ReviewsService().getClientReviews(id, language));
        return "/WEB-INF/view/client-reviews.jsp";
    }
}
