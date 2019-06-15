package model.services;

import model.entities.ReviewDto;
import model.entities.User;

import java.util.List;

public interface ReviewsFinder {
    List<ReviewDto> getClientReviews(Integer id, String language);
    List<ReviewDto> getAdminReviews(User master, String language);
}
