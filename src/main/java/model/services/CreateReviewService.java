package model.services;

import model.dao.DaoFactory;
import model.dao.ReviewDao;
import model.entities.Review;

import java.time.LocalDate;

public class CreateReviewService {
    public void sendReview(Integer clientId, Integer masterId, String reviewText) {
        ReviewDao reviewDao = DaoFactory.getInstance().createReviewDao();
        Review review = new Review(LocalDate.now(), clientId, masterId, reviewText);

        reviewDao.create(review);
        reviewDao.close();
    }
}
