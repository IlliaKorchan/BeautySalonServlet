package model.services;

import model.dao.DaoFactory;
import model.dao.ReviewDtoDao;
import model.entities.ReviewDto;
import model.entities.User;

import java.util.List;

import static string.containers.QueryContainer.*;


public class ReviewsService implements ReviewsFinder {
    @Override
    public List<ReviewDto> getClientReviews(Integer id, String language) {
        ReviewDtoDao reviewDtoDao = DaoFactory.getInstance().createReviewDtoDao();
        String query = (language.equals("uk")) ? FIND_REVIEWS_BY_CLIENT_ID_UKR : FIND_REVIEWS_BY_CLIENT_ID_EN;

        List<ReviewDto> clientReviews = reviewDtoDao.findAllById(id, query);
        reviewDtoDao.close();

        return clientReviews;
    }

    @Override
    public List<ReviewDto> getAdminReviews(User master, String language) {
        ReviewDtoDao reviewDtoDao = DaoFactory.getInstance().createReviewDtoDao();

        List<ReviewDto> adminReviews = reviewDtoDao.findAllById(master.getId(), language.equals("uk")
                                                                                ? FIND_REVIEWS_BY_MASTER_ID_UKR
                                                                                : FIND_REVIEWS_BY_MASTER_ID_EN);

        reviewDtoDao.close();

        return adminReviews;
    }
}
