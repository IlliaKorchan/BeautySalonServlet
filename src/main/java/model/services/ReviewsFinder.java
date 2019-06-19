package model.services;

import model.entities.UserReview;
import model.entities.User;

import java.util.List;

public interface ReviewsFinder {
    List<UserReview> getClientReviews(Integer id, String language);
    List<UserReview> getAdminReviews(User master, String language);
}
