package model.services.impl;

import model.dao.DaoFactory;
import model.dao.ReviewDtoDao;
import model.entities.ReviewDto;
import model.entities.User;
import model.services.ReviewsFinder;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import static string.containers.QueryContainer.*;
import static string.containers.StringContainer.FIND_REVIEWS_BY_CLIENT_ID;
import static string.containers.StringContainer.FIND_REVIEWS_BY_MASTER_ID;
import static string.containers.StringContainer.LOCALE_UKR;


public class ReviewsService implements ReviewsFinder {
    @Override
    public List<ReviewDto> getClientReviews(Integer id, String language) {
        ResourceBundle queriesBundle = ResourceBundle.getBundle("queries", new Locale(language));
        ReviewDtoDao reviewDtoDao = DaoFactory.getInstance().createReviewDtoDao();
        String query = queriesBundle.getString(FIND_REVIEWS_BY_CLIENT_ID);

        List<ReviewDto> clientReviews = reviewDtoDao.findAllById(id, query);
        reviewDtoDao.close();

        return clientReviews;
    }

    @Override
    public List<ReviewDto> getAdminReviews(User master, String language) {
        ResourceBundle queriesBundle = ResourceBundle.getBundle("queries", new Locale(language));
        ReviewDtoDao reviewDtoDao = DaoFactory.getInstance().createReviewDtoDao();

        String query = queriesBundle.getString(FIND_REVIEWS_BY_MASTER_ID);

        List<ReviewDto> adminReviews = reviewDtoDao.findAllById(master.getId(), query);

        reviewDtoDao.close();

        return adminReviews;
    }
}
