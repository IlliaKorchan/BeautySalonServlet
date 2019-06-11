package model.services;

import model.dao.DaoFactory;
import model.entities.ClientReviewDto;

import java.util.List;

import static string.containers.QueryContainer.FIND_REVIEWS_BY_CLIENT_ID_EN;
import static string.containers.QueryContainer.FIND_REVIEWS_BY_CLIENT_ID_UKR;

public class ReviewsService {
    public List<ClientReviewDto> getClientReviews(Integer id, String language) {
        String query = (language.equals("uk")) ? FIND_REVIEWS_BY_CLIENT_ID_UKR : FIND_REVIEWS_BY_CLIENT_ID_EN;
        return DaoFactory.getInstance().createClientReviewDao().findReviewsByClientId(id, query);
    }
}
