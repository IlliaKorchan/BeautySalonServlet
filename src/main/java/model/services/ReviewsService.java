package model.services;

import model.dao.DaoFactory;
import model.entities.ReviewDto;

import java.util.List;

import static string.containers.QueryContainer.*;

public class ReviewsService {
    public List<ReviewDto> getClientReviews(Integer id, String language) {
        String query = (language.equals("uk")) ? FIND_REVIEWS_BY_CLIENT_ID_UKR : FIND_REVIEWS_BY_CLIENT_ID_EN;
        return DaoFactory.getInstance().createReviewDtoDao().findAllById(id, query);
    }

    public List<ReviewDto> getAdminReviews(String masterSurname, String language) {
        String findMasterQuery;
        String findReviewsQuery;
        if (language.equals("uk")) {
            findMasterQuery = FIND_MASTER_BY_SURNAME_UKR;
            findReviewsQuery = FIND_REVIEWS_BY_MASTER_ID_UKR;
        } else {
            findMasterQuery = FIND_MASTER_BY_SURNAME_EN;
            findReviewsQuery = FIND_REVIEWS_BY_MASTER_ID_EN;
        }

        Integer id = DaoFactory.getInstance().createUserDao().findBySurname(masterSurname, findMasterQuery).getId();
        return DaoFactory.getInstance().createReviewDtoDao().findAllById(id, findReviewsQuery);
    }
}
