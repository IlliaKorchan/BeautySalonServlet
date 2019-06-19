package model.services.impl;

import model.dao.DaoFactory;
import model.dao.UserReviewDao;
import model.entities.UserReview;
import model.entities.User;
import model.services.ReviewsFinder;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import static string.containers.QueryContainer.FIND_REVIEWS_BY_CLIENT_ID;
import static string.containers.QueryContainer.FIND_REVIEWS_BY_MASTER_ID;

public class ReviewsService implements ReviewsFinder {
    @Override
    public List<UserReview> getClientReviews(Integer id, String language) {
        ResourceBundle queriesBundle = ResourceBundle.getBundle("queries", new Locale(language));
        UserReviewDao userReviewDao = DaoFactory.getInstance().createReviewDtoDao();
        String query = queriesBundle.getString(FIND_REVIEWS_BY_CLIENT_ID);

        List<UserReview> clientReviews = userReviewDao.findAllById(id, query);
        userReviewDao.close();

        return clientReviews;
    }

    @Override
    public List<UserReview> getAdminReviews(User master, String language) {
        ResourceBundle queriesBundle = ResourceBundle.getBundle("queries", new Locale(language));
        UserReviewDao userReviewDao = DaoFactory.getInstance().createReviewDtoDao();

        String query = queriesBundle.getString(FIND_REVIEWS_BY_MASTER_ID);

        List<UserReview> adminReviews = userReviewDao.findAllById(master.getId(), query);

        userReviewDao.close();

        return adminReviews;
    }
}
